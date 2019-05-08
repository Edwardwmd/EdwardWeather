package com.edwardwmd.weather.utils;

import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.edwardwmd.weather.EdWeatherApp;

public class LocationUtils {


	  public static boolean isLocationEnabled() {
		    int locationMode;
		    String locationProviders;
		    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				try {
					  locationMode = Settings.Secure.getInt(EdWeatherApp.getAppContext().getContentResolver(), Settings.Secure.LOCATION_MODE);
				} catch (Settings.SettingNotFoundException e) {
					  e.printStackTrace();
					  return false;
				}
				return locationMode != Settings.Secure.LOCATION_MODE_OFF;
		    } else {
				locationProviders = Settings.Secure.getString(EdWeatherApp.getAppContext().getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
				return !TextUtils.isEmpty(locationProviders);
		    }
	  }


}
