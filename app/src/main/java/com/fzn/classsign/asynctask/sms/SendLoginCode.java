package com.fzn.classsign.asynctask.sms;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class SendLoginCode<T> extends CustomAsyncTask<T> {
    public SendLoginCode(Map headers, Map body, Map params){
        super(headers,body,params, RequestConstant.URL.SEND_LOGIN_CODE);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);

        Boolean data = temp.getData();

        super.onPostExecute(s);
    }
}
