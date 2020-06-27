package com.fzn.classsign.asynctask.student;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.fragment.StudentFragmentActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class ClassExit<T> extends CustomAsyncTask<T> {
    private Context context;

    public ClassExit(Map headers, Map body, Map params,Context context){
        super(headers,body,params, RequestConstant.URL.STU_CLASS_EXIT);
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);
        int code = temp.getCode();
        if(code == 200){
            Toast.makeText(context,"退出班级成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, StudentFragmentActivity.class);
            context.startActivity(intent);
        }
        super.onPostExecute(s);
    }
}
