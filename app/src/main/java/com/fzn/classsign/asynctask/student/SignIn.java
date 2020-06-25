package com.fzn.classsign.asynctask.student;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.SignInResultActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class SignIn<T> extends CustomAsyncTask<T> {
    private Context context;

    public SignIn(Map headers, Map body, Map params, Context context) {
        super(headers, body, params, RequestConstant.URL.STU_SIGN_SIGNIN);
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);
        int code = temp.getCode();
        if (code == 200) {
            int statusCode = temp.getStatusCode();
            if (statusCode == 201) {
                Toast.makeText(context, "签到成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SignInResultActivity.class);
                intent.putExtra("RESULT", "SUCCESS");
                context.startActivity(intent);
            } else if (statusCode == 202) {
                Toast.makeText(context, "签到异常", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SignInResultActivity.class);
                intent.putExtra("RESULT", "ABNORMAL");
                context.startActivity(intent);
            }
        } else if (code == 203) {
            String text = temp.getAddtionalInfo();
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "签到失败！", Toast.LENGTH_SHORT).show();
        }

        super.onPostExecute(s);
    }
}
