package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fzn.classsign.R;
import com.fzn.classsign.adapter.ClassListAdapter;
import com.fzn.classsign.adapter.MemberListAdapter;
import com.fzn.classsign.asynctask.common.ClassListStudent;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 成员列表
 */
public class MemberListActivity extends AppCompatActivity {

    private String cid;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        Intent intent = getIntent();
        cid = intent.getStringExtra("CID");

        recyclerView = findViewById(R.id.lv_ml_allstudent);

        Map<String, String> head = new HashMap<>();
        head.put("Authorization","Bearer "+ Token.token);
        new ClassListStudent<List<Map<String, Object>>>(head,null,null, recyclerView,MemberListActivity.this).gett().execute(cid);


    }
}