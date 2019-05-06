package com.edwardwmd.weather.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BaseMVPActivity;
import com.edwardwmd.weather.bean.City;
import com.edwardwmd.weather.bean.TopWeather;
import com.edwardwmd.weather.mvp.contract.MainContract;
import com.edwardwmd.weather.mvp.model.event.AddCityMessage;
import com.edwardwmd.weather.mvp.model.event.MainMessage;
import com.edwardwmd.weather.mvp.presenter.MainPresenter;
import com.edwardwmd.weather.ui.fragment.DrawerFragment;
import com.edwardwmd.weather.ui.fragment.MainFragment;
import com.edwardwmd.weather.utils.DateUtils;
import com.edwardwmd.weather.utils.StringUtils;
import com.edwardwmd.weather.utils.ToastUtils;
import com.edwardwmd.weather.weight.citypickview.CityPicker;
import com.edwardwmd.weather.weight.citypickview.OnPickListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

import static com.edwardwmd.weather.utils.ConstantUtils.START_REFRESH;


public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.View {


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
	  @BindView(R.id.fl_detail_container)
	  FrameLayout flDetailContainer;
	  @BindView(R.id.ed_refresh_layout)
	  SmartRefreshLayout edRefreshLayout;
	  @BindView(R.id.fragment_container_drawer_menu)
	  FrameLayout fragmentContainerDrawerMenu;
	  @BindView(R.id.drawer_layout)
	  public DrawerLayout drawerLayout;


	  @Override
	  protected int initLayout() {
		    return R.layout.activity_main;
	  }


	  @Override
	  protected void initInject() {
		    getActivityComponent().inject(this);
	  }


	  @Override
	  protected void initView() {
		    super.initView();
		    //参数占位
		    init();
		    //初始化Toolbar
		    setSupportActionBar(toolbar);
		    //设置DrawerLayout相关
		    setByDrawerToggle();
		    //初始化下拉刷新控件基础设置
		    initRefreshLayout();
		    //添加Fragment
		    popToFragment();
		    //初始化天气数据
		    mPresenter.initTopPageWeather();


	  }


	  @SuppressLint("SetTextI18n")
	  private void init() {
		    EventBus.getDefault().register(this);
		    tvUpdateDate.setText(StringUtils.getString(R.string.update_by_text) + DateUtils.getCurrentSystemDate());
		    imgWeatherShow.setImageResource(R.drawable.ic_999);
		    tvWeatherInfo.setText(StringUtils.getString(R.string.text_Unknown));
		    tvTemp.setText(StringUtils.getString(R.string.text_temp_Unknown_show));
		    edCollapsingToolbar.setTitle(StringUtils.getString(R.string.text_Unknown));

	  }


	  private void initRefreshLayout() {
		    ClassicsHeader header = new ClassicsHeader(this);
		    header.setPrimaryColors(this.getResources().getColor(R.color.colorPrimary), Color.WHITE);
		    edRefreshLayout.setRefreshHeader(header);
	  }


	  private void popToFragment() {
		    //1.天气预报主界面
		    MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fl_detail_container);
		    if (mainFragment == null) {
				mainFragment = MainFragment.newInstance();
				addFragmentToActivity(getSupportFragmentManager(), mainFragment, R.id.fl_detail_container);
		    }
		    //2.侧滑添加城市及测试界面
		    DrawerFragment drFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_drawer_menu);
		    if (drFragment == null) {
				drFragment = DrawerFragment.newInstance();
				addFragmentToActivity(getSupportFragmentManager(), drFragment, R.id.fragment_container_drawer_menu);
		    }
	  }


	  @Override
	  protected void onDestroy() {
		    super.onDestroy();
		    if (EventBus.getDefault().isRegistered(this))
				EventBus.getDefault().unregister(this);
		    EdWeatherApp.getInstance().removeActivity(this);
	  }


	  @Override
	  public void showTopPageWeather(TopWeather topWeather) {
		    tvUpdateDate.setText(StringUtils.getString(R.string.update_by_text) + DateUtils.getCurrentSystemDate());
		    imgWeatherShow.setImageResource(topWeather.getIconResource());
		    tvWeatherInfo.setText(topWeather.getWeatherText());
		    tvTemp.setText(topWeather.getTep_value());
		    edCollapsingToolbar.setTitle(topWeather.getAddress());

	  }


	  @SuppressLint("SetTextI18n")
	  @Override
	  public void showLoading() {

		    edRefreshLayout.setOnRefreshListener(refreshLayout -> {
				mPresenter.initTopPageWeather();

				EventBus.getDefault().post(MainMessage.getInstance(START_REFRESH));
		    });
	  }


	  @Override
	  public void hideLoading() {
		    edRefreshLayout.finishRefresh();
	  }


	  private void setByDrawerToggle() {
		    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		    assert drawerLayout != null;
		    drawerLayout.addDrawerListener(toggle);
		    toggle.syncState();
	  }


	  @Subscribe(threadMode = ThreadMode.MAIN)
	  public void omGetSearchCityData(AddCityMessage city) {
		    ToastUtils.showToast_S("---->当前城市： " + city.city.getName());
		    mPresenter.addSearchCity(city.city);

	  }


}
