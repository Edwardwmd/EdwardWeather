package com.edwardwmd.weather.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BaseMVPFragment;
import com.edwardwmd.weather.bean.TopWeather;
import com.edwardwmd.weather.mvp.contract.MainDetailContract;
import com.edwardwmd.weather.mvp.presenter.MainDetailPresenter;
import com.edwardwmd.weather.utils.ConstantUtils;

import butterknife.BindView;

import static com.edwardwmd.weather.utils.ConstantUtils.MAIN_FRAGMENT_KEYS;

public class MainFragment extends BaseMVPFragment<MainDetailPresenter> implements MainDetailContract.View {


	  @BindView(R.id.rcv_weatherdetail)
	  RecyclerView rcvWeatherdetail;


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
	  protected int getLayoutId() {
		    return R.layout.fragment_main;
	  }


	  @Override
	  protected void initInject() {
		    getFragmentComponent().inject(this);
	  }


	  @Override
	  protected void initView(View v) {
		    super.initView(v);


	  }


	  @Override
	  protected void initData() {
		    super.initData();
	  }


	  @Override
	  public void useNightMode(boolean isNight) {

	  }


	  @Override
	  public void showLoading() {

	  }


	  @Override
	  public void hideLoading() {


	  }


	  @Override
	  public void showDetailWeather(TopWeather topWeather) {

	  }


}
