package com.fzn.classsign.asynctask.common;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.entity.Class;
import com.fzn.classsign.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class GetUserBaseInfo<T> extends CustomAsyncTask<T> {
    public GetUserBaseInfo(Map headers, Map body, Map params, String url){
        super(headers,body,params,url);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, String>> temp = (ResponseResultJson<Map<String, String>>) getResponse(s);

        Map<String, String> data = temp.getData();
        User user=new User();
        user.setUid(Integer.parseInt(data.get("uid")));
        user.setPhone(data.get("phone"));
        user.setuNum(data.get("uNum"));
        user.setName(data.get("name"));
        user.setSex(data.get("sex"));

        super.onPostExecute(s);
    }
}
