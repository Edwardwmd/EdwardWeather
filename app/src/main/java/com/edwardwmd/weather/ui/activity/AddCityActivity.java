package com.edwardwmd.weather.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.R;
import com.edwardwmd.weather.bean.City;
import com.edwardwmd.weather.utils.ToastUtils;
import com.edwardwmd.weather.weight.citypickview.CityPicker;
import com.edwardwmd.weather.weight.citypickview.OnPickListener;

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
