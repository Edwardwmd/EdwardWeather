package com.edwardwmd.weather.utils;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.ui.fragment.MainFragment;

public class ConstantUtils {


	  //默认时间（ms）
	  public final static long DEAUFULT_TIME = 500;
	  public final static long LONG_TIME = 1500;

	  //默认经纬度
	  public static Double NOW_LON = 112.57;
	  public static Double NOW_LAT = 22.26;

	  //数据库表Flag
	  public final static int ALL_CITY_POSITION = 1;
	  public final static int HOT_CITY_POSITION = 2;

	  //用户id&&用户key
	  public static final String APK_USERNAME = "HE1904061032181216";
	  public static final String APK_KEY = "15f1ad91592a4577b6efdb8acfa692b2";

	  //数据库名
	  public final static String DB_NAME = "city.db";
	  //数据库文件路径
	  public static final String DB_PATH = "/data/data/" + EdWeatherApp.getAppContext().getPackageName() + "/databases/";

	  public final static String MAIN_FRAGMENT_KEYS = MainFragment.class.getSimpleName();
	  public final static String START_REFRESH = "start_refresh_and_loading_data_for_main";




}
