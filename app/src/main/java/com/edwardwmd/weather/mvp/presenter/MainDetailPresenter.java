package com.edwardwmd.weather.mvp.presenter;

import android.util.Log;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.LocationManager;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.bean.TopWeather;
import com.edwardwmd.weather.mvp.contract.MainDetailContract;

import java.util.List;

import javax.inject.Inject;


public class MainDetailPresenter extends BasePresenter<MainDetailContract.View> implements MainDetailContract.Presenter {


	  private LocationManager locationManager;


	  @Inject
	  public MainDetailPresenter(LocationManager locationManager) {
		    this.locationManager = locationManager;
		    this.locationManager.initLocation();
	  }


	  @Override
	  public void initDetailWeather() {

	  }


}
