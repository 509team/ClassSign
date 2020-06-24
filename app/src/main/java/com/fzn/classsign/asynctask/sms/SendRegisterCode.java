package com.fzn.classsign.asynctask.sms;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fzn.classsign.activitys.GetVerificationCodeActivity;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class SendRegisterCode<T> extends CustomAsyncTask<T> {
    private Context context;
    private String phone;
    private int falg;  //记录当前类由谁调用，用于处理不同的回调处理；1、QuickLoginActivity   2、GetverificationCodeActivity

    public SendRegisterCode(Map headers, Map body, Map params, Context context, String phone, int falg) {
        super(headers, body, params, RequestConstant.URL.SEND_REGISTER_CODE);
        this.context = context;
        this.phone = phone;
    }

    public SendRegisterCode(Map headers, Map body, Map params, Context context, int falg) {
        super(headers, body, params, RequestConstant.URL.SEND_LOGIN_CODE);
        this.context = context;
        this.phone = phone;
        this.falg = falg;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Boolean> temp = (ResponseResultJson<Boolean>) getResponse(s);

        Boolean data = temp.getData();
        int code = temp.getCode();
        int statusCode = temp.getStatusCode();
        if (falg == 1) {//登录端获取验证码
            if (code == 200) {
                Intent intent = new Intent(context, GetVerificationCodeActivity.class);
                intent.putExtra("TYPE", 3);
                intent.putExtra("PHONE", phone);
                context.startActivity(intent);
            } else {
                if (statusCode == 1) {
                    Toast.makeText(context, "手机号已被注册", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "未知错误，请重新发送", Toast.LENGTH_SHORT).show();
                }
            }
        } else {//重新获取验证码
            if (code == 200) {
                Toast.makeText(context, "重新发送成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "未知错误，请重新发送", Toast.LENGTH_SHORT).show();
            }
        }

        super.onPostExecute(s);
    }
}
