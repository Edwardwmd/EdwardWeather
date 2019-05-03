package com.edwardwmd.weather.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.edwardwmd.weather.weight.StatusBarHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {


	  private Unbinder mUnbinder;


	  @Override
	  protected void onCreate(@Nullable Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    getWindow().requestFeature(Window.FEATURE_NO_TITLE);//去掉标题
		    //初始化主题
		    initTheme();
		    //置状态栏字体图标
		    StatusBarHelper.statusBarDarkMode(this);
		    //获取布局
		    setContentView(initLayout());
		    mUnbinder = ButterKnife.bind(this);
		    initView();
	  }


	  @Override
	  protected void onResume() {
		    super.onResume();
		    ininData();
	  }


	  protected void ininData() {

	  }


	  protected void initView() {

	  }


	  protected abstract int initLayout();


	  private void initTheme() {
		    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				Window window = getWindow();
				window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					  | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
				window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
				window.setStatusBarColor(Color.TRANSPARENT);
		    }
	  }


	  @Subscribe(threadMode = ThreadMode.MAIN)
	  public void onGetMessage(Object message) {

	  }


	  @Override
	  protected void onDestroy() {
		    super.onDestroy();
		    if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
				mUnbinder.unbind();
		    this.mUnbinder = null;

	  }

	  public  void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
		    FragmentTransaction transaction = fragmentManager.beginTransaction();
		    transaction.add(frameId, fragment);
		    transaction.commit();
	  }


}
