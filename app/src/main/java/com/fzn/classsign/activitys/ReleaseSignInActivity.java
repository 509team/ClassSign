package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.R;

import static com.yzq.zxinglibrary.encode.CodeCreator.createQRCode;

/**
 * 发布签到
 */
public class ReleaseSignInActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvCode;
    private ImageView ivCode;
    private TextView tvTime;
    private TextView tvNumber;
    private Button bEnd;

    private int timeLong=2; //记录倒计时长
    //计时器
    CountDownTimer timer=new CountDownTimer(timeLong*60000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int minute=(int)millisUntilFinished/60000;
            int second=(int)millisUntilFinished/1000%60;
            tvTime.setText(minute+":"+second);
        }

        @Override
        public void onFinish() {
            Toast.makeText(ReleaseSignInActivity.this,"签到结束",Toast.LENGTH_SHORT).show();
        }
    };

    private String code="FF12ZZ";//存储上一页面传过来的六位签到码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_sign_in);
        tvTitle=findViewById(R.id.tv_tsb_title);
        tvTitle.setText("签到");
        //开启计时器
        tvTime=findViewById(R.id.tv_rs_time);
        timer.start();

        ivCode=findViewById(R.id.iv_rs_code);
        //生成二维码
        Bitmap map=createQRCode(code,159,159,null);
        ivCode.setImageBitmap(map);


    }
}