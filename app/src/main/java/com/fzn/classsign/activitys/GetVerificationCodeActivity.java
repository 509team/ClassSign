package com.fzn.classsign.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import com.fzn.classsign.R;
import com.fzn.classsign.asynctask.sms.SendLoginCode;
import com.fzn.classsign.asynctask.sms.SendRegisterCode;
import com.fzn.classsign.asynctask.user.IsRegisterCodeRight;
import com.fzn.classsign.asynctask.user.UserLogin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 获取验证码
 */
public class GetVerificationCodeActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etInput;
    private TextView tvTime;
    private TextView tvResend;
    private Button bCheck;
    private ImageView tv_gvc_back;
    //计时器
    CountDownTimer timer=new CountDownTimer(60000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int second=(int)millisUntilFinished/1000;
            tvTime.setText(second+"秒");
        }

        @Override
        public void onFinish() {
            tvTime.setVisibility(View.INVISIBLE);
            tvResend.setTextColor(getResources().getColor(R.color.skyblue));
            tvResend.setClickable(true);
        }
    };

    private int type;
    private String phone;//存储上一界面传递过来的手机号
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_verification_code);

        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE", 0);
        phone=intent.getStringExtra("PHONE");
//        phone="18290000630";
//        type=3;
        etInput = findViewById(R.id.et_gvc_input);

        tvTime=findViewById(R.id.tv_gvc_time);
        tvResend=findViewById(R.id.tv_gvc_resend);
        bCheck=findViewById(R.id.b_gvc_check);
        tv_gvc_back=findViewById(R.id.tv_gvc_back);
        timer.start();

        tvResend.setOnClickListener(GetVerificationCodeActivity.this);
        tvResend.setTag(1);
        tvResend.setClickable(false);

        bCheck.setOnClickListener(this);
        bCheck.setTag(2);
        tv_gvc_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_gvc_back){
            Intent intent=new Intent(this,QuickLoginActivity.class  );
            intent.putExtra("TYPE",type);
            this.startActivity(intent);
            return;
        }
        switch((int)v.getTag()){
            case 1:
                tvTime.setVisibility(View.VISIBLE);
                tvResend.setTextColor(getResources().getColor(R.color.gray));
                tvResend.setClickable(false);
                timer.start();
                if (type == 3) {      //调用接口重新发送验证
                    new SendRegisterCode<Boolean>(null, null, null, this,2)
                            .gett()
                            .execute(phone);
                } else { //调用登录获取验证码接口
                    new SendLoginCode<Boolean>(null, null, null, this, type,2)
                            .gett()
                            .execute(phone);
                }
                break;
            case 2:
                Intent intent2;
                String code=etInput.getText().toString();    //验证码
                if(code.equals("")||code.length()!=6){
                    Toast.makeText(this,"请检查验证码",Toast.LENGTH_SHORT).show();
                }else {
                    if(type==3){//调用用户注册账号接口
                        Map<String,String> body=new HashMap<>();
                        body.put("phone",phone);
                        body.put("code",code);
                        new IsRegisterCodeRight<Boolean>(null, body, null, this,phone,code)
                                .post()
                                .execute();
                    }else{//调用验证码登录接口账号接口
                        Map<String,String> map=new HashMap<>();
                        map.put("phone",phone);
                        map.put("password",code);
                        new UserLogin<String>(null, map, null, type, this,2)
                                .post()
                                .execute();
                    }
                }
                break;
        }
    }
}