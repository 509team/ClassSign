package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.R;

/**
 * 密码登录
 */
public class PasswordLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitle;
    private EditText etPhone;
    private EditText etPwd;
    private Button bLigon;
    private TextView tvOtherWay;

    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);

        tvTitle = findViewById(R.id.tv_pl_title);
        etPhone = findViewById(R.id.et_pl_phone);
        etPwd = findViewById(R.id.et_pl_pwd);
        bLigon = findViewById(R.id.b_pl_login);
        tvOtherWay = findViewById(R.id.tv_pl_other_login);

        Intent intent=getIntent();
        type=intent.getIntExtra("TYPE",0);
        String title=tvTitle.getText().toString();
        if(type==1){
            title="教师端登录";
        }else if(type==2){
            title="学生端登录";
        }
        tvTitle.setText(title);

        bLigon.setOnClickListener(this);
        bLigon.setTag(1);
        tvOtherWay.setOnClickListener(this);
        tvOtherWay.setTag(2);
    }

    @Override
    public void onClick(View v) {
        Intent intent2=null;
        switch ((int)v.getTag()){
            case 1 :
                String phone=etPhone.getText().toString();
                String pwd=etPwd.getText().toString();
                if(phone.equals("")||pwd.equals("")){
                    Toast.makeText(this,"账户或密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(phone.length()!=11){
                    Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                }else if(pwd.length()<6||pwd.length()>20){
                    Toast.makeText(this,"请输入正确的密码",Toast.LENGTH_SHORT).show();
                }

                //调用接口判断账号正确性，待实现
                if(type==1){
                    intent2=new Intent(this,ClassHomePageTeacherActivity.class);
                }else if(type ==2){
                    intent2=new Intent(this,ClassHomePageStudentActivity.class);
                }
               startActivity(intent2);
                break;
            case 2:
                intent2=new Intent(this,QuickLoginActivity.class);
                intent2.putExtra("TYPE",type);
                startActivity(intent2);
                break;

        }
    }
}