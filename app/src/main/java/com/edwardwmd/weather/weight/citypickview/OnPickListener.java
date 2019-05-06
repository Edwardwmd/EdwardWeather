package com.edwardwmd.weather.weight.citypickview;


import com.edwardwmd.weather.bean.City;

public interface OnPickListener {
    void onPick(int position, City data);
    void onCancel();
}
