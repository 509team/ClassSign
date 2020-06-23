package com.fzn.classsign.asynctask.sms;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class SendRegisterCode<T> extends CustomAsyncTask<T> {
    public SendRegisterCode(Map headers, Map body, Map params, String url){
        super(headers,body,params,url);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);

        Boolean data = temp.getData();

        super.onPostExecute(s);
    }
}
