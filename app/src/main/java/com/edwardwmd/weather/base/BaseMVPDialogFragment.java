package com.edwardwmd.weather.base;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.di.component.DaggerFragmentComponent;
import com.edwardwmd.weather.di.component.FragmentComponent;
import com.edwardwmd.weather.di.module.FragmentModule;
import com.edwardwmd.weather.utils.DisplayUtils;
import com.edwardwmd.weather.utils.SnackbarUtil;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMVPDialogFragment<P extends BasePresenter> extends DialogFragment implements BaseView {


	  @Inject
	  protected P dPresenter;

	  private Unbinder mUnbinder;
	  private int height;
	  private int width;


	  protected FragmentComponent getFragmentComponent() {
		    return DaggerFragmentComponent
				.builder()
				.appComponent(EdWeatherApp.getAppComponent())
				.fragmentModule(getFragmentModule())
				.build();
	  }


	  protected FragmentModule getFragmentModule() {
		    return new FragmentModule(this);
	  }


	  @Override
	  public void onStart() {
		    super.onStart();
                //弹窗全屏幕设置
		    Dialog dialog = getDialog();
		    dialog.setOnKeyListener((dialog1, keyCode, event) -> {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					  doForKeyBlack();
				}
				return false;
		    });

		    measure();
		    Window window = dialog.getWindow();
		    if (window != null) {
				window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
				window.setGravity(Gravity.BOTTOM);
				window.setLayout(width, height - DisplayUtils.getStatusBarHeight(getActivity()));
				initTheme(window);
		    }

	  }


	  @Override
	  public void onCreate(@Nullable Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setStyle(STYLE_NORMAL, R.style.CityPickerStyle);
	  }


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

		    if (dPresenter != null) {
				dPresenter.detachView();
				dPresenter = null;
		    }

	  }


	  @Override
	  public void showErrorMsg(String msg) {
		    SnackbarUtil.show(((ViewGroup) Objects.requireNonNull(getActivity()).findViewById(android.R.id.content)).getChildAt(0), msg);
	  }


	  //测量屏幕宽高
	  private void measure() {
		    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				DisplayMetrics dm = new DisplayMetrics();
				Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getRealMetrics(dm);
				height = dm.heightPixels;
				width = dm.widthPixels;
		    } else {
				DisplayMetrics dm = getResources().getDisplayMetrics();
				height = dm.heightPixels;
				width = dm.widthPixels;
		    }
	  }


	  protected void initView(View view) {
		    initInject();
		    dPresenter.attachView(this);
	  }


	  protected abstract void initTheme(Window window);


	  protected abstract void doForKeyBlack();

	  protected abstract void initInject();

	  protected abstract int getLayout();


	  protected void initData() {

	  }


}
