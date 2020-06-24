package com.fzn.classsign.asynctask.common;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.List;
import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class ClassListStudent<T> extends CustomAsyncTask<T> {
    public ClassListStudent(Map headers, Map body, Map params){
        super(headers,body,params, RequestConstant.URL.CLASS_LISTSTU);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, String>>> temp = (ResponseResultJson<List<Map<String, String>>>) getResponse(s);

        List<Map<String, String>> data=temp.getData();

        super.onPostExecute(s);
    }
}
