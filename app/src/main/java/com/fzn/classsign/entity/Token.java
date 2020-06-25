package com.fzn.classsign.entity;

import com.fzn.classsign.asynctask.user.UserRefresh;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Token {
    public static String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIyIiwiYXVkIjpbImNsYXNzU2lnbkFuZHJvaWQiXSwidXNlcl9uYW1lIjoiMiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1OTMwNzkzNjUsImF1dGhvcml0aWVzIjpbImNvbW1vbiJdLCJqdGkiOiJlNDc2ZWVkZC0xZGQ1LTQ4M2EtOGU5Mi0zZjQ5NzNhNTY3NmMiLCJjbGllbnRfaWQiOiJjbGFzc1NpZ25BbmRyb2lkIn0.SY2DvjefXY4x2UxHHDuZ9_mq3wCaC0xfmSQYG5ERs24";
    public static String refreshToken;

    public static void FreshToken(){
        Map<String, String> body = new HashMap<>();
        body.put("token",Token.refreshToken);
        new UserRefresh<Map<String, Object>>(null,body,null)
                .post()
                .execute();
    }
}
