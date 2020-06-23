package com.fzn.classsign.asynctask.user;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.ClassHomePageStudentActivity;
import com.fzn.classsign.activitys.ClassHomePageTeacherActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.entity.Token;

import java.util.List;
import java.util.Map;

public class UserLogin<T> extends CustomAsyncTask<T> {

    Token token = new Token();
    public int type;    //1为验证码，2为密码登录
    Context context;

    public UserLogin(Map headers, Map body, Map params, String url, int type, Context context) {
        super(headers, body, params, url);
        this.type = type;
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, Object>> temp = (ResponseResultJson<Map<String, Object>>) getResponse(s);
        int code = temp.getCode();
        if(code == 200){
            Map<String, Object> map = temp.getData();
            Token.token = map.get("access_token").toString();
            if(type==1){
                Intent intent =new Intent(context, ClassHomePageTeacherActivity.class);
                context.startActivity(intent);
            }else if(type ==2){
                Intent intent=new Intent(context, ClassHomePageStudentActivity.class);
                context.startActivity(intent);
            }
        }else{
            if(type == 1){
                Toast.makeText(context,"验证码错误",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"手机号或密码错误",Toast.LENGTH_SHORT).show();
            }
        }
        super.onPostExecute(s);
    }

}
