package com.edwardwmd.weather.weight.sunrisesunsetview.formatter;

import androidx.annotation.NonNull;

import com.edwardwmd.weather.weight.sunrisesunsetview.Timer;

import java.util.Locale;

public class SimpleSunriseSunsetLabelFormatter implements SunriseSunsetLabelFormatter {


	  @Override
	  public String formatSunriseLabel(@NonNull Timer sunrise) {
		    return null;
	  }


	  @Override
	  public String formatSunsetLabel(@NonNull Timer sunset) {
		    return null;
	  }

	  public String formatTime(Timer time) {
		    return String.format(Locale.getDefault(), "%d:%d", time.hour, time.minute);
	  }

}
