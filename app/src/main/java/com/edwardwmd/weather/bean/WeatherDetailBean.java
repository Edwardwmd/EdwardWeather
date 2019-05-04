package com.edwardwmd.weather.bean;


public class WeatherDetailBean {
         private int imageResourceId;
         private String detailText;
         private String weatherValue;
         
         
         public WeatherDetailBean() {
         }
         
         public WeatherDetailBean(int imageResourceId, String detailText, String weatherValue) {
                  this.imageResourceId = imageResourceId;
                  this.detailText = detailText;
                  this.weatherValue = weatherValue;
         }
         
         public int getImageResourceId() {
                  return imageResourceId;
         }
         
         public void setImageResourceId(int imageResourceId) {
                  this.imageResourceId = imageResourceId;
         }
         
         public String getDetailText() {
                  return detailText;
         }
         
         public void setDetailText(String detailText) {
                  this.detailText = detailText;
         }
         
         public String getWeatherValue() {
                  return weatherValue;
         }
         
         public void setWeatherValue(String weatherValue) {
                  this.weatherValue = weatherValue;
         }
}
