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

import java.util.List;
import java.util.Map;

public class ClassHomePageTeacherActivity extends AppCompatActivity implements View.OnClickListener {
    /*创建课程图片*/
    private ImageView iv_chpt_creatclass;

    private List<Map<String, Object>> datalist;
    private ClassListAdapter classListAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_home_page_teacher);

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
    }
}