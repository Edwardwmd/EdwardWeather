package com.edwardwmd.weather.ui.activity;

import android.util.Log;
import android.widget.FrameLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BaseMVPActivity;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.mvp.contract.MainContract;
import com.edwardwmd.weather.mvp.presenter.MainPresenter;
import com.edwardwmd.weather.ui.fragment.MainFragment;


import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.View {


	  @BindView(R.id.main_container)
	  FrameLayout mainContainer;
	  @BindView(R.id.fragment_container_drawer_menu)
	  FrameLayout fragmentContainerDrawerMenu;
	  @BindView(R.id.drawer_layout)
	  DrawerLayout drawerLayout;


	  @Override
	  protected void initInject() {
		    getActivityComponent().inject(this);
	  }


	  @Override
	  protected void initView() {
		    super.initView();
		    mPresenter.showAllData();
		    //1.天气预报主界面
		    MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
		    if (mainFragment == null) {
				mainFragment = MainFragment.newInstance();
				addFragmentToActivity(getSupportFragmentManager(), mainFragment, R.id.main_container);
		    }
	  }


	  public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
		    FragmentTransaction transaction = fragmentManager.beginTransaction();
		    transaction.add(frameId, fragment);
		    transaction.commit();
	  }


	  @Override
	  protected int initLayout() {
		    return R.layout.activity_main;
	  }


	  @Override
	  public void showAllCity(List<ChinaCityInfo> chinaCityInfos) {
		    for (int i = 0; i < chinaCityInfos.size(); i++) {
				Log.e("全国城市数据---》", "城市------》" + chinaCityInfos.get(i).getCity_CN());

		    }
	  }


	  @Override
	  protected void onDestroy() {
		    super.onDestroy();
		    EdWeatherApp.getInstance().exitApp();
	  }


}
