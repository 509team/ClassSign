package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;

/**
 * 签到成功
 */
public class SignInResultActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_sir_signresultimage;
    private TextView tv_sir_signresulttext;
    private Button bt_sir_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_result);

        iv_sir_signresultimage = findViewById(R.id.iv_sir_signresultimage);
        tv_sir_signresulttext = findViewById(R.id.tv_sir_signresulttext);
        bt_sir_return = findViewById(R.id.bt_sir_return);
        bt_sir_return.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.getStringExtra("RESULT").equals("SUCCESS")) {
            iv_sir_signresultimage.setImageResource(R.drawable.sign_success);
            tv_sir_signresulttext.setText("签到成功");
        } else if (intent.getStringExtra("RESULT").equals("ABNORMAL")) {
            iv_sir_signresultimage.setImageResource(R.drawable.sign_failed);
            tv_sir_signresulttext.setText("签到异常");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_sir_return) {
            Intent intent = new Intent(SignInResultActivity.this, SignInActivity.class);
            startActivity(intent);
        }
    }
}