package com.fzn.classsign.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;
import com.fzn.classsign.entity.SignIn;

/**
 * 课堂主页
 */
public class ClassHomePageStudentActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_bsbs_signin;
    private ImageView iv_bsbs_class;
    private ImageView iv_bsbs_mine;
    private TextView tv_bsbs_sign;
    private TextView tv_bsbs_class;
    private TextView tv_bsbs_mine;
    private ImageView ivInto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_home_page_student);

        iv_bsbs_signin = findViewById(R.id.iv_bsbs_signin);
        iv_bsbs_class = findViewById(R.id.iv_bsbs_class);
        iv_bsbs_mine = findViewById(R.id.iv_bsbs_mine);
        tv_bsbs_sign = findViewById(R.id.tv_bsbs_sign);
        tv_bsbs_class = findViewById(R.id.tv_bsbs_class);
        tv_bsbs_mine = findViewById(R.id.tv_bsbs_mine);
        ivInto=findViewById(R.id.iv_chps_joinclass);

        iv_bsbs_signin.setOnClickListener(this);
        iv_bsbs_class.setOnClickListener(this);
        iv_bsbs_mine.setOnClickListener(this);
        tv_bsbs_sign.setOnClickListener(this);
        tv_bsbs_class.setOnClickListener(this);
        tv_bsbs_mine.setOnClickListener(this);
        ivInto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       if(v.getId() ==  R.id.iv_bsbs_signin || v.getId() ==  R.id.tv_bsbs_sign){
           Intent intent = new Intent(ClassHomePageStudentActivity.this, SignInActivity.class);
           startActivity(intent);
       }
       if(v.getId() == R.id.iv_bsbs_mine || v.getId() == R.id.tv_bsbs_mine){
           Intent intent = new Intent(ClassHomePageStudentActivity.this, MyInfoStudentActivity.class);
           startActivity(intent);
        }
        if(v.getId() == R.id.iv_chps_joinclass){
            Intent intent = new Intent(ClassHomePageStudentActivity.this, JoinClassActivity.class);
            startActivity(intent);
        }
    }
}