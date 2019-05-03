package com.edwardwmd.weather.di.component;

import android.app.Activity;

import com.edwardwmd.weather.di.module.FragmentModule;
import com.edwardwmd.weather.di.scope.FragmentScope;
import com.edwardwmd.weather.ui.fragment.MainFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {


	  Activity getActivity();

	  void inject(MainFragment mainFragment);


}
