package com.fzn.classsign.fragment.student;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.fzn.classsign.R;
import com.fzn.classsign.activitys.SignInActivity;
import com.fzn.classsign.asynctask.student.SignIn;
import com.fzn.classsign.entity.Token;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private Context mContext;

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
                    latitude = aMapLocation.getLatitude();//获取纬度
                    longitude = aMapLocation.getLongitude();//获取经度
                    locationFlag = true;
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息。
                    String text = "定位错误，请检查相关权限和GPS是否开启！\nlocation Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo();
                    Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public SignFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignFragment newInstance(String param1, String param2) {
        SignFragment fragment = new SignFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMap();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sign, container, false);
        mContext = getContext();
        /*扫一扫图片*/
        iv_si_scan = mView.findViewById(R.id.iv_si_scan);
        /*六位码*/
        et_si_sixcode = mView.findViewById(R.id.et_si_sixcode);
        /*签到按钮*/
        bt_si_sign = mView.findViewById(R.id.bt_si_sign);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iv_si_scan.setOnClickListener(this);
        bt_si_sign.setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

                        new SignIn<Boolean>(head, body, null, mContext)
                                .post()
                                .execute();
                    } else if (!locationFlag) {
                        initMap();
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (locationFlag) {
                            Map<String, Object> body = new HashMap<>();
                            body.put("signCode", scanResult);
                            body.put("longitude", longitude);
                            body.put("latitude", latitude);

                            new SignIn<Boolean>(head, body, null, mContext)
                                    .post()
                                    .execute();
                        } else {
                            Toast.makeText(mContext, "定位失败！请重新打开此界面重试！", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    scanFlag = false;
                    Toast.makeText(mContext, "扫描结果与实际签到码不符！请重试！", Toast.LENGTH_SHORT).show();
                }
            } else {
                scanFlag = false;
                Toast.makeText(mContext, "扫描结果为空！", Toast.LENGTH_SHORT).show();
            }
        } else {
            scanFlag = false;
            Toast.makeText(mContext, "扫描出错，请重新扫描！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(mContext, CaptureActivity.class);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_si_scan) {
            //动态申请相机权限、读取文件权限；
            initMap();
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                //扫码返回结果
                Intent intent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        }
        if (v.getId() == R.id.bt_si_sign) {
            String sixcode = et_si_sixcode.getText().toString();
            if (sixcode.length() != 6) {
                Toast.makeText(mContext, "签到码不符合要求", Toast.LENGTH_SHORT).show();
            } else {
                //定位成功
                if (locationFlag) {
                    Map<String, String> head = new HashMap<>();
                    head.put("Authorization", "Bearer " + Token.token);
                    Map<String, Object> body = new HashMap<>();
                    body.put("signCode", sixcode);
                    body.put("longitude", longitude);
                    body.put("latitude", latitude);
                    new SignIn<Boolean>(head, body, null, mContext)
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
                        new SignIn<Boolean>(head, body, null, mContext)
                                .post()
                                .execute();
                    } else {
                        Toast.makeText(mContext, "定位失败！请重新打开此界面重试！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
    }

    private void initMap() {
        //初始化定位
        mLocationClient = new AMapLocationClient(mContext);
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