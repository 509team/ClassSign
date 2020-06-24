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
import com.fzn.classsign.asynctask.sms.SendLoginCode;
import com.fzn.classsign.asynctask.sms.SendRegisterCode;

/**
 * 快捷登录
 */
public class QuickLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitle;
    private EditText etPhone;
    private Button bGet;
    private TextView tvOtherWay;
    private String phone;

    private int type;   //标记此页面为教师端、学生端、注册页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_login);

        tvTitle = findViewById(R.id.tv_ql_title);
        etPhone = findViewById(R.id.et_ql_phone);
        bGet = findViewById(R.id.b_ql_get_verification);
        tvOtherWay = findViewById(R.id.tv_ql_other_login);

        //修改标题
        String title = tvTitle.getText().toString();
        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE", 0);
        if (type == 1) {
            title += "\n" + "老师端";
            tvOtherWay.setVisibility(TextView.VISIBLE);
            tvOtherWay.setOnClickListener(this);
            tvOtherWay.setTag(2);
        } else if (type == 2) {
            title += "\n" + "学生端";
            tvOtherWay.setVisibility(TextView.VISIBLE);
            tvOtherWay.setOnClickListener(this);
            tvOtherWay.setTag(2);
        } else if (type == 3) {
            title += "\n" + "注册";
        }
        tvTitle.setText(title);

        bGet.setOnClickListener(this);
        bGet.setTag(1);
    }

    @Override
    public void onClick(View v) {
        Intent intent2;
        switch ((Integer) v.getTag()) {
            case 1:
                phone = etPhone.getText().toString();
                if (phone.equals("")) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (phone.length() != 11) {
                    Toast.makeText(this, "输入正确的手机号", Toast.LENGTH_SHORT).show();
                } else {
                    if (type == 3) {    //调用注册获取验证码接口
                        new SendRegisterCode<Boolean>(null, null, null, this, phone,1)
                                .gett()
                                .execute(phone);
                    } else { //调用登录获取验证码接口
                        new SendLoginCode<Boolean>(null, null, null, this, phone, type,1)
                                .gett()
                                .execute(phone);
                    }
//                    intent2=new Intent(this,GetVerificationCodeActivity.class);
//                    intent2.putExtra("TYPE",type);
//                    intent2.putExtra("PHONE",phone);
//                    startActivity(intent2);
                }
                break;
            case 2:
                intent2 = new Intent(this, PasswordLoginActivity.class);
                intent2.putExtra("TYPE", type);
                startActivity(intent2);
                break;
        }
    }
}