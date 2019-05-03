package com.edwardwmd.weather.mvp.presenter;

import com.edwardwmd.weather.base.BasePresenter;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.mvp.model.data.GreenDaoOptions;
import com.edwardwmd.weather.mvp.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


	  private GreenDaoOptions mGreenDaoOptions;


	  @Inject
	  public MainPresenter(GreenDaoOptions mGreenDaoOptions) {
		    this.mGreenDaoOptions = mGreenDaoOptions;
	  }


	  @Override
	  public void showAllData() {
		    List<ChinaCityInfo> chinaCityInfos = mGreenDaoOptions.queryAlCity();
		    if (!isAttachView()) {
				return;
		    } else {

		    	  if (chinaCityInfos!=null&&chinaCityInfos.size()>0){
				mView.showAllCity(chinaCityInfos);
				mView.showLoading();
			  }else {
		    	  	  mView.showErrorMsg("No Data!!");
		    	  	  mView.hideLoading();
			  }
		    }

	  }


}
