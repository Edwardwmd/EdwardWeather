package com.edwardwmd.weather.di.component;

import android.app.Activity;

import com.edwardwmd.weather.ui.activity.MainActivity;
import com.edwardwmd.weather.di.module.ActivityModule;
import com.edwardwmd.weather.di.scope.ActivityScope;
import com.edwardwmd.weather.ui.activity.LauncherActivity;


import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
	  Activity getActivity();
	  void inject(LauncherActivity launcherActivity);
	  void inject(MainActivity mainActivity);



}
