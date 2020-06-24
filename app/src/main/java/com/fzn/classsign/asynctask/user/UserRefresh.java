package com.fzn.classsign.asynctask.user;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.Token;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class UserRefresh<T> extends CustomAsyncTask<T> {
    public UserRefresh(Map headers, Map body, Map params){
        super(headers,body,params, RequestConstant.URL.REFRESH);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, Object>> temp = (ResponseResultJson<Map<String, Object>>) getResponse(s);
        int code = temp.getCode();
        if(code == 200){
            Map<String,Object> map = temp.getData();
            Token.token = map.get("access_token").toString();
            Token.refreshToken = (String) map.get("refresh_token").toString();
        }

        super.onPostExecute(s);
    }
}
