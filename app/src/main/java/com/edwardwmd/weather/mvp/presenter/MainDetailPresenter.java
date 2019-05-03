package com.edwardwmd.weather.mvp.presenter;

import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.mvp.contract.MainDetailContract;

import javax.inject.Inject;

public class MainDetailPresenter extends BasePresenter<MainDetailContract.View> implements MainDetailContract.Presenter {


	  @Inject
	  public MainDetailPresenter() {

	  }


}
