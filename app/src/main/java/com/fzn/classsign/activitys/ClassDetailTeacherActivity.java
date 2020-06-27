package com.fzn.classsign.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.R;
import com.fzn.classsign.activitys.fragment.TeacherFragmentActivity;
import com.fzn.classsign.adapter.SignInListTeacherAdapter;
import com.fzn.classsign.asynctask.common.ListSignStatistics;
import com.fzn.classsign.entity.SignInStatistics;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yzq.zxinglibrary.encode.CodeCreator.createQRCode;

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


    private RecyclerView recyclerView;

    private SignInListTeacherAdapter signAdapter;


    private View classJoinCode;
    private ImageView iv_class_join_code;
    private ImageView tv_tsb_back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail_teacher);


        initView();
        initData();
    }

    public void initView() {
        /*课程名称、课号、加入码的textview*/
        tv_cdt_classname = findViewById(R.id.tv_cdt_classname);
        tv_cdt_classcode = findViewById(R.id.tv_cdt_classcode);
        tv_cdt_joinclasscode = findViewById(R.id.tv_cdt_joinclasscode);
        tv_tsb_back2 = findViewById(R.id.tv_tsb_back2);
        recyclerView = findViewById(R.id.lv_cdt_class);
        signAdapter = new SignInListTeacherAdapter(ClassDetailTeacherActivity.this, R.layout.list_sign_in_list);
        LinearLayoutManager llm = new LinearLayoutManager(ClassDetailTeacherActivity.this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(signAdapter);
        signAdapter.setOnItemClickListener(new SignInListTeacherAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, SignInStatistics data) {
                Intent intent = new Intent(ClassDetailTeacherActivity.this, SignInRecordListActivity.class);
                intent.putExtra("SSID", data.getSsid().toString());
                startActivity(intent);
            }
        });


    }

    public void initData() {
        Intent intent = getIntent();
        cid = intent.getStringExtra("CID");
        cid = cid.substring(0, cid.indexOf("."));
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
        tv_cdt_joinclasscode.setOnClickListener(this);
        tv_tsb_back2.setOnClickListener(this);

        Map<String, String> head = new HashMap<>();
        head.put("Authorization", "Bearer " + Token.token);
        new ListSignStatistics<List<SignInStatistics>>(head, null, null, signAdapter)
                .gett().execute(cid);
    }

    @Override
    public void onClick(View v) {
        System.out.println(v.getId() + "---" + R.id.tv_tsb_back2);
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
        if (v.getId() == R.id.tv_cdt_joinclasscode) {
            //生成二维码
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ClassDetailTeacherActivity.this);
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
            Intent intent = new Intent(this, TeacherFragmentActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(this, TeacherFragmentActivity.class);
            this.startActivity(intent);
            return false;
        }
        return false;
    }
}