package com.edwardwmd.weather.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
         
         /**
          * 获取系统当前时间
          * @return mSystemDate
          */
         public static String getCurrentSystemDate() {
                  String mSystemDate = "2019年1月1日 12:00:00";
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd- HH:mm");
                  Date date = new Date(System.currentTimeMillis());
                  mSystemDate = simpleDateFormat.format(date);
                  return mSystemDate;
         }
         
         /**
          * 日期转换成星期
          */
         public static String dateToWeek(String datetime) {
                  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                  String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
                  Calendar cal = Calendar.getInstance(); // 获得一个日历
                  Date datet = null;
                  try {
                           datet = f.parse(datetime);
                           cal.setTime(datet);
                  } catch (ParseException e) {
                           e.printStackTrace();
                  }
                  int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
                  if (w < 0)
                           w = 0;
                  return weekDays[w];
         }

         
}
