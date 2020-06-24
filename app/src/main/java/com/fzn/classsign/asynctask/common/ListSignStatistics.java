package com.fzn.classsign.asynctask.common;

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
    public ListSignStatistics(Map headers, Map body, Map params) {
        super(headers, body, params, RequestConstant.URL.SIGN_LIST_SIGN_STATISTICS);

    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, String>>> temp = (ResponseResultJson<List<Map<String, String>>>) getResponse(s);

        List<Map<String, String>> data = temp.getData();
        List<SignInStatistics> list = new ArrayList<>();
        for (Map<String, String> d : data) {
            SignInStatistics statistics = new SignInStatistics();
            statistics.setSsid(Integer.parseInt(d.get("ssid")));
            statistics.setCid(Integer.parseInt(d.get("cid")));
            statistics.setName(d.get("name"));
            statistics.setStartTime(Long.parseLong(d.get("startTime")));
            statistics.setCheckInNum(Integer.parseInt(d.get("checkInNum")));
            statistics.setTotal(Integer.parseInt(d.get("total")));
            statistics.setStatus(d.get("status"));
            list.add(statistics);
        }

        super.onPostExecute(s);
    }
}
