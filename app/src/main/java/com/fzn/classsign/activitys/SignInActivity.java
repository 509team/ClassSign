package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;

/**
 * 学生签到
 */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv_bsbs_signin;
    private ImageView iv_bsbs_class;
    private ImageView iv_bsbs_mine;
    private TextView tv_bsbs_sign;
    private TextView tv_bsbs_class;
    private TextView tv_bsbs_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        iv_bsbs_signin = findViewById(R.id.iv_bsbs_signin);
        iv_bsbs_class = findViewById(R.id.iv_bsbs_class);
        iv_bsbs_mine = findViewById(R.id.iv_bsbs_mine);
        tv_bsbs_sign = findViewById(R.id.tv_bsbs_sign);
        tv_bsbs_class = findViewById(R.id.tv_bsbs_class);
        tv_bsbs_mine = findViewById(R.id.tv_bsbs_mine);

        iv_bsbs_signin.setOnClickListener(this);
        iv_bsbs_class.setOnClickListener(this);
        iv_bsbs_mine.setOnClickListener(this);
        tv_bsbs_sign.setOnClickListener(this);
        tv_bsbs_class.setOnClickListener(this);
        tv_bsbs_mine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==  R.id.iv_bsbs_signin || v.getId() ==  R.id.tv_bsbs_sign){
            Intent intent = new Intent(SignInActivity.this, SignInActivity.class);
            startActivity(intent);
        }
        if(v.getId() ==  R.id.iv_bsbs_class || v.getId() ==  R.id.tv_bsbs_class){
            Intent intent = new Intent(SignInActivity.this, ClassHomePageStudentActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.iv_bsbs_mine || v.getId() == R.id.tv_bsbs_mine){
            Intent intent = new Intent(SignInActivity.this, MyInfoStudentActivity.class);
            startActivity(intent);
        }
    }
}