package com.fzn.classsign.asynctask.student;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fzn.classsign.R;
import com.fzn.classsign.adapter.ClassListAdapter;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

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
    private RecyclerView recyclerView;
    private Context context;

    public ClassList(Map headers, Map body, Map params, RecyclerView recyclerView, Context context) {
        super(headers, body, params, RequestConstant.URL.STU_CLASS_LIST);
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, Object>>> temp = (ResponseResultJson<List<Map<String, Object>>>) getResponse(s);
        int code = temp.getCode();
        if(code == 200){
            datalist = temp.getData();

            if(datalist.size() != 0){
                classListAdapter = new ClassListAdapter(context, R.layout.list_class_student,datalist);
                LinearLayoutManager llm = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(classListAdapter);
            }
        }

        super.onPostExecute(s);
    }
}