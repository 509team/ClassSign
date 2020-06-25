package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;
import com.fzn.classsign.adapter.ClassListAdapter;
import com.fzn.classsign.asynctask.teacher.ClassList;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassHomePageTeacherActivity extends AppCompatActivity implements View.OnClickListener{
    /*底部状态栏*/
    private ImageView iv_bsbt_class;
    private ImageView iv_bsbt_mine;
    private TextView tv_bsbt_class;
    private TextView tv_bsbt_mine;
    private ImageView ivCreate;
    /*创建课程图片*/
    private ImageView iv_chpt_creatclass;

    private List<Map<String, Object>> datalist;
    private ClassListAdapter classListAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_home_page_teacher);

        /*底部状态栏*/
        iv_bsbt_class = findViewById(R.id.iv_bsbt_class);
        iv_bsbt_mine = findViewById(R.id.iv_bsbt_mine);
        tv_bsbt_class = findViewById(R.id.tv_bsbt_class);
        tv_bsbt_mine = findViewById(R.id.tv_bsbt_mine);
        ivCreate=findViewById(R.id.iv_chpt_creatclass);

        iv_bsbt_class.setOnClickListener(this);
        iv_bsbt_mine.setOnClickListener(this);
        tv_bsbt_class.setOnClickListener(this);
        tv_bsbt_mine.setOnClickListener(this);
        ivCreate.setOnClickListener(this);
        /*创建课程图片*/
        iv_chpt_creatclass = findViewById(R.id.iv_chpt_creatclass);
        iv_chpt_creatclass.setOnClickListener(this);

        recyclerView = findViewById(R.id.lv_chpt_class);

        initData();

    }

    private void initData() {
//        Map<String, String> head = new HashMap<>();
//        head.put("Authorization","Bearer "+ Token.token);
//        new ClassList<List<Map<String, Object>>>(head,null,null,)
//                .gett()
//                .execute();
    }

    @Override
    public void onClick(View v) {
//        if(v.getId() ==  R.id.iv_bsbt_mine || v.getId() ==  R.id.tv_bsbt_mine){
//            Intent intent = new Intent(ClassHomePageTeacherActivity.this, MyInfoTeacherActivity.class);
//            startActivity(intent);
//        }
//        if(v.getId() ==  R.id.iv_chpt_creatclass){
//            Intent intent = new Intent(ClassHomePageTeacherActivity.this, CreateClassActivity.class);
//            startActivity(intent);
//        }
    }
}