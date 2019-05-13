package com.edwardwmd.weather.utils;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * 高斯模糊处理背景图片
 */
public class BlurTransformation extends BitmapTransformation {
	  private Context context;
	  public BlurTransformation(Context context) {
		    this.context = context;
	  }


	  @Override
	  protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
		    return BlurBitmapUtil.instance().blurBitmap(context,toTransform,20,outWidth,outHeight);
	  }


	  @Override
	  public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

	  }


}
