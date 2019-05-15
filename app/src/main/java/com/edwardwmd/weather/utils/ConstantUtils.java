package com.edwardwmd.weather.utils;


import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.ui.activity.WebActivity;
import com.edwardwmd.weather.ui.fragment.MainFragment;

public class ConstantUtils {


	  //默认时间（ms）
	  public final static long DEAUFULT_TIME = 500;
	  public final static long LONG_TIME = 1500;

	  //默认经纬度
	  public static Double NOW_LON = 113.28064;
	  public static Double NOW_LAT = 23.125177;

	  //数据库表Flag
	  public final static int ALL_CITY_POSITION = 1;
	  public final static int HOT_CITY_POSITION = 2;

	  //用户id&&用户key
	  public static final String APK_USERNAME = "HE1904061032181216";
	  public static final String APK_KEY = "15f1ad91592a4577b6efdb8acfa692b2";

	  //数据库名
	  public final static String DB_NAME = "city.db";
	  //数据库文件路径
	  public final static String DB_PATH = "/data/data/" + EdWeatherApp.getAppContext().getPackageName() + "/databases/";

	  public final static String MAIN_FRAGMENT_KEYS = MainFragment.class.getSimpleName();
	  public final static String START_REFRESH = "start_refresh_and_loading_data_for_main";

	  public final static String WEBVIEW_KEY= WebActivity.class.getSimpleName();

	  //Github地址
	  public final static String GITHUB_URL = "https://github.com/Edwardwmd?tab=repositories";

	  //个人博客
	  public final static String PERSONAL_BLOG_URL="https://www.csdn.net/";

	  public final static String LOCATION_LON_LAT_KEY="location_lon_lat_key";


}
