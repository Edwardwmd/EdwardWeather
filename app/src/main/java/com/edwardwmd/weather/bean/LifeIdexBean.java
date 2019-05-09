package com.edwardwmd.weather.bean;

public class LifeIdexBean {
         private int iconResource;
         private String lifeText;
         private String lifeVaule;
         private String lifeDetailText;
         
         public LifeIdexBean(int iconResource, String lifeText, String lifeVaule,String lifeDetailText) {
                  this.iconResource = iconResource;
                  this.lifeText = lifeText;
                  this.lifeVaule = lifeVaule;
                  this.lifeDetailText=lifeDetailText;
         }


        public String getLifeDetailText() {
                return lifeDetailText;
        }


        public void setLifeDetailText(String lifeDetailText) {
                this.lifeDetailText = lifeDetailText;
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
