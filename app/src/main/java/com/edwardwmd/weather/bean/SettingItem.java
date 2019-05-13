package com.edwardwmd.weather.bean;

import androidx.annotation.DrawableRes;

public class SettingItem {


	  private @DrawableRes
	  int icon_left;
	  private String item_text;
	  private @DrawableRes
	  int icon_right;


	  public SettingItem(@DrawableRes int icon_left, String item_text, @DrawableRes int icon_right) {
		    this.icon_left = icon_left;
		    this.item_text = item_text;
		    this.icon_right = icon_right;
	  }


	  public @DrawableRes
	  int getIcon_left() {
		    return icon_left;
	  }


	  public void setIcon_left(@DrawableRes int icon_left) {
		    this.icon_left = icon_left;
	  }


	  public String getItem_text() {
		    return item_text;
	  }


	  public void setItem_text(String item_text) {
		    this.item_text = item_text;
	  }


	  public @DrawableRes
	  int getIcon_right() {
		    return icon_right;
	  }


	  public void setIcon_right(@DrawableRes int icon_right) {
		    this.icon_right = icon_right;
	  }


	  @Override
	  public String toString() {
		    return "SettingItem{" +
				"icon_left=" + icon_left +
				", item_text='" + item_text + '\'' +
				", icon_right=" + icon_right +
				'}';
	  }


}
