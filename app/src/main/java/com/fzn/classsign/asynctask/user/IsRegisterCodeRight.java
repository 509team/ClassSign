package com.fzn.classsign.asynctask.user;



import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

public class IsRegisterCodeRight<T>  extends CustomAsyncTask<T> {
    public IsRegisterCodeRight(Map headers, Map body, Map params){
        super(headers,body,params, RequestConstant.URL.IS_Register_Code_Right);

    }

    @Override
    protected void onPostExecute(String s) {

        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);
        Boolean data=(Boolean) temp.getData();

        super.onPostExecute(s);
    }
}
