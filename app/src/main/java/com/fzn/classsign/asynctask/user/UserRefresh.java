package com.fzn.classsign.asynctask.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.fzn.classsign.R;
import com.fzn.classsign.activitys.LoginSelectionActivity;
import com.fzn.classsign.activitys.QuickLoginActivity;
import com.fzn.classsign.activitys.fragment.StudentFragmentActivity;
import com.fzn.classsign.activitys.fragment.TeacherFragmentActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.Token;
import com.fzn.classsign.fragment.teacher.ClassFragment;

import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class UserRefresh<T> extends CustomAsyncTask<T> {
    private Context context;
    private int type;
    private int jumpTag;

    public UserRefresh(Map headers, Map body, Map params,Context context,int type,int jumpTag){
        super(headers,body,params, RequestConstant.URL.REFRESH);
        this.context = context;
        this.type = type;
        this.jumpTag = jumpTag;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Map<String, Object>> temp = (ResponseResultJson<Map<String, Object>>) getResponse(s);
        int code = temp.getCode();
        if(code == 200){
            Map<String,Object> map = temp.getData();
            Token.token = map.get("access_token").toString();
            Token.refreshToken = (String) map.get("refresh_token").toString();
            storeRefreshToken();
            //登录调用，跳转到主页
            if(type == 1){
                //教师端
                if(jumpTag == 1){
                    Intent intent = new Intent(context, TeacherFragmentActivity.class);
                    context.startActivity(intent);
                }else if(jumpTag == 2){
                    Intent intent = new Intent(context, StudentFragmentActivity.class);
                    context.startActivity(intent);
                }
            }
        }else {
            //跳转到快速登录
            if(type == 1){
                Intent intent=new Intent(context, QuickLoginActivity.class);
                intent.putExtra("TYPE",jumpTag);
                context.startActivity(intent);
            }
        }

        super.onPostExecute(s);
    }

    //存储refreshToken
    private void storeRefreshToken() {
        String spFileName = context.getResources().getString(R.string.shared_preferences_file_name);
        String refreshTokenKey = context.getResources().getString(R.string.refresh_token);

        SharedPreferences spFile = context.getSharedPreferences(spFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spFile.edit();
        editor.putString(refreshTokenKey,Token.refreshToken);
        editor.apply();
    }
}
