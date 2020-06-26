package com.fzn.classsign.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.fzn.classsign.R;
import com.fzn.classsign.adapter.SignInStatusListAdapter;
import com.fzn.classsign.asynctask.common.ListSignIn;
import com.fzn.classsign.asynctask.teacher.SignUpdate;
import com.fzn.classsign.entity.SignIn;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 签到记录列表
 */
public class SignInRecordListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;

    private SignInStatusListAdapter signAdapter;

    private Integer selectedSid;
    private Integer ssid;

    private View updateView;

    private TextView unum;
    private TextView name;
    private Spinner spinner;

    private Integer selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_record_list);

        initView();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initData();
    }

    public void initView() {
        recyclerView = findViewById(R.id.lv_cdt_class);
        signAdapter = new SignInStatusListAdapter(SignInRecordListActivity.this, R.layout.list_sign_in_status);
        LinearLayoutManager llm = new LinearLayoutManager(SignInRecordListActivity.this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(signAdapter);
        signAdapter.setOnItemClickListener(new SignInStatusListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, SignIn data) {
                selectedSid = data.getSid();
                unum.setText(data.getuNum());
                name.setText(data.getName());
                spinner.setSelection(getPositionOfStatus(data.getStatus()));
                AlertDialog.Builder dialog = new AlertDialog.Builder(SignInRecordListActivity.this);
                updateView = getLayoutInflater().inflate(R.layout.modify_sign_status, null);
                unum = updateView.findViewById(R.id.tv_unum_data);
                name = updateView.findViewById(R.id.tv_name_data);
                spinner = updateView.findViewById(R.id.s_status);
                dialog.setView(updateView);
                dialog.setPositiveButton("修改", updateClick);
                dialog.setNegativeButton("返回", backClick);
                dialog.create();
                dialog.show();
            }
        });
    }

    public void initData() {
        Intent intent = getIntent();
        ssid = Integer.parseInt(intent.getStringExtra("SSID"));

        Map<String, String> head = new HashMap<>();
        head.put("Authorization", "Bearer " + Token.token);
        new ListSignIn<List<SignIn>>(head, null, null, signAdapter)
                .gett()
                .execute(ssid);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_tsb_back4) {
            this.finish();
        }
    }

    private DialogInterface.OnClickListener updateClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            int position = spinner.getSelectedItemPosition();
            String status = null;
            switch (position) {
                case 0:
                    status = "normal";
                    break;
                case 1:
                    status = "abnormal";
                    break;
                case 2:
                    status = "absense";
                    break;
                default:
                    break;
            }
            Map<String, String> head = new HashMap<>();
            head.put("Authorization", "Bearer " + Token.token);
            Map<String, Object> params = new HashMap<>();
            params.put("sid", selectedSid.toString());
            params.put("ssid", ssid.toString());
            params.put("status", status);
            new SignUpdate<Boolean>(head, null, params, SignInRecordListActivity.this, signAdapter, selectedSid, status)
                    .post()
                    .execute();
            ((ViewGroup) updateView.getParent()).removeView(updateView);
        }
    };

    private DialogInterface.OnClickListener backClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ((ViewGroup) updateView.getParent()).removeView(updateView);
            dialog.dismiss();
        }
    };

    private int getPositionOfStatus(String status) {
        switch (status) {
            case "normal":
                return 0;
            case "abnormal":
                return 1;
            case "absense":
                return 2;
            default:
                return 0;
        }
    }
}