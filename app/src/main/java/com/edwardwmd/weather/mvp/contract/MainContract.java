package com.edwardwmd.weather.mvp.contract;

import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.base.BaseView;
import com.edwardwmd.weather.base.IPresenter;
import com.edwardwmd.weather.bean.ChinaCityInfo;

import java.util.List;

public interface MainContract {


	  interface View extends BaseView {
         void showAllCity(List<ChinaCityInfo>chinaCityInfos);

	  }


	  interface Presenter extends IPresenter<View> {
          void showAllData();

	  }


}
