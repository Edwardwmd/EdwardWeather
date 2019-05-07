package com.edwardwmd.weather.mvp.model.event;

import com.edwardwmd.weather.bean.ChinaCityInfo;

public class AddCityMessage {

	  public final ChinaCityInfo city;


	  public static AddCityMessage getInstance(ChinaCityInfo city) {
		    return new AddCityMessage(city);
	  }


	  private AddCityMessage(ChinaCityInfo city) {
		    this.city = city;
	  }

}
