package com.fzn.classsign.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fzn.classsign.R;
import com.fzn.classsign.entity.SignIn;
import com.fzn.classsign.entity.SignInStatistics;

import java.util.ArrayList;
import java.util.List;

public class SignInStatusListAdapter extends RecyclerView.Adapter<SignInStatusListAdapter.ViewHolder> {
    private List<SignIn> dataList;
    private Context mContext;
    private int resourceId;

    public SignInStatusListAdapter(Context context, int resourceId) {
        this.mContext = context;
        this.resourceId = resourceId;
        this.dataList = new ArrayList<>();
    }

    @Override
    public SignInStatusListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        SignInStatusListAdapter.ViewHolder viewHolder = new SignInStatusListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SignInStatusListAdapter.ViewHolder holder, int position) {
        SignIn signIn = dataList.get(position);
        holder.tv_lsis_studentid.setText(signIn.getuNum());
        holder.tv_lsis_name.setText(signIn.getName());
        holder.tv_lsis_status.setText(signIn.getStatus());
        switch (signIn.getStatus()){
            case "出勤":
                holder.tv_lsis_status.setTextColor(Color.parseColor("#27B148"));
                break;
            case "异常":
                holder.tv_lsis_status.setTextColor(Color.parseColor("#FCCA00"));
                break;
            case "缺席":
                holder.tv_lsis_status.setTextColor(Color.parseColor("#FF2525"));
                break;
            default:
                holder.tv_lsis_status.setTextColor(Color.parseColor("#FF2525"));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<SignIn> data) {
        this.dataList.clear();
        if (data != null) {
            this.dataList.addAll(data);
        }
        this.notifyDataSetChanged();
    }

    public void updateData(int sid, String status) {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getSid() == sid) {
                String str;
                switch (status) {
                    case "normal":
                        str="出勤";
                        break;
                    case "abnormal":
                        str="异常";
                        break;
                    case "absense":
                        str="缺席";
                        break;
                    default:
                        str="缺席";
                        break;
                }
                dataList.get(i).setStatus(str);
                break;
            }
        }
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_lsis_studentid;
        TextView tv_lsis_name;
        TextView tv_lsis_status;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_lsis_studentid = itemView.findViewById(R.id.tv_lsis_studentid);
            tv_lsis_name = itemView.findViewById(R.id.tv_lsis_name);
            tv_lsis_status = itemView.findViewById(R.id.tv_lsis_status);

            tv_lsis_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, dataList.get(getLayoutPosition()));
                    }
                }
            });
        }
    }


    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param data 点击的item的数据
         */
        public void OnItemClick(View view, SignIn data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private SignInStatusListAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(SignInStatusListAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}