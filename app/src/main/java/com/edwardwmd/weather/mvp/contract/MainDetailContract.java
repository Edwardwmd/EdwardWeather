package com.edwardwmd.weather.mvp.contract;

import com.edwardwmd.weather.base.BaseView;
import com.edwardwmd.weather.base.IPresenter;
import com.edwardwmd.weather.bean.ForecastWeatherBean;
import com.edwardwmd.weather.bean.LifeIdexBean;
import com.edwardwmd.weather.bean.WeatherDetailBean;

import java.util.List;


public interface MainDetailContract {


	  interface Model {


	  }


	  interface View extends BaseView {


		    void showDetailNowWeather(List<WeatherDetailBean> weathers);
		    void showForecastWeather(List<ForecastWeatherBean> weathers);
		    void showLifeIndex(List<LifeIdexBean> weathers);



	  }


	  interface Presenter extends IPresenter<View> {


		    void initDetailWeather();

		    void startLoad();
	  }


}
