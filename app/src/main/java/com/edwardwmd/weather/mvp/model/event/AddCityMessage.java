package com.edwardwmd.weather.mvp.model.event;

import com.edwardwmd.weather.bean.City;

public class AddCityMessage {

	  public final City city;


	  public static AddCityMessage getInstance(City city) {
		    return new AddCityMessage(city);
	  }


	  private AddCityMessage(City city) {
		    this.city = city;
	  }

}
