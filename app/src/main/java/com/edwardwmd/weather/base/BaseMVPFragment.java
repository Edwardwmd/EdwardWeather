package com.edwardwmd.weather.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;


import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.di.component.DaggerFragmentComponent;
import com.edwardwmd.weather.di.component.FragmentComponent;
import com.edwardwmd.weather.di.module.FragmentModule;
import com.edwardwmd.weather.utils.SnackbarUtil;

import java.util.Objects;

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
	  protected void initView(View v) {
		    super.initView(v);
		    initInject();
		    mPresenter.attachView(this);

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


	  @Override
	  public void showErrorMsg(String msg) {
		    SnackbarUtil.show(((ViewGroup) Objects.requireNonNull(getActivity()).findViewById(android.R.id.content)).getChildAt(0), msg);
	  }


	  protected void initData() {
	  }


	  protected abstract void initInject();


}
