package com.edwardwmd.weather.mvp.contract;

import com.edwardwmd.weather.base.BaseView;
import com.edwardwmd.weather.base.IPresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;

public interface LauncherContract {


	  interface Model {


	  }


	  interface View extends BaseView {
		    void turnToMain();

	  }


	  interface Presenter extends IPresenter<View> {
        void chearkPermissions(RxPermissions permissions);

	  }


}
