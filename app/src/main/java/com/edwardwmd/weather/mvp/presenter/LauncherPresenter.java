package com.edwardwmd.weather.mvp.presenter;

import android.Manifest;
import android.content.Intent;
import android.util.Log;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.mvp.contract.LauncherContract;
import com.edwardwmd.weather.service.LocationService;
import com.edwardwmd.weather.utils.ConstantUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LauncherPresenter extends BasePresenter<LauncherContract.View> implements LauncherContract.Presenter {

	  @Inject
	  public LauncherPresenter(){}


	  @Override
	  public void chearkPermissions(RxPermissions rxPermissions) {
		    addSubscribe(rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.READ_PHONE_STATE,
				Manifest.permission.ACCESS_FINE_LOCATION,
				Manifest.permission.ACCESS_COARSE_LOCATION
				)
					  .subscribe(new Consumer<Boolean>() {
						    @Override
						    public void accept(Boolean granted) {
								if (granted) {

									  EdWeatherApp.getAppContext().startService(new Intent(EdWeatherApp.getAppContext(), LocationService.class));
									  JumpToMainByTimer();
								} else {
									  mView.showErrorMsg("想要定位当前城市的天气，需要添加权限定位哟^=^");
									  JumpToMainByTimer();
								}
						    }
					  })
		    );

	  }


	  private void JumpToMainByTimer() {

		    addSubscribe(Observable.timer(ConstantUtils.LONG_TIME, TimeUnit.MILLISECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(aLong -> mView.turnToMain()));
	  }





}
