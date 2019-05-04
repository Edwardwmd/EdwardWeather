package com.edwardwmd.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edwardwmd.weather.R;

import com.edwardwmd.weather.bean.WeatherDetailBean;
import com.edwardwmd.weather.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherDetailAdapter extends BaseRecyclerViewAdapter<WeatherDetailAdapter.ViewHolder> {
         
         
         private List<WeatherDetailBean> weatherDetails=new ArrayList<>();
         private Context mC;
         
         public WeatherDetailAdapter(Context context) {
                  mC = context;
         }
         
         /**
          * 设置数据源
          *
          * @param weatherDetails 数据
          */
         public void setmDatas(List<WeatherDetailBean> weatherDetails) {
                  this.weatherDetails.clear();
                  this.weatherDetails=weatherDetails;
                  notifyDataSetChanged();
         }
         
         
         @NonNull
         @Override
         public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                  View itemView = LayoutInflater.from(mC)
                            .inflate(R.layout.item_detail_weather, parent, false);
                  return new ViewHolder(itemView, this);
         }
         
         @Override
         public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                  if (weatherDetails != null && weatherDetails.size() > 0) {
                           WeatherDetailBean detail = weatherDetails.get(position);
                           holder.imgDetailWeather.setImageResource(detail.getImageResourceId());
                           holder.detailKeyTextView.setText(detail.getDetailText());
                           holder.detailValueTextView.setText(detail.getWeatherValue());
                  } else {
                           ToastUtils.showToast_S("你的数据没法加载-_-");
                  }
                  
         }
         
         @Override
         public int getItemCount() {
                  return weatherDetails == null ? 0 : weatherDetails.size();
         }
         
         
         static class ViewHolder extends RecyclerView.ViewHolder {
                  @BindView(R.id.img_detail_weather)
                  ImageView imgDetailWeather;
                  @BindView(R.id.detail_key_text_view)
                  TextView detailKeyTextView;
                  @BindView(R.id.detail_value_text_view)
                  TextView detailValueTextView;
                  @BindView(R.id.cv_weather_info)
                  CardView cvWeatherInfo;
                  
                  ViewHolder(View view, WeatherDetailAdapter weatherDetailAdapter) {
                           super(view);
                           ButterKnife.bind(this, view);
                  }
         }
}
