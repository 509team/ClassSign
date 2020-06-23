package com.fzn.classsign.asynctask;

import java.util.List;
import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class UserRefresh<T> extends CustomAsyncTask<T>{
    public UserRefresh(Map headers, Map body, Map params,String url){
        super(headers,body,params,url);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, String>>> temp = (ResponseResultJson<List<Map<String, String>>>) getResponse(s);

        super.onPostExecute(s);
    }
}
