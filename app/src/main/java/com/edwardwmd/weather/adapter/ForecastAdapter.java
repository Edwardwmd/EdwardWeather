package com.edwardwmd.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.bean.ForecastWeatherBean;
import com.edwardwmd.weather.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastAdapter extends BaseRecyclerViewAdapter<ForecastAdapter.ViewHolder> {
         private Context mC;
         private List<ForecastWeatherBean> mFws;
         
         public ForecastAdapter(Context mC) {
                  this.mC = mC;
                  mFws = new ArrayList<>();
         }
         
         public void setAllDatas(List<ForecastWeatherBean> mFws) {
                  this.mFws.clear();
                  this.mFws = mFws;
                  notifyDataSetChanged();
         }
         
         @NonNull
         @Override
         public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                  View itemView = LayoutInflater.from(mC).inflate(R.layout.item_forecast, parent, false);
                  return new ViewHolder(itemView, this);
         }
         
         @Override
         public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                  if (mFws != null && mFws.size() > 0) {
                           holder.tvMaxTempText.setText(mFws.get(position).getMaxTemp());
                           holder.tvMinTempText.setText(mFws.get(position).getMinTemp());
                           holder.tvWeatherText.setText(mFws.get(position).getForcastText());
                           if (position == 0) {
                                    holder.tvWeekText.setText("今天");
                           } else
                                    holder.tvWeekText.setText(DateUtils.dateToWeek(mFws.get(position).getDataText()));
                           holder.tvWindDirSc.setText(mFws.get(position).getWindDir() + "/" + mFws.get(position).getWindsc());
                           holder.weatherIconImageView.setImageResource(mFws.get(position).getImgResources());
                  }
                  
         }
         
         @Override
         public int getItemCount() {
                  return mFws == null ? 0 : mFws.size();
         }
         
         static class ViewHolder extends RecyclerView.ViewHolder {
                  @BindView(R.id.tv_week_text)
                  TextView tvWeekText;
                  @BindView(R.id.weather_icon_image_view)
                  ImageView weatherIconImageView;
                  @BindView(R.id.tv_weather_text)
                  TextView tvWeatherText;
                  @BindView(R.id.tv_wind_dir_sc)
                  TextView tvWindDirSc;
                  @BindView(R.id.tv_min_temp_text)
                  TextView tvMinTempText;
                  @BindView(R.id.separator_text_view)
                  TextView separatorTextView;
                  @BindView(R.id.tv_max_temp_text)
                  TextView tvMaxTempText;
                  
                  ViewHolder(View view, ForecastAdapter forecastAdapter) {
                           super(view);
                           ButterKnife.bind(this, view);
                  }
         }
}
