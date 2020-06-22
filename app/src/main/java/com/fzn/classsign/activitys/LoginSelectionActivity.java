package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fzn.classsign.R;

/**
 * 登录端选择
 */
public class LoginSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    Button bTeacher;
    Button bStuder;
    Button bLogon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_selection);

        bTeacher=findViewById(R.id.b_ls_teacher);
        bTeacher.setTag(1);
        bStuder=findViewById(R.id.b_ls_student);
        bStuder.setTag(2);
        bLogon=findViewById(R.id.b_ls_logon);
        bLogon.setTag(3);

        bTeacher.setOnClickListener(this);
        bStuder.setOnClickListener(this);
        bLogon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(LoginSelectionActivity.this,QuickLoginActivity.class);
        int tag=(Integer) v.getTag();
        switch (tag){
            case 1:
                intent.putExtra("TYPE",1);
                break;
            case 2:
                intent.putExtra("TYPE",2);
                break;
            case 3:
                intent.putExtra("TYPE",3);
        }
        startActivity(intent);
    }
}