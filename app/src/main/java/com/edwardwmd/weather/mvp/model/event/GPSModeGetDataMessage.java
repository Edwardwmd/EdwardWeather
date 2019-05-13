package com.edwardwmd.weather.mvp.model.event;



public class GPSModeGetDataMessage {
	  public final String gps;


	  public static GPSModeGetDataMessage getInstance(String gps) {
		    return new GPSModeGetDataMessage(gps);
	  }


	  private GPSModeGetDataMessage(String gps) {
		    this.gps = gps;
	  }

}
