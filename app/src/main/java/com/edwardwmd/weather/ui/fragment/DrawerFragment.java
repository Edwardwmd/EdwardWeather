package com.edwardwmd.weather.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BaseFragment;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.mvp.model.event.AddCityMessage;
import com.edwardwmd.weather.ui.activity.MainActivity;
import com.edwardwmd.weather.weight.citypickview.CityPicker;
import com.edwardwmd.weather.weight.citypickview.OnPickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class DrawerFragment extends BaseFragment {


	  @BindView(R.id.add_city_btn)
	  Button addCityBtn;
	  @BindView(R.id.tv_setting)
	  TextView tvSetting;
	  @BindView(R.id.tv_about)
	  TextView tvAbout;


	  public static DrawerFragment newInstance() {
		    DrawerFragment drawerFragment = null;
		    Bundle bundle = new Bundle();
		    synchronized (DrawerFragment.class) {
				if (drawerFragment == null) {
					  drawerFragment = new DrawerFragment();
					  drawerFragment.setArguments(bundle);
				}
		    }
		    return drawerFragment;
	  }


	  @Override
	  protected int getLayoutId() {
		    return R.layout.fragment_drawer;
	  }


	  @Override
	  protected void initView(View v) {
		    super.initView(v);


	  }


	  @OnClick({R.id.add_city_btn, R.id.tv_setting, R.id.tv_about})
	  public void onViewClicked(View view) {
		    switch (view.getId()) {
				case R.id.add_city_btn:
					  CityPicker.from(this)
						    .enableAnimation(true)
						    .setAnimationStyle(R.style.CustomAnim)
						    .setOnPickListener(new OnPickListener() {
								@Override
								public void onPick(int position, ChinaCityInfo data) {
									  ((MainActivity) Objects.requireNonNull(getActivity())).drawerLayout.closeDrawers();
									  EventBus.getDefault().post(AddCityMessage.getInstance(data));
								}


								@Override
								public void onCancel() {


								}
						    }).show();
					  break;

				case R.id.tv_setting:
					  break;
				case R.id.tv_about:
					  break;
		    }
	  }


}
