package com.edwardwmd.weather.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
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
	  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		    super.onViewCreated(view, savedInstanceState);
		    initData();
	  }


	  @Override
	  public void onDestroyView() {
		    super.onDestroyView();
		    if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
				mUnbinder.unbind();
		    this.mUnbinder = null;
	  }


	  protected void initData() {
	  }


	  protected abstract @LayoutRes
	  int getLayoutId();


	  protected void initView(View v) {

	  }


}
