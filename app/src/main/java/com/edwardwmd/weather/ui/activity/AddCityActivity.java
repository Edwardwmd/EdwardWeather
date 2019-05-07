package com.edwardwmd.weather.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.edwardwmd.weather.EdWeatherApp;

public class AddCityActivity extends AppCompatActivity {


	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
//		    setContentView(R.layout.activity_add_city);


	  }


	  @Override
	  protected void onDestroy() {
		    super.onDestroy();
		    EdWeatherApp.getInstance().removeActivity(this);
	  }


}
