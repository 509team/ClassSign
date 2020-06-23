package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.fzn.classsign.R;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

/**
 * 加入班级
 */
public class JoinClassActivity extends AppCompatActivity implements View.OnClickListener {

    /*六位码*/
    private EditText et_jc_sixcode;
    /*扫一扫*/
    private ImageView iv_jc_scan;
    /*加入班级按钮*/
    private Button bt_jc_joinclass;

    /*权限*/
    private final int REQUEST_CODE_SCAN = 0x0000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);

        /*六位码*/
        et_jc_sixcode = findViewById(R.id.et_jc_sixcode);
        et_jc_sixcode.setOnClickListener(this);
        /*扫一扫*/
        iv_jc_scan = findViewById(R.id.iv_jc_scan);
        iv_jc_scan.setOnClickListener(this);
        /*加入班级按钮*/
        bt_jc_joinclass = findViewById(R.id.bt_jc_joinclass);
        bt_jc_joinclass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==  R.id.iv_jc_scan){
            //动态申请相机权限、读取文件权限；
            if (ContextCompat.checkSelfPermission(JoinClassActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(JoinClassActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(JoinClassActivity.this, new String[]{Manifest.permission.CAMERA , Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }else {
                //扫码返回结果
                Intent intent = new Intent(JoinClassActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
                //TODO
                //判断二维码信息是否一致
            }
        }
        if(v.getId() ==  R.id.bt_jc_joinclass){
            //TODO
            //加入班级
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                et_jc_sixcode.setText("扫描结果为：" + content);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(JoinClassActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                }
                break;
            default:
                break;
        }
    }
}