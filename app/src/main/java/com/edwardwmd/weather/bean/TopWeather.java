package com.edwardwmd.weather.bean;

public class TopWeather {


	  private int IconResource;
	  private String weatherText;
	  private String tep_value;
	  private String address;


	  public String getAddress() {
		    return address;
	  }


	  public void setAddress(String address) {
		    this.address = address;
	  }


	  public TopWeather(int iconResource, String weatherText, String tep_value, String address) {
		    IconResource = iconResource;
		    this.weatherText = weatherText;
		    this.tep_value = tep_value;
		    this.address=address;
	  }


	  public int getIconResource() {
		    return IconResource;
	  }


	  public void setIconResource(int iconResource) {
		    IconResource = iconResource;
	  }


	  public String getWeatherText() {
		    return weatherText;
	  }


	  public void setWeatherText(String weatherText) {
		    this.weatherText = weatherText;
	  }


	  public String getTep_value() {
		    return tep_value;
	  }


	  public void setTep_value(String tep_value) {
		    this.tep_value = tep_value;
	  }


}
