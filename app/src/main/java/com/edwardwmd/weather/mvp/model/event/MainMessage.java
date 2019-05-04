package com.edwardwmd.weather.mvp.model.event;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.weather.Weather;

public class MainMessage {


	  //	  public final String message;
	  public List<Weather> weathers;


	  public static MainMessage getInstance(List<Weather> weathers) {
		    return new MainMessage(weathers);
	  }


	  private MainMessage(List<Weather> weathers) {
		    this.weathers = weathers;
	  }


}
