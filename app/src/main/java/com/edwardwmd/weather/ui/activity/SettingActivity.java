package com.edwardwmd.weather.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.adapter.SettingAdapter;
import com.edwardwmd.weather.base.BaseActivity;
import com.edwardwmd.weather.bean.SettingItem;
import com.edwardwmd.weather.utils.BlurTransformation;

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
				.load(R.drawable.ic_github)
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
		    settingItems.add(new SettingItem(R.drawable.ic_github, "切换主题", R.drawable.ic_item_right_t));
		    settingItems.add(new SettingItem(R.drawable.ic_github, "离线缓存", R.drawable.ic_item_right_t));
		    settingItems.add(new SettingItem(R.drawable.ic_github, "版本更新", R.drawable.ic_item_right_t));
		    settingItems.add(new SettingItem(R.drawable.ic_github, "关于", R.drawable.ic_item_right_t));
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
					 //主题切换


					  break;
				case 1:
					  //清除数据


					  break;
				case 2:
					  //检查更新APP


					  break;
				case 3:
					  //跳转至关于页面
					  startActivity(new Intent(this, AboutActivity.class));
					  finish();
					  break;
		    }

	  }


}
