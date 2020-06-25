package com.fzn.classsign.asynctask.common;

import com.fzn.classsign.adapter.SignInListTeacherAdapter;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.SignInStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class ListSignStatistics<T> extends CustomAsyncTask<T> {
    private SignInListTeacherAdapter adapter;

    public ListSignStatistics(Map headers, Map body, Map params, SignInListTeacherAdapter adapter) {
        super(headers, body, params, RequestConstant.URL.SIGN_LIST_SIGN_STATISTICS);
        this.adapter = adapter;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, Object>>> temp = (ResponseResultJson<List<Map<String, Object>>>) getResponse(s);

        List<Map<String, Object>> data = temp.getData();
        List<SignInStatistics> list = new ArrayList<>();
        for (Map<String, Object> d : data) {
            SignInStatistics statistics = new SignInStatistics();
            statistics.setSsid((int) Double.parseDouble(d.get("ssid").toString()));
            statistics.setCid((int) Double.parseDouble(d.get("cid").toString()));
            statistics.setName(d.get("name").toString());
            statistics.setStartTime((long) Double.parseDouble(d.get("startTime").toString()));
            statistics.setCheckInNum((int) Double.parseDouble(d.get("checkInNum").toString()));
            statistics.setTotal((int) Double.parseDouble(d.get("total").toString()));
            statistics.setStatus(getPositionOfStatus(d.get("status").toString()));
            list.add(statistics);
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
