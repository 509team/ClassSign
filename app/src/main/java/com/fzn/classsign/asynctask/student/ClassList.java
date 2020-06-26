package com.fzn.classsign.asynctask.student;

import com.fzn.classsign.adapter.ClassListAdapter;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;
import com.fzn.classsign.entity.Token;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 令牌刷新接口
 *
 * @param <T>
 */
public class ClassList<T> extends CustomAsyncTask<T> {

    private List<Map<String, Object>> datalist;
    private ClassListAdapter classListAdapter;

    public ClassList(Map headers, Map body, Map params, ClassListAdapter adapter) {
        super(headers, body, params, RequestConstant.URL.STU_CLASS_LIST);
        this.classListAdapter = adapter;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, Object>>> temp = (ResponseResultJson<List<Map<String, Object>>>) getResponse(s);
        int code = temp.getCode();
        if(code == 200){
            if(datalist!=null){
                datalist.clear();
            }
            datalist = temp.getData();

            if(datalist.size() != 0){
                classListAdapter.addData(datalist);
            }
        }else if (code == 401) {
            Token.FreshToken();
            Map<String, String> head = new HashMap<>();
            head.put("Authorization", "Bearer " + Token.token);
            new ClassList<List<Map<String, Object>>>(head, null, null,classListAdapter)
                    .gett()
                    .execute();
        }

        super.onPostExecute(s);
    }
}
