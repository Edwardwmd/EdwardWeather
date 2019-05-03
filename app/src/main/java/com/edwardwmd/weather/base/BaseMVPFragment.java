package com.edwardwmd.weather.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;


import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.bean.DaoSession;
import com.edwardwmd.weather.di.component.DaggerFragmentComponent;
import com.edwardwmd.weather.di.component.FragmentComponent;
import com.edwardwmd.weather.di.module.FragmentModule;

import javax.inject.Inject;

public abstract class BaseMVPFragment< P extends BasePresenter> extends BaseFragment implements BaseView {

	  @Inject
	  protected P mPresenter;


	  protected FragmentComponent getFragmentComponent() {
		    return DaggerFragmentComponent
				.builder()
				.appComponent(EdWeatherApp.getAppComponent())
				.fragmentModule(getFragmentModule())
				.build();
	  }


	  protected FragmentModule getFragmentModule() {
		    return new FragmentModule(this);
	  }


	  @Override
	  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		    initInject();
		    mPresenter.attachView(this);
		    super.onViewCreated(view, savedInstanceState);
	  }


	  @Override
	  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);
		    initData();
	  }


	  @Override
	  public void onDestroy() {
		    super.onDestroy();
		    if (mPresenter != null)
				mPresenter.detachView();
		    mPresenter = null;
	  }


	  protected void initData() {
	  }


	  protected abstract void initInject();


}
