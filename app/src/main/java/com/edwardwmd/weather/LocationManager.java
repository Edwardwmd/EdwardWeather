package com.edwardwmd.weather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.edwardwmd.weather.utils.ToastUtils;

import static com.edwardwmd.weather.utils.ConstantUtils.NOW_LAT;
import static com.edwardwmd.weather.utils.ConstantUtils.NOW_LON;

public class LocationManager {


	  //声明AMapLocationClient类对象
	  public AMapLocationClient mLocationClient = null;
	  //经度
	  public Double lon = NOW_LON;
	  //纬度
	  public Double lat = NOW_LAT;


	  public LocationManager() {
	  }


	  private static LocationManager locationManager;


	  public static LocationManager getInstance() {
		    if (locationManager == null) {
				synchronized (LocationManager.class) {
					  if (locationManager == null) {
						    locationManager = new LocationManager();
					  }
				}
		    }

		    return locationManager;
	  }


	  //声明定位回调监听器
	  public AMapLocationListener mLocationListener = aMapLocation -> {

		  if (aMapLocation.getErrorCode() == 0) {
//		  	  Log.e("初始化定位","只在此山中！！！！！");
		    lon = aMapLocation.getLongitude();
		    lat = aMapLocation.getLatitude();
		    mLocationClient.onDestroy();
		  } else {
		    if (ContextCompat.checkSelfPermission(EdWeatherApp.getAppContext(), Manifest.permission.ACCESS_FINE_LOCATION)
				  != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(EdWeatherApp.getAppContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
				  != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(EdWeatherApp.getAppContext(), Manifest.permission.READ_PHONE_STATE)
				  != PackageManager.PERMISSION_GRANTED) {
				  ToastUtils.showToast_S("如需获取当前位置，请打开GPS定位权限！");

		    }
		    mLocationClient.onDestroy();
		  }
  };


	  public void initLocation() {
		    //初始化定位
		    mLocationClient = new AMapLocationClient(EdWeatherApp.getAppContext().getApplicationContext());
		    //设置定位回调监听
		    mLocationClient.setLocationListener(mLocationListener);
		    //声明AMapLocationClientOption对象
		    AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
		    //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
		    mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
		    //设置定位间隔,单位毫秒,默认为2000ms，最低15000ms。
		    mLocationOption.setInterval(15000);
		    //单位是毫秒，默认30000毫秒，建议超时时间不要低于25000毫秒。
		    mLocationOption.setHttpTimeOut(25000);
		    //给定位客户端对象设置定位参数
		    mLocationClient.setLocationOption(mLocationOption);
		    //启动定位
		    mLocationClient.startLocation();

	  }


	  public Double getLon() {
		    Log.e("当前LON","LON----》"+lon);
		    return lon;
	  }


	  public Double getLat() {
		    Log.e("当前LAT","LAT----》"+lat);
		    return lat;
	  }


}
