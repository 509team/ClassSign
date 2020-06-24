package com.fzn.classsign.asynctask.teacher;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class SignCreate<T> extends CustomAsyncTask<T> {
    public SignCreate(Map headers, Map body, Map params){
        super(headers,body,params, RequestConstant.URL.TEACHER_SIGN_CREATE);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<String> temp = (ResponseResultJson<String>) getResponse(s);

        String data=temp.getData();

        super.onPostExecute(s);
    }
}
