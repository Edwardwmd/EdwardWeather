package com.edwardwmd.weather.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends BaseView> implements IPresenter<V> {


	  protected V mView;
	  protected CompositeDisposable mCompositeDisposable;


	  protected void unSubscribe() {
		    if (mCompositeDisposable != null) {
				mCompositeDisposable.clear();
				mCompositeDisposable = null;
		    }
	  }


	  protected void addSubscribe(Disposable subscription) {
		    if (mCompositeDisposable == null) {
				mCompositeDisposable = new CompositeDisposable();
		    }
		    mCompositeDisposable.add(subscription);
	  }


	  @Override
	  public void attachView(V view) {
		    this.mView = view;
	  }


	  @Override
	  public void detachView() {
		    if (this.mView != null)
				this.mView = null;
		    unSubscribe();

	  }


	  @Override
	  public boolean isAttachView() {
		    return this.mView != null;
	  }


}
