package com.edwardwmd.weather.bean;

public class ForecastWeatherBean {
         private int imgResources;
         private String forcastText;
         private String dataText;
         private String windDir;
         private String windsc;
         private String minTemp;
         private String maxTemp;
         public ForecastWeatherBean(int imgResources,
                                    String dataText,
                                    String forcastText,
                                    String windDir,
                                    String windsc,
                                    String minTemp,
                                    String maxTemp) {
                  this.imgResources=imgResources;
                  this.forcastText = forcastText;
                  this.dataText = dataText;
                  this.windDir = windDir;
                  this.windsc = windsc;
                  this.minTemp = minTemp;
                  this.maxTemp = maxTemp;
         }
         public int getImgResources() {
                  return imgResources;
         }
         
         public void setImgResources(int imgResources) {
                  this.imgResources = imgResources;
         }
         
         public String getForcastText() {
                  return forcastText;
         }
         
         public void setForcastText(String forcastText) {
                  this.forcastText = forcastText;
         }
         
         public String getDataText() {
                  return dataText;
         }
         
         public void setDataText(String dataText) {
                  this.dataText = dataText;
         }
         
         public String getWindDir() {
                  return windDir;
         }
         
         public void setWindDir(String windDir) {
                  this.windDir = windDir;
         }
         
         public String getWindsc() {
                  return windsc;
         }
         
         public void setWindsc(String windsc) {
                  this.windsc = windsc;
         }
         
         public String getMinTemp() {
                  return minTemp;
         }
         
         public void setMinTemp(String minTemp) {
                  this.minTemp = minTemp;
         }
         
         public String getMaxTemp() {
                  return maxTemp;
         }
         
         public void setMaxTemp(String maxTemp) {
                  this.maxTemp = maxTemp;
         }
         
       
         
         
}
