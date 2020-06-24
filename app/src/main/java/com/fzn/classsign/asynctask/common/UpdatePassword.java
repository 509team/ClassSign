package com.fzn.classsign.asynctask.common;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.MyInfoStudentActivity;
import com.fzn.classsign.activitys.MyInfoTeacherActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class UpdatePassword<T> extends CustomAsyncTask<T> {
    private Context context;
    private int type;

    public UpdatePassword(Map headers, Map body, Map params, Context context, int type) {
        super(headers, body, params, RequestConstant.URL.UPDATE_PASSWORD);
        this.context = context;
        this.type = type;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);

        Boolean data = (Boolean) temp.getData();
        if (data) {
            Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
            if (type == 1) {
                Intent intent2 = new Intent(context, MyInfoTeacherActivity.class);
                context.startActivity(intent2);
            } else if (type == 2) {
                Intent intent2 = new Intent(context, MyInfoStudentActivity.class);
                context.startActivity(intent2);
            }
        } else {
            String msg = temp.getMsg();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        super.onPostExecute(s);
    }
}

