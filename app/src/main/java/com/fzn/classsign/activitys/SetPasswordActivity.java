package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fzn.classsign.R;

/**
 * 设置密码
 */
public class SetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPwd;
    private Button bSet;

    private String phone;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        Intent intent = getIntent();
        phone = intent.getStringExtra("PHONE");
        etPwd = findViewById(R.id.et_sp_input);
        bSet = findViewById(R.id.b_sp_finish);

        bSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        pwd = etPwd.getText().toString();
        if (pwd.equals("")) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (pwd.length() < 6) {
            Toast.makeText(this, "密码不能小于6", Toast.LENGTH_SHORT).show();
        } else if (pwd.length() > 20) {
            Toast.makeText(this, "密码不能大于20", Toast.LENGTH_SHORT).show();
        } else {
            //调用接口保存密码
            Toast.makeText(this, "注册成功，重新登录!", Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(this, LoginSelectionActivity.class);
            startActivity(intent2);
        }
    }
}