package com.edwardwmd.weather.mvp.model.data;


import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.bean.ChinaCityInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 时间：2019/3/21/14：32
 * 作者：EdwardWu
 * 网站：https://github.com/Edwardwmd
 * 作用：数据库增删改查操作类
 * 声明：版权归作者所有
 */

public class GreenDaoOptions {


	  private static String TAG = GreenDaoOptions.class.getSimpleName();

	  private DaoManager manager;


	  /**
	   * 初始化数据表操作类
	   *
	   */
	  public GreenDaoOptions() {
			manager = DaoManager.getInstance();
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
			final List<ChinaCityInfo> cityInfoBeans = manager.getDaoSession().loadAll(ChinaCityInfo.class);
			manager.closeConnection();
			return cityInfoBeans;
	  }



	  /**
	   * 使用queryBuilder进行查询
	   *
	   * @return
	   */
	  public List<ChinaCityInfo> queryOneCityByBuilder(String city_cn) {
			QueryBuilder<ChinaCityInfo> queryBuilder = manager.getDaoSession().queryBuilder(ChinaCityInfo.class);
			final List<ChinaCityInfo> cityInfoBeanList = queryBuilder.where(ChinaCityInfoDao.Properties.City_CN.eq(city_cn)).list();
			manager.closeConnection();
			return cityInfoBeanList;
	  }


	  /**
	   * 查询所有热点城市
	   * @return hotcity
	   */
//	  public List<HotCity> queryAllHotCity() {
//			final List<HotCity> hotCities = manager.getDaoSession().loadAll(HotCity.class);
//			manager.closeConnection();
//			return hotCities;
//	  }


}
