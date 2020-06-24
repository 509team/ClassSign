package com.fzn.classsign.asynctask.common;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.Class;

import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class ClassGet<T> extends CustomAsyncTask<T> {
    public ClassGet(Map headers, Map body, Map params) {
        super(headers, body, params, RequestConstant.URL.CLASS_GET);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, String>> temp = (ResponseResultJson<Map<String, String>>) getResponse(s);

        Map<String, String> data = temp.getData();
        Class cclass = new Class();
        cclass.setCid(Integer.parseInt(data.get("cid")));
        cclass.setcNum(data.get("cNum"));
        cclass.setName(data.get("name"));
        cclass.setUid(Integer.parseInt(data.get("uid")));
        cclass.settName(data.get("tName"));
        cclass.setJoinCode(data.get("joinCode"));
        cclass.setTotal(Integer.parseInt("total"));

        super.onPostExecute(s);
    }
}
