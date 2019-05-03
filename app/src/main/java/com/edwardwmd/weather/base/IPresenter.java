package com.edwardwmd.weather.base;

/**
 * Created by codeest on 2016/8/2.
 * Presenter基类
 */
public interface IPresenter<V extends BaseView>{

    void attachView(V view);

    void detachView();

    boolean isAttachView();

}
