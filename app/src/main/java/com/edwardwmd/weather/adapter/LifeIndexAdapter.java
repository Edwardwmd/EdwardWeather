package com.edwardwmd.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.bean.LifeIdexBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LifeIndexAdapter extends BaseRecyclerViewAdapter<LifeIndexAdapter.ViewHolder> {


	  private Context mC;
	  private List<LifeIdexBean> lifeIdexBeans;


	  public LifeIndexAdapter(Context mC) {
		    this.mC = mC;
		    lifeIdexBeans = new ArrayList<>();
	  }


	  public void setAllLifeIndexData(List<LifeIdexBean> lifeIdexBeans) {
		    this.lifeIdexBeans = lifeIdexBeans;
		    notifyDataSetChanged();
	  }

	  public List<LifeIdexBean>getLifeIdexBeans(){
	  	  return lifeIdexBeans;
	  }

	  public void dataClear() {
		    this.lifeIdexBeans.clear();
		    notifyDataSetChanged();
	  }


	  @NonNull
	  @Override
	  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		    View rootView = LayoutInflater.from(mC).inflate(R.layout.item_life_index, parent, false);
		    return new ViewHolder(rootView, this);
	  }


	  @Override
	  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		    holder.indexIconImageView.setImageResource(lifeIdexBeans.get(position).getIconResource());
		    holder.indexLevelTextView.setText(lifeIdexBeans.get(position).getLifeText());
		    holder.indexNameTextView.setText(lifeIdexBeans.get(position).getLifeVaule());
	  }


	  @Override
	  public int getItemCount() {
		    return lifeIdexBeans == null ? 0 : lifeIdexBeans.size();
	  }


	  static class ViewHolder extends RecyclerView.ViewHolder {


		    @BindView(R.id.index_icon_image_view)
		    ImageView indexIconImageView;
		    @BindView(R.id.index_level_text_view)
		    TextView indexLevelTextView;
		    @BindView(R.id.index_name_text_view)
		    TextView indexNameTextView;
		    @BindView(R.id.cv_weather_information)
		    CardView cvWeatherInformation;


		    ViewHolder(View view, LifeIndexAdapter lifeIndexAdapter) {
				super(view);
				ButterKnife.bind(this, view);
				itemView.setOnClickListener(new View.OnClickListener() {
					  @Override
					  public void onClick(View v) {
						    lifeIndexAdapter.onItemHolderClick(ViewHolder.this);
					  }
				});

		    }


	  }


}
