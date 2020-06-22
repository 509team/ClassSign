package com.fzn.classsign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fzn.classsign.R;

import java.util.List;
import java.util.Map;

public class SignInListStudentAdapter extends RecyclerView.Adapter<SignInListStudentAdapter.ViewHolder> {
    private List<Map<String, Object>> mapList;
    private Context mContext;
    private int resourceId;

    private String signNameKey = "name";
    private String timeKey = "startTime";
    private String statusKey = "status";

    private static String status1 = "出勤";
    private static String status2 = "异常";
    private static String status3 = "缺勤";

    public SignInListStudentAdapter(Context context, int resourceId, List<Map<String, Object>> data){
        this.mContext = context;
        this.mapList = data;
        this.resourceId = resourceId;
    }

    @Override
    public SignInListStudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        SignInListStudentAdapter.ViewHolder viewHolder = new SignInListStudentAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SignInListStudentAdapter.ViewHolder holder, int position) {
        Map<String, Object> map = mapList.get(position);
        holder.tv_lsil_signname.setText(map.get(signNameKey).toString());
        holder.tv_lsil_time.setText(map.get(timeKey).toString());
        String s = "";
        switch (map.get(statusKey).toString().trim()){
            case "normal":
                s=status1;
                break;
            case "abnormal":
                s=status2;
                break;
            case "absence":
                s=status3;
                break;
        }
        holder.tv_lsil_ratioofpeople.setText(s);
    }

    @Override
    public int getItemCount() {
        return mapList.size();
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