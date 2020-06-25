package com.fzn.classsign.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.fzn.classsign.R;
import com.fzn.classsign.asynctask.student.SignIn;
import com.fzn.classsign.entity.Token;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 学生签到
 */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    /*扫一扫图片*/
    private ImageView iv_si_scan;
    /*六位码*/
    private EditText et_si_sixcode;
    /*签到按钮*/
    private Button bt_si_sign;

    /*权限*/
    private final int REQUEST_CODE_SCAN = 0x0000;

    /*经纬度*/
    private Double longitude;
    private Double latitude;

    /*二维码扫描结果*/
    private String scanResult;

    private boolean scanFlag = false;
    private boolean locationFlag = false;


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
                    Double latitude = aMapLocation.getLatitude();//获取纬度
                    Double longitude = aMapLocation.getLongitude();//获取经度
                    //TODO
                    //上传至服务端

                    String text = "经度:" + longitude.toString() + "\t纬度:" + latitude.toString();
                    Toast.makeText(SignInActivity.this, text, Toast.LENGTH_SHORT).show();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        /*扫一扫图片*/
        iv_si_scan = findViewById(R.id.iv_si_scan);
        iv_si_scan.setOnClickListener(this);

        /*六位码*/
        et_si_sixcode = findViewById(R.id.et_si_sixcode);
        /*签到按钮*/
        bt_si_sign = findViewById(R.id.bt_si_sign);
        bt_si_sign.setOnClickListener(this);

        initMap();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_si_scan) {
            //动态申请相机权限、读取文件权限；
            if (ContextCompat.checkSelfPermission(SignInActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(SignInActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SignInActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                //扫码返回结果
                Intent intent = new Intent(SignInActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);

            }
        }
        if (v.getId() == R.id.bt_si_sign) {
            String sixcode = et_si_sixcode.getText().toString();
            if (sixcode.length() != 6) {
                Toast.makeText(SignInActivity.this, "签到码不符合要求", Toast.LENGTH_SHORT).show();
            } else {
                //定位成功
                if (locationFlag) {
                    Map<String, String> head = new HashMap<>();
                    head.put("Authorization", "Bearer " + Token.token);
                    Map<String, Object> body = new HashMap<>();
                    body.put("signCode", sixcode);
                    body.put("longitude", longitude);
                    body.put("latitude", latitude);
                    new SignIn<Boolean>(head, body, null, SignInActivity.this)
                            .post()
                            .execute();
                } else {
                    initMap();
                    if (locationFlag) {
                        Map<String, String> head = new HashMap<>();
                        head.put("Authorization", "Bearer " + Token.token);
                        Map<String, Object> body = new HashMap<>();
                        body.put("signCode", sixcode);
                        body.put("longitude", longitude);
                        body.put("latitude", latitude);
                        new SignIn<Boolean>(head, body, null, SignInActivity.this)
                                .post()
                                .execute();
                    } else {
                        Toast.makeText(SignInActivity.this, "定位失败！请重新打开此界面重试！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                scanResult = data.getStringExtra(Constant.CODED_CONTENT);
                scanResult = scanResult.substring(scanResult.indexOf("-") + 1);
                if (scanResult.length() == 6) {
                    scanFlag = true;
                    Map<String, String> head = new HashMap<>();
                    head.put("Authorization", "Bearer " + Token.token);
                    //扫描结果成功且定位成功
                    if (locationFlag && scanFlag) {
                        Map<String, Object> body = new HashMap<>();
                        body.put("signCode", scanResult);
                        body.put("longitude", longitude);
                        body.put("latitude", latitude);

                        new SignIn<Boolean>(head, body, null, SignInActivity.this)
                                .post()
                                .execute();
                    } else if (!locationFlag) {
                        initMap();
                        if (locationFlag) {
                            Map<String, Object> body = new HashMap<>();
                            body.put("signCode", scanResult);
                            body.put("longitude", longitude);
                            body.put("latitude", latitude);

                            new SignIn<Boolean>(head, body, null, SignInActivity.this)
                                    .post()
                                    .execute();
                        } else {
                            Toast.makeText(SignInActivity.this, "定位失败！请重新打开此界面重试！", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    scanFlag = false;
                    Toast.makeText(SignInActivity.this, "扫描结果与实际签到码不符！请重试！", Toast.LENGTH_SHORT).show();
                }
            } else {
                scanFlag = false;
                Toast.makeText(SignInActivity.this, "扫描结果为空！", Toast.LENGTH_SHORT).show();
            }
        } else {
            scanFlag = false;
            Toast.makeText(SignInActivity.this, "扫描出错，请重新扫描！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(SignInActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                }
                break;
            case 200:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMap();
                }
                break;
            default:
                break;

        }
    }

    private void initMap() {
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