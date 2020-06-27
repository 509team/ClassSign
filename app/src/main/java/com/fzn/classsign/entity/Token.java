package com.fzn.classsign.entity;

import android.content.Context;

import com.fzn.classsign.asynctask.user.UserRefresh;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Token {
    public static String token;
    public static String refreshToken;

    /**
     * 刷新token
     * type：登录端刷新token为1，其他活动刷新token为0
     * jumpTag：登录端跳转到教师端为1，跳转到学生端为2；其他活动为0
     **/
    public static void FreshToken(Context context,int type,int jumpTag){
        Map<String, String> body = new HashMap<>();
        body.put("token",Token.refreshToken);
        new UserRefresh<Map<String, Object>>(null,body,null,context,type,jumpTag)
                .post()
                .execute();
    }
}
