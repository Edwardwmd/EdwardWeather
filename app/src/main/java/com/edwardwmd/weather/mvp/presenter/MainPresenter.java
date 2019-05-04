package com.edwardwmd.weather.mvp.presenter;

import android.util.Log;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.LocationManager;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.bean.TopWeather;
import com.edwardwmd.weather.mvp.contract.MainContract;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import interfaces.heweather.com.interfacesmodule.bean.weather.Weather;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


	  private LocationManager locationManager;
	  private List<Weather> weathers;


	  @Inject
	  public MainPresenter(LocationManager locationManager) {
		    this.locationManager = locationManager;
		    locationManager.initLocation();
	  }


	  @Override
	  public void initTopPageWeather() {
		    if (!isAttachView()) {
				return;
		    }
		    mView.showLoading();
		    HeWeather.getWeather(EdWeatherApp.getAppContext(),
				locationManager.getLon() + "," + locationManager.getLat(), new HeWeather.OnResultWeatherDataListBeansListener() {
					  @Override
					  public void onError(Throwable throwable) {
						    mView.showErrorMsg(throwable.getMessage());
						    mView.hideLoading();
						    Log.e("----->", "------出错了！！！！" + throwable.getMessage());

					  }


					  @Override
					  public void onSuccess(List<Weather> list) {
						    if (list != null && list.size() > 0) {
								weathers=list;
								Weather weather=list.get(0);
								addTopWeather(weather);
								mView.hideLoading();
						    } else {
								mView.showErrorMsg("数据未加载，请检查你的网络！>_<");
								mView.hideLoading();
						    }
					  }


					  private void addTopWeather(Weather weather) {
						    NowBase now = weather.getNow();
						    if ("晴".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_100, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("多云".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_101, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("少云".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_102, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("晴间多云".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_103, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("阴".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_104, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("阵雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_300, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("强阵雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_301, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("雷阵雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_302, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("强雷阵雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_303, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("雷阵雨伴有冰雹".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_304, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("小雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_305, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("中雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_306, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("大雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_307, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("极端降雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_312, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("毛毛雨/细雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_309, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("暴雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_310, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("特大暴雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_311, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("冻雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_313, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("小到中雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_314, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("中到大雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_315, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("大到暴雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_316, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));
						    } else if ("暴雨到大暴雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_317, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));

						    } else if ("大暴雨到特大暴雨".equals(now.getCond_txt())) {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_318, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));

						    } else {
								mView.showTopPageWeather(new TopWeather(R.drawable.ic_999, now.getCond_txt(), now.getTmp(), weather.getBasic().getLocation()));

						    }
					  }
				});
	  }


	  @Override
	  public List<Weather> getWeatherData() {
		    return weathers;
	  }


}
