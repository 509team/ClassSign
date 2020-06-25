package com.fzn.classsign.entity;

import com.fzn.classsign.asynctask.user.UserRefresh;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Token {
    public static String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIyIiwiYXVkIjpbImNsYXNzU2lnbkFuZHJvaWQiXSwidXNlcl9uYW1lIjoiMiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1OTMwODA2NjAsImF1dGhvcml0aWVzIjpbImNvbW1vbiJdLCJqdGkiOiI4NWM3MGE2My0yYTVlLTQ5YmQtOTI5Yi03MmFlMmZhNGMzNGYiLCJjbGllbnRfaWQiOiJjbGFzc1NpZ25BbmRyb2lkIn0.Rd11ZpVB4Ai8qGJ_XTVzZp4LrTKHxH0aAmJlAbo2JLY";
    public static String refreshToken;

    public static void FreshToken(){
        Map<String, String> body = new HashMap<>();
        body.put("token",Token.refreshToken);
        new UserRefresh<Map<String, Object>>(null,body,null)
                .post()
                .execute();
    }
}
