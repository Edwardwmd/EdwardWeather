package com.edwardwmd.weather.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edwardwmd.weather.R;
import com.edwardwmd.weather.utils.DisplayUtils;

import androidx.annotation.Nullable;

public class WeatherTextView extends LinearLayout {
         private int defaultTextSize = 9;// 默认文字大小
         private int defaultBackgroundColorId = R.color.default_background_color;// 默认背景颜色
         private int defaultTextColorId = R.color.default_text_color;// 默认文字颜色
         private int defaultLineColorId = R.color.default_line_color;// 默认底部线条颜色
         
         private String text;
         private int textSize;
         private int textColor;
         private int backgroundColor;
         private int lineColor;
         
         public WeatherTextView(Context context) {
                  super(context);
         }
         
         public WeatherTextView(Context context, @Nullable AttributeSet attrs) {
                  super(context, attrs);
                  initAttrs(context, attrs);
                  initView();
                  
         }
         
         private void initAttrs(Context context, AttributeSet attrs) {
                  DisplayMetrics dm = getResources().getDisplayMetrics();
                  TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeatherTextView);
                  this.defaultTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, defaultTextSize, dm);
                  text = typedArray.getString(R.styleable.WeatherTextView_weatherViewText);
                  this.textSize = typedArray.getDimensionPixelSize(R.styleable.WeatherTextView_weatherViewTextSize, defaultTextSize);
                  this.textSize = DisplayUtils.px2sp(context, textSize);
                  textColor = typedArray.getColor(R.styleable.WeatherTextView_weatherViewTextColor, getResources().getColor(defaultTextColorId));
                  backgroundColor = typedArray.getColor(R.styleable.WeatherTextView_weatherViewBackground, getResources().getColor(defaultBackgroundColorId));
                  lineColor = typedArray.getColor(R.styleable.WeatherTextView_weatherViewLineColor, getResources().getColor(defaultLineColorId));
                  typedArray.recycle();
         }
         
         private void initView() {
                  LayoutInflater.from(getContext()).inflate(R.layout.layout_weather_top_view, this, true);
                  this.setOrientation(VERTICAL);
                  this.setBackgroundColor(backgroundColor);
                  
                  TextView weatherTextView = findViewById(R.id.title_text_view);
                  weatherTextView.setText(text);
                  weatherTextView.setTextSize(textSize);
                  weatherTextView.setTextColor(textColor);
                  
                  View view = findViewById(R.id.title_line_view);
                  view.setBackgroundColor(lineColor);
         }
         
}
