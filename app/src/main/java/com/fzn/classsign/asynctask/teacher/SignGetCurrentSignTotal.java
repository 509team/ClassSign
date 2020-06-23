package com.fzn.classsign.asynctask.teacher;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class SignGetCurrentSignTotal<T> extends CustomAsyncTask<T> {
    public SignGetCurrentSignTotal(Map headers, Map body, Map params, String url){
        super(headers,body,params,url);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, String>> temp = (ResponseResultJson<Map<String, String>>) getResponse(s);

        Map<String, String> data=temp.getData();

        super.onPostExecute(s);
    }
}
