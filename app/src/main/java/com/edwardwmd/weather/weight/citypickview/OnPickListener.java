package com.edwardwmd.weather.weight.citypickview;


import com.edwardwmd.weather.bean.ChinaCityInfo;

public interface OnPickListener {
    void onPick(int position, ChinaCityInfo data);
//    void onLocate();
    void onCancel();
}
