package com.edwardwmd.weather.bean;

public class HotCity extends ChinaCityInfo {


	  public HotCity(Long id,
			     String cityName_cn,
			     String province_EN,
			     String province_CN,
			     String admin_district_EN,
			     String admin_district_CN,
			     Double lon,
			     Double lat,
			     String ad_code) {
		    super(id,
				"热门城市",
				cityName_cn,
				"CN",
				"China",
				"中国",
				province_EN,
				province_CN,
				admin_district_EN,
				admin_district_CN,
				lat,
				lon,
				ad_code);
	  }


}
