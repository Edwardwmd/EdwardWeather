package com.edwardwmd.weather.mvp.contract;

import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.base.BaseView;
import com.edwardwmd.weather.base.IPresenter;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.bean.TopWeather;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.weather.Weather;

public interface MainContract {


	  interface View extends BaseView {
		    void showTopPageWeather(TopWeather topWeather);



	  }


	  interface Presenter extends IPresenter<View> {
		    void initTopPageWeather();


	  }


}
