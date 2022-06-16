package com.irvinedotson.verticalslides;

import android.content.Context;
import android.graphics.Color;

import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSlides extends SlideMetadata{

	public static final int ALIGN_TOP = 1;
	public static final int ALIGN_MIDDLE = 2;
	public static final int ALIGN_BOTTOM = 3;
	public static final int VIDEO_NO_REACTION = 1;
	public static final int VIDEO_LIKED = 2;
	public static final int VIDEO_DISLIKED = 3;
	ViewPager2 view;
	List<SlideItem> Slides = new ArrayList<>();
	SlideConf slideConf;

	public AdvancedSlides(Context context, ViewPager2 view, boolean showControls){
		this.view = view;
		this.view.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
		slideConf = new SlideConf();
		slideConf.Ctrls = showControls;
	}

	public void addSlide(String link){
		addSlide(link, null, null, null);
	}

	public void addSlide(String link, String title){
		addSlide(link, title, null, null);
	}

	public void addSlide(String link, String title, String description){
		addSlide(link, title, description, null);
	}

	public void setVideoPosition(int videoPosition){
		slideConf.VidPos = videoPosition;
	}

	public void setSlideBackground(String hexColor){
		try{
			slideConf.SldBG = Color.parseColor(hexColor);
		}catch(Exception ignored){

		}
	}

	public void addSlide(String link, String title, String description, Integer reaction){
		SlideItem slideItem = new SlideItem();
		slideItem.link = link;
		slideItem.title = title;
		slideItem.description = description;
		if(reaction != null){
			slideItem.reaction = reaction;
		}else{
			slideItem.reaction = VIDEO_NO_REACTION;
		}
		Slides.add(slideItem);
	}

	public void setLikeDrawables(int likeDrawable, int likeActiveDrawable){
		slideConf.likeDrbl = likeDrawable;
		slideConf.likeActDrbl = likeActiveDrawable;
	}

	public void setDislikeDrawables(int dislikeDrawable, int dislikeActiveDrawable){
		slideConf.dislDrbl = dislikeDrawable;
		slideConf.dislActDrbl = dislikeActiveDrawable;
	}

	public void setTitleSize(int unit, float size){
		slideConf.ttlUnt = unit;
		slideConf.ttlSz = size;
	}

	public void setDescriptionSize(int unit, float size){
		slideConf.descUnt = unit;
		slideConf.descSz = size;
	}

	public void initializeSlide(){
		view.setAdapter(new SlideHelper(Slides, slideConf));
	}
}