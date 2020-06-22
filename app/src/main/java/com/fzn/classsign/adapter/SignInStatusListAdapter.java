package com.fzn.classsign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fzn.classsign.R;
import com.fzn.classsign.entity.SignIn;

import java.util.List;

public class SignInStatusListAdapter extends RecyclerView.Adapter<SignInStatusListAdapter.ViewHolder> {
    private List<SignIn> dataList;
    private Context mContext;
    private int resourceId;

    public SignInStatusListAdapter(Context context, int resourceId, List<SignIn> data){
        this.mContext = context;
        this.dataList = data;
        this.resourceId = resourceId;
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
        holder.tv_lsis_hiddenuid.setText(signIn.getUid());
        holder.tv_lsis_studentid.setText(signIn.getuNum());
        holder.tv_lsis_name.setText(signIn.getName());
        holder.tv_lsis_status.setText(signIn.getStatus());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_lsis_hiddenuid;
        TextView tv_lsis_studentid;
        TextView tv_lsis_name;
        TextView tv_lsis_status;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_lsis_hiddenuid = itemView.findViewById(R.id.tv_lsis_hiddenuid);
            tv_lsis_studentid = itemView.findViewById(R.id.tv_lsis_studentid);
            tv_lsis_name = itemView.findViewById(R.id.tv_lsis_name);
            tv_lsis_status = itemView.findViewById(R.id.tv_lsis_status);
        }
    }
}