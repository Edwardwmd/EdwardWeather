package com.edwardwmd.weather.utils;

import android.content.Context;
import android.util.TypedValue;

public class DisplayUtils {
         /**
          * convert px to its equivalent dp
          *
          * 将px转换为与之相等的dp
          */
         public static int px2dp(Context context, float pxValue) {
                  final float scale =  context.getResources().getDisplayMetrics().density;
                  return (int) (pxValue / scale + 0.5f);
         }
         
         
         /**
          * convert dp to its equivalent px
          *
          * 将dp转换为与之相等的px
          */
         public static int dp2px(Context context, float dipValue) {
                  final float scale = context.getResources().getDisplayMetrics().density;
                  return (int) (dipValue * scale + 0.5f);
         }
         
         
         /**
          * convert px to its equivalent sp
          *
          * 将px转换为sp
          */
         public static int px2sp(Context context, float pxValue) {
                  final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
                  return (int) (pxValue / fontScale + 0.5f);
         }
         
         
         /**
          * convert sp to its equivalent px
          *
          * 将sp转换为px
          */
         public static int sp2px(Context context, float spValue) {
                  final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
                  return (int) (spValue * fontScale + 0.5f);
         }
         /**
          * convert dp to its equivalent px
          */
         protected int dp2px(int dp,Context context){
                  return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,context.getResources().getDisplayMetrics());
         }
         
         /**
          * convert sp to its equivalent px
          */
         protected int sp2px(int sp,Context context){
                  return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,context.getResources().getDisplayMetrics());
         }

}
