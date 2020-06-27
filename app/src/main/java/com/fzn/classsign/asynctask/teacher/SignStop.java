package com.fzn.classsign.asynctask.teacher;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.ClassDetailTeacherActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.asynctask.common.ClassGet;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.Token;
import com.fzn.classsign.fragment.teacher.ClassFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class SignStop<T> extends CustomAsyncTask<T> {
    private Context context;
    private int  cid;

    public SignStop(Map headers, Map body, Map params,String cid, Context context) {
        super(headers, body, params, RequestConstant.URL.TEACHER_SIGN_STOP);
        this.context = context;
        this.cid = Integer.parseInt(cid);
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);
        int code = temp.getCode();
        if (code == 200) {
            Toast.makeText(context, "签到结束", Toast.LENGTH_SHORT).show();
            Map<String,String> heads=new HashMap<>();
            heads.put("Authorization","Bearer "+ Token.token);
            new ClassGet<Map<String, Object>>(heads,null,null,context)
                    .gett()
                    .execute(cid);
        } else {
            Toast.makeText(context, "结束签到失败", Toast.LENGTH_SHORT).show();
        }

        super.onPostExecute(s);
    }
}
