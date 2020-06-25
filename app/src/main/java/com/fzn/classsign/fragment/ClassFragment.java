package com.fzn.classsign.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.R;
import com.fzn.classsign.activitys.ClassHomePageStudentActivity;
import com.fzn.classsign.activitys.JoinClassActivity;
import com.fzn.classsign.activitys.MyInfoStudentActivity;
import com.fzn.classsign.activitys.SignInActivity;
import com.fzn.classsign.adapter.ClassListAdapter;
import com.fzn.classsign.asynctask.student.ClassList;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A fragment representing a list of Items.
 */
public class ClassFragment extends Fragment implements View.OnClickListener {


    private View mView;
    private Context mContext;

    /*加入课堂图片*/
    private ImageView ivInto;
    private RecyclerView recyclerView;

    private ClassListAdapter classListAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ClassFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ClassFragment newInstance() {
        ClassFragment fragment = new ClassFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_class_list, container, false);
        mContext = getContext();
        /*加入课堂图片*/
        ivInto = mView.findViewById(R.id.iv_chps_joinclass);
        recyclerView = mView.findViewById(R.id.lv_chps_class);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ivInto.setOnClickListener(this);
        initView();
        initData();
    }

    private void initView() {
        classListAdapter = new ClassListAdapter(mContext, R.layout.list_class_student);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(classListAdapter);
        classListAdapter.setOnItemClickListener(new ClassListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Map<String, Object> data) {
                Toast.makeText(getActivity(), data.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        Map<String, String> head = new HashMap<>();
        head.put("Authorization", "Bearer " + Token.token);
        new ClassList<List<Map<String, Object>>>(head, null, null, classListAdapter)
                .gett()
                .execute();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_bsbs_signin || v.getId() == R.id.tv_bsbs_sign) {
            Intent intent = new Intent(mContext, SignInActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.iv_bsbs_mine || v.getId() == R.id.tv_bsbs_mine) {
            Intent intent = new Intent(mContext, MyInfoStudentActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.iv_chps_joinclass) {
            Intent intent = new Intent(mContext, JoinClassActivity.class);
            startActivity(intent);
        }
    }
}