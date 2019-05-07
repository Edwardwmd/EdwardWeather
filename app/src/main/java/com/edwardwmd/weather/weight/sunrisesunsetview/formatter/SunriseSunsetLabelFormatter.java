package com.edwardwmd.weather.weight.sunrisesunsetview.formatter;

import androidx.annotation.NonNull;

import com.edwardwmd.weather.weight.sunrisesunsetview.Timer;

public interface SunriseSunsetLabelFormatter {
	  String formatSunriseLabel(@NonNull Timer sunrise);

	  String formatSunsetLabel(@NonNull Timer sunset);

}
