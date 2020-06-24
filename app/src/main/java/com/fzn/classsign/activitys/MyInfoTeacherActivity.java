package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;

public class MyInfoTeacherActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv_bsbt_class;
    private ImageView iv_bsbt_mine;
    private TextView tv_bsbt_class;
    private TextView tv_bsbt_mine;

    private TextView tvUpdate;
    private ImageView ivUpdate;
    private Button bUpdate;
    private Button bEnd;

    //记录个人信息
    private TextView tvName;
    private TextView tvSex;
    private TextView tvNumber;
    private String name;
    private String sex;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_teacher);

        iv_bsbt_class = findViewById(R.id.iv_bsbt_class);
        iv_bsbt_mine = findViewById(R.id.iv_bsbt_mine);
        tv_bsbt_class = findViewById(R.id.tv_bsbt_class);
        tv_bsbt_mine = findViewById(R.id.tv_bsbt_mine);

        tvUpdate=findViewById(R.id.tv_mit_resetpassword);
        ivUpdate=findViewById(R.id.iv_mit_resetpassword);
        bUpdate=findViewById(R.id.bt_mit_updateinfo);
        bEnd=findViewById(R.id.bt_mit_logout);

        tvName=findViewById(R.id.tv_mit_name);
        tvSex=findViewById(R.id.tv_mit_sex);
        tvNumber=findViewById(R.id.tv_mit_teacherid);

        //调用接口获取个人信息，填充显示个人信息

        iv_bsbt_class.setOnClickListener(this);
        iv_bsbt_mine.setOnClickListener(this);
        tv_bsbt_class.setOnClickListener(this);
        tv_bsbt_mine.setOnClickListener(this);

        tvUpdate.setOnClickListener(this);
        ivUpdate.setOnClickListener(this);
        bUpdate.setOnClickListener(this);
        bEnd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==  R.id.iv_bsbt_class || v.getId() ==  R.id.tv_bsbt_class){
            Intent intent = new Intent(MyInfoTeacherActivity.this, ClassHomePageTeacherActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.tv_mit_resetpassword || v.getId() == R.id.iv_mit_resetpassword){
            Intent intent = new Intent(MyInfoTeacherActivity.this, UpdatePasswordActivity.class);
            intent.putExtra("TYPE",1);
            startActivity(intent);
        }
        if(v.getId() == R.id.bt_mit_updateinfo){
            Intent intent = new Intent(MyInfoTeacherActivity.this, UpdateInfoActivity.class);
            name= tvName.getHint().toString();
            sex= tvSex.getHint().toString();
            number= tvNumber.getHint().toString();
            intent.putExtra("NAME",name);
            intent.putExtra("SEX",sex);
            intent.putExtra("NUMBER",number);
            intent.putExtra("TYPE",1);

            startActivity(intent);
        }
        if(v.getId() == R.id.bt_mit_logout){
            Intent intent = new Intent(MyInfoTeacherActivity.this, LoginSelectionActivity.class);
            startActivity(intent);
        }
    }
}