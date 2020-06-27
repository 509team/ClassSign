package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.fzn.classsign.R;
import com.fzn.classsign.activitys.fragment.TeacherFragmentActivity;
import com.fzn.classsign.asynctask.teacher.SignCreate;
import com.fzn.classsign.entity.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建签到
 */
public class CreateSignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_cs_sign_name;
    private Spinner tv_cs_select;
    private Button b_cs_release;

    private TextView tv_tsb_title;

    /*经纬度*/
    private Double longitude;
    private Double latitude;
    private boolean locationFlag = false;

    private String cid;
    private String total;
    private String seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sign_in);
        initView();

        //定位
        initMap();
    }

    private void initView(){
        tv_tsb_title = findViewById(R.id.tv_tsb_title);
        tv_tsb_title.setText("创建签到");

        Intent intent = getIntent();
        cid = intent.getStringExtra("CID");
        if (cid.contains(".")) {
            cid = cid.substring(0, cid.indexOf("."));
        }
        total = intent.getStringExtra("TOTAL");

        et_cs_sign_name = findViewById(R.id.et_cs_sign_name);
        tv_cs_select = findViewById(R.id.tv_cs_select);
        b_cs_release = findViewById(R.id.b_cs_release);

        tv_cs_select.getSelectedItem().toString();
        b_cs_release.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /*定位*/
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    latitude = aMapLocation.getLatitude();//获取纬度
                    longitude = aMapLocation.getLongitude();//获取经度
                    locationFlag = true;
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息。
                    String text = "定位错误，请检查相关权限和GPS是否开启！\nlocation Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo();
                    Toast.makeText(CreateSignInActivity.this, text, Toast.LENGTH_SHORT).show();
                    locationFlag = false;
                }
            }
        }
    };


    private void initMap() {
        //动态申请定位权限
        if (ContextCompat.checkSelfPermission(CreateSignInActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CreateSignInActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            //初始化定位
            mLocationClient = new AMapLocationClient(this);
            //设置定位回调监听
            mLocationClient.setLocationListener(mLocationListener);
            mLocationOption = new AMapLocationClientOption();
            //设置定位模式为高精度模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            //获取一次定位结果
            mLocationOption.setOnceLocation(true);
            //获取最近3s内精度最高的一次定位结果
            mLocationOption.setOnceLocationLatest(true);
            //设置是否开启wifi扫描
            mLocationOption.setWifiScan(true);
            //设置是否使用缓存定位
            mLocationOption.setLocationCacheEnable(true);
            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            //启动定位
            mLocationClient.startLocation();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b_cs_release) {
            String name = et_cs_sign_name.getText().toString().trim();
            seconds = tv_cs_select.getSelectedItem().toString().trim();
            if (name != null && name.length() > 0 && seconds != null && seconds.length() > 0) {
                switch (seconds) {
                    case "1分钟":
                        seconds = "60";
                        break;
                    case "3分钟":
                        seconds = "180";
                        break;
                    case "5分钟":
                        seconds = "300";
                        break;
                }
                if (locationFlag == true) {
                    //获取六位签到码
                    Map<String, String> head = new HashMap<>();
                    head.put("Authorization", "Bearer " + Token.token);
                    Map<String, Object> body = new HashMap<>();
                    body.put("cid", cid);
                    body.put("name", name);
                    body.put("longitude", longitude);
                    body.put("latitude", latitude);
                    body.put("seconds", seconds);

                    new SignCreate<String>(head, body, null, CreateSignInActivity.this, cid, total, seconds)
                            .post().execute();
                } else {
                    initMap();
                    if (locationFlag == true) {
                        //获取六位签到码
                        Map<String, String> head = new HashMap<>();
                        head.put("Authorization", "Bearer " + Token.token);
                        Map<String, Object> body = new HashMap<>();
                        body.put("cid", cid);
                        body.put("name", name);
                        body.put("longitude", longitude);
                        body.put("latitude", latitude);
                        body.put("seconds", seconds);

                        new SignCreate<String>(head, body, null, CreateSignInActivity.this, cid, total, seconds)
                                .post().execute();
                    } else {
                        Toast.makeText(CreateSignInActivity.this, "定位失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(CreateSignInActivity.this, "编辑框不能为空！", Toast.LENGTH_SHORT).show();
            }

        }
    }
}