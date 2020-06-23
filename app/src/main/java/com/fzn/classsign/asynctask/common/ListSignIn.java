package com.fzn.classsign.asynctask.common;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.entity.SignIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class ListSignIn<T> extends CustomAsyncTask<T> {
    public ListSignIn(Map headers, Map body, Map params, String url) {
        super(headers, body, params, url);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, String>>> temp = (ResponseResultJson<List<Map<String, String>>>) getResponse(s);

        List<Map<String, String>> data = temp.getData();
        List<SignIn> list = new ArrayList<>();
        for (Map<String, String> d : data) {
            SignIn signIn = new SignIn();
            signIn.setSid(Integer.parseInt(d.get("sid")));
            signIn.setSsid(Integer.parseInt(d.get("ssid")));
            signIn.setUid(Integer.parseInt(d.get("uid")));
            signIn.setuNum(d.get("uNum"));
            signIn.setName(d.get("name"));
            signIn.setStatus(d.get("status"));
            list.add(signIn);
        }


        super.onPostExecute(s);
    }
}
