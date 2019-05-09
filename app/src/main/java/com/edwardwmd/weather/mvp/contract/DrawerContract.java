package com.edwardwmd.weather.mvp.contract;

import android.content.Context;

import com.edwardwmd.weather.base.BaseView;
import com.edwardwmd.weather.base.IPresenter;

public interface DrawerContract {


	  interface Model {


	  }


	  interface View extends BaseView {





	  }


	  interface Presenter extends IPresenter<View> {


		    void initDrawerData();



	  }


}
