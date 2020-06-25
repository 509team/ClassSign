package com.fzn.classsign.asynctask.teacher;

import android.content.Context;
import android.widget.Toast;

import com.fzn.classsign.adapter.SignInStatusListAdapter;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class SignUpdate<T> extends CustomAsyncTask<T> {
    private Context context;
    private SignInStatusListAdapter signAdapter;

    private Integer sid;
    private String status;

    public SignUpdate(Map headers, Map body, Map params, Context context, SignInStatusListAdapter signAdapter, Integer sid, String status) {
        super(headers, body, params, RequestConstant.URL.TEACHER_SIGN_UPDATE);
        this.context=context;
        this.signAdapter = signAdapter;
        this.sid = sid;
        this.status = status;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);
        if (temp.getCode() == 200) {
            Toast.makeText(context, "修改签到记录成功", Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(context, "修改签到记录失败", Toast.LENGTH_SHORT);
        }
        signAdapter.updateData(sid, status);
        super.onPostExecute(s);
    }
}
