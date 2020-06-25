package com.fzn.classsign.asynctask.teacher;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.ClassDetailTeacherActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.Token;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class ClassCreate<T> extends CustomAsyncTask<T> {
    private Context context;
    private Map<String, String> params;

    public ClassCreate(Map headers, Map body, Map params, Context context){
        super(headers,body,params, RequestConstant.URL.TEACHER_CLASS_CREATE);
        this.context = context;
        this.params = params;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, Object>> temp = (ResponseResultJson<Map<String, Object>>) getResponse(s);
        int code = temp.getCode();
        if(code == 200){
            Map<String, Object> data = temp.getData();

            Toast.makeText(context,"创建成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context, ClassDetailTeacherActivity.class);
            intent.putExtra("CID",data.get("cid").toString());
            intent.putExtra("CNUM",data.get("cnum").toString());
            intent.putExtra("NAME",data.get("name").toString());
            intent.putExtra("JOINCODE",data.get("joinCode").toString());
            context.startActivity(intent);
        }else if(code == 401){
            Token.FreshToken();
            Toast.makeText(context,"请求超时，请重试！",Toast.LENGTH_SHORT);
        }else {
            Toast.makeText(context,temp.getAddtionalInfo(),Toast.LENGTH_SHORT);
        }
        super.onPostExecute(s);
    }
}
