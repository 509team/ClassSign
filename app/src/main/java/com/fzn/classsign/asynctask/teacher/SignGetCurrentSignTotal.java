package com.fzn.classsign.asynctask.teacher;

import android.widget.TextView;

import com.fzn.classsign.R;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class SignGetCurrentSignTotal<T> extends CustomAsyncTask<T> {

    private TextView tvNumber;
    private String total;

    public SignGetCurrentSignTotal(Map headers, Map body, Map params, TextView tvNumber, String total) {
        super(headers, body, params, RequestConstant.URL.TEACHER_SIGN_GET_CURRENT_SIGN_TOTAL);
        this.tvNumber = tvNumber.findViewById(R.id.tv_rs_people);
        if (total.contains(".")) {
            this.total = total.substring(0, total.indexOf("."));
        } else {
            this.total = total;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<Object> temp = (ResponseResultJson<Object>) getResponse(s);
        int code = temp.getCode();
        if (code == 200) {
            String data = temp.getData().toString();
            if (data.contains(".")) {
                data = data.substring(0, data.indexOf("."));
            }
            String text = "签到人数 " + data + "/" + total + "  人     ";
            tvNumber.setText(text);
        }

        super.onPostExecute(s);
    }
}
