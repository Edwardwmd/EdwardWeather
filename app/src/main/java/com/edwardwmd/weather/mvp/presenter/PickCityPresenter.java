package com.edwardwmd.weather.mvp.presenter;


import android.annotation.SuppressLint;
import android.util.Log;

import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.bean.HotCity;
import com.edwardwmd.weather.mvp.contract.PickCityContract;
import com.edwardwmd.weather.mvp.model.data.GreenDaoOptions;
import com.edwardwmd.weather.utils.ThreadUtils;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class PickCityPresenter extends BasePresenter<PickCityContract.View> implements PickCityContract.Presenter {


      private GreenDaoOptions greenDaoOptions;
      private List<ChinaCityInfo> mAllCities=new ArrayList<>();
      private List<HotCity> mHotCities;
      private List<ChinaCityInfo> mResult;


      @Inject
      public PickCityPresenter(GreenDaoOptions gDO) {
            this.greenDaoOptions = gDO;
      }


      @SuppressLint("CheckResult")
      @Override
      public void searchAllCity() {
            mResult = new ArrayList<>();
		    mAllCities = greenDaoOptions.queryAlCity();
            //初始化热门城市
            if (mHotCities == null || mHotCities.isEmpty()) {

                  mHotCities = new ArrayList<>();
                  mHotCities.add(new HotCity(101010100L,
                          "北京",
                          "beijing",
                          "北京",
                          "beijing",
                          "北京",
                          116.40529,
                          39.904987,
                          "110,100,110,000,100,000"

                  ));
                  mHotCities.add(new HotCity(101010117L,
                          "上海",
                          "shanghai",
                          "上海",
                          "shanghai",
                          "上海",
                          121.47264,
                          31.231707,
                          "310,100,310,000"

                  ));
                  mHotCities.add(new HotCity(101012879L,
                          "广州",
                          "guangdong",
                          "广东",
                          "guangzhou",
                          "广州",
                          113.28064,
                          23.125177,
                          "440,101,440,100,440,000,000,000"

                  ));
                  mHotCities.add(new HotCity(101012925L,
                          "深圳",
                          "guangdong",
                          "广东",
                          "shenzhen",
                          "深圳",
                          114.085945,
                          22.547,
                          "440,301,440,300,440,000,000,000"

                  ));
                  mHotCities.add(new HotCity(101012015L,
                          "杭州",
                          "zhejiang",
                          "浙江",
                          "hangzhou",
                          "杭州",
                          120.15358,
                          30.287458,
                          "330,101,330,100,330,000"

                  ));
                  mHotCities.add(new HotCity(101011791L,
                          "南京",
                          "jiangsu",
                          "江苏",
                          "nanjing",
                          "南京",
                          118.76741,
                          32.041546,
                          "320,101,320,100,320,000"

                  ));
                  mHotCities.add(new HotCity(101010134L,
                          "天津",
                          "tianjin",
                          "天津",
                          "tianjin",
                          "天津",
                          117.190186,
                          39.125595,
                          "120,100,120,000"

                  ));
                  mHotCities.add(new HotCity(101011900L,
                          "武汉",
                          "hubei",
                          "湖北",
                          "wuhan",
                          "武汉",
                          114.29857,
                          30.584354,
                          "420,101,420,100,420,000"

                  ));
                  mHotCities.add(new HotCity(101012677L,
                          "成都",
                          "sichuan",
                          "四川",
                          "chengdu",
                          "成都",
                          104.065735,
                          30.659462,
                          "510,101,510,100,510,000"

                  ));
                  mHotCities.add(new HotCity(101012997L,
                          "东莞",
                          "guangdong",
                          "广东",
                          "dongguan",
                          "东莞",
                          113.74626,
                          23.046238,
                          "441900"

                  ));

            }

            Log.e("PICKCITY_线程11","当前是否为主线程："+ ThreadUtils.isMainThread()+"  "+ThreadUtils.getThreadId()+ThreadUtils.getThreadName());
            mAllCities.add(0, new HotCity(0L,
                    "热门城市",
                    "unknow",
                    "unknow",
                    "unknow",
                    "unknow",
                    0.0,
                    0.0,
                    "00000"));
            mView.postCityData(mAllCities, mHotCities);
            mResult = mAllCities;

      }


      @Override
      public List<ChinaCityInfo> AllData() {
            if (mResult != null && !mResult.isEmpty()) {
                  return mResult;
            }

            return greenDaoOptions.queryAlCity();
      }


      @Override
      public List<ChinaCityInfo> keywordsSearch(String keyword) {
            return greenDaoOptions.searchBykeyword(keyword);
      }


      @Override
      public void insertNewHotCitys(List<HotCity> hotCities) {
            if (hotCities != null && !hotCities.isEmpty()) {
                  this.mHotCities = hotCities;
            }

      }


}
