package com.edwardwmd.weather.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.bean.SettingItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingAdapter extends BaseRecyclerViewAdapter<SettingAdapter.ViewHolder> {


	  private Context mc;
	  private List<SettingItem> settingItemList;


	  public SettingAdapter(Context mc) {
		    this.mc = mc;
		    settingItemList = new ArrayList<>();
	  }


	  public void setItems(List<SettingItem> settingItemList) {
		    this.settingItemList.clear();
		    this.settingItemList = settingItemList;
		    notifyDataSetChanged();
	  }


	  @NonNull
	  @Override
	  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		    View rootView = LayoutInflater.from(mc).inflate(R.layout.item_setting, parent, false);
		    return new ViewHolder(rootView, this);
	  }


	  @Override
	  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		    holder.tvItemLeft.setText(settingItemList.get(position).getItem_text());
		    holder.tvItemLeft.setCompoundDrawablesWithIntrinsicBounds(mc.getResources().getDrawable(settingItemList.get(position).getIcon_left()), null, null, null);
		    holder.icItemRight.setImageResource(settingItemList.get(position).getIcon_right());
	  }


	  @Override
	  public int getItemCount() {
		    return settingItemList == null ? 0 : settingItemList.size();
	  }



	  static class ViewHolder extends RecyclerView.ViewHolder {


		    @BindView(R.id.tv_item_left)
		    TextView tvItemLeft;
		    @BindView(R.id.ic_item_right)
		    ImageView icItemRight;


		    ViewHolder(View view, SettingAdapter settingAdapter) {
				super(view);
				ButterKnife.bind(this, view);
				itemView.setOnClickListener(v -> settingAdapter.onItemHolderClick(SettingAdapter.ViewHolder.this));
		    }


	  }


}
