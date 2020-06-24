package com.fzn.classsign.asynctask.student;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class ClassAdd<T> extends CustomAsyncTask<T> {
    public ClassAdd(Map headers, Map body, Map params){
        super(headers,body,params, RequestConstant.URL.STU_CLASS_ADD);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);

        Boolean data = temp.getData();

        super.onPostExecute(s);
    }
}
