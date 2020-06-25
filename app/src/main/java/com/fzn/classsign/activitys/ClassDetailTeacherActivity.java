package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.R;

/**
 * 教师端端课程详情
 */
public class ClassDetailTeacherActivity extends AppCompatActivity implements View.OnClickListener {

    /*课程名称、课号、加入码的textview*/
    private TextView tv_cdt_classname;
    private TextView tv_cdt_classcode;
    private TextView tv_cdt_joinclasscode;

    /*所有学生及发布签到按钮*/
    private Button bt_cdt_allstudent;
    private Button bt_cdt_creatsignin;

    private String cid;
    private String cnum;
    private String name;
    private String joinCode;
    private String total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail_teacher);

        /*课程名称、课号、加入码的textview*/
        tv_cdt_classname = findViewById(R.id.tv_cdt_classname);
        tv_cdt_classcode = findViewById(R.id.tv_cdt_classcode);
        tv_cdt_joinclasscode = findViewById(R.id.tv_cdt_joinclasscode);

        Intent intent = getIntent();
        cid = intent.getStringExtra("CID");
        cnum = intent.getStringExtra("CNUM");
        name = intent.getStringExtra("NAME");
        joinCode = intent.getStringExtra("JOINCODE");
        total = intent.getStringExtra("TOTAL");
        if (total.contains(".")) {
            total = total.substring(0, total.indexOf("."));
        }

        tv_cdt_classname.setText(name);
        tv_cdt_classcode.setText(cnum);
        tv_cdt_joinclasscode.setText(joinCode);

        /*所有学生及发布签到按钮*/
        bt_cdt_allstudent = findViewById(R.id.bt_cdt_allstudent);
        bt_cdt_creatsignin = findViewById(R.id.bt_cdt_creatsignin);
        bt_cdt_allstudent.setOnClickListener(this);
        bt_cdt_creatsignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_cdt_allstudent) {
            Uri uri;
            Intent intent = new Intent(ClassDetailTeacherActivity.this, MemberListActivity.class);
            intent.putExtra("CID", cid);
            startActivity(intent);
        }
        if (v.getId() == R.id.bt_cdt_creatsignin) {
            if (total.equals("0")) {
                Toast.makeText(ClassDetailTeacherActivity.this, "课堂人数为0，无法发布签到!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(ClassDetailTeacherActivity.this, CreateSignInActivity.class);
                intent.putExtra("CID", cid);
                intent.putExtra("TOTAL", total);
                startActivity(intent);
            }
        }
    }
}