package com.edwardwmd.weather.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BaseMVPFragment;
import com.edwardwmd.weather.bean.TopWeather;
import com.edwardwmd.weather.mvp.contract.MainDetailContract;
import com.edwardwmd.weather.mvp.presenter.MainDetailPresenter;
import com.edwardwmd.weather.ui.activity.MainActivity;
import com.edwardwmd.weather.utils.DateUtils;
import com.edwardwmd.weather.utils.SnackbarUtil;
import com.edwardwmd.weather.utils.StringUtils;
import com.edwardwmd.weather.utils.ToastUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Objects;

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
	  protected void initView(View v) {
		    super.initView(v);
		    //添加Toolbar
		    ((MainActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
		    ClassicsHeader header = new ClassicsHeader(getActivity());
		    header.setPrimaryColors(this.getResources().getColor(R.color.colorPrimary), Color.WHITE);
		    edRefreshLayout.setRefreshHeader(header);
		    //设置DrawerLayout相关
//		    setByDrawerToggle();
	  }


	  @Override
	  protected void initData() {
		    super.initData();
		    mPresenter.initTopPageWeather();
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
	  public void showTopPageWeather(TopWeather topWeather) {
		    imgWeatherShow.setImageResource(topWeather.getIconResource());
		    tvWeatherInfo.setText(topWeather.getWeatherText());
		    tvTemp.setText(topWeather.getTep_value());
		    edCollapsingToolbar.setTitle(topWeather.getAddress());
	  }


	  @Override
	  public void useNightMode(boolean isNight) {

	  }


	  @Override
	  public void showLoading() {
		    tvUpdateDate.setText(StringUtils.getString(R.string.update_by_text) + DateUtils.getCurrentSystemDate());
		    edRefreshLayout.setOnRefreshListener(refreshLayout -> mPresenter.initTopPageWeather());
	  }


	  @Override
	  public void hideLoading() {
		    edRefreshLayout.finishRefresh();

	  }


}
