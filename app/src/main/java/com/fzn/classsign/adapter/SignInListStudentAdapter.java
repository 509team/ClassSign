package com.fzn.classsign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fzn.classsign.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SignInListStudentAdapter extends RecyclerView.Adapter<SignInListStudentAdapter.ViewHolder> {
    private List<Map<String, Object>> mapList;
    private Context mContext;
    private int resourceId;

    private String signNameKey = "name";
    private String timeKey = "starttime";
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

        try {
            holder.tv_lsil_time.setText(StampToDate(map.get(timeKey).toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        holder.tv_lsil_ratioofpeople.setTextSize(30);
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

    @Override
    public int getItemCount() {
        return mapList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
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
                        onItemClickListener.OnItemClick(v, mapList.get(getLayoutPosition()));
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
        public void OnItemClick(View view, Map<String, Object> data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private SignInListStudentAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(SignInListStudentAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}