package com.edwardwmd.weather.ui.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BaseActivity;
import com.edwardwmd.weather.weight.MaterialProgressBar;

import butterknife.BindView;

import static com.edwardwmd.weather.utils.ConstantUtils.WEBVIEW_KEY;

public class WebActivity extends BaseActivity {


	  @BindView(R.id.webView)
	  WebView webView;
	  @BindView(R.id.progress)
	  MaterialProgressBar progress;
	  private WebSettings settings;
	  private String url;


	  @Override
	  protected int initLayout() {
		    return R.layout.activity_web;
	  }


	  @SuppressLint("SetJavaScriptEnabled")
	  @Override
	  protected void initView() {
		    super.initView();
		    Intent intent = getIntent();
		    if (intent != null) {
				url = intent.getStringExtra(WEBVIEW_KEY);
		    }
		    settings = webView.getSettings();
		    settings.setJavaScriptEnabled(true);
		    settings.setUseWideViewPort(true);
		    settings.setSupportZoom(true);
		    settings.setLoadsImagesAutomatically(true); //支持自动加载图片
		    webView.loadUrl(url);
		    webView.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					  view.loadUrl(url);
					  return true;
				}
		    });

		    webView.setWebChromeClient(new WebChromeClient() {


				//获取网站标题
				@Override
				public void onReceivedTitle(WebView view, String title) {


				}


				//获取加载进度
				@RequiresApi(api = Build.VERSION_CODES.N)
				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					  Log.e("进度条值", "进度值------》" + newProgress);

					  if (newProgress < 100) {
						    progress.setVisibility(View.VISIBLE);
						    progress.setProgress(newProgress, true);

					  } else {
						    progress.setVisibility(View.GONE);
					  }
				}
		    });


	  }


	  @Override
	  public boolean onKeyDown(int keyCode, KeyEvent event) {
		    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
				webView.goBack();
				return true;
		    }

		    return super.onKeyDown(keyCode, event);
	  }


	  @Override
	  public void onBackPressed() {
		    super.onBackPressed();
		    startActivity(new Intent(this, MainActivity.class));
		    finish();
	  }


	  //销毁Webview
	  @Override
	  protected void onDestroy() {
		    if (webView != null) {
				webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
				webView.clearHistory();

				((ViewGroup) webView.getParent()).removeView(webView);
				webView.destroy();
				webView = null;
		    }
		    super.onDestroy();
		    EdWeatherApp.getInstance().removeActivity(this);
	  }


}
