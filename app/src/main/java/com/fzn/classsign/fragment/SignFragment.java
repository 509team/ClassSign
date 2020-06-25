package com.fzn.classsign.fragment;

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
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

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


    /*定位*/
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption=null;
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

                    String text = "经度:"+longitude.toString() + "\t纬度:"+latitude.toString();
                    Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sign, container, false);
        mContext = getContext();
        /*扫一扫图片*/
        iv_si_scan = mView.findViewById(R.id.iv_si_scan);
        /*六位码*/
        et_si_sixcode =mView. findViewById(R.id.et_si_sixcode);
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

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                et_si_sixcode.setText("扫描结果为：" + content);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(mContext, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                }
                break;
            case 200:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMap();
                }
                break;
            default:
                break;

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_si_scan){
            //动态申请相机权限、读取文件权限；
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA , Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }else {
                //扫码返回结果
                Intent intent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
                //TODO
                //判断二维码信息是否一致

                //动态申请定位权限
                if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},200);
                }else {
                    //获取位置信息
                    initMap();
                }
            }
        }
        if(v.getId() == R.id.bt_si_sign){
            //TODO
            //比对六位码是否正确

            //动态申请定位权限
            if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},200);
            }else {
                //获取位置信息
                initMap();
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