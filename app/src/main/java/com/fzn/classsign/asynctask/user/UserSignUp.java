package com.fzn.classsign.asynctask.user;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.LoginSelectionActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 *
 *
 * @param <T>
 */
public class UserSignUp<T> extends CustomAsyncTask<T> {
    private Context context;
    public UserSignUp(Map headers, Map body, Map params,Context context) {
        super(headers, body, params, RequestConstant.URL.SIGN_UP);
        this.context=context;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);

        int code=temp.getCode();
        Intent intent=new Intent(context, LoginSelectionActivity.class);
        if(code==200){
            Toast.makeText(context,"注册成功!",Toast.LENGTH_SHORT).show();
        }else{
            if(temp.getStatusCode()==1){
                Toast.makeText(context,"验证码错误!",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"用户创建失败!",Toast.LENGTH_SHORT).show();
            }

        }
        context.startActivity(intent);
        super.onPostExecute(s);
    }
}
