package com.edwardwmd.weather.adapter;

import android.content.Context;
import android.os.Handler;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.bean.ChinaCityInfo;
import com.edwardwmd.weather.bean.HotCity;
import com.edwardwmd.weather.weight.citypickview.InnerListener;
import com.edwardwmd.weather.weight.citypickview.decoration.GridItemDecoration;

import java.util.List;


/**
 * @Author: Bro0cL
 * @Date: 2018/2/5 12:06
 */
public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.BaseViewHolder> {


	  private static final int VIEW_TYPE_HOT = 11;

	  private Context mContext;
	  private List<ChinaCityInfo> mData;
	  private List<HotCity> mHotData;
	  private InnerListener mInnerListener;
	  private LinearLayoutManager mLayoutManager;
	  private boolean stateChanged;


	  public CityListAdapter(Context context, List<ChinaCityInfo> data, List<HotCity> hotData) {

		    this.mData = data;
		    this.mContext = context;
		    this.mHotData = hotData;
	  }


	  public void setLayoutManager(LinearLayoutManager manager) {
		    this.mLayoutManager = manager;
	  }


	  public void updateData(List<ChinaCityInfo> data) {
		    this.mData = data;
		    notifyDataSetChanged();
	  }


	  /**
	   * 滚动RecyclerView到索引位置
	   *
	   * @param index
	   */
	  public void scrollToSection(String index) {
		    if (mData == null || mData.isEmpty()) return;
		    if (TextUtils.isEmpty(index)) return;
		    int size = mData.size();
		    for (int i = 0; i < size; i++) {
				if (TextUtils.equals(index.substring(0, 1), mData.get(i).getSection().substring(0, 1))) {
					  if (mLayoutManager != null) {
						    mLayoutManager.scrollToPositionWithOffset(i, 0);
						    if (TextUtils.equals(index.substring(0, 1), "热")) {
								//防止滚动时进行刷新
								new Handler().postDelayed(new Runnable() {
									  @Override
									  public void run() {
										    if (stateChanged)
												notifyItemChanged(0);
									  }
								}, 1000);
						    }
						    return;
					  }
				}
		    }
	  }


	  public void refreshHotCityItem() {
		    //如果定位城市的item可见则进行刷新
		    if (stateChanged && mLayoutManager.findFirstVisibleItemPosition() == 0) {
				stateChanged = false;
				notifyItemChanged(0);
		    }
	  }


	  @NonNull
	  @Override
	  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		    View view;

		    switch (viewType) {
				case VIEW_TYPE_HOT:
					  view = LayoutInflater.from(mContext).inflate(R.layout.cp_list_item_hot_layout, parent, false);
					  return new HotViewHolder(view);
				default:
					  view = LayoutInflater.from(mContext).inflate(R.layout.cp_list_item_default_layout, parent, false);
					  return new DefaultViewHolder(view);
		    }
	  }


	  @Override
	  public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
		    if (holder instanceof DefaultViewHolder) {
				final int pos = holder.getAdapterPosition();
				final ChinaCityInfo data = mData.get(pos);
				if (data == null) return;
				((DefaultViewHolder) holder).name.setText(data.getCity_CN());
				((DefaultViewHolder) holder).name.setOnClickListener(new View.OnClickListener() {
					  @Override
					  public void onClick(View v) {
						    if (mInnerListener != null) {
								mInnerListener.dismiss(pos, data);
						    }
					  }
				});
		    }

		    //热门城市

		    if (holder instanceof HotViewHolder) {
				final int pos = holder.getAdapterPosition();
				final ChinaCityInfo data = mData.get(pos);
				if (data == null) return;
				GridListAdapter mAdapter = new GridListAdapter(mContext, mHotData);
				mAdapter.setInnerListener(mInnerListener);
				((HotViewHolder) holder).mRecyclerView.setAdapter(mAdapter);
		    }
	  }


	  @Override
	  public int getItemCount() {
		    return mData == null ? 0 : mData.size();
	  }


	  @Override
	  public int getItemViewType(int position) {
		    if (position == 0 && TextUtils.equals("热", mData.get(position).getSection().substring(0, 1)))
				return VIEW_TYPE_HOT;

		    return super.getItemViewType(position);

	  }


	  public void setInnerListener(InnerListener listener) {
		    this.mInnerListener = listener;
	  }


	  static class BaseViewHolder extends RecyclerView.ViewHolder {


		    BaseViewHolder(View itemView) {
				super(itemView);
		    }


	  }


	  public static class DefaultViewHolder extends BaseViewHolder {


		    TextView name;


		    DefaultViewHolder(View itemView) {
				super(itemView);
				name = itemView.findViewById(R.id.cp_list_item_name);
		    }


	  }


	  public static class HotViewHolder extends BaseViewHolder {


		    RecyclerView mRecyclerView;


		    HotViewHolder(View itemView) {
				super(itemView);
				mRecyclerView = itemView.findViewById(R.id.cp_hot_list);
				mRecyclerView.setHasFixedSize(true);
				mRecyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(),
					  GridListAdapter.SPAN_COUNT, RecyclerView.VERTICAL, false));
				int space = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.cp_grid_item_space);
				mRecyclerView.addItemDecoration(new GridItemDecoration(GridListAdapter.SPAN_COUNT,
					  space));
		    }


	  }


}
