package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

/**
 * 学生签到
 */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    /*底部状态栏*/
    private ImageView iv_bsbs_signin;
    private ImageView iv_bsbs_class;
    private ImageView iv_bsbs_mine;
    private TextView tv_bsbs_sign;
    private TextView tv_bsbs_class;
    private TextView tv_bsbs_mine;
    /*扫一扫图片*/
    private ImageView iv_si_scan;
    /*六位码*/
    private EditText et_si_sixcode;

    /*权限*/
    private final int REQUEST_CODE_SCAN = 0x0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

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
        /*扫一扫图片*/
        iv_si_scan = findViewById(R.id.iv_si_scan);
        iv_si_scan.setOnClickListener(this);

        et_si_sixcode = findViewById(R.id.et_si_sixcode);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==  R.id.iv_bsbs_signin || v.getId() ==  R.id.tv_bsbs_sign){
            Intent intent = new Intent(SignInActivity.this, SignInActivity.class);
            startActivity(intent);
        }
        if(v.getId() ==  R.id.iv_bsbs_class || v.getId() ==  R.id.tv_bsbs_class){
            Intent intent = new Intent(SignInActivity.this, ClassHomePageStudentActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.iv_bsbs_mine || v.getId() == R.id.tv_bsbs_mine){
            Intent intent = new Intent(SignInActivity.this, MyInfoStudentActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.iv_si_scan){
            //动态申请相机权限、读取文件权限；
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA , Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }else {
                //扫码返回结果
                Intent intent = new Intent(SignInActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                et_si_sixcode.setText("扫描结果为：" + content);
            }
        }
    }
}