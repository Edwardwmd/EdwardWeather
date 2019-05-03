package com.edwardwmd.weather.weight;

import android.app.Activity;
import android.view.Window;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 魅族Flyme状态栏样式设置
 */
public class FlymeHelper implements AndroidSystemBarHelper {
         @Override
         public boolean setStatusBarLightMode(Activity activity, boolean isFontColorDark) {
                  
                  Window window = activity.getWindow();
                  boolean result = false;
                  if (window != null) {
                           Class clazz = window.getClass();
                           try {
                                    int darkModeFlag = 0;
                                    Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                                    Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                                    darkModeFlag = field.getInt(layoutParams);
                                    Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                                    if (isFontColorDark) {
                                             extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                                    } else {
                                             extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                                    }
                                    result = true;
                           } catch (Exception e) {
                                    e.printStackTrace();
                           }
                  }
                  return result;
         }
}
