package com.edwardwmd.weather.base;

/**
 * Created by codeest on 2016/8/2.
 * View基类
 */
public interface BaseView {

    void showErrorMsg(String msg);

    default void showLoading(){}

    default void hideLoading(){}

    void useNightMode(boolean isNight);


}
