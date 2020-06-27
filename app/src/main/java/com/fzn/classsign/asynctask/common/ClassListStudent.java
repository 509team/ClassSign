package com.fzn.classsign.asynctask.common;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fzn.classsign.R;
import com.fzn.classsign.adapter.MemberListAdapter;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.List;
import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class ClassListStudent<T> extends CustomAsyncTask<T> {
    private Context context;
    private List<Map<String, Object>> datalist;
    private MemberListAdapter memberListAdapter;
    private RecyclerView recyclerView;

    public ClassListStudent(Map headers, Map body, Map params, RecyclerView recyclerView,Context context){
        super(headers,body,params, RequestConstant.URL.CLASS_LISTSTU);
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, Object>>> temp = (ResponseResultJson<List<Map<String, Object>>>) getResponse(s);
        int code = temp.getCode();
        if(code == 200) {
            datalist = temp.getData();
            if (datalist.size() != 0) {
                memberListAdapter = new MemberListAdapter(context, R.layout.list_member_list, datalist);
                LinearLayoutManager llm = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(memberListAdapter);
            }
        }
        super.onPostExecute(s);
    }
}
