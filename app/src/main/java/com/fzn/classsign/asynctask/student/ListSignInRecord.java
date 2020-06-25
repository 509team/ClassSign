package com.fzn.classsign.asynctask.student;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.fzn.classsign.R;
import com.fzn.classsign.asynctask.base.CustomAsyncTask;
import com.fzn.classsign.constant.RequestConstant;

import java.util.List;
import java.util.Map;

/**
 * 令牌刷新接口
 * @param <T>
 */
public class ListSignInRecord<T> extends CustomAsyncTask<T> {

    private int normalNum = 0;
    private int abnormalNum = 0;
    private int absenceNum = 0;

    //三个出勤数字
    private TextView tv_cds_attendancenum;
    private TextView tv_cds_abnormalnum;
    private TextView tv_cds_absencenum;

    private Context context;


    public ListSignInRecord(Map headers, Map body, Map params, TextView tvnormal, TextView tvabnormal, TextView absence, Context context){
        super(headers,body,params, RequestConstant.URL.STU_SIGN_LIST_SIGNIN_RECORD);
        this.tv_cds_attendancenum = tvnormal.findViewById(R.id.tv_cds_attendancenum);
        this.tv_cds_abnormalnum = tvabnormal.findViewById(R.id.tv_cds_abnormalnum);
        this.tv_cds_absencenum = absence.findViewById(R.id.tv_cds_absencenum);
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        ResponseResultJson<List<Map<String, Object>>> temp = (ResponseResultJson<List<Map<String, Object>>>) getResponse(s);
        int code = temp.getCode();
        if(code == 200){
            List<Map<String, Object>> list = temp.getData();
            for(Map<String, Object> data : list){
                if(data.get("status").toString().equals("normal")){
                    normalNum++;
                }else if(data.get("status").toString().equals("abnormal")){
                    abnormalNum++;
                }else if(data.get("status").toString().equals("absence")){
                    absenceNum++;
                }
            }
            tv_cds_attendancenum.setText(String.valueOf(normalNum));
            tv_cds_abnormalnum.setText(String.valueOf(abnormalNum));
            tv_cds_absencenum.setText(String.valueOf(absenceNum));
        }else {
            Toast.makeText(context,"获取出勤信息失败！",Toast.LENGTH_SHORT).show();
        }

        super.onPostExecute(s);
    }
}
