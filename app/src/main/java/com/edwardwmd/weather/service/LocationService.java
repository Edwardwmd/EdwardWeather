package com.edwardwmd.weather.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.edwardwmd.weather.utils.ConstantUtils;

import androidx.annotation.Nullable;

import java.util.Objects;

public class LocationService extends Service {


	  private final String TAG = "LocationService";

	  //声明AMapLocationClient类对象
	  public AMapLocationClient mLocationClient = null;
	  //声明定位回调监听器
	  public AMapLocationListener mLocationListener = aMapLocation -> {
		    if (aMapLocation.getErrorCode() == 0) {

				ConstantUtils.NOW_LON = aMapLocation.getLongitude();
				ConstantUtils.NOW_LAT = aMapLocation.getLatitude();
				Log.i(TAG, "now location: " + ConstantUtils.NOW_LON + "," + ConstantUtils.NOW_LAT);
		    } else {

				String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
				Log.e(TAG, errText);
		    }
	  };


	  public LocationService() {

	  }


	  @Override
	  public void onCreate() {

		    super.onCreate();
		    Log.e("定位服伍","服务开启");
		    //初始化定位
		    mLocationClient = new AMapLocationClient(getApplicationContext());
		    //声明AMapLocationClientOption对象
		    AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
		    //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
		    mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
		    //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
		    mLocationOption.setInterval(10000);
		    //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
		    mLocationOption.setHttpTimeOut(20000);
		    mLocationClient.setLocationListener(mLocationListener);
		    //给定位客户端对象设置定位参数
		    mLocationClient.setLocationOption(mLocationOption);
		    //启动定位
		    mLocationClient.startLocation();

	  }


	  @Nullable
	  @Override
	  public IBinder onBind(Intent intent) {
		    return null;
	  }


}
