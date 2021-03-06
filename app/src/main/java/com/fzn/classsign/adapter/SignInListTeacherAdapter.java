package com.fzn.classsign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fzn.classsign.R;
import com.fzn.classsign.entity.SignInStatistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SignInListTeacherAdapter extends RecyclerView.Adapter<SignInListTeacherAdapter.ViewHolder> {
    private List<SignInStatistics> dataList;
    private Context mContext;
    private int resourceId;

    public SignInListTeacherAdapter(Context context, int resourceId) {
        this.mContext = context;
        this.resourceId = resourceId;
        dataList = new ArrayList<>();
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
        try {
            holder.tv_lsil_time.setText(StampToDate(signInStatistics.getStartTime().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String proportion = signInStatistics.getCheckInNum().toString() + "/" + signInStatistics.getTotal().toString();
        holder.tv_lsil_ratioofpeople.setText(proportion);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<SignInStatistics> data) {
        if (data != null) {
            this.dataList.addAll(data);
        }
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_lsil_signname;
        TextView tv_lsil_time;
        TextView tv_lsil_ratioofpeople;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_lsil_signname = itemView.findViewById(R.id.tv_lsil_signname);
            tv_lsil_time = itemView.findViewById(R.id.tv_lsil_time);
            tv_lsil_ratioofpeople = itemView.findViewById(R.id.tv_lsil_ratioofpeople);

            itemView.setOnClickListener(new View.OnClickListener() {
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
        public void OnItemClick(View view, SignInStatistics data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private SignInListTeacherAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(SignInListTeacherAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String StampToDate(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.valueOf(s));
        return simpleDateFormat.format(date).toString();
    }
}