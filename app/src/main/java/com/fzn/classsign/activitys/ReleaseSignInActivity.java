package com.fzn.classsign.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.R;
import com.fzn.classsign.activitys.fragment.TeacherFragmentActivity;
import com.fzn.classsign.asynctask.teacher.SignGetCurrentSignTotal;
import com.fzn.classsign.asynctask.teacher.SignStop;
import com.fzn.classsign.entity.Token;
import com.fzn.classsign.fragment.teacher.ClassFragment;

import java.util.HashMap;
import java.util.Map;

import static com.yzq.zxinglibrary.encode.CodeCreator.createQRCode;

/**
 * 发布签到
 */
public class ReleaseSignInActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvCode;
    private ImageView ivCode;
    private TextView tvTime;
    private TextView tvNumber;
    private Button bEnd;

    private String cid;
    private String total;
    private int seconds = 16;
    private String code;

    private boolean stopFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_sign_in);

        tvTitle = findViewById(R.id.tv_tsb_title);
        tvTitle.setText("签到");

        Intent intent = getIntent();

        cid = intent.getStringExtra("CID");
        total = intent.getStringExtra("TOTAL");
        seconds = Integer.parseInt(intent.getStringExtra("TIME"));
        code = intent.getStringExtra("CODE");

        //签到人数显示框
        tvNumber = findViewById(R.id.tv_rs_people);

        //计时器
        CountDownTimer timer = new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minute = (int) millisUntilFinished / 60000;
                int second = (int) millisUntilFinished / 1000 % 60;
                tvTime.setText(minute + ":" + second);
                //获取当前签到人数
                getCurrentSignTotal();
            }

            @Override
            public void onFinish() {
                //点击结束签到按钮，计时器关闭
                if(stopFlag == true){
                    this.cancel();
                }else{
                    Map<String, String> head = new HashMap<>();
                    head.put("Authorization", "Bearer " + Token.token);
                    Map<String, Object> params = new HashMap<>();
                    params.put("signCode", code);
                    new SignStop<Boolean>(head, null, params, cid,ReleaseSignInActivity.this)
                            .post().execute();
                }
            }
        };

        //开启计时器
        tvTime = findViewById(R.id.tv_rs_time);
        timer.start();
        //六位码
        tvCode = findViewById(R.id.tv_rs_code);
        tvCode.setText(code);

        ivCode = findViewById(R.id.iv_rs_code);
        //生成二维码
        Bitmap map = createQRCode("sign-" + code, 159, 159, null);
        ivCode.setImageBitmap(map);

        //结束签到按钮
        bEnd = findViewById(R.id.b_rs_end);
        bEnd.setOnClickListener(this);
    }

    //获取当前签到人数
    private void getCurrentSignTotal() {
        Map<String, String> head = new HashMap<>();
        head.put("Authorization", "Bearer " + Token.token);
        Map<String, Object> params = new HashMap<>();
        params.put("signCode", code);
        new SignGetCurrentSignTotal<Object>(head, null, params, tvNumber, total)
                .post()
                .execute();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b_rs_end) {
            stopFlag = true;
            Map<String, String> head = new HashMap<>();
            head.put("Authorization", "Bearer " + Token.token);
            Map<String, Object> params = new HashMap<>();
            params.put("signCode", code);
            new SignStop<Boolean>(head, null, params, cid,ReleaseSignInActivity.this)
                    .post().execute();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ReleaseSignInActivity.this);
            dialog.setMessage("是否结束签到？");
            dialog.setPositiveButton("是",yesClick);
            dialog.setNegativeButton("否",noClick);
            dialog.create();
            dialog.show();
        }
        return false;
    }

    private DialogInterface.OnClickListener yesClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            stopFlag = true;
            Map<String, String> head = new HashMap<>();
            head.put("Authorization", "Bearer " + Token.token);
            Map<String, Object> params = new HashMap<>();
            params.put("signCode", code);
            new SignStop<Boolean>(head, null, params, cid,ReleaseSignInActivity.this)
                    .post().execute();
        }
    };

    private DialogInterface.OnClickListener noClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };
}