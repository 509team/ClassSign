package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;
import com.fzn.classsign.asynctask.common.GetUserBaseInfo;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的信息
 */
public class MyInfoStudentActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv_bsbs_signin;
    private ImageView iv_bsbs_class;
    private ImageView iv_bsbs_mine;
    private TextView tv_bsbs_sign;
    private TextView tv_bsbs_class;
    private TextView tv_bsbs_mine;

    private TextView tvUpdate;
    private ImageView ivUpdate;
    private Button bUpdate;
    private Button bEnd;

    //记录个人信息
    private TextView tvName;
    private TextView tvSex;
    private TextView tvPhone;
    private TextView tvNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_student);

        iv_bsbs_signin = findViewById(R.id.iv_bsbs_signin);
        iv_bsbs_class = findViewById(R.id.iv_bsbs_class);
        iv_bsbs_mine = findViewById(R.id.iv_bsbs_mine);
        tv_bsbs_sign = findViewById(R.id.tv_bsbs_sign);
        tv_bsbs_class = findViewById(R.id.tv_bsbs_class);
        tv_bsbs_mine = findViewById(R.id.tv_bsbs_mine);

        tvUpdate=findViewById(R.id.tv_mis_resetpassword);
        ivUpdate=findViewById(R.id.iv_mis_resetpassword);
        bUpdate=findViewById(R.id.bt_mis_updateinfo);
        bEnd=findViewById(R.id.bt_mis_logout);

        tvName=findViewById(R.id.tv_mis_name);
        tvSex=findViewById(R.id.tv_mis_sex);
        tvPhone=findViewById(R.id.tv_mis_phone);
        tvNumber=findViewById(R.id.tv_mis_studentid);

        //调用接口获取个人信息，填充显示个人信息
        Map<String,String> heads=new HashMap<>();
        heads.put("Authorization","Bearer "+ Token.token);
        new GetUserBaseInfo<Map<String,String>>(heads,null,null,tvName,tvSex,tvPhone,tvNumber)
                .gett()
                .execute();

        iv_bsbs_signin.setOnClickListener(this);
        iv_bsbs_class.setOnClickListener(this);
        iv_bsbs_mine.setOnClickListener(this);
        tv_bsbs_sign.setOnClickListener(this);
        tv_bsbs_class.setOnClickListener(this);
        tv_bsbs_mine.setOnClickListener(this);

        tvUpdate.setOnClickListener(this);
        ivUpdate.setOnClickListener(this);
        bUpdate.setOnClickListener(this);
        bEnd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==  R.id.iv_bsbs_signin || v.getId() ==  R.id.tv_bsbs_sign){
            Intent intent = new Intent(MyInfoStudentActivity.this, SignInActivity.class);
            startActivity(intent);
        }
        if(v.getId() ==  R.id.iv_bsbs_class || v.getId() ==  R.id.tv_bsbs_class){
            Intent intent = new Intent(MyInfoStudentActivity.this, ClassHomePageStudentActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.tv_mis_resetpassword || v.getId() == R.id.iv_mis_resetpassword){
            Intent intent = new Intent(MyInfoStudentActivity.this, UpdatePasswordActivity.class);
            intent.putExtra("TYPE",2);
            startActivity(intent);
        }
        if(v.getId() == R.id.bt_mis_updateinfo){
            Intent intent = new Intent(MyInfoStudentActivity.this, UpdateInfoActivity.class);
            intent.putExtra("NAME",tvName.getHint().toString());
            intent.putExtra("SEX",tvSex.getHint().toString());
            intent.putExtra("NUMBER",tvNumber.getHint().toString());
            intent.putExtra("TYPE",2);
            startActivity(intent);
        }
        if(v.getId() == R.id.bt_mis_logout){
            Intent intent = new Intent(MyInfoStudentActivity.this, LoginSelectionActivity.class);
            startActivity(intent);
        }
    }
}