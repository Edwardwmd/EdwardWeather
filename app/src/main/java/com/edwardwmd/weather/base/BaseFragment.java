package com.edwardwmd.weather.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {


	  private Unbinder mUnbinder;


	  @Override
	  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		    View view = inflater.inflate(this.getLayoutId(), container, false);
		    mUnbinder = ButterKnife.bind(this, view);
		    initView(view);
		    return view;
	  }


	  @Override
	  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);

	  }


	  @Override
	  public void onDestroy() {
		    super.onDestroy();
		    if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
				mUnbinder.unbind();
		    this.mUnbinder = null;

	  }


	  protected abstract int getLayoutId();


	  protected void initView(View v) {

	  }


}
