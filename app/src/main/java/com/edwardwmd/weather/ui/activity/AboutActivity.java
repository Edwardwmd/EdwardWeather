package com.edwardwmd.weather.ui.activity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.base.BaseActivity;
import com.edwardwmd.weather.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.edwardwmd.weather.utils.ConstantUtils.GITHUB_URL;
import static com.edwardwmd.weather.utils.ConstantUtils.PERSONAL_BLOG_URL;
import static com.edwardwmd.weather.utils.ConstantUtils.WEBVIEW_KEY;

public class AboutActivity extends BaseActivity {


	  @BindView(R.id.tv_contact_with_me)
	  TextView tvContactWithMe;
	  @BindView(R.id.tv_visit)
	  TextView tvVisit;
	  @BindView(R.id.tv_github)
	  TextView tvGithub;


	  @Override
	  protected int initLayout() {
		    return R.layout.activity_about;
	  }


	  @OnClick({R.id.tv_contact_with_me, R.id.tv_visit, R.id.tv_github})
	  public void onViewClicked(View view) {
		    switch (view.getId()) {
				case R.id.tv_contact_with_me:
					  openEmail();
					  break;
				case R.id.tv_visit:
					  turnToWebAct(PERSONAL_BLOG_URL);
					  break;
				case R.id.tv_github:
					  turnToWebAct(GITHUB_URL);
					  break;
		    }
	  }


	  private void turnToWebAct(String url) {
		    Intent intent = new Intent(this, WebActivity.class);
		    intent.putExtra(WEBVIEW_KEY, url);
		    startActivity(intent);
		    finish();
	  }


	  @Override
	  public void onBackPressed() {
		    super.onBackPressed();
		    startActivity(new Intent(this, MainActivity.class));
		    finish();
	  }


	  @Override
	  protected void onDestroy() {
		    super.onDestroy();
		    EdWeatherApp.getInstance().removeActivity(this);
	  }



	  /**
	   * 打开邮箱客户端
	   */
	  private void openEmail() {
		    Uri uri = Uri.parse("mailto:" + "");
		    List<ResolveInfo> packageInfos = getPackageManager().queryIntentActivities(new Intent(Intent.ACTION_SENDTO, uri), 0);
		    List<String> tempPkgNameList = new ArrayList<>();
		    List<Intent> emailIntents = new ArrayList<>();
		    for (ResolveInfo info : packageInfos) {
				String pkgName = info.activityInfo.packageName;
				if (!tempPkgNameList.contains(pkgName)) {
					  tempPkgNameList.add(pkgName);
					  Intent intent = getPackageManager().getLaunchIntentForPackage(pkgName);
					  emailIntents.add(intent);
				}
		    }
		    if (!emailIntents.isEmpty()) {
				Intent chooserIntent = Intent.createChooser(emailIntents.remove(0), "选择邮箱");
				if (chooserIntent != null) {
					  chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, emailIntents.toArray(new Parcelable[]{}));
					  startActivity(chooserIntent);
				} else {
					  ToastUtils.showToast_S("没有找到可用的邮件客户端");
				}
		    } else {
				ToastUtils.showToast_S("没有找到可用的邮件客户端");
		    }
	  }


}
