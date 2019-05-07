package com.edwardwmd.weather.di.component;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.LocationManager;
import com.edwardwmd.weather.di.module.AppModule;
import com.edwardwmd.weather.mvp.model.data.GreenDaoOptions;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
	  EdWeatherApp getContext();  // 提供App的Context

	  GreenDaoOptions getGreenDaoOptions(); //数据中心


//	  RetrofitHelper retrofitHelper();  //提供http的帮助类
//
//	  RealmHelper realmHelper();    //提供数据库帮助类

//	  ImplPreferencesHelper preferencesHelper(); //提供sp帮助类

}
