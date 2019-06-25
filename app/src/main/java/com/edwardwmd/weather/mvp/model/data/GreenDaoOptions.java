package com.edwardwmd.weather.mvp.model.data;


import android.util.Log;

import com.edwardwmd.weather.EdWeatherApp;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.bean.ChinaCityInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 时间：2019/3/21/14：32
 * 作者：EdwardWu
 * 网站：https://github.com/Edwardwmd
 * 作用：数据库增删改查操作类
 * 声明：版权归作者所有
 */

public class GreenDaoOptions{


      private static String TAG = GreenDaoOptions.class.getSimpleName();

      private DaoManager manager;


      /**
       * 初始化数据表操作类
       */
      public GreenDaoOptions() {
            manager = DaoManager.getInstance();
            manager.init(EdWeatherApp.getAppContext());
      }


      /**
       * 删除所有数据
       *
       * @return boolean
       */
      public boolean delAllCity() {

            boolean flag = false;
            try {
                  manager.getDaoSession().deleteAll(ChinaCityInfo.class);
                  flag = true;
            } catch (Exception e) {
                  e.printStackTrace();
            }
            manager.closeConnection();
            return flag;
      }


      /**
       * 查询所有城市记录
       *
       * @return
       */
      public List<ChinaCityInfo> queryAlCity() {
           List<ChinaCityInfo> allCitys = manager
                    .getDaoSession()
                    .loadAll(ChinaCityInfo.class);
            manager.closeConnection();
            Collections.sort(allCitys, new CityComparator());
            return allCitys;
      }


      /**
       * 模糊查询
       *
       * @return List
       */
      public List<ChinaCityInfo> searchBykeyword(String keyword) {
            QueryBuilder<ChinaCityInfo> queryBuilder = manager.getDaoSession().queryBuilder(ChinaCityInfo.class);
            final List<ChinaCityInfo> searchResult = queryBuilder
                    .whereOr(ChinaCityInfoDao.Properties.City_CN.like("%" + keyword.toLowerCase() + "%"),
                            ChinaCityInfoDao.Properties.City_EN.like("%" + keyword.toLowerCase() + "%"),
                            ChinaCityInfoDao.Properties.Province_CN.like("%" + keyword.toLowerCase() + "%"),
                            ChinaCityInfoDao.Properties.Province_EN.like("%" + keyword.toLowerCase() + "%"),
                            ChinaCityInfoDao.Properties.Admin_district_CN.like("%" + keyword.toLowerCase() + "%"),
                            ChinaCityInfoDao.Properties.Admin_district_EN.like("%" + keyword.toLowerCase() + "%")
                    ).list();
            manager.closeConnection();
            Collections.sort(searchResult, new CityComparator());
            return searchResult;
      }

      /**
       * 字母排序（A-Z）
       */
      private class CityComparator implements Comparator<ChinaCityInfo> {

            @Override
            public int compare(ChinaCityInfo lhs, ChinaCityInfo rhs) {
                  String a = lhs.getCity_EN().substring(0, 1);
                  String b = rhs.getCity_EN().substring(0, 1);
                  return a.compareTo(b);
            }
      }

}
