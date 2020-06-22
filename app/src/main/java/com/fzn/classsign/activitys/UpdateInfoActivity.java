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
 * 修改信息
 */
public class UpdateInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView topTitle;
    private EditText etName;
    private EditText etSex;
    private EditText etNumber;
    private Button bUpdate;

    private String name;
    private String sex;
    private String number;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        //设置顶部状态栏信息
        topTitle = findViewById(R.id.tv_tsb_title);
        topTitle.setText("修改个人信息");
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        sex = intent.getStringExtra("SEX");
        number = intent.getStringExtra("NUMBER");
        type = intent.getIntExtra("TYPE", 0);

        etName = findViewById(R.id.et_ui_user_name);
        etSex = findViewById(R.id.et_ui_sex);
        etNumber = findViewById(R.id.et_ui_number);
        bUpdate = findViewById(R.id.b_ui_update);

        etName.setHint(name);
        etSex.setHint(sex);
        etNumber.setHint(number);

        bUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isUpdate = false;
        if (etName.getText().toString().equals("") || etSex.getText().toString().equals("") || etNumber.getText().toString().equals("")) {
            Toast.makeText(UpdateInfoActivity.this, "信息不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (!name.equals(etName.getText().toString())) {
                name = etName.getText().toString();
                isUpdate = true;
            }
            if (!sex.equals(etSex.getText().toString())) {
                sex = etSex.getText().toString();
                isUpdate = true;
            }
            if (!number.equals(etNumber.getText().toString())) {
                number = etNumber.getText().toString();
                isUpdate = true;
            }
        }
        if (isUpdate) {
            //调用接口更新信息



            Toast.makeText(UpdateInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            if (type == 1) {//返回教师端
                Intent intent2 = new Intent(UpdateInfoActivity.this, MyInfoTeacherActivity.class);
                startActivity(intent2);
            } else if (type == 2) {//返回学生端
                Intent intent2 = new Intent(UpdateInfoActivity.this, MyInfoStudentActivity.class);
                startActivity(intent2);
            }
        }
    }
}