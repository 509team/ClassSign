package com.fzn.classsign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fzn.classsign.R;
import com.fzn.classsign.entity.SignInStatistics;

import java.util.List;

public class SignInListTeacherAdapter extends RecyclerView.Adapter<SignInListTeacherAdapter.ViewHolder> {
    private List<SignInStatistics> dataList;
    private Context mContext;
    private int resourceId;

    public SignInListTeacherAdapter(Context context, int resourceId, List<SignInStatistics> data){
        this.mContext = context;
        this.dataList = data;
        this.resourceId = resourceId;
    }

    @Override
    public SignInListTeacherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        SignInListTeacherAdapter.ViewHolder viewHolder = new SignInListTeacherAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SignInListTeacherAdapter.ViewHolder holder, int position) {
        SignInStatistics signInStatistics = dataList.get(position);
        holder.tv_lsil_signname.setText(signInStatistics.getName());
        holder.tv_lsil_time.setText(signInStatistics.getStartTime().toString());
        String proportion = signInStatistics.getCheckInNum().toString() + "/" + signInStatistics.getTotal().toString();
        holder.tv_lsil_ratioofpeople.setText(proportion);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_lsil_signname;
        TextView tv_lsil_time;
        TextView tv_lsil_ratioofpeople;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_lsil_signname = itemView.findViewById(R.id.tv_lsil_signname);
            tv_lsil_time = itemView.findViewById(R.id.tv_lsil_time);
            tv_lsil_ratioofpeople = itemView.findViewById(R.id.tv_lsil_ratioofpeople);
        }
    }
}