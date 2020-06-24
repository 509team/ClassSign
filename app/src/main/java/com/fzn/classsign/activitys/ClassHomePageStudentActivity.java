package com.fzn.classsign.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;
import com.fzn.classsign.adapter.ClassListAdapter;
import com.fzn.classsign.asynctask.student.ClassList;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课堂主页
 */
public class ClassHomePageStudentActivity extends AppCompatActivity implements View.OnClickListener{

    /*底部状态栏*/
    private ImageView iv_bsbs_signin;
    private ImageView iv_bsbs_class;
    private ImageView iv_bsbs_mine;
    private TextView tv_bsbs_sign;
    private TextView tv_bsbs_class;
    private TextView tv_bsbs_mine;
    /*加入课堂图片*/
    private ImageView ivInto;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_home_page_student);

        /*底部状态栏*/
        iv_bsbs_signin = findViewById(R.id.iv_bsbs_signin);
        iv_bsbs_class = findViewById(R.id.iv_bsbs_class);
        iv_bsbs_mine = findViewById(R.id.iv_bsbs_mine);
        tv_bsbs_sign = findViewById(R.id.tv_bsbs_sign);
        tv_bsbs_class = findViewById(R.id.tv_bsbs_class);
        tv_bsbs_mine = findViewById(R.id.tv_bsbs_mine);
        iv_bsbs_signin.setOnClickListener(this);
        iv_bsbs_class.setOnClickListener(this);
        iv_bsbs_mine.setOnClickListener(this);
        tv_bsbs_sign.setOnClickListener(this);
        tv_bsbs_class.setOnClickListener(this);
        tv_bsbs_mine.setOnClickListener(this);

        /*加入课堂图片*/
        ivInto = findViewById(R.id.iv_chps_joinclass);
        ivInto.setOnClickListener(this);

        recyclerView = findViewById(R.id.lv_chps_class);
        initData();
    }

    private void initData() {
        Map<String, String> head = new HashMap<>();
        head.put("Authorization","Bearer "+ Token.token);
        new ClassList<List<Map<String, Object>>>(head,null,null,recyclerView,ClassHomePageStudentActivity.this)
                .gett()
                .execute();
    }

    @Override
    public void onClick(View v) {
       if(v.getId() ==  R.id.iv_bsbs_signin || v.getId() ==  R.id.tv_bsbs_sign){
           Intent intent = new Intent(ClassHomePageStudentActivity.this, SignInActivity.class);
           startActivity(intent);
       }
       if(v.getId() == R.id.iv_bsbs_mine || v.getId() == R.id.tv_bsbs_mine){
           Intent intent = new Intent(ClassHomePageStudentActivity.this, MyInfoStudentActivity.class);
           startActivity(intent);
        }
        if(v.getId() == R.id.iv_chps_joinclass){
            Intent intent = new Intent(ClassHomePageStudentActivity.this, JoinClassActivity.class);
            startActivity(intent);
        }
    }
}