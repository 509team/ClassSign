package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;

public class ClassHomePageTeacherActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv_bsbt_class;
    private ImageView iv_bsbt_mine;
    private TextView tv_bsbt_class;
    private TextView tv_bsbt_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_home_page_teacher);

        iv_bsbt_class = findViewById(R.id.iv_bsbt_class);
        iv_bsbt_mine = findViewById(R.id.iv_bsbt_mine);
        tv_bsbt_class = findViewById(R.id.tv_bsbt_class);
        tv_bsbt_mine = findViewById(R.id.tv_bsbt_mine);

        iv_bsbt_class.setOnClickListener(this);
        iv_bsbt_mine.setOnClickListener(this);
        tv_bsbt_class.setOnClickListener(this);
        tv_bsbt_mine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==  R.id.iv_bsbt_class || v.getId() ==  R.id.tv_bsbt_class){
            Intent intent = new Intent(ClassHomePageTeacherActivity.this, ClassHomePageTeacherActivity.class);
            startActivity(intent);
        }
        if(v.getId() ==  R.id.iv_bsbt_mine || v.getId() ==  R.id.tv_bsbt_mine){
            Intent intent = new Intent(ClassHomePageTeacherActivity.this, MyInfoTeacherActivity.class);
            startActivity(intent);
        }
    }
}