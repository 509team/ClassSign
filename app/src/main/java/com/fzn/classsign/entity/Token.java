package com.fzn.classsign.entity;

import com.fzn.classsign.asynctask.user.UserRefresh;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Token {
    public static String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIyIiwiYXVkIjpbImNsYXNzU2lnbkFuZHJvaWQiXSwidXNlcl9uYW1lIjoiMiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1OTMwNzg5OTEsImF1dGhvcml0aWVzIjpbImNvbW1vbiJdLCJqdGkiOiI1NTdmM2NmZi0xNTczLTQ1NzYtOTY1Mi01NzMzY2JmN2Q2YmUiLCJjbGllbnRfaWQiOiJjbGFzc1NpZ25BbmRyb2lkIn0.F17ZubIB4xMJxupHET4QkZ_SXCC2niH1xETan_u9_2M";
    public static String refreshToken;

    public static void FreshToken(){
        Map<String, String> body = new HashMap<>();
        body.put("token",Token.refreshToken);
        new UserRefresh<Map<String, Object>>(null,body,null)
                .post()
                .execute();
    }
}
