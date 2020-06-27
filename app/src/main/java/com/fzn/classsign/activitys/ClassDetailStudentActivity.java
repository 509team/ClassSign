package com.fzn.classsign.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;
import com.fzn.classsign.activitys.fragment.StudentFragmentActivity;
import com.fzn.classsign.activitys.fragment.TeacherFragmentActivity;
import com.fzn.classsign.asynctask.student.ListSignInRecord;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yzq.zxinglibrary.encode.CodeCreator.createQRCode;

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

    private RecyclerView recyclerView;

    private View classJoinCode;
    private ImageView iv_class_join_code;
    private ImageView tv_tsb_back2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail_student);

        tv_cds_classname = findViewById(R.id.tv_cds_classname);
        tv_cds_classcode = findViewById(R.id.tv_cds_classcode);
        tv_cds_joinclasscode = findViewById(R.id.tv_cds_joinclasscode);

        Intent intent = getIntent();
        cid = intent.getStringExtra("CID");
        if (cid.contains(".")) {
            cid = cid.substring(0, cid.indexOf("."));
        }
        className = intent.getStringExtra("NAME");
        classNum = intent.getStringExtra("CNUM");
        joinCode = intent.getStringExtra("JOINCLDE");

        tv_cds_classcode.setText(classNum);
        tv_cds_classname.setText(className);
        tv_cds_joinclasscode.setText(joinCode);

        tv_cds_attendancenum = findViewById(R.id.tv_cds_attendancenum);
        tv_cds_abnormalnum = findViewById(R.id.tv_cds_abnormalnum);
        tv_cds_absencenum = findViewById(R.id.tv_cds_absencenum);
        tv_tsb_back2=findViewById(R.id.tv_tsb_back2);


        recyclerView = findViewById(R.id.lv_cds_class);

        Map<String, String> head = new HashMap<>();
        head.put("Authorization", "Bearer " + Token.token);

        new ListSignInRecord<List<Map<String, Object>>>(head, null, null, tv_cds_attendancenum, tv_cds_abnormalnum, tv_cds_absencenum, recyclerView, ClassDetailStudentActivity.this)
                .gett().execute(cid);

        bt_cds_allstudent = findViewById(R.id.bt_cds_allstudent);
        bt_cds_allstudent.setOnClickListener(this);
        tv_cds_joinclasscode.setOnClickListener(this);
        tv_tsb_back2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_cds_allstudent) {
            Intent intent = new Intent(ClassDetailStudentActivity.this, MemberListActivity.class);
            intent.putExtra("CID", cid);
            startActivity(intent);
        }
        if (v.getId() == R.id.tv_cds_joinclasscode) {
            //生成二维码
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ClassDetailStudentActivity.this);
            //加课码浮窗
            classJoinCode = getLayoutInflater().inflate(R.layout.class_join_code, null);
            iv_class_join_code = classJoinCode.findViewById(R.id.iv_class_join_code);
            dialogBuilder.setView(classJoinCode);
            Bitmap map = createQRCode("class-" + joinCode, 159, 159, null);
            iv_class_join_code.setImageBitmap(map);
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();
        }
        if (v.getId() == R.id.tv_tsb_back2) {
                Intent intent = new Intent(this, StudentFragmentActivity.class);
                this.startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(this, StudentFragmentActivity.class);
            this.startActivity(intent);
            return false;
        }
        return false;
    }
}