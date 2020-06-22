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
import java.util.Map;

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
        holder.text_lsis_studentid.setText(signIn.getuNum());
        holder.text_lsis_name.setText(signIn.getName());
        holder.text_lsis_status.setText(signIn.getStatus());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_lsis_studentid;
        TextView text_lsis_name;
        TextView text_lsis_status;
        public ViewHolder(View itemView) {
            super(itemView);
            text_lsis_studentid = itemView.findViewById(R.id.text_lsis_studentid);
            text_lsis_name = itemView.findViewById(R.id.text_lsis_name);
            text_lsis_status = itemView.findViewById(R.id.text_lsis_status);
        }
    }
}