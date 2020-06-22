package com.fzn.classsign.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import com.fzn.classsign.R;

/**
 * 获取验证码
 */
public class GetVerificationCodeActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etInput;
    private TextView tvResend;
    private Button bCheck;

    private int type;
    private String phone;//存储上一界面传递过来的手机号
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_verification_code);

        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE", 0);
        phone=intent.getStringExtra("PHONE");
        etInput = findViewById(R.id.et_gvc_input);

        tvResend=findViewById(R.id.tv_gvc_resend);
       bCheck=findViewById(R.id.b_gvc_check);

        tvResend.setOnClickListener(this);
        tvResend.setTag(1);
        bCheck.setOnClickListener(this);
        bCheck.setTag(2);
    }

    @Override
    public void onClick(View v) {
        switch((int)v.getTag()){
            case 1:
                //调用接口重新发送验证
                break;
            case 2:
                Intent intent2;
                String code=etInput.getText().toString();    //验证码
                if(code.equals("")||code.length()!=6){
                    Toast.makeText(this,"请检查验证码",Toast.LENGTH_SHORT).show();
                }else {
                    if(true){ //调用接口判断验证码正确
                        if(type==1){    //调用教师端
                            intent2=new Intent(this,ClassHomePageTeacherActivity.class);
                            startActivity(intent2);
                        }else if(type==2){  //调用
                            intent2=new Intent(this,ClassHomePageStudentActivity.class);
                            startActivity(intent2);
                        }else if(type==3){
                            intent2=new Intent(this,SetPasswordActivity.class);
                            intent2.putExtra("PHONE",phone);
                            startActivity(intent2);
                        }

                    }
                }
                break;
        }
    }
}