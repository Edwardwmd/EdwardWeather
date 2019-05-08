package com.edwardwmd.weather.di.module;


import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.mvp.model.data.GreenDaoOptions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {


	  private final EdWeatherApp application;


	  public AppModule(EdWeatherApp application) {
		    this.application = application;
	  }


	  @Provides
	  @Singleton
	  EdWeatherApp provideApplicationContext() {
		    return application;
	  }


	  @Provides
	  @Singleton
	  GreenDaoOptions provideGreenDaoOptions() {
		    return new GreenDaoOptions();
	  }




}
