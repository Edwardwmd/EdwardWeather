package com.edwardwmd.weather.utils;

import android.content.Context;
import android.widget.Toast;

import com.edwardwmd.weather.EdWeatherApp;


public class ToastUtils {


	  public static void showToast_S(String text) {
		    Toast.makeText(EdWeatherApp.getAppContext(), text, Toast.LENGTH_SHORT).show();
	  }


	  public static void showToast_L(String text) {
		    Toast.makeText(EdWeatherApp.getAppContext(), text, Toast.LENGTH_LONG).show();
	  }


}