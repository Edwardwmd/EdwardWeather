package com.edwardwmd.weather.ui.fragment;

import android.os.Bundle;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BaseFragment;

public class DrawerFragment extends BaseFragment {


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


}
