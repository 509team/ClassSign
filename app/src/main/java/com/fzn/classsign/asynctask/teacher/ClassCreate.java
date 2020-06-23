package com.fzn.classsign.asynctask.teacher;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class ClassCreate<T> extends CustomAsyncTask<T> {
    public ClassCreate(Map headers, Map body, Map params, String url){
        super(headers,body,params,url);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<String> temp = (ResponseResultJson<String>) getResponse(s);

        String data=temp.getData();

        super.onPostExecute(s);
    }
}
