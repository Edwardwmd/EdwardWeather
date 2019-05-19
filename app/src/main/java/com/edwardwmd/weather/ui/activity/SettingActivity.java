package com.edwardwmd.weather.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.adapter.SettingAdapter;
import com.edwardwmd.weather.base.BaseActivity;
import com.edwardwmd.weather.bean.SettingItem;
import com.edwardwmd.weather.utils.ACache;
import com.edwardwmd.weather.utils.BlurTransformation;
import com.edwardwmd.weather.utils.ToastUtils;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SettingActivity extends BaseActivity implements AdapterView.OnItemClickListener {


	  @BindView(R.id.img_personal_logo)
	  ImageView imgPersonalLogo;
	  @BindView(R.id.tv_nickname)
	  TextView tvNickname;
	  @BindView(R.id.tv_vertical_symbol)
	  TextView tvVerticalSymbol;
	  @BindView(R.id.tv_accent)
	  TextView tvAccent;
	  @BindView(R.id.rcl_setting)
	  RecyclerView rclSetting;
	  @BindView(R.id.img_bgm)
	  ImageView imgBgm;
	  @BindView(R.id.rl_top_contaner)
	  RelativeLayout rlTopContaner;
	  private List<SettingItem> settingItems;
	  private SettingAdapter adapter;
	  private LoadingDialog ld;
	  Handler handler = new Handler();


	  @Override
	  protected int initLayout() {
		    return R.layout.activity_setting;
	  }


	  @Override
	  protected void initView() {
		    super.initView();
		    Glide
				.with(this)
				.load(R.drawable.ic_main_top_refreshbackground)
				.apply(RequestOptions.bitmapTransform(new BlurTransformation(this)))
				.into(imgBgm);
		    Glide
				.with(this)
				.load(R.drawable.ic_main_top_refreshbackground)
				.apply(RequestOptions.bitmapTransform(new CircleCrop()))
				.into(imgPersonalLogo);
		    getItemList();
		    adapter = new SettingAdapter(this);
		    rclSetting.setLayoutManager(new LinearLayoutManager(this));
		    adapter.setItems(settingItems);
		    rclSetting.setAdapter(adapter);
		    adapter.setOnItemClickListener(this);
	  }


	  private void getItemList() {
		    settingItems = new ArrayList<>();
		    settingItems.add(new SettingItem(R.drawable.ic_clean, "离线缓存", R.drawable.ic_item_right_t));
		    settingItems.add(new SettingItem(R.drawable.ic_update, "版本更新", R.drawable.ic_item_right_t));
		    settingItems.add(new SettingItem(R.drawable.ic_about, "关于", R.drawable.ic_item_right_t));
	  }


	  @Override
	  public void onBackPressed() {
		    super.onBackPressed();
		    startActivity(new Intent(this, MainActivity.class));
		    finish();
	  }


	  @Override
	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    switch (position) {

				case 0:
					  //清除数据
					  clearDataDailog();

					  break;
				case 1:
					  //检查更新APP
					  updateDialog();

					  break;
				case 2:
					  //跳转至关于页面
					  startActivity(new Intent(this, AboutActivity.class));
					  finish();
					  break;
		    }

	  }


	  /**
	   * 清除数据
	   */
	  private void clearDataDailog() {
		    new AlertDialog
				.Builder(this)
				.setIcon(R.drawable.ic_location_logo)
				.setTitle(R.string.notifyTitle)
				.setMessage(R.string.clear_data_msg)
				//取消直接进入城市搜索
				.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss())
				//跳转GPS设置界面，对GPS定位开关进行设置
				.setPositiveButton(R.string.btn_sure, (dialog, which) -> {
					  ACache.get(this).clear();
					  ToastUtils.showToast_S("缓存数据已清除! ^_^");
				})
				.setCancelable(false)
				.show();
	  }


	  @SuppressLint("ResourceAsColor")
	  private void updateDialog() {
		    ld = new LoadingDialog(this);
		    ld.setLoadingText("正在查找新版本")
				.setSuccessText("当前为最新版本^_^")
				.setInterceptBack(false)
				.setLoadSpeed(LoadingDialog.Speed.SPEED_ONE)
				.show();
		    handler.postDelayed(() -> ld.loadSuccess(), 4500);


	  }


	  @Override
	  protected void onDestroy() {
		    super.onDestroy();
		    if (handler != null) {
				handler.removeCallbacksAndMessages(this);
				handler = null;
		    }
		    EdWeatherApp.getInstance().removeActivity(this);
	  }


}
