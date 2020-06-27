package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.fzn.classsign.R;
import com.fzn.classsign.entity.Token;

/**
 * 登录端选择
 */
public class LoginSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    Button bTeacher;
    Button bStuder;
    Button bLogon;

    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_selection);

        bTeacher=findViewById(R.id.b_ls_teacher);
        bTeacher.setTag(1);
        bStuder=findViewById(R.id.b_ls_student);
        bStuder.setTag(2);
        bLogon=findViewById(R.id.b_ls_logon);
        bLogon.setTag(3);

        bTeacher.setOnClickListener(this);
        bStuder.setOnClickListener(this);
        bLogon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int tag=(Integer) v.getTag();
        switch (tag){
            case 1:
                readRefreshToken(tag);
                break;
            case 2:
                readRefreshToken(tag);
                break;
            case 3:
                Intent intent=new Intent(LoginSelectionActivity.this,QuickLoginActivity.class);
                intent.putExtra("TYPE",3);
                startActivity(intent);
                break;
        }
    }

    //读取SharedPreferences存储的token
    private void readRefreshToken(int tag){
        String spFileName = getResources().getString(R.string.shared_preferences_file_name);
        String refreshTokenKey = getResources().getString(R.string.refresh_token);
        SharedPreferences spFile = getSharedPreferences(spFileName,MODE_PRIVATE);
        Token.refreshToken = spFile.getString(refreshTokenKey,null);
        if(Token.refreshToken != null && !TextUtils.isEmpty(Token.refreshToken)){
            //刷新token和refresh_token
            Token.FreshToken(LoginSelectionActivity.this,1,tag);
        }else {
            Intent intent=new Intent(LoginSelectionActivity.this,QuickLoginActivity.class);
            intent.putExtra("TYPE",tag);
            startActivity(intent);
        }
    }
}