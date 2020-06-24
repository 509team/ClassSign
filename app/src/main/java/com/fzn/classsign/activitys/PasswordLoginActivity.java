package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.R;
import com.fzn.classsign.asynctask.user.UserLogin;

import java.util.HashMap;
import java.util.Map;

import static com.fzn.classsign.constant.RequestConstant.URL.LOGIN;

/**
 * 密码登录
 */
public class PasswordLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitle;
    private EditText etPhone;
    private EditText etPwd;
    private ImageView ivSee;
    private Button bLigon;
    private TextView tvOtherWay;

    private int type;
    private boolean see;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);

        tvTitle = findViewById(R.id.tv_pl_title);
        etPhone = findViewById(R.id.et_pl_phone);
        etPwd = findViewById(R.id.et_pl_pwd);
        ivSee=findViewById(R.id.iv_pl_see);
        bLigon = findViewById(R.id.b_pl_login);
        tvOtherWay = findViewById(R.id.tv_pl_other_login);

        see=false;
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
        ivSee.setOnClickListener(this);
        ivSee.setTag(3);
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
                    Toast.makeText(this,"手机号格式不正确",Toast.LENGTH_SHORT).show();
                }else if(pwd.length()<6||pwd.length()>20){
                    Toast.makeText(this,"密码格式不正确",Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, String> map = new HashMap<>();
                    map.put("phone", phone);
                    map.put("password", pwd);
                    new UserLogin<String>(null, map, null, 2, PasswordLoginActivity.this,1)
                            .post()
                            .execute();
                }
                break;
            case 2:
                intent2=new Intent(this,QuickLoginActivity.class);
                intent2.putExtra("TYPE",type);
                startActivity(intent2);
                break;
            case 3:
                see =!see;
                if(see){
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ivSee.setImageResource(R.drawable.ic_baseline_visibility_off_24);

                }else{
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                    ivSee.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
                break;
        }
    }
}