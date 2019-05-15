package com.edwardwmd.weather;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.edwardwmd.weather.di.component.AppComponent;
import com.edwardwmd.weather.di.component.DaggerAppComponent;
import com.edwardwmd.weather.di.module.AppModule;
import com.edwardwmd.weather.mvp.model.data.DaoManager;


import java.util.HashSet;
import java.util.Set;

import interfaces.heweather.com.interfacesmodule.view.HeConfig;

import static com.edwardwmd.weather.utils.ConstantUtils.APK_KEY;
import static com.edwardwmd.weather.utils.ConstantUtils.APK_USERNAME;
import static com.edwardwmd.weather.utils.ConstantUtils.DB_NAME;

public class EdWeatherApp extends Application {


	  private static EdWeatherApp instance = null;
	  public static AppComponent appComponent;
	  private Set<Activity> allActivities;


	  @Override
	  public void onCreate() {
		    super.onCreate();
		    instance = this;
		    //初始化和风天气
		    HeConfig.init(APK_USERNAME, APK_KEY);
		    HeConfig.switchToFreeServerNode();
		    //将Raw中的数据库文件拷贝到Android（"/data/data/com.edwardwmd.weather/databases/"）文件夹下。
		    DaoManager.getInstance().copyDbFile(instance, DB_NAME);

	  }


	  public static EdWeatherApp getInstance() {
		    return instance;
	  }


	  public static Context getAppContext() {
		    return instance.getApplicationContext();
	  }


	  /**
	   * 添加Activity
	   *
	   * @param act
	   */
	  public void addActivity(Activity act) {
		    if (allActivities == null) {
				allActivities = new HashSet<>();
		    }
		    Log.e("栈添加Activity-->", "当前移除的是---->" + act);
		    allActivities.add(act);
	  }


	  /**
	   * 移除一个Activity
	   *
	   * @param act
	   */
	  public void removeActivity(Activity act) {
		    if (allActivities != null) {
				Log.e("栈移除Activity-->", "当前移除的是---->" + act);
				allActivities.remove(act);
		    }
	  }


	  /**
	   * 退出程序
	   */
	  public void exitApp() {
		    if (allActivities != null) {
				synchronized (allActivities) {
					  for (Activity act : allActivities) {
						    act.finish();
					  }
				}
		    }
		    android.os.Process.killProcess(android.os.Process.myPid());
		    System.exit(0);
	  }


	  /**
	   * 初始化全局appComponent
	   * @return AppComponent
	   */
	  public static AppComponent getAppComponent() {
		    if (appComponent == null) {
				appComponent = DaggerAppComponent.builder()
					  .appModule(new AppModule(instance))
					  .build();
		    }
		    return appComponent;
	  }


}
