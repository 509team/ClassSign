package com.fzn.classsign.asynctask.common;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.MyInfoStudentActivity;
import com.fzn.classsign.activitys.MyInfoTeacherActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class UpdateUserBaseInfo<T> extends CustomAsyncTask<T> {
    private Context context;
    private int type;

    public UpdateUserBaseInfo(Map headers, Map body, Map params, Context context, int type) {
        super(headers, body, params, RequestConstant.URL.UPDATE_USER_BASEINFO);
        this.context = context;
        this.type = type;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);

        int code=temp.getCode();
        if(code==200){
            Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
            if(type==1){
                Intent intent=new Intent(context, MyInfoTeacherActivity.class);
                context.startActivity(intent);
            }else{
                Intent intent=new Intent(context, MyInfoStudentActivity.class);
                context.startActivity(intent);
            }
       }else{
            Toast.makeText(context,"未知错误，修改失败",Toast.LENGTH_SHORT).show();
        }

        super.onPostExecute(s);
    }
}
