package com.edwardwmd.weather.mvp.presenter;

import android.os.Looper;
import android.util.Log;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.LocationManager;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.bean.ForecastWeatherBean;
import com.edwardwmd.weather.bean.LifeIdexBean;
import com.edwardwmd.weather.bean.WeatherDetailBean;
import com.edwardwmd.weather.mvp.contract.MainDetailContract;
import com.edwardwmd.weather.utils.StringUtils;
import com.edwardwmd.weather.utils.ThreadUtils;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import interfaces.heweather.com.interfacesmodule.bean.weather.Weather;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.Forecast;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.ForecastBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.Lifestyle;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.LifestyleBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainDetailPresenter extends BasePresenter<MainDetailContract.View> implements MainDetailContract.Presenter {


	  List<WeatherDetailBean> detailBeans = new ArrayList<>();
	  List<ForecastWeatherBean> forecastWeatherBeans = new ArrayList<>();
	  List<LifeIdexBean> lifeIdexBeans = new ArrayList<>();
	  private LocationManager locationManager;


	  @Inject
	  public MainDetailPresenter(LocationManager locationManager) {
		    this.locationManager = locationManager;
		    this.locationManager.initLocation();
	  }


	  @Override
	  public void initDetailWeather() {
		    if (!isAttachView()) {
				return;
		    }

		    Log.e("当前线程P0", "当前是否为主线程：" + ThreadUtils.isMainThrean());
//		    HeWeather.getWeather(EdWeatherApp.getAppContext(), locationManager.getLon() + "," + locationManager.getLat(), new HeWeather.OnResultWeatherDataListBeansListener() {
//				@Override
//				public void onError(Throwable throwable) {
//					  mView.showErrorMsg(throwable.getMessage());
//					  mView.hideLoading();
//				}
//
//
//				@Override
//				public void onSuccess(List<Weather> l) {
//
//					  if (l != null && l.size() > 0) {
//						    Log.e("当前线程P", "当前是否为主线程：" + ThreadUtils.isMainThrean());
//
//						    NowBase now = l.get(0).getNow();
//						    List<ForecastBase> df = l.get(0).getDaily_forecast();
//						    List<LifestyleBase> lifestyles = l.get(0).getLifestyle();
//
//						    //1.天气详情
//						    addNowBaseData(detailBeans, now);
//						    mView.showDetailNowWeather(detailBeans);
//
//						    //3.生活指数
//						    if (lifestyles != null && lifestyles.size() > 0) {
//								for (int j = 0; j < lifestyles.size(); j++) {
//									  lifeIdexData(j, lifeIdexBeans, lifestyles);
//								}
//								mView.showLifeIndex(lifeIdexBeans);
//						    }
//
//					  } else {
//						    mView.showErrorMsg("无法获取当前数据，请检查你的网络是否正常！<_>");
//					  }
//
//
//				}
//		    });
//


		    HeWeather.getWeatherNow(EdWeatherApp.getAppContext(), locationManager.getLon() + "," + locationManager.getLat(), new HeWeather.OnResultWeatherNowBeanListener() {
				@Override
				public void onError(Throwable throwable) {

				}


				@Override
				public void onSuccess(List<Now> list) {
					  Log.e("刷新数据11111:", "--------->数据刷新了!!!!" + new Gson().toJson(list));
					  //1.天气详情
					  addNowBaseData(detailBeans, list.get(0).getNow());
					  mView.showDetailNowWeather(detailBeans);
				}
		    });

		    HeWeather.getWeatherForecast(EdWeatherApp.getAppContext(), locationManager.getLon() + "," + locationManager.getLat(), new HeWeather.OnResultWeatherForecastBeanListener() {
				@Override
				public void onError(Throwable throwable) {

				}


				@Override
				public void onSuccess(List<Forecast> list) {
					  Log.e("刷新数据22222:", "--------->数据刷新了!!!!" + new Gson().toJson(list));
					  //2.未来7日天气预报
					  Forecast forecast = list.get(0);
					  if (forecast.getDaily_forecast() != null && forecast.getDaily_forecast().size() > 0) {
						    for (int i = 0; i < forecast.getDaily_forecast().size(); i++) {
								getForcastBean(i, forecastWeatherBeans, forecast.getDaily_forecast());
						    }
						    mView.showForecastWeather(forecastWeatherBeans);
					  }
				}
		    });

		    HeWeather.getWeatherLifeStyle(EdWeatherApp.getAppContext(), locationManager.getLon() + "," + locationManager.getLat(), new HeWeather.OnResultWeatherLifeStyleBeanListener() {
				@Override
				public void onError(Throwable throwable) {

				}


				@Override
				public void onSuccess(List<Lifestyle> list) {
					  if (list.get(0).getLifestyle() != null && list.get(0).getLifestyle().size() > 0) {
						    Log.e("刷新数据333333:", "--------->数据刷新了!!!!" + new Gson().toJson(list));
						    for (int j = 0; j < list.get(0).getLifestyle().size(); j++) {
								lifeIdexData(j, lifeIdexBeans, list.get(0).getLifestyle());
						    }
						    mView.showLifeIndex(lifeIdexBeans);

					  }
				}
		    });
	  }


	  @Override
	  public void startLoad() {
		    mView.showLoading();
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


	  public void getForcastBean(int position, List<ForecastWeatherBean> forecastWeatherBeans, List<ForecastBase> fb) {
		    if ("晴".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_100, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("多云".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_101, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("少云".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_102, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("晴间多云".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_103, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("阴".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_104, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("阵雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_300, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("强阵雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_301, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("雷阵雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_302, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("强雷阵雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_303, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("小雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_305, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("中雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_306, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("大雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_307, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("极端降雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_312, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("毛毛雨/细雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_309, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("暴雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_310, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("大暴雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_311, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("特大暴雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_312, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("小到中雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_314, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("中到大雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_315, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("大到暴雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_316, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("暴雨到大暴雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_317, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else if ("大暴雨到特大暴雨".equals(fb.get(position).getCond_txt_d())) {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_318, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    } else {
				forecastWeatherBeans.add(new ForecastWeatherBean(R.drawable.ic_999, fb.get(position).getDate(),
					  fb.get(position).getCond_txt_d(), fb.get(position).getWind_dir(),
					  fb.get(position).getWind_sc() + StringUtils.getString(R.string.wind_unit),
					  fb.get(position).getTmp_min() + StringUtils.getString(R.string.temp_unit),
					  fb.get(position).getTmp_max() + StringUtils.getString(R.string.temp_unit)));
		    }


	  }


	  private void lifeIdexData(int position, List<LifeIdexBean> lifeIdexBeans, List<LifestyleBase> lifestyles) {
		    switch (position) {
				case 0:
					  lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_910, "舒适度指数", lifestyles.get(0).getBrf()));
					  break;
				case 1:
					  lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_902, "洗车指数", lifestyles.get(1).getBrf()));
					  break;
				case 2:
					  lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_903, "穿衣指数", lifestyles.get(2).getBrf()));
					  break;
				case 3:
					  lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_904, "感冒指数", lifestyles.get(3).getBrf()));
					  break;
				case 4:
					  lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_905, "运动指数", lifestyles.get(4).getBrf()));
					  break;
				case 5:
					  lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_906, "旅游指数", lifestyles.get(5).getBrf()));
					  break;
				case 6:
					  lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_907, "紫外线指数", lifestyles.get(6).getBrf()));
					  break;
				case 7:
					  lifeIdexBeans.add(new LifeIdexBean(R.drawable.ic_908, "污染扩散指数", lifestyles.get(7).getBrf()));
					  break;

		    }
	  }


}
