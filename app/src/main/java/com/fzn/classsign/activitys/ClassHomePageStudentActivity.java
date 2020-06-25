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

    /*加入课堂图片*/
    private ImageView ivInto;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_home_page_student);

        /*加入课堂图片*/
        ivInto = findViewById(R.id.iv_chps_joinclass);
        ivInto.setOnClickListener(this);

        recyclerView = findViewById(R.id.lv_chps_class);
        initData();
    }

    private void initData() {
        Map<String, String> head = new HashMap<>();
        head.put("Authorization","Bearer "+ Token.token);
        new ClassList<List<Map<String, Object>>>(head,null,null,null)
                .gett()
                .execute();
    }

    @Override
    public void onClick(View v) {

    }
}