package com.edwardwmd.weather.di.module;

import android.app.Activity;

import com.edwardwmd.weather.di.scope.ActivityScope;


import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
	  private Activity mActivity;

	  public ActivityModule(Activity activity) {
		    this.mActivity = activity;
	  }

	  @Provides
	  @ActivityScope
	  public Activity provideActivity() {
		    return mActivity;
	  }

}
