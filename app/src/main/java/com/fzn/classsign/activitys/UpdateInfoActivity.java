package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.R;
import com.fzn.classsign.asynctask.common.UpdateUserBaseInfo;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改信息
 */
public class UpdateInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView topTitle;
    private EditText etName;

    private Spinner sSex;
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
        sSex=findViewById(R.id.s_ui_sex);
        etNumber = findViewById(R.id.et_ui_number);
        bUpdate = findViewById(R.id.b_ui_update);

        etName.setHint(name);
        etNumber.setHint(number);
        //sSex.setTooltipText(sex);
        bUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isUpdate = false;
        if (etName.getText().toString().equals("") || etNumber.getText().toString().equals("")) {
            Toast.makeText(UpdateInfoActivity.this, "信息不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (!name.equals(etName.getText().toString())) {
                name = etName.getText().toString();
                isUpdate = true;
            }
            if (!sex.equals((String)sSex.getSelectedItem())) {
                sex = (String)sSex.getSelectedItem();
                isUpdate = true;
            }
            if (!number.equals(etNumber.getText().toString())) {
                number = etNumber.getText().toString();
                isUpdate = true;
            }
        }
        if (isUpdate) {
            //调用接口更新信息
            Map<String,String> heads=new HashMap<>();
            heads.put("Authorization","Bearer "+ Token.token);
            Map<String,String> body=new HashMap<>();
            body.put("uNum",number);
            body.put("name",name);
            if(sex.equals("男")){
                sex="male";
            }else{
                sex="female";
            }
            body.put("sex",sex);
            new UpdateUserBaseInfo<Boolean>(heads,body,null,this,type)
                    .post()
                    .execute();
        }
    }
}