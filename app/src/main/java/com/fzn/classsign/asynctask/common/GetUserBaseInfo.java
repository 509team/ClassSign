package com.fzn.classsign.asynctask.common;

import android.widget.TextView;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.User;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class GetUserBaseInfo<T> extends CustomAsyncTask<T> {
    private TextView name;
    public GetUserBaseInfo(Map headers, Map body, Map params,TextView name,TextView sex,TextView phone,TextView number){
        super(headers,body,params, RequestConstant.URL.GET_USER_BASEINFO);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, String>> temp = (ResponseResultJson<Map<String, String>>) getResponse(s);


        super.onPostExecute(s);
    }
}
