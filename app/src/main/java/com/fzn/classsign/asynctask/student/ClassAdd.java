package com.fzn.classsign.asynctask.student;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.ClassHomePageStudentActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class ClassAdd<T> extends CustomAsyncTask<T> {
    private Context context;

    public ClassAdd(Map headers, Map body, Map params, Context context) {
        super(headers, body, params, RequestConstant.URL.STU_CLASS_ADD);
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);
        int code = temp.getCode();
        if (code == 200) {
            Toast.makeText(context, "加入班级成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ClassHomePageStudentActivity.class);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "加入班级失败", Toast.LENGTH_SHORT).show();
        }
        Boolean data = temp.getData();

        super.onPostExecute(s);
    }
}
