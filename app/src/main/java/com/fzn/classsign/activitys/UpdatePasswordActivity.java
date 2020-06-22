package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.R;

/**
 * 修改密码
 */
public class UpdatePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView topTitle;

    private EditText etOldPwd;
    private EditText etNewPwd;
    private EditText etAgainPed;
    private ImageView ivSee;
    private Button bUpdate;

    private boolean isSee = false;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        topTitle = findViewById(R.id.tv_tsb_title);
        topTitle.setText("修改密码");

        Intent intent=getIntent();
        type=intent.getIntExtra("TYPE",0);
        etOldPwd = findViewById(R.id.et_up_old_pwd);
        etNewPwd = findViewById(R.id.et_up_new_pwd);
        etAgainPed = findViewById(R.id.et_up_again);
        ivSee = findViewById(R.id.iv_up_see);
        bUpdate = findViewById(R.id.b_up_update);

        bUpdate.setOnClickListener(this);
        bUpdate.setTag(1);
        ivSee.setOnClickListener(this);
        ivSee.setTag(2);

    }

    @Override
    public void onClick(View v) {
        switch ((int) v.getTag()) {
            case 1:
                String oldPwd = etOldPwd.getText().toString();
                String newPwd = etNewPwd.getText().toString();
                String againPwd = etAgainPed.getText().toString();
                System.out.println("old:"+oldPwd);
                System.out.println("new:"+newPwd);
                System.out.println("again"+againPwd);
                if (oldPwd.equals("")) {
                    Toast.makeText(UpdatePasswordActivity.this, "请输入原密码", Toast.LENGTH_SHORT).show();
                } else {
                    if (newPwd.equals("") || againPwd.equals("")) {
                        Toast.makeText(UpdatePasswordActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    } else if (newPwd.equals(againPwd)) {
                        //调用接口修改密码


                        Toast.makeText(UpdatePasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        if (type==1){
                            Intent intent2=new Intent(UpdatePasswordActivity.this,MyInfoTeacherActivity.class);
                            startActivity(intent2);
                        }else if(type==2){
                            Intent intent2=new Intent(UpdatePasswordActivity.this,MyInfoStudentActivity.class);
                            startActivity(intent2);
                        }

                    }else{
                        Toast.makeText(UpdatePasswordActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT);
                    }
                }


                break;
            case 2:
                isSee = !isSee;
                if (isSee) {
                    etOldPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    etNewPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    etAgainPed.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ivSee.setImageResource(R.drawable.ic_baseline_visibility_off_24);

                } else {
                    etOldPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    etNewPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    etAgainPed.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    ivSee.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
                break;
        }
    }
}