package com.edwardwmd.weather.weight.SunriseSunsetView.formatter;

import androidx.annotation.NonNull;

import com.edwardwmd.weather.weight.SunriseSunsetView.Timer;

public interface SunriseSunsetLabelFormatter {
	  String formatSunriseLabel(@NonNull Timer sunrise);

	  String formatSunsetLabel(@NonNull Timer sunset);

}
