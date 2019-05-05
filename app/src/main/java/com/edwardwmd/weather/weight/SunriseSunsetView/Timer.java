package com.edwardwmd.weather.weight.SunriseSunsetView;

import com.edwardwmd.weather.utils.StringUtils;

public class Timer {


	  public static final int MINUTES_PER_HOUR = 60;

	  /**
	   * 时
	   */
	  public int hour;
	  /**
	   * 分
	   */
	  public int minute;


	  public Timer(String time) {
		    hour = StringUtils.getIntBeforeColon(time);
		    minute = StringUtils.getIntAfterColon(time);

	  }


	  public int transformToMinutes() {
		    return hour * MINUTES_PER_HOUR + minute;
	  }


}
