package com.fzn.classsign.asynctask.user;

import android.content.Context;
import android.widget.Toast;

import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.entity.Token;

import java.util.List;
import java.util.Map;

public class UserLogin<T> extends CustomAsyncTask<T> {

    Token token = new Token();
    private boolean flag = false;
    public int type;    //1为验证码，2为密码登录
    Context context;

    public UserLogin(Map headers, Map body, Map params, String url, int type, Context context) {
        super(headers, body, params, url);
        this.type = type;
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, String>>> temp = (ResponseResultJson<List<Map<String, String>>>) getResponse(s);
        int code = temp.getCode();
        if(code == 200){
            List<Map<String, String>> list = temp.getData();
            for ( Map<String, String> map:list){
                if(map.get("access_token")!=null&&(map.get("access_token"))!=""){
                    token.setToken(map.get("access_token"));
                }
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
