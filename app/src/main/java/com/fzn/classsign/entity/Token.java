package com.fzn.classsign.entity;

import com.fzn.classsign.asynctask.user.UserRefresh;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Token {
    public static String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIyIiwiYXVkIjpbImNsYXNzU2lnbkFuZHJvaWQiXSwidXNlcl9uYW1lIjoiMiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1OTMwODkxMjIsImF1dGhvcml0aWVzIjpbImNvbW1vbiJdLCJqdGkiOiI4M2MxOGI5Yy1jODc1LTRiNDYtOTYyYy1hMTdlNDIwZTM5OGIiLCJjbGllbnRfaWQiOiJjbGFzc1NpZ25BbmRyb2lkIn0.zVI7Ym2GsCKQ5zLm_YgGOWXD4hklae-4_slguOVIPm4";
    public static String refreshToken;

    public static void FreshToken(){
        Map<String, String> body = new HashMap<>();
        body.put("token",Token.refreshToken);
        new UserRefresh<Map<String, Object>>(null,body,null)
                .post()
                .execute();
    }
}
