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

import org.w3c.dom.Text;

/**
 * 创建班级
 */
public class CreateClassActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private EditText etClassNumber;
    private EditText etClassName;
    private EditText etTeacherName;
    private Button bCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        tvTitle=findViewById(R.id.tv_tsb_title);
        tvTitle.setText("创建班级");

        etClassNumber=findViewById(R.id.et_cc_class_number);
        etClassName=findViewById(R.id.et_cc_class_name);
        etTeacherName=findViewById(R.id.et_cc_teacher_name);
        bCreate=findViewById(R.id.bt_cc_creatclass);

        bCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String className=etClassName.getText().toString();
        String classNumber=etClassNumber.getText().toString();
        String teacherName=etClassNumber.getText().toString();

        if(className.equals("")||classNumber.equals("")||teacherName.equals("")){
            Toast.makeText(CreateClassActivity.this,"输入框不能为空",Toast.LENGTH_SHORT);
        }else{
            //调用接口创建班级



            Intent intent=new Intent(CreateClassActivity.this,ClassHomePageTeacherActivity.class);
            startActivity(intent);
            Toast.makeText(CreateClassActivity.this,"创建成功",Toast.LENGTH_SHORT);
        }
    }
}