package com.edwardwmd.weather.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(
	  nameInDb = "ChinaCityInfo",
	  createInDb = false,
	  generateGettersSetters = true
)
public class ChinaCityInfo {


	  @Property(nameInDb = "City_ID")
	  @Id(autoincrement = true)
	  private Long city_Id;
	  @Property(nameInDb = "City_EN")
	  private String city_EN;
	  @Property(nameInDb = "City_CN")
	  private String city_CN;
	  @Property(nameInDb = "Country_code")
	  private String country_Code;
	  @Property(nameInDb = "Country_EN")
	  private String country_EN;
	  @Property(nameInDb = "Country_CN")
	  private String country_CN;
	  @Property(nameInDb = "Province_EN")
	  private String province_EN;
	  @Property(nameInDb = "Province_CN")
	  private String province_CN;
	  @Property(nameInDb = "Admin_district_EN")
	  private String admin_District_EN;
	  @Property(nameInDb = "Admin_district_CN")
	  private String admin_District_CN;
	  @Property(nameInDb = "Latitude")
	  private Float latitude;
	  @Property(nameInDb = "Longitude")
	  private Float longitude;
	  @Property(nameInDb = "AD_code")
	  private String ad_Code;
			@Generated(hash = 557978206)
			public ChinaCityInfo(Long city_Id, String city_EN, String city_CN,
					String country_Code, String country_EN, String country_CN, String province_EN,
					String province_CN, String admin_District_EN, String admin_District_CN,
					Float latitude, Float longitude, String ad_Code) {
				this.city_Id = city_Id;
				this.city_EN = city_EN;
				this.city_CN = city_CN;
				this.country_Code = country_Code;
				this.country_EN = country_EN;
				this.country_CN = country_CN;
				this.province_EN = province_EN;
				this.province_CN = province_CN;
				this.admin_District_EN = admin_District_EN;
				this.admin_District_CN = admin_District_CN;
				this.latitude = latitude;
				this.longitude = longitude;
				this.ad_Code = ad_Code;
			}
			@Generated(hash = 484638101)
			public ChinaCityInfo() {
			}
			public Long getCity_Id() {
				return this.city_Id;
			}
			public void setCity_Id(Long city_Id) {
				this.city_Id = city_Id;
			}
			public String getCity_EN() {
				return this.city_EN;
			}
			public void setCity_EN(String city_EN) {
				this.city_EN = city_EN;
			}
			public String getCity_CN() {
				return this.city_CN;
			}
			public void setCity_CN(String city_CN) {
				this.city_CN = city_CN;
			}
			public String getCountry_Code() {
				return this.country_Code;
			}
			public void setCountry_Code(String country_Code) {
				this.country_Code = country_Code;
			}
			public String getCountry_EN() {
				return this.country_EN;
			}
			public void setCountry_EN(String country_EN) {
				this.country_EN = country_EN;
			}
			public String getCountry_CN() {
				return this.country_CN;
			}
			public void setCountry_CN(String country_CN) {
				this.country_CN = country_CN;
			}
			public String getProvince_EN() {
				return this.province_EN;
			}
			public void setProvince_EN(String province_EN) {
				this.province_EN = province_EN;
			}
			public String getProvince_CN() {
				return this.province_CN;
			}
			public void setProvince_CN(String province_CN) {
				this.province_CN = province_CN;
			}
			public String getAdmin_District_EN() {
				return this.admin_District_EN;
			}
			public void setAdmin_District_EN(String admin_District_EN) {
				this.admin_District_EN = admin_District_EN;
			}
			public String getAdmin_District_CN() {
				return this.admin_District_CN;
			}
			public void setAdmin_District_CN(String admin_District_CN) {
				this.admin_District_CN = admin_District_CN;
			}
			public Float getLatitude() {
				return this.latitude;
			}
			public void setLatitude(Float latitude) {
				this.latitude = latitude;
			}
			public Float getLongitude() {
				return this.longitude;
			}
			public void setLongitude(Float longitude) {
				this.longitude = longitude;
			}
			public String getAd_Code() {
				return this.ad_Code;
			}
			public void setAd_Code(String ad_Code) {
				this.ad_Code = ad_Code;
			}


}
