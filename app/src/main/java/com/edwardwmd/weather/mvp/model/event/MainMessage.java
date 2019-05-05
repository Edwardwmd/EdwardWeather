package com.edwardwmd.weather.mvp.model.event;


public class MainMessage {


	  	  public final String message;


	  public static MainMessage getInstance(String message) {
		    return new MainMessage(message);
	  }


	  private MainMessage(String message) {
		    this.message = message;
	  }


}
