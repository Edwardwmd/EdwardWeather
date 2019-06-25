package com.edwardwmd.weather.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.adapter.CityListAdapter;
import com.edwardwmd.weather.base.BaseMVPDialogFragment;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.bean.HotCity;
import com.edwardwmd.weather.mvp.contract.PickCityContract;
import com.edwardwmd.weather.mvp.presenter.PickCityPresenter;
import com.edwardwmd.weather.utils.DisplayUtils;
import com.edwardwmd.weather.weight.citypickview.InnerListener;
import com.edwardwmd.weather.weight.citypickview.OnPickListener;
import com.edwardwmd.weather.weight.citypickview.SideIndexBar;
import com.edwardwmd.weather.weight.citypickview.decoration.DividerItemDecoration;
import com.edwardwmd.weather.weight.citypickview.decoration.SectionItemDecoration;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;


public class PickCityFragment extends BaseMVPDialogFragment<PickCityPresenter> implements PickCityContract.View,
        InnerListener,
        SideIndexBar.OnIndexTouchedChangedListener,
        TextWatcher {


      @BindView(R.id.cp_search_box)
      EditText cpSearchBox;
      @BindView(R.id.cp_clear_all)
      ImageView cpClearAll;
      @BindView(R.id.cp_cancel)
      TextView cpCancel;
      @BindView(R.id.cp_city_recyclerview)
      RecyclerView cpCityRecyclerview;
      @BindView(R.id.cp_overlay)
      TextView cpOverlay;
      @BindView(R.id.cp_side_index_bar)
      SideIndexBar cpSideIndexBar;
      @BindView(R.id.cp_empty_view)
      LinearLayout cpEmptyView;

      private int mAnimStyle = R.style.DefaultCityPickerAnimation;
      private OnPickListener mOnPickListener;
      private boolean enableAnim = false;
      private CityListAdapter mAdapter;
      private List<ChinaCityInfo> mResults;


      public static PickCityFragment newInstance(boolean enable) {
            final PickCityFragment fragment = new PickCityFragment();
            Bundle args = new Bundle();
            args.putBoolean("cp_enable_anim", enable);
            fragment.setArguments(args);
            return fragment;
      }


      @Override
      protected void initInject() {
            getFragmentComponent().inject(this);
      }


      @Override
      protected int getLayout() {
            return R.layout.cp_dialog_city_picker;
      }


      @Override
      protected void initView(View view) {
            super.initView(view);

      }


      @Override
      protected void initData() {
            super.initData();
            initArgumentsData();
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            cpCityRecyclerview.setLayoutManager(mLayoutManager);
            cpCityRecyclerview.setHasFixedSize(true);

            dPresenter.searchAllCity();

            mAdapter.setLayoutManager(mLayoutManager);
            cpCityRecyclerview.setAdapter(mAdapter);
            mAdapter.setInnerListener(this);

            cpSideIndexBar.setNavigationBarHeight(DisplayUtils.getNavigationBarHeight(Objects.requireNonNull(getActivity())));
            cpSideIndexBar.setOverlayTextView(cpOverlay)
                    .setOnIndexChangedListener(this);
            cpSearchBox.addTextChangedListener(this);
            cpCityRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
                  @Override
                  public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        //确保热门城市能正常刷新
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                              mAdapter.refreshHotCityItem();
                        }
                  }


                  @Override
                  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                  }
            });
      }


      @Override
      public void useNightMode(boolean isNight) {

      }


      @OnClick({R.id.cp_clear_all, R.id.cp_cancel})
      public void onViewClicked(View view) {
            switch (view.getId()) {

                  case R.id.cp_clear_all:
                        cpSearchBox.setText("");
                        break;
                  case R.id.cp_cancel:
                        dismiss();
                        if (mOnPickListener != null)
                              mOnPickListener.onCancel();

                        break;

            }
      }


      @Override
      public void postCityData(List<ChinaCityInfo> chinaCityInfos, List<HotCity> hotCities) {
            if (chinaCityInfos != null && chinaCityInfos.size() > 0) {
                  cpCityRecyclerview.addItemDecoration(new SectionItemDecoration(getActivity(), chinaCityInfos), 0);
                  cpCityRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity()), 1);
                  mAdapter = new CityListAdapter(getActivity(), chinaCityInfos, hotCities);

                  mResults = chinaCityInfos;
            }

      }


      @Override
      protected void initTheme(Window window) {
            if (enableAnim) {
                  window.setWindowAnimations(mAnimStyle);
            }
      }


      @Override
      protected void doForKeyBlack() {
            if (mOnPickListener != null) {
                  mOnPickListener.onCancel();
            }
      }


      private void initArgumentsData() {
            Bundle args = getArguments();
            if (args != null) {
                  enableAnim = args.getBoolean("cp_enable_anim");
            }
      }


      @Override
      public void dismiss(int position, ChinaCityInfo data) {
            dismiss();
            if (mOnPickListener != null) {
                  mOnPickListener.onPick(position, data);
            }
      }


      @Override
      public void onIndexChanged(String index, int position) {
            //滚动RecyclerView到索引位置
            mAdapter.scrollToSection(index);
      }


      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }


      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }


      @Override
      public void afterTextChanged(Editable s) {
            String keyword = s.toString();
            if (TextUtils.isEmpty(keyword)) {
                  cpCancel.setVisibility(View.GONE);
                  cpEmptyView.setVisibility(View.GONE);
                  mResults = dPresenter.AllData();
                  ((SectionItemDecoration) (cpCityRecyclerview.getItemDecorationAt(0))).setData(mResults);
                  mAdapter.updateData(mResults);
            } else {
                  cpClearAll.setVisibility(View.VISIBLE);
                  //开始数据库查找
                  mResults = dPresenter.keywordsSearch(keyword);
                  ((SectionItemDecoration) (cpCityRecyclerview.getItemDecorationAt(0))).setData(mResults);
                  if (mResults == null || mResults.isEmpty()) {
                        cpEmptyView.setVisibility(View.VISIBLE);
                  } else {
                        cpEmptyView.setVisibility(View.GONE);
                        mAdapter.updateData(mResults);
                  }
            }
            cpCityRecyclerview.scrollToPosition(0);
      }


      @SuppressLint("ResourceType")
      public void setAnimationStyle(@StyleRes int resId) {
            this.mAnimStyle = resId <= 0 ? mAnimStyle : resId;
      }


      public void setHotCities(List<HotCity> data) {
            if (data != null && !data.isEmpty()) {
                  dPresenter.insertNewHotCitys(data);

            }
      }


      public void setOnPickListener(OnPickListener listener) {
            this.mOnPickListener = listener;
      }


}
