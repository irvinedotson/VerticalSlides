package com.irvinedotson.verticalslides;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

public class SlideMetadata{

	boolean mAdjustViewBounds;
	AppCompatImageView mAppCompatImageView;

	public static boolean isImage(String mimeType){
		if(mimeType == null)
			return false;
		return mimeType.startsWith("image");
	}

	public static boolean isVideo(String mimeType){
		if(mimeType == null)
			return false;
		return mimeType.startsWith("video");
	}

	public static boolean isGif(String mimeType){
		if(mimeType == null)
			return false;
		return mimeType.equals(MimeType.GIF.toString());
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		Drawable mDrawable = mAppCompatImageView.getDrawable();
		if(mDrawable == null){
			return;
		}
		if(mAdjustViewBounds){
			int mDrawableWidth = mDrawable.getIntrinsicWidth();
			int mDrawableHeight = mDrawable.getIntrinsicHeight();
			int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
			int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
			int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
			int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);

			if(heightMode == View.MeasureSpec.EXACTLY && widthMode != View.MeasureSpec.EXACTLY){
				// Fixed Height & Adjustable Width
				int height = heightSize;
				int width = height * mDrawableWidth / mDrawableHeight;
			}
		}
	}
}
