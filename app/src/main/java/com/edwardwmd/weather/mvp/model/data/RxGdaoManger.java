package com.edwardwmd.weather.mvp.model.data;

import com.edwardwmd.weather.bean.ChinaCityInfo;

import java.util.List;

import io.reactivex.Observable;


public interface RxGdaoManger {

      /**
       * 删除数据
       *
       * @return Observable<Boolean>
       */
      Observable<Boolean> delCities();

      /**
       * 查询所有数据
       *
       * @return Observable<List < ChinaCityInfo>>
       */
      Observable<List<ChinaCityInfo>> queryAllCity();

      /**
       * 模糊查询
       *
       * @param keyWord 关键字
       * @return Observable<List < ChinaCityInfo>>
       */
      Observable<List<ChinaCityInfo>> searchCity(String keyWord);

}
