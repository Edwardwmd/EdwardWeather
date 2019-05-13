package com.edwardwmd.weather.weight.citypickview;



import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.edwardwmd.weather.bean.HotCity;
import com.edwardwmd.weather.ui.fragment.PickCityFragment;

import java.lang.ref.WeakReference;
import java.util.List;

public class CityPicker {
    private static final String TAG = CityPicker.class.getSimpleName();

    private WeakReference<FragmentActivity> mContext;
    private WeakReference<Fragment> mFragment;
    private WeakReference<FragmentManager> mFragmentManager;

    private boolean enableAnim;
    private int mAnimStyle;
    private List<HotCity> mHotCities;
    private OnPickListener mOnPickListener;

    private CityPicker(){}

    private CityPicker(Fragment fragment){
        this(fragment.getActivity(), fragment);
        mFragmentManager = new WeakReference<>(fragment.getChildFragmentManager());
    }

    private CityPicker(FragmentActivity activity){
        this(activity, null);
        mFragmentManager = new WeakReference<>(activity.getSupportFragmentManager());
    }

    private CityPicker(FragmentActivity activity, Fragment fragment){
        mContext = new WeakReference<>(activity);
        mFragment = new WeakReference<>(fragment);
    }

    public static CityPicker from(Fragment fragment){
        return new CityPicker(fragment);
    }

    public static CityPicker from(FragmentActivity activity){
        return new CityPicker(activity);
    }

    /**
     * 设置动画效果
     * @param animStyle
     * @return
     */
    public CityPicker setAnimationStyle(@StyleRes int animStyle) {
        this.mAnimStyle = animStyle;
        return this;
    }


    public CityPicker setHotCities(List<HotCity> data){
        this.mHotCities = data;
        return this;
    }

    /**
     * 启用动画效果，默认为false
     * @param enable
     * @return
     */
    public CityPicker enableAnimation(boolean enable){
        this.enableAnim = enable;
        return this;
    }

    /**
     * 设置选择结果的监听器
     * @param listener
     * @return
     */
    public CityPicker setOnPickListener(OnPickListener listener){
        this.mOnPickListener = listener;
        return this;
    }

    public void show(){
        FragmentTransaction ft = mFragmentManager.get().beginTransaction();
        final Fragment prev = mFragmentManager.get().findFragmentByTag(TAG);
        if (prev != null){
            ft.remove(prev).commit();
            ft = mFragmentManager.get().beginTransaction();
        }
        ft.addToBackStack(null);

        final PickCityFragment pickCityFragment=PickCityFragment.newInstance(enableAnim);
        pickCityFragment.setHotCities(mHotCities);
        pickCityFragment.setAnimationStyle(mAnimStyle);
        pickCityFragment.setOnPickListener(mOnPickListener);
        pickCityFragment.show(ft,TAG);

    }

}
