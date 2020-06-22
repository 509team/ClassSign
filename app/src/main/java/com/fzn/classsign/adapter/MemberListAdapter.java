package com.fzn.classsign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fzn.classsign.R;

import java.util.List;
import java.util.Map;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.ViewHolder> {
    private List<Map<String, Object>> mapList;
    private Context mContext;
    private int resourceId;

    private String  nameKey = "name";
    private String  studentIdKey = "uNum";

    public MemberListAdapter(Context context, int resourceId, List<Map<String, Object>> data){
        this.mContext = context;
        this.mapList = data;
        this.resourceId = resourceId;
    }

    @Override
    public MemberListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        MemberListAdapter.ViewHolder viewHolder = new MemberListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MemberListAdapter.ViewHolder holder, int position) {
        Map<String, Object> map = mapList.get(position);
        holder.iv_lml_image.setImageResource(R.drawable.ic_baseline_account_circle_24);
        holder.tv_lml_name.setText(map.get(nameKey).toString());
        holder.tv_lml_studentid.setText(map.get(studentIdKey).toString());
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }


    static  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_lml_image;
        TextView tv_lml_name;
        TextView tv_lml_studentid;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_lml_image = itemView.findViewById(R.id.iv_lml_image);
            tv_lml_name = itemView.findViewById(R.id.tv_lml_name);
            tv_lml_studentid = itemView.findViewById(R.id.tv_lml_studentid);
        }
    }
}