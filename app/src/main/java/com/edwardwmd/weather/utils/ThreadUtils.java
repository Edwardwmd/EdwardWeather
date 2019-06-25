package com.edwardwmd.weather.utils;

import android.os.Looper;


public class ThreadUtils {

      /**
       * 判断是否为主线程
       *
       * @return isMainThread
       */
      public static boolean isMainThread() {
            return Thread.currentThread() == Looper.getMainLooper().getThread();
      }

      /**
       * 获取线程名称
       * @return getThreadName
       */
      public static String getThreadName(){
            return Thread.currentThread().getName();
      }

      /**
       * 获取当前线程ID
       */
      public static long getThreadId(){
            return Thread.currentThread().getId();
      }
}
