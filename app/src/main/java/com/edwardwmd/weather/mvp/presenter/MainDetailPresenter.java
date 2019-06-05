package com.edwardwmd.weather.mvp.presenter;


import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.bean.ForecastWeatherBean;
import com.edwardwmd.weather.bean.LifeIdexBean;
import com.edwardwmd.weather.bean.WeatherDetailBean;
import com.edwardwmd.weather.mvp.contract.MainDetailContract;
import com.edwardwmd.weather.utils.ACache;
import com.edwardwmd.weather.utils.StringUtils;
import com.edwardwmd.weather.utils.ToastUtils;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.Forecast;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.ForecastBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.Lifestyle;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.LifestyleBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

import static com.edwardwmd.weather.utils.ConstantUtils.LOCATION_LON_LAT_KEY;
import static com.edwardwmd.weather.utils.ConstantUtils.NOW_LAT;
import static com.edwardwmd.weather.utils.ConstantUtils.NOW_LON;


public class MainDetailPresenter extends BasePresenter<MainDetailContract.View> implements MainDetailContract.Presenter {


	  //声明AMapLocationClient类对象
	  private AMapLocationClient mLocationClient = null;
	  private List<WeatherDetailBean> detailBeans = new ArrayList<>();
	  private List<ForecastWeatherBean> forecastWeatherBeans = new ArrayList<>();
	  private List<LifeIdexBean> lifeIdexBeans = new ArrayList<>();
	  private Double mLon = NOW_LON;
	  private Double mLat = NOW_LAT;
	  private boolean isFirstSearch = false;
	  private final ACache mACache;


	  @Inject
	  public MainDetailPresenter() {
		    mACache = ACache.get(EdWeatherApp.getAppContext());
	  }


	  @Override
	  public void initDetailWeather() {
		    String asString = mACache.getAsString(LOCATION_LON_LAT_KEY);
		    if (asString==null){
				if (!isFirstSearch) {
					  initLocation();
				} else {
					  initData(mLon, mLat);
				}
		    }else {
				initData(StringUtils.getDoubleBeforeComma(asString),StringUtils.getDoubleAfterComma(asString));
		    }



	  }


	  private void initData(Double lon, Double lat) {
		    if (!isAttachView()) {
				return;
		    }
		    HeWeather.getWeatherNow(EdWeatherApp.getAppContext(), lon + "," + lat, new HeWeather.OnResultWeatherNowBeanListener() {
				@Override
				public void onError(Throwable throwable) {
					  mView.showErrorMsg(throwable.getMessage());
				}


				@Override
				public void onSuccess(List<Now> list) {
					  //1.天气详情
					  addNowBaseData(detailBeans, list.get(0).getNow());
					  mView.showDetailNowWeather(detailBeans);
				}
		    });

		    HeWeather.getWeatherForecast(EdWeatherApp.getAppContext(), lon + "," + lat , new HeWeather.OnResultWeatherForecastBeanListener() {
				@Override
				public void onError(Throwable throwable) {
					  mView.showErrorMsg(throwable.getMessage());
				}


				@Override
				public void onSuccess(List<Forecast> list) {
					  //2.未来7日天气预报
					  Forecast forecast = list.get(0);
					  if (forecast.getDaily_forecast() != null && forecast.getDaily_forecast().size() > 0) {
						    for (ForecastBase forecastBase : forecast.getDaily_forecast()) {
								getForcastBean(forecastWeatherBeans, forecastBase);
						    }

					  }
				}
		    });

		    HeWeather.getWeatherLifeStyle(EdWeatherApp.getAppContext(), lon + "," + lat, new HeWeather.OnResultWeatherLifeStyleBeanListener() {
				@Override
				public void onError(Throwable throwable) {
//					  mView.showErrorMsg(throwable.getMessage());
				}


				@Override
				public void onSuccess(List<Lifestyle> list) {
					  //生活指数
					  if (list.get(0).getLifestyle() != null && list.get(0).getLifestyle().size() > 0) {
						    for (LifestyleBase lifestyleBase : list.get(0).getLifestyle()) {
								lifeIdexData(lifeIdexBeans, lifestyleBase);
						    }

					  }
				}
		    });

	  }


	  @Override
	  public void startLoad() {
		    mView.showLoading();
	  }


	  @Override
	  public void addSearchCity(ChinaCityInfo chinaCityInfo) {
		    isFirstSearch = true;
		    mLat = chinaCityInfo.getLatitude();
		    mLon = chinaCityInfo.getLongitude();

		    initData(mLon, mLat);
	  }


	  //声明定位回调监听器
	  public AMapLocationListener mLocationListener = aMapLocation -> {

		    if (aMapLocation.getErrorCode() == 0) {
				Double lon = aMapLocation.getLongitude();
				Double lat = aMapLocation.getLatitude();
				initData(lon, lat);
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


	  public void addNowBaseData(List<WeatherDetailBean> detailBeans, NowBase nowBase) {
		    detailBeans.add(new WeatherDetailBean(R.drawable.ic_body_tem, "体感温度", nowBase.getFl() + StringUtils.getString(R.string.temp_unit)));
		    detailBeans.add(new WeatherDetailBean(R.drawable.ic_900, "温度", nowBase.getTmp() + StringUtils.getString(R.string.temp_unit)));
		    detailBeans.add(new WeatherDetailBean(R.drawable.ic_512, "相对湿度", nowBase.getHum() + StringUtils.getString(R.string.cloud_wet_unit)));
		    detailBeans.add(new WeatherDetailBean(R.drawable.ic_399, "降水量", nowBase.getPcpn() + StringUtils.getString(R.string.precipitation_unit)));
		    detailBeans.add(new WeatherDetailBean(R.drawable.ic_503, "大气压强", nowBase.getPres() + StringUtils.getString(R.string.atmospheric_pressure_unit)));
		    detailBeans.add(new WeatherDetailBean(R.drawable.ic_500, "能见度", nowBase.getVis() + StringUtils.getString(R.string.visibility_unit)));
		    detailBeans.add(new WeatherDetailBean(R.drawable.ic_101, "云量", nowBase.getCloud() + StringUtils.getString(R.string.cloud_wet_unit)));
		    detailBeans.add(new WeatherDetailBean(R.drawable.ic_cloud_p, "风向", nowBase.getWind_dir()));
		    detailBeans.add(new WeatherDetailBean(R.drawable.ic_200, "风力", nowBase.getWind_sc() + StringUtils.getString(R.string.wind_unit)));
	  }


	  public void getForcastBean(List<ForecastWeatherBean> forecastWeatherBeans, ForecastBase fb) {
		    if ("晴".equals(fb.getCond_txt_d())) {

				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_100, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("多云".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_101, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("少云".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_102, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("晴间多云".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_103, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("阴".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_104, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("阵雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_300, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("强阵雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_301, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("雷阵雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_302, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("强雷阵雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_303, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("小雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_305, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("中雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_306, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("大雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_307, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("极端降雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_312, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("毛毛雨/细雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_309, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("暴雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_310, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("大暴雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_311, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("特大暴雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_312, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("小到中雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_314, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("中到大雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_315, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("大到暴雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_316, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("暴雨到大暴雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_317, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else if ("大暴雨到特大暴雨".equals(fb.getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_318, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    } else {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_999, fb.getDate(),
					  fb.getCond_txt_d(), fb.getWind_dir(),
					  fb.getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.getTmp_max() + StringUtils.getString(R.string.temp_unit),
					  fb.getSr(), fb.getSs()));
		    }
		    mView.showForecastWeather(forecastWeatherBeans);
	  }


	  private void lifeIdexData(List<LifeIdexBean> lifeIdexBeans, LifestyleBase lifestyles) {
		    if ("comf".equals(lifestyles.getType())) {
		    	  
				lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_910, "舒适度指数", lifestyles.getBrf(),lifestyles.getTxt()));
		    } else if ("cw".equals(lifestyles.getType())) {
				lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_902, "洗车指数", lifestyles.getBrf(),lifestyles.getTxt()));
		    } else if ("drsg".equals(lifestyles.getType())) {
				lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_903, "穿衣指数", lifestyles.getBrf(),lifestyles.getTxt()));
		    } else if ("flu".equals(lifestyles.getType())) {
				lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_904, "感冒指数", lifestyles.getBrf(),lifestyles.getTxt()));
		    } else if ("sport".equals(lifestyles.getType())) {
				lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_905, "运动指数", lifestyles.getBrf(),lifestyles.getTxt()));
		    } else if ("trav".equals(lifestyles.getType())) {
				lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_906, "旅游指数", lifestyles.getBrf(),lifestyles.getTxt()));
		    } else if ("uv".equals(lifestyles.getType())) {
				lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_907, "紫外线指数", lifestyles.getBrf(),lifestyles.getTxt()));
		    } else if ("air".equals(lifestyles.getType())) {
				lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_908, "污染扩散指数", lifestyles.getBrf(),lifestyles.getTxt()));
		    }
		    mView.showLifeIndex(lifeIdexBeans);
	  }


}
