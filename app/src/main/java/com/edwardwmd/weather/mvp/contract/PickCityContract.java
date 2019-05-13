package com.edwardwmd.weather.mvp.contract;

import android.app.Dialog;
import android.content.Context;

import com.edwardwmd.weather.base.BaseView;
import com.edwardwmd.weather.base.IPresenter;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.bean.HotCity;
import com.edwardwmd.weather.ui.fragment.PickCityFragment;

import java.util.List;

public interface PickCityContract {


	  interface View extends BaseView {


		    void postCityData(List<ChinaCityInfo> chinaCityInfos, List<HotCity> hotCities);


	  }


	  interface Presenter extends IPresenter<View> {


		    void searchAllCity();

		    List<ChinaCityInfo> AllData();

		    List<ChinaCityInfo> keywordsSearch(String keyword);

		    void insertNewHotCitys(List<HotCity> hotCities);


	  }


}
