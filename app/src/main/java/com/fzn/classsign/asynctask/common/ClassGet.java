package com.fzn.classsign.asynctask.common;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.activitys.ClassDetailTeacherActivity;
import com.fzn.classsign.activitys.fragment.TeacherFragmentActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.Class;

import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class ClassGet<T> extends CustomAsyncTask<T> {

    private Context context;

    public ClassGet(Map headers, Map body, Map params,Context context) {
        super(headers, body, params, RequestConstant.URL.CLASS_GET);
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, Object>> temp = (ResponseResultJson<Map<String, Object>>) getResponse(s);
        int code = temp.getCode();
        if(code == 200){
            Map<String, Object> data = temp.getData();

            Intent intent = new Intent(context, ClassDetailTeacherActivity.class);
            intent.putExtra("CID",data.get("cid").toString());
            intent.putExtra("CNUM",data.get("cnum").toString());
            intent.putExtra("NAME",data.get("name").toString());
            intent.putExtra("JOINCODE",data.get("joinCode").toString());
            intent.putExtra("TOTAL",data.get("total").toString());
            context.startActivity(intent);
        }
        else {
            Toast.makeText(context,"获取班级信息出错!返回主界面",Toast.LENGTH_SHORT);
            Intent intent = new Intent(context, TeacherFragmentActivity.class);
            intent.putExtra("POSITION",0);
            context.startActivity(intent);
        }
        super.onPostExecute(s);
    }
}
