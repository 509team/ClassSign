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
import com.fzn.classsign.asynctask.teacher.ClassCreate;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.Class;
import com.fzn.classsign.entity.Token;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建班级
 */
public class CreateClassActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private EditText etClassNumber;
    private EditText etClassName;
    private Button bCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        tvTitle=findViewById(R.id.tv_tsb_title);
        tvTitle.setText("创建班级");

        etClassNumber=findViewById(R.id.et_cc_class_number);
        etClassName=findViewById(R.id.et_cc_class_name);
        bCreate=findViewById(R.id.bt_cc_creatclass);

        bCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt_cc_creatclass){
            String className=etClassName.getText().toString();
            String classNumber=etClassNumber.getText().toString();

            if(className.equals("")||classNumber.equals("")){
                Toast.makeText(CreateClassActivity.this,"输入框不能为空",Toast.LENGTH_SHORT).show();
            }else{
                Map<String, String> head = new HashMap<>();
                head.put("Authorization","Bearer "+ Token.token);
                Map<String, String> params = new HashMap<>();
                params.put("cNum",classNumber);
                params.put("name",className);
                new ClassCreate<Map<String, Object>>(head,null,params,CreateClassActivity.this)
                        .post()
                        .execute();
            }
        }
    }
}