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

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {
    private List<Map<String, Object>> mapList;
    private Context mContext;
    private int resourceId;

    private int text;

    private String  classNameKey = "name";
    private String  classCodeKey = "cNum";
    private String  joinClassCodeKey = "joinCode";
    private String  memberNumKey = "total";

    public ClassListAdapter(Context context, int resourceId, List<Map<String, Object>> data){
        this.mContext = context;
        this.mapList = data;
        this.resourceId = resourceId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, Object> map = mapList.get(position);
        holder.tv_lcs_classname.setText(map.get(classNameKey).toString());
        holder.tv_lcs_classcode.setText(map.get(classCodeKey).toString());
        holder.tv_lcs_joinclasscode.setText(map.get(joinClassCodeKey).toString());
        holder.tv_lcs_membernum.setText(map.get(memberNumKey).toString());
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_lcs_classname;
        TextView tv_lcs_classcode;
        TextView tv_lcs_joinclasscode;
        TextView tv_lcs_membernum;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_lcs_classname = itemView.findViewById(R.id.tv_lcs_classname);
            tv_lcs_classcode = itemView.findViewById(R.id.tv_lcs_classcode);
            tv_lcs_joinclasscode = itemView.findViewById(R.id.tv_lcs_joinclasscode);
            tv_lcs_membernum = itemView.findViewById(R.id.tv_lcs_membernum);
        }
    }
}
