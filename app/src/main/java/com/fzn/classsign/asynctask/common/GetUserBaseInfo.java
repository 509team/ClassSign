package com.fzn.classsign.asynctask.common;

import android.widget.TextView;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class GetUserBaseInfo<T> extends CustomAsyncTask<T> {
    private TextView name;
    private TextView sex;
    private TextView phone;
    private TextView number;
    public GetUserBaseInfo(Map headers, Map body, Map params,TextView name,TextView sex,TextView phone,TextView number){
        super(headers,body,params, RequestConstant.URL.GET_USER_BASEINFO);
        this.name=name;
        this.sex=sex;
        this.phone=phone;
        this.number=number;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, String>> temp = (ResponseResultJson<Map<String, String>>) getResponse(s);

        Map<String, String> data = temp.getData();
        name.setHint(data.get("name"));
        if(data.get("sex").equals("male")){
            sex.setHint("男");
        }else{
            sex.setHint("女");
        }
        phone.setHint(data.get("phone"));
        number.setHint(data.get("unum"));

        super.onPostExecute(s);
    }
}
