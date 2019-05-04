package com.edwardwmd.weather.bean;

public class LifeIdexBean {
         private int iconResource;
         private String lifeText;
         private String lifeVaule;
         
         public LifeIdexBean(int iconResource, String lifeText, String lifeVaule) {
                  this.iconResource = iconResource;
                  this.lifeText = lifeText;
                  this.lifeVaule = lifeVaule;
         }
         
         public int getIconResource() {
                  return iconResource;
         }
         
         public void setIconResource(int iconResource) {
                  this.iconResource = iconResource;
         }
         
         public String getLifeText() {
                  return lifeText;
         }
         
         public void setLifeText(String lifeText) {
                  this.lifeText = lifeText;
         }
         
         public String getLifeVaule() {
                  return lifeVaule;
         }
         
         public void setLifeVaule(String lifeVaule) {
                  this.lifeVaule = lifeVaule;
         }
         
}
