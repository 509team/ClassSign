package com.fzn.classsign.entity;

import com.fzn.classsign.asynctask.user.UserRefresh;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Token {
    public static String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIyIiwiYXVkIjpbImNsYXNzU2lnbkFuZHJvaWQiXSwidXNlcl9uYW1lIjoiMiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1OTMwOTQxNTEsImF1dGhvcml0aWVzIjpbImNvbW1vbiJdLCJqdGkiOiJjY2I5MDNhNS0yOWYxLTRkZDItYjIwNC0wOGJhNWY3YjBiMGUiLCJjbGllbnRfaWQiOiJjbGFzc1NpZ25BbmRyb2lkIn0.49y1DGXi6OjN1RenQF9HuzbyHG94VcrocCLbjMPDjyY";
    public static String refreshToken;

    public static void FreshToken(){
        Map<String, String> body = new HashMap<>();
        body.put("token",Token.refreshToken);
        new UserRefresh<Map<String, Object>>(null,body,null)
                .post()
                .execute();
    }
}
