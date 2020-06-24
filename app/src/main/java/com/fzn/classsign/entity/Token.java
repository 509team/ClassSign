package com.fzn.classsign.entity;

import com.fzn.classsign.asynctask.user.UserRefresh;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Token {
    public static String token;
    public static String refreshToken;

    public static void FreshToken(){
        Map<String, String> body = new HashMap<>();
        body.put("token",Token.refreshToken);
        new UserRefresh<Map<String, Object>>(null,body,null)
                .post()
                .execute();
    }
}
