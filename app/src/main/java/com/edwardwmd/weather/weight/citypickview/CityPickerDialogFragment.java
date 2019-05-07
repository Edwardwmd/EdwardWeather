package com.edwardwmd.weather.weight.citypickview;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.adapter.CityListAdapter;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.bean.HotCity;
import com.edwardwmd.weather.mvp.model.data.GreenDaoOptions;
import com.edwardwmd.weather.utils.DisplayUtils;
import com.edwardwmd.weather.weight.citypickview.decoration.DividerItemDecoration;
import com.edwardwmd.weather.weight.citypickview.decoration.SectionItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Bro0cL
 * @Date: 2018/2/6 20:50
 */
public class CityPickerDialogFragment extends DialogFragment implements TextWatcher,
	  View.OnClickListener, SideIndexBar.OnIndexTouchedChangedListener, InnerListener {


	  private View mContentView;
	  private RecyclerView mRecyclerView;
	  private View mEmptyView;
	  private TextView mOverlayTextView;
	  private SideIndexBar mIndexBar;
	  private EditText mSearchBox;
	  private TextView mCancelBtn;
	  private ImageView mClearAllBtn;

	  private LinearLayoutManager mLayoutManager;
	  private CityListAdapter mAdapter;
	  private List<ChinaCityInfo> mAllCities;
	  private List<HotCity> mHotCities;
	  private List<ChinaCityInfo> mResults;

	  private GreenDaoOptions dbManager;

	  private int height;
	  private int width;

	  private boolean enableAnim = false;
	  private int mAnimStyle = R.style.DefaultCityPickerAnimation;
	  //    private LocatedCity mLocatedCity;
	  private int locateState;
	  private OnPickListener mOnPickListener;


	  /**
	   * 获取实例
	   *
	   * @param enable 是否启用动画效果
	   * @return
	   */
	  public static CityPickerDialogFragment newInstance(boolean enable) {
		    final CityPickerDialogFragment fragment = new CityPickerDialogFragment();
		    Bundle args = new Bundle();
		    args.putBoolean("cp_enable_anim", enable);
		    fragment.setArguments(args);
		    return fragment;
	  }


	  @Override
	  public void onCreate(@Nullable Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setStyle(STYLE_NORMAL, R.style.CityPickerStyle);
	  }


	  public void setHotCities(List<HotCity> data) {
		    if (data != null && !data.isEmpty()) {
				this.mHotCities = data;
		    }
	  }


	  @SuppressLint("ResourceType")
	  public void setAnimationStyle(@StyleRes int resId) {
		    this.mAnimStyle = resId <= 0 ? mAnimStyle : resId;
	  }


	  @Nullable
	  @Override
	  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		    mContentView = inflater.inflate(R.layout.cp_dialog_city_picker, container, false);
		    return mContentView;
	  }


	  @Override
	  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		    super.onViewCreated(view, savedInstanceState);
		    initData();
		    initViews();
	  }


	  private void initViews() {
		    mRecyclerView = mContentView.findViewById(R.id.cp_city_recyclerview);
		    mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
		    mRecyclerView.setLayoutManager(mLayoutManager);
		    mRecyclerView.setHasFixedSize(true);
		    mRecyclerView.addItemDecoration(new SectionItemDecoration(getActivity(), mAllCities), 0);
		    mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()), 1);
		    mAdapter = new CityListAdapter(getActivity(), mAllCities, mHotCities);
//        mAdapter.autoLocate(true);
		    mAdapter.setInnerListener(this);
		    mAdapter.setLayoutManager(mLayoutManager);
		    mRecyclerView.setAdapter(mAdapter);
		    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
				@Override
				public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
					  //确保定位城市能正常刷新
					  if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    mAdapter.refreshLocationItem();
					  }
				}


				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				}
		    });

		    mEmptyView = mContentView.findViewById(R.id.cp_empty_view);
		    mOverlayTextView = mContentView.findViewById(R.id.cp_overlay);

		    mIndexBar = mContentView.findViewById(R.id.cp_side_index_bar);
		    mIndexBar.setNavigationBarHeight(DisplayUtils.getNavigationBarHeight(getActivity()));
		    mIndexBar.setOverlayTextView(mOverlayTextView)
				.setOnIndexChangedListener(this);

		    mSearchBox = mContentView.findViewById(R.id.cp_search_box);
		    mSearchBox.addTextChangedListener(this);

		    mCancelBtn = mContentView.findViewById(R.id.cp_cancel);
		    mClearAllBtn = mContentView.findViewById(R.id.cp_clear_all);
		    mCancelBtn.setOnClickListener(this);
		    mClearAllBtn.setOnClickListener(this);
	  }


	  private void initData() {
		    Bundle args = getArguments();
		    if (args != null) {
				enableAnim = args.getBoolean("cp_enable_anim");
		    }
		    //初始化热门城市
		    if (mHotCities == null || mHotCities.isEmpty()) {
				mHotCities = new ArrayList<>();
				mHotCities.add(new HotCity(101010100L,
					  "北京",
					  "beijing",
					  "北京",
					  "beijing",
					  "北京",
					  116.40529,
					  39.904987,
					  "110,100,110,000,100,000"

				));
				mHotCities.add(new HotCity(101010117L,
					  "上海",
					  "shanghai",
					  "上海",
					  "shanghai",
					  "上海",
					  121.47264,
					  31.231707,
					  "310,100,310,000"

				));
				mHotCities.add(new HotCity(101012879L,
					  "广州",
					  "guangdong",
					  "广东",
					  "guangzhou",
					  "广州",
					  113.28064,
					  23.125177,
					  "440,101,440,100,440,000,000,000"

				));
				mHotCities.add(new HotCity(101012925L,
					  "深圳",
					  "guangdong",
					  "广东",
					  "shenzhen",
					  "深圳",
					  114.085945,
					  22.547,
					  "440,301,440,300,440,000,000,000"

				));
				mHotCities.add(new HotCity(101012015L,
					  "杭州",
					  "zhejiang",
					  "浙江",
					  "hangzhou",
					  "杭州",
					  120.15358,
					  30.287458,
					  "330,101,330,100,330,000"

				));
				mHotCities.add(new HotCity(101011791L,
					  "南京",
					  "jiangsu",
					  "江苏",
					  "nanjing",
					  "南京",
					  118.76741,
					  32.041546,
					  "320,101,320,100,320,000"

				));
				mHotCities.add(new HotCity(101010134L,
					  "天津",
					  "tianjin",
					  "天津",
					  "tianjin",
					  "天津",
					  117.190186,
					  39.125595,
					  "120,100,120,000"

				));
				mHotCities.add(new HotCity(101011900L,
					  "武汉",
					  "hubei",
					  "湖北",
					  "wuhan",
					  "武汉",
					  114.29857,
					  30.584354,
					  "420,101,420,100,420,000"

				));
				mHotCities.add(new HotCity(101012677L,
					  "成都",
					  "sichuan",
					  "四川",
					  "chengdu",
					  "成都",
					  104.065735,
					  30.659462,
					  "510,101,510,100,510,000"

				));
				mHotCities.add(new HotCity(101012997L,
					  "东莞",
					  "guangdong",
					  "广东",
					  "dongguan",
					  "东莞",
					  113.74626,
					  23.046238,
					  "441900"

				));

		    }

		    dbManager = new GreenDaoOptions(getActivity());
		    mAllCities = dbManager.queryAlCity();
		    mAllCities.add(0, new HotCity(0L,
				"热门城市",
				"unknow",
				"unknow",
				"unknow",
				"unknow",
				0.0,
				0.0,
				"00000"));
		    mResults = mAllCities;
	  }


	  @Override
	  public void onStart() {
		    super.onStart();
		    Dialog dialog = getDialog();
		    dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					  if (keyCode == KeyEvent.KEYCODE_BACK) {
						    if (mOnPickListener != null) {
								mOnPickListener.onCancel();
						    }
					  }
					  return false;
				}
		    });

		    measure();
		    Window window = dialog.getWindow();
		    if (window != null) {
				window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
				window.setGravity(Gravity.BOTTOM);
				window.setLayout(width, height - DisplayUtils.getStatusBarHeight(getActivity()));
				if (enableAnim) {
					  window.setWindowAnimations(mAnimStyle);
				}
		    }
	  }


	  //测量宽高
	  private void measure() {
		    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				DisplayMetrics dm = new DisplayMetrics();
				getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(dm);
				height = dm.heightPixels;
				width = dm.widthPixels;
		    } else {
				DisplayMetrics dm = getResources().getDisplayMetrics();
				height = dm.heightPixels;
				width = dm.widthPixels;
		    }
	  }


	  /**
	   * 搜索框监听
	   */
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
				mClearAllBtn.setVisibility(View.GONE);
				mEmptyView.setVisibility(View.GONE);
				mResults = mAllCities;
				((SectionItemDecoration) (mRecyclerView.getItemDecorationAt(0))).setData(mResults);
				mAdapter.updateData(mResults);
		    } else {
				mClearAllBtn.setVisibility(View.VISIBLE);
				//开始数据库查找
				mResults = dbManager.searchBykeyword(keyword);
				((SectionItemDecoration) (mRecyclerView.getItemDecorationAt(0))).setData(mResults);
				if (mResults == null || mResults.isEmpty()) {
					  mEmptyView.setVisibility(View.VISIBLE);
				} else {
					  mEmptyView.setVisibility(View.GONE);
					  mAdapter.updateData(mResults);
				}
		    }
		    mRecyclerView.scrollToPosition(0);
	  }


	  @Override
	  public void onClick(View v) {
		    int id = v.getId();
		    if (id == R.id.cp_cancel) {
				dismiss();
				if (mOnPickListener != null) {
					  mOnPickListener.onCancel();
				}
		    } else if (id == R.id.cp_clear_all) {
				mSearchBox.setText("");
		    }
	  }


	  @Override
	  public void onIndexChanged(String index, int position) {
		    //滚动RecyclerView到索引位置
		    mAdapter.scrollToSection(index);
	  }


	  @Override
	  public void dismiss(int position, ChinaCityInfo data) {
		    dismiss();
		    if (mOnPickListener != null) {
				mOnPickListener.onPick(position, data);
		    }
	  }


	  public void setOnPickListener(OnPickListener listener) {
		    this.mOnPickListener = listener;
	  }


}
