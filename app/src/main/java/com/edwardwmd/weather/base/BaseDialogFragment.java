package com.edwardwmd.weather.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialogFragment extends DialogFragment {


	  private Unbinder mUnbinder;


	  @Nullable
	  @Override
	  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		    View view = inflater.inflate(getLayout(), container, false);
		    mUnbinder = ButterKnife.bind(this, view);
		    initView(view);
		    return view;
	  }


	  @Override
	  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);
		    initData();
	  }


	  @Override
	  public void onDestroyView() {
		    super.onDestroyView();
		    if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
				mUnbinder.unbind();
				this.mUnbinder = null;
		    }

	  }


	  protected abstract @LayoutRes
	  int getLayout();


	  protected void initView(View view) {
	  }


	  protected void initData() {

	  }


}
