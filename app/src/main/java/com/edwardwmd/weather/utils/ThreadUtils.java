package com.edwardwmd.weather.utils;

import android.os.Looper;

public class ThreadUtils {


	  public static boolean isMainThrean() {
		    return Thread.currentThread() == Looper.getMainLooper().getThread();
	  }


}
