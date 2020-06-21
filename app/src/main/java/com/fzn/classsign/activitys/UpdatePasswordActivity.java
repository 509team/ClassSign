package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.fzn.classsign.R;

/**
 * 修改密码
 */
public class UpdatePasswordActivity extends AppCompatActivity {
    TextView topTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        topTitle=findViewById(R.id.tv_tsb_title);
        topTitle.setText("修改密码");
    }
}