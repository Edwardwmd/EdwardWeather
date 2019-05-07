package com.edwardwmd.weather.bean;

import android.text.TextUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(
	  //指定Table名称
	  nameInDb = "ChinaCityInfo",
	  //因为已存在表所以无需创建
	  createInDb = false,
	  // 是否生成属性的getter和setter
	  generateGettersSetters = true
)
public class ChinaCityInfo {


	  @Property(nameInDb = "City_ID")
	  @Id(autoincrement = true)
	  private Long City_ID;
	  @Property(nameInDb = "City_EN")
	  private String City_EN;
	  @Property(nameInDb = "City_CN")
	  private String City_CN;
	  @Property(nameInDb = "Country_code")
	  private String Country_code;
	  @Property(nameInDb = "Country_EN")
	  private String Country_EN;
	  @Property(nameInDb = "Country_CN")
	  private String Country_CN;
	  @Property(nameInDb = "Province_EN")
	  private String Province_EN;
	  @Property(nameInDb = "Province_CN")
	  private String Province_CN;
	  @Property(nameInDb = "Admin_district_EN")
	  private String Admin_district_EN;
	  @Property(nameInDb = "Admin_district_CN")
	  private String Admin_district_CN;
	  @Property(nameInDb = "Latitude")
	  private Double Latitude;
	  @Property(nameInDb = "Longitude")
	  private Double Longitude;
	  @Property(nameInDb = "AD_code")
	  private String AD_code;

			@Generated(hash = 94840675)
			public ChinaCityInfo(Long City_ID, String City_EN, String City_CN,
					String Country_code, String Country_EN, String Country_CN, String Province_EN,
					String Province_CN, String Admin_district_EN, String Admin_district_CN,
					Double Latitude, Double Longitude, String AD_code) {
				this.City_ID = City_ID;
				this.City_EN = City_EN;
				this.City_CN = City_CN;
				this.Country_code = Country_code;
				this.Country_EN = Country_EN;
				this.Country_CN = Country_CN;
				this.Province_EN = Province_EN;
				this.Province_CN = Province_CN;
				this.Admin_district_EN = Admin_district_EN;
				this.Admin_district_CN = Admin_district_CN;
				this.Latitude = Latitude;
				this.Longitude = Longitude;
				this.AD_code = AD_code;
			}

			@Generated(hash = 484638101)
			public ChinaCityInfo() {
			}

	  /***
	   * 获取悬浮栏文本，（#、定位、热门 需要特殊处理）
	   * @return
	   */
	  public String getSection(){
		    if (TextUtils.isEmpty(City_EN)) {
				return "#";
		    } else {
				String c = City_EN.substring(0, 1);
				Pattern p = Pattern.compile("[a-zA-Z]");
				Matcher m = p.matcher(c);
				if (m.matches()) {
					  return c.toUpperCase();
				}
				//在添加定位和热门数据时设置的section就是‘定’、’热‘开头
				else if (TextUtils.equals(c, "热"))
					  return City_EN;
				else
					  return "#";
		    }
	  }

			public Long getCity_ID() {
				return this.City_ID;
			}

			public void setCity_ID(Long City_ID) {
				this.City_ID = City_ID;
			}

			public String getCity_EN() {
				return this.City_EN;
			}

			public void setCity_EN(String City_EN) {
				this.City_EN = City_EN;
			}

			public String getCity_CN() {
				return this.City_CN;
			}

			public void setCity_CN(String City_CN) {
				this.City_CN = City_CN;
			}

			public String getCountry_code() {
				return this.Country_code;
			}

			public void setCountry_code(String Country_code) {
				this.Country_code = Country_code;
			}

			public String getCountry_EN() {
				return this.Country_EN;
			}

			public void setCountry_EN(String Country_EN) {
				this.Country_EN = Country_EN;
			}

			public String getCountry_CN() {
				return this.Country_CN;
			}

			public void setCountry_CN(String Country_CN) {
				this.Country_CN = Country_CN;
			}

			public String getProvince_EN() {
				return this.Province_EN;
			}

			public void setProvince_EN(String Province_EN) {
				this.Province_EN = Province_EN;
			}

			public String getProvince_CN() {
				return this.Province_CN;
			}

			public void setProvince_CN(String Province_CN) {
				this.Province_CN = Province_CN;
			}

			public String getAdmin_district_EN() {
				return this.Admin_district_EN;
			}

			public void setAdmin_district_EN(String Admin_district_EN) {
				this.Admin_district_EN = Admin_district_EN;
			}

			public String getAdmin_district_CN() {
				return this.Admin_district_CN;
			}

			public void setAdmin_district_CN(String Admin_district_CN) {
				this.Admin_district_CN = Admin_district_CN;
			}

			public Double getLatitude() {
				return this.Latitude;
			}

			public void setLatitude(Double Latitude) {
				this.Latitude = Latitude;
			}

			public Double getLongitude() {
				return this.Longitude;
			}

			public void setLongitude(Double Longitude) {
				this.Longitude = Longitude;
			}

			public String getAD_code() {
				return this.AD_code;
			}

			public void setAD_code(String AD_code) {
				this.AD_code = AD_code;
			}







}
