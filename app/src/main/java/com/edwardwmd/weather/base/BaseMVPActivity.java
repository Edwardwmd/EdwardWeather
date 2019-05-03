package com.edwardwmd.weather.base;


import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatDelegate;


import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.di.component.ActivityComponent;
import com.edwardwmd.weather.di.component.DaggerActivityComponent;
import com.edwardwmd.weather.di.module.ActivityModule;
import com.edwardwmd.weather.utils.SnackbarUtil;

import javax.inject.Inject;

public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity implements BaseView {


	  @Inject
	  protected P mPresenter;


	  protected ActivityComponent getActivityComponent() {
		    return DaggerActivityComponent.builder()
				.appComponent(EdWeatherApp.getAppComponent())
				.activityModule(getActivityModule())
				.build();
	  }


	  protected ActivityModule getActivityModule() {
		    return new ActivityModule(this);
	  }


	  @Override
	  protected void initView() {
		    super.initView();
		    initInject();
		    if (mPresenter != null)
				mPresenter.attachView(this);
	  }


	  @Override
	  protected void onDestroy() {
		    super.onDestroy();
		    if (mPresenter != null)
				mPresenter.detachView();

		    mPresenter = null;
	  }


	  @Override
	  public void showErrorMsg(String msg) {
		    SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
	  }


	  @Override
	  public void useNightMode(boolean isNight) {
		    if (isNight) {
				AppCompatDelegate.setDefaultNightMode(
					  AppCompatDelegate.MODE_NIGHT_YES);
		    } else {
				AppCompatDelegate.setDefaultNightMode(
					  AppCompatDelegate.MODE_NIGHT_NO);
		    }
		    recreate();
	  }


	  protected abstract void initInject();


}
