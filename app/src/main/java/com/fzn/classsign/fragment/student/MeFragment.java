package com.fzn.classsign.fragment.student;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzn.classsign.R;
import com.fzn.classsign.activitys.LoginSelectionActivity;
import com.fzn.classsign.activitys.UpdateInfoActivity;
import com.fzn.classsign.activitys.UpdatePasswordActivity;
import com.fzn.classsign.asynctask.common.GetUserBaseInfo;
import com.fzn.classsign.entity.Token;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class MeFragment extends Fragment implements View.OnClickListener{
    private View mView;
    private Context mContext;

    private TextView tvUpdate;
    private ImageView ivUpdate;
    private Button bUpdate;
    private Button bEnd;

    //记录个人信息
    private TextView tvName;
    private TextView tvSex;
    private TextView tvPhone;
    private TextView tvNumber;


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MeFragment newInstance(int columnCount) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_stu_me, container, false);
        mContext = getContext();
        tvUpdate=mView.findViewById(R.id.tv_mis_resetpassword);
        ivUpdate=mView.findViewById(R.id.iv_mis_resetpassword);
        bUpdate=mView.findViewById(R.id.bt_mis_updateinfo);
        bEnd=mView.findViewById(R.id.bt_mis_logout);

        tvName=mView.findViewById(R.id.tv_mis_name);
        tvSex=mView.findViewById(R.id.tv_mis_sex);
        tvPhone=mView.findViewById(R.id.tv_mis_phone);
        tvNumber=mView.findViewById(R.id.tv_mis_studentid);



        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //调用接口获取个人信息，填充显示个人信息
        Map<String,String> heads=new HashMap<>();
        heads.put("Authorization","Bearer "+ Token.token);
        new GetUserBaseInfo<Map<String,String>>(heads,null,null,tvName,tvSex,tvPhone,tvNumber)
                .gett()
                .execute();

        tvUpdate.setOnClickListener(this);
        ivUpdate.setOnClickListener(this);
        bUpdate.setOnClickListener(this);
        bEnd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_mis_resetpassword || v.getId() == R.id.iv_mis_resetpassword){
            Intent intent = new Intent(mContext, UpdatePasswordActivity.class);
            intent.putExtra("TYPE",2);
            startActivity(intent);
        }
        if(v.getId() == R.id.bt_mis_updateinfo){
            Intent intent = new Intent(mContext, UpdateInfoActivity.class);
            intent.putExtra("NAME",tvName.getHint().toString());
            intent.putExtra("SEX",tvSex.getHint().toString());
            intent.putExtra("NUMBER",tvNumber.getHint().toString());
            intent.putExtra("TYPE",2);
            startActivity(intent);
        }
        if(v.getId() == R.id.bt_mis_logout){
            AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
            dialog.setMessage("是否退出登录");
            dialog.setPositiveButton("是",yesClick);
            dialog.setNegativeButton("否",noClick);
            dialog.create();
            dialog.show();
        }
    }

    private DialogInterface.OnClickListener yesClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String spFileName = mContext.getResources().getString(R.string.shared_preferences_file_name);
            String refreshTokenKey = mContext.getResources().getString(R.string.refresh_token);
            SharedPreferences spFile = mContext.getSharedPreferences(spFileName,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = spFile.edit();
            editor.remove(refreshTokenKey);
            editor.apply();
            Intent intent = new Intent(mContext, LoginSelectionActivity.class);
            startActivity(intent);
        }
    };

    private DialogInterface.OnClickListener noClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };
}