package com.fzn.classsign.asynctask.common;

import com.fzn.classsign.adapter.SignInListTeacherAdapter;
import com.fzn.classsign.adapter.SignInStatusListAdapter;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.SignIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class ListSignIn<T> extends CustomAsyncTask<T> {
    private SignInStatusListAdapter adapter;

    public ListSignIn(Map headers, Map body, Map params, SignInStatusListAdapter adapter) {
        super(headers, body, params, RequestConstant.URL.SIGN_LIST_SIGNIN);
        this.adapter = adapter;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, Object>>> temp = (ResponseResultJson<List<Map<String, Object>>>) getResponse(s);

        List<Map<String, Object>> data = temp.getData();
        List<SignIn> list = new ArrayList<>();
        for (Map<String, Object> d : data) {
            SignIn signIn = new SignIn();
            signIn.setSid((int)Double.parseDouble(d.get("sid").toString()));
            signIn.setSsid((int)Double.parseDouble(d.get("ssid").toString()));
            signIn.setUid((int)Double.parseDouble(d.get("uid").toString()));
            signIn.setuNum(d.get("unum").toString());
            signIn.setName(d.get("name").toString());
            signIn.setStatus(getPositionOfStatus(d.get("status").toString()));
            list.add(signIn);
        }


        if (list.size() != 0) {
            adapter.addData(list);
        }


        super.onPostExecute(s);
    }
    private String getPositionOfStatus(String status) {
        switch (status) {
            case "normal":
                return "出勤";
            case "abnormal":
                return "异常";
            case "absense":
                return "缺席";
            default:
                return "缺席";
        }
    }
}
