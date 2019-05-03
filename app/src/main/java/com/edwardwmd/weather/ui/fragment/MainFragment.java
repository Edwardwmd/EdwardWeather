package com.edwardwmd.weather.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BaseMVPFragment;
import com.edwardwmd.weather.mvp.contract.MainDetailContract;
import com.edwardwmd.weather.mvp.presenter.MainDetailPresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

public class MainFragment extends BaseMVPFragment<MainDetailPresenter> implements MainDetailContract.View {

	  @BindView(R.id.img_weather_show)
	  ImageView imgWeatherShow;
	  @BindView(R.id.tv_weather_info)
	  TextView tvWeatherInfo;
	  @BindView(R.id.tv_temp)
	  TextView tvTemp;
	  @BindView(R.id.tv_update_date)
	  TextView tvUpdateDate;
	  @BindView(R.id.ed_cl_background)
	  ConstraintLayout edClBackground;
	  @BindView(R.id.toolbar)
	  Toolbar toolbar;
	  @BindView(R.id.ed_collapsing_toolbar)
	  CollapsingToolbarLayout edCollapsingToolbar;
	  @BindView(R.id.ed_appbar)
	  AppBarLayout edAppbar;
	  @BindView(R.id.recy_detail_total)
	  RecyclerView recyDetailTotal;
	  @BindView(R.id.ed_refresh_layout)
	  SmartRefreshLayout edRefreshLayout;


	  public static MainFragment newInstance() {
		    MainFragment mainFragment = null;
		    Bundle bundle = new Bundle();
		    synchronized (MainFragment.class) {
		  if (mainFragment == null) {
				mainFragment = new MainFragment();
				mainFragment.setArguments(bundle);
		  }
		}
		    return mainFragment;
	  }


	  @Override
	  protected void initInject() {
		    getFragmentComponent().inject(this);
	  }


	  @Override
	  protected int getLayoutId() {
		    return R.layout.fragment_main;
	  }


	  @Override
	  public void showErrorMsg(String msg) {

	  }


	  @Override
	  public void useNightMode(boolean isNight) {

	  }


}
