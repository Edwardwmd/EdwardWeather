package com.edwardwmd.weather.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.mvp.contract.DrawerContract;
import com.edwardwmd.weather.ui.activity.MainActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DrawerPresenter extends BasePresenter<DrawerContract.View> implements DrawerContract.Presenter {


	  @Inject
	  public DrawerPresenter() {
	  }


	  @Override
	  public void initDrawerData() {

	  }








}
