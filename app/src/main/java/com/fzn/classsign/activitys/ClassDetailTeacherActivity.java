package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fzn.classsign.R;

/**
 * 教师端端课程详情
 */
public class ClassDetailTeacherActivity extends AppCompatActivity implements View.OnClickListener {

    /*课程名称、课号、加入码的textview*/
    private TextView tv_cdt_classname;
    private TextView tv_cdt_classcode;
    private TextView tv_cdt_joinclasscode;

    private String cid;
    private String cnum;
    private String name;
    private String joinCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail_teacher);

        tv_cdt_classname = findViewById(R.id.tv_cdt_classname);
        tv_cdt_classcode = findViewById(R.id.tv_cdt_classcode);
        tv_cdt_joinclasscode = findViewById(R.id.tv_cdt_joinclasscode);

        Intent intent = getIntent();
        cid =  intent.getStringExtra("CID");
        cnum =  intent.getStringExtra("CNUM");
        name =  intent.getStringExtra("NAME");
        joinCode =  intent.getStringExtra("JOINCODE");

        tv_cdt_classname.setText(name);
        tv_cdt_classcode.setText(cnum);
        tv_cdt_joinclasscode.setText(joinCode);

    }

    @Override
    public void onClick(View v) {

    }
}