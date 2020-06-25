package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fzn.classsign.R;
import com.fzn.classsign.asynctask.student.ListSignInRecord;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生端课程详情
 */
public class ClassDetailStudentActivity extends AppCompatActivity implements View.OnClickListener {

    //卡片信息
    private TextView tv_cds_classname;
    private TextView tv_cds_classcode;
    private TextView tv_cds_joinclasscode;

    //三个出勤数字
    private TextView tv_cds_attendancenum;
    private TextView tv_cds_abnormalnum;
    private TextView tv_cds_absencenum;

    /*所有学生*/
    private Button bt_cds_allstudent;

    private String cid;
    private String className;
    private String classNum;
    private String joinCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail_student);

        tv_cds_classname = findViewById(R.id.tv_cds_classname);
        tv_cds_classcode = findViewById(R.id.tv_cds_classcode);
        tv_cds_joinclasscode = findViewById(R.id.tv_cds_joinclasscode);

        Intent intent = getIntent();
        cid = intent.getStringExtra("CID");
        cid = cid.substring(0,cid.indexOf("."));
        className = intent.getStringExtra("NAME");
        classNum = intent.getStringExtra("CNUM");
        joinCode = intent.getStringExtra("JOINCLDE");
        tv_cds_classcode.setText(classNum);
        tv_cds_classname.setText(className);
        tv_cds_joinclasscode.setText(joinCode);

        tv_cds_attendancenum = findViewById(R.id.tv_cds_attendancenum);
        tv_cds_abnormalnum = findViewById(R.id.tv_cds_abnormalnum);
        tv_cds_absencenum = findViewById(R.id.tv_cds_absencenum);

        Map<String, String> head = new HashMap<>();
        head.put("Authorization","Bearer "+ Token.token);

        new ListSignInRecord<List<Map<String, Object>>>(head,null,null,tv_cds_attendancenum,tv_cds_abnormalnum,tv_cds_absencenum,ClassDetailStudentActivity.this)
                .gett().execute(cid);

        bt_cds_allstudent = findViewById(R.id.bt_cds_allstudent);
        bt_cds_allstudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt_cds_allstudent){
            Intent intent = new Intent(ClassDetailStudentActivity.this,MemberListActivity.class);
            intent.putExtra("CID",cid);
            startActivity(intent);
        }
    }
}