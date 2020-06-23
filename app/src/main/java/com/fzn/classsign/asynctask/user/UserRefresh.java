package com.fzn.classsign.asynctask.user;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;

import java.util.List;
import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class UserRefresh<T> extends CustomAsyncTask<T> {
    public UserRefresh(Map headers, Map body, Map params,String url){
        super(headers,body,params,url);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, String>> temp = (ResponseResultJson<Map<String, String>>) getResponse(s);

        Map<String,String> data=temp.getData();

        super.onPostExecute(s);
    }
}
