package com.edwardwmd.weather.ui.activity;


import android.content.Intent;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BaseMVPActivity;
import com.edwardwmd.weather.mvp.contract.LauncherContract;
import com.edwardwmd.weather.mvp.presenter.LauncherPresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class LauncherActivity extends BaseMVPActivity<LauncherPresenter> implements LauncherContract.View {


	  @Override
	  protected int initLayout() {
		    return R.layout.activity_launcher;
	  }


	  @Override
	  protected void initInject() {
		    getActivityComponent().inject(this);
	  }


	  @Override
	  protected void initView() {
		    super.initView();
		    mPresenter.chearkPermissions(new RxPermissions(this));
	  }


	  @Override
	  public void turnToMain() {
		    Intent intent = new Intent();
		    intent.setClass(this, MainActivity.class);
		    startActivity(intent);
		    finish();
		    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	  }


}
