package com.fzn.classsign.asynctask.user;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.SetPasswordActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

public class IsRegisterCodeRight<T> extends CustomAsyncTask<T> {
    private Context context;
    private String phone;
    private String code;

    public IsRegisterCodeRight(Map headers, Map body, Map params, Context context, String phone, String code) {
        super(headers, body, params, RequestConstant.URL.IS_Register_Code_Right);
        this.context = context;
        this.phone = phone;
        this.code = code;
    }

    @Override
    protected void onPostExecute(String s) {

        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);
        Boolean data = (Boolean) temp.getData();
        int statuCode=temp.getCode();
        if (statuCode==200) {
            Intent intent = new Intent(context, SetPasswordActivity.class);
            intent.putExtra("PHONE",phone);
            intent.putExtra("CODE",code);
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"验证码错误，请检查验证码",Toast.LENGTH_SHORT).show();
        }
        super.onPostExecute(s);
    }
}
