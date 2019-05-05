package com.edwardwmd.weather.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.adapter.ForecastAdapter;
import com.edwardwmd.weather.adapter.LifeIndexAdapter;
import com.edwardwmd.weather.adapter.WeatherDetailAdapter;
import com.edwardwmd.weather.base.BaseMVPFragment;
import com.edwardwmd.weather.bean.ForecastWeatherBean;
import com.edwardwmd.weather.bean.LifeIdexBean;
import com.edwardwmd.weather.bean.WeatherDetailBean;
import com.edwardwmd.weather.mvp.contract.MainDetailContract;
import com.edwardwmd.weather.mvp.model.event.MainMessage;
import com.edwardwmd.weather.mvp.presenter.MainDetailPresenter;
import com.edwardwmd.weather.utils.ConstantUtils;
import com.edwardwmd.weather.utils.ThreadUtils;
import com.edwardwmd.weather.weight.WeatherTextView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;


public class MainFragment extends BaseMVPFragment<MainDetailPresenter> implements MainDetailContract.View {


	  @BindView(R.id.detail_title)
	  WeatherTextView detailTitle;
	  @BindView(R.id.detail_recycler_view)
	  RecyclerView detailRecyclerView;
	  @BindView(R.id.cv_detail_weather)
	  CardView cvDetailWeather;
	  @BindView(R.id.forecast_title)
	  WeatherTextView forecastTitle;
	  @BindView(R.id.forecast_recycler_view)
	  RecyclerView forecastRecyclerView;
	  @BindView(R.id.cv_forecast_weather)
	  CardView cvForecastWeather;
	  @BindView(R.id.index_title)
	  WeatherTextView indexTitle;
	  @BindView(R.id.life_index_recycler_view)
	  RecyclerView lifeIndexRecyclerView;
	  @BindView(R.id.cv_life_index)
	  CardView cvLifeIndex;
	  @BindView(R.id.nsv_layout)
	  NestedScrollView nsvLayout;
	  private WeatherDetailAdapter dAdapter;
	  private ForecastAdapter fAdapter;
	  private LifeIndexAdapter iAdapter;


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
	  public void onAttach(@NonNull Context context) {
		    super.onAttach(context);
		    EventBus.getDefault().register(this);
	  }


	  @Override
	  protected void initView(View v) {
		    super.initView(v);
		    dAdapter = new WeatherDetailAdapter(getActivity());
		    fAdapter = new ForecastAdapter(getActivity());
		    iAdapter = new LifeIndexAdapter(getActivity());
//		    detailRecyclerView.setHasFixedSize(true);
//		    forecastRecyclerView.setHasFixedSize(true);
//		    lifeIndexRecyclerView.setHasFixedSize(true);
		    detailRecyclerView.setNestedScrollingEnabled(false);
		    forecastRecyclerView.setNestedScrollingEnabled(false);
		    lifeIndexRecyclerView.setNestedScrollingEnabled(false);
		    detailRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
		    forecastRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		    lifeIndexRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

	  }


	  @Override
	  protected int getLayoutId() {
		    return R.layout.fragment_main;
	  }


	  @Override
	  protected void initInject() {
		    getFragmentComponent().inject(this);
	  }


	  @Override
	  protected void initData() {
		    super.initData();
		    mPresenter.initDetailWeather();

	  }


	  @Override
	  public void useNightMode(boolean isNight) {

	  }


	  @Override
	  public void showLoading() {
		    mPresenter.initDetailWeather();
		    fAdapter.dataClear();
		    dAdapter.dataClear();
		    iAdapter.dataClear();
	  }


	  @Override
	  public void hideLoading() {


	  }


	  @Override
	  public void showDetailNowWeather(List<WeatherDetailBean> weathers) {
		    Log.e("获取天气详情数据:", "--------->数据刷新了!!!!");

		    dAdapter.setmDatas(weathers);
		    detailRecyclerView.setAdapter(dAdapter);
	  }


	  @Override
	  public void showForecastWeather(List<ForecastWeatherBean> forecastWeatherBeans) {
//		    Log.e("获取未来天气预报数据:", "--------->数据刷新了!!!!");
		    fAdapter.setAllDatas(forecastWeatherBeans);
		    forecastRecyclerView.setAdapter(fAdapter);

	  }


	  @Override
	  public void showLifeIndex(List<LifeIdexBean> lifeIdexBeans) {
		    Log.e("当前线程M","当前是否为主线程："+ ThreadUtils.isMainThrean());
		    Log.e("获取生活指数数据:", "--------->数据刷新了!!!!");
		    iAdapter.setAllLifeIndexData(lifeIdexBeans);
		    lifeIndexRecyclerView.setAdapter(iAdapter);
	  }


	  @Override
	  public void onDestroy() {
		    super.onDestroy();
		    EventBus.getDefault().unregister(this);
	  }


	  @Subscribe(threadMode = ThreadMode.MAIN)
	  public void onGetMessage(MainMessage weather) {
//		    if (START_REFRESH.equals(weather.weather)) {
//				Log.e("刷新数据:", "--------->数据刷新了!!!!"+new Gson().toJson(weather.weathers));
		    mPresenter.startLoad();

//		    }
	  }


}
