package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    private String code="FF12ZZ";//存储上一页面传过来的六位签到码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_sign_in);

        ivCode=findViewById(R.id.iv_rs_code);
        //生成二维码
        Bitmap map=createQRCode(code,159,159,null);
        ivCode.setImageBitmap(map);
    }
}