package com.fzn.classsign.asynctask.teacher;

import android.content.Context;
import android.content.Intent;

import com.fzn.classsign.activitys.ReleaseSignInActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class SignCreate<T> extends CustomAsyncTask<T> {
    private Context context;
    private String cid;
    private String total;
    private String seconds;

    public SignCreate(Map headers, Map body, Map params, Context context, String cid, String total, String seconds) {
        super(headers, body, params, RequestConstant.URL.TEACHER_SIGN_CREATE);
        this.context = context;
        this.cid = cid;
        this.total = total;
        this.seconds = seconds;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<String> temp = (ResponseResultJson<String>) getResponse(s);
        int code = temp.getCode();
        if (code == 200) {
            String date = temp.getData();
            Intent intent = new Intent(context, ReleaseSignInActivity.class);
            intent.putExtra("CID", cid);
            intent.putExtra("TOTAL", total);
            intent.putExtra("TIME", seconds);
            intent.putExtra("CODE", date);
            context.startActivity(intent);
        }

        super.onPostExecute(s);
    }
}
