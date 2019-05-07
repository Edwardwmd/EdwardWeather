package com.edwardwmd.weather.weight.helper;

import android.app.Activity;
import android.os.Build;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 *适配4.4以上版本 MIUI、Flyme 和其他 Android6.0 及以上版本状态栏字体颜色
 */
public class StatusBarHelper {
         
         public static final int MIUI = 1001;
         public static final int FLYME = 1002;
         public static final int ANDROID_M = 1003;
         public static final int OTHER = 1004;
         
         @IntDef({MIUI, FLYME, ANDROID_M, OTHER})
         @Retention(RetentionPolicy.SOURCE)
         @interface SystemType {
         }
         
         public static int statusBarLightMode(Activity activity) {
                  return statusMode(activity, true);
         }
         
         public static int statusBarDarkMode(Activity activity) {
                  return statusMode(activity, false);
         }
         
         private static int statusMode(Activity activity, boolean isFontColorDark) {
                  @SystemType int result = 0;
                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                           if (new MIUIHelper().setStatusBarLightMode(activity, isFontColorDark)) {
                                    result = MIUI;
                           } else if (new FlymeHelper().setStatusBarLightMode(activity, isFontColorDark)) {
                                    result = FLYME;
                           }
                  }
                  return result;
         }
         
         
         public static void statusBarLightMode(Activity activity, @SystemType int type) {
                  statusBarMode(activity, type, true);
                  
         }
         
         public static void statusBarDarkMode(Activity activity, @SystemType int type) {
                  statusBarMode(activity, type, false);
         }
         
         private static void statusBarMode(Activity activity, @SystemType int type, boolean isFontColorDark) {
                  if (type == MIUI) {
                           new MIUIHelper().setStatusBarLightMode(activity, isFontColorDark);
                  } else if (type == FLYME) {
                           new FlymeHelper().setStatusBarLightMode(activity, isFontColorDark);
                  }
//        else if (type == ANDROID_M) {
//            new AndroidMHelper().setStatusBarLightMode(activity, isFontColorDark);
//        }
         }

}
