package com.edwardwmd.weather.mvp.contract;

import com.edwardwmd.weather.base.BaseView;
import com.edwardwmd.weather.base.IPresenter;
import com.edwardwmd.weather.bean.TopWeather;

public interface MainDetailContract {


	  interface Model {


	  }


	  interface View extends BaseView {


		    void showTopPageWeather(TopWeather topWeather);


	  }


	  interface Presenter extends IPresenter<View> {


		    void initTopPageWeather();


	  }


}
