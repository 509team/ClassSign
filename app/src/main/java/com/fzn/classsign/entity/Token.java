package com.fzn.classsign.entity;

import com.fzn.classsign.asynctask.user.UserRefresh;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Token {
    public static String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIyIiwiYXVkIjpbImNsYXNzU2lnbkFuZHJvaWQiXSwidXNlcl9uYW1lIjoiMiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1OTMwNzM1MTMsImF1dGhvcml0aWVzIjpbImNvbW1vbiJdLCJqdGkiOiI5N2MwODhmOC03YjM3LTQwMDctODVlYS02ZGM3YTFmMjY1ZGYiLCJjbGllbnRfaWQiOiJjbGFzc1NpZ25BbmRyb2lkIn0.DvyXzJ7cQG4qaAmL9cRhDMa02Pn2-pBvza6dIsbI_Yg";
    public static String refreshToken;

    public static void FreshToken(){
        Map<String, String> body = new HashMap<>();
        body.put("token",Token.refreshToken);
        new UserRefresh<Map<String, Object>>(null,body,null)
                .post()
                .execute();
    }
}
