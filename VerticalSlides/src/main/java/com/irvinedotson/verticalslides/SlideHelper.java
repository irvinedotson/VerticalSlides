package com.irvinedotson.verticalslides;

import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SlideHelper extends RecyclerView.Adapter<SlideHelper.VideoViewHolder>{
	private final List<SlideItem> slideItems;
	SlideConf slideConf;

	public SlideHelper(List<SlideItem> slideItems, SlideConf slideConf){
		this.slideItems = slideItems;
		this.slideConf = slideConf;
	}

	@NonNull
	@Override
	public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
		return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_wrap, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull VideoViewHolder holder, int position){
		SlideItem slideItem = slideItems.get(position);
		holder.LoadingView.setVisibility(View.VISIBLE);
		holder.VidOverlay.setVisibility(View.VISIBLE);
		if(this.slideConf.SldBG != null){
			try{
				holder.ParentView.setBackgroundColor(slideConf.SldBG);
				holder.VidOverlay.setBackgroundColor(slideConf.SldBG);
			}catch(Exception ignored){

			}
		}
		if(slideConf.likeDrbl != null && slideConf.dislDrbl != null){
			try{
				if(slideItem.reaction == AdvancedSlides.VIDEO_LIKED){
					holder.VidLikeBtn.setImageResource(slideConf.likeActDrbl);
					holder.VidDislikeBtn.setImageResource(slideConf.dislDrbl);
				}else if(slideItem.reaction == AdvancedSlides.VIDEO_DISLIKED){
					holder.VidDislikeBtn.setImageResource(slideConf.dislActDrbl);
					holder.VidLikeBtn.setImageResource(slideConf.likeDrbl);
				}else{
					holder.VidLikeBtn.setImageResource(slideConf.likeDrbl);
					holder.VidDislikeBtn.setImageResource(slideConf.dislDrbl);
				}
				holder.VidDislikeBtn.setVisibility(View.VISIBLE);
				holder.VidDislikeBtn.setVisibility(View.VISIBLE);
			}catch(Exception ignored){
				holder.VidLikeBtn.setVisibility(View.GONE);
				holder.VidDislikeBtn.setVisibility(View.GONE);
			}
		}else{
			holder.VidLikeBtn.setVisibility(View.GONE);
			holder.VidDislikeBtn.setVisibility(View.GONE);
		}
		if(slideConf.VidPos != null && slideConf.VidPos == AdvancedSlides.ALIGN_TOP){
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.MainVid.getLayoutParams();
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			holder.MainVid.setLayoutParams(layoutParams);
		}else if(slideConf.VidPos != null && slideConf.VidPos == AdvancedSlides.ALIGN_MIDDLE){
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.MainVid.getLayoutParams();
			layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
			holder.MainVid.setLayoutParams(layoutParams);
		}else if(slideConf.VidPos != null && slideConf.VidPos == AdvancedSlides.ALIGN_BOTTOM){
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.MainVid.getLayoutParams();
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			holder.MainVid.setLayoutParams(layoutParams);
		}
		if(slideConf.ttlUnt != null && slideConf.ttlSz != null){
			holder.VidTitle.setTextSize(slideConf.ttlUnt, slideConf.ttlSz);
		}
		if(slideConf.descUnt != null && slideConf.descSz != null){
			holder.VidDescription.setTextSize(slideConf.descUnt, slideConf.descSz);
		}
		if(!TextUtils.isEmpty(slideItem.title)){
			holder.VidTitle.setText(slideItem.title);
		}else{
			holder.VidTitle.setVisibility(View.GONE);
		}
		if(!TextUtils.isEmpty(slideItem.description)){
			holder.VidDescription.setText(slideItem.description);
		}else{
			holder.VidDescription.setVisibility(View.GONE);
		}
		holder.MainVid.setVideoURI(Uri.parse(slideItem.link));
		//holder.MainVid.setVideoPath(slideItem.link);
		holder.MainVid.setZOrderOnTop(true);
		if(slideConf.Ctrls){
			MediaController vidControl = new MediaController(holder.MainVid.getContext());
			vidControl.setAnchorView(holder.MainVid);
			holder.MainVid.setMediaController(vidControl);
		}
		holder.MainVid.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
			@Override
			public void onPrepared(MediaPlayer mediaPlayer){
				holder.LoadingView.setVisibility(View.GONE);
				mediaPlayer.start();
				mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener(){
					@Override
					public boolean onInfo(MediaPlayer mp, int what, int extra){
						if(what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START){
							holder.VidOverlay.setVisibility(View.GONE);
							return true;
						}
						return false;
					}
				});
			}
		});
		holder.MainVid.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
			@Override
			public void onCompletion(MediaPlayer mediaPlayer){
				mediaPlayer.start();
			}
		});
		holder.VidLikeBtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				if(slideConf.likeDrbl != null && slideConf.dislDrbl != null){
					try{
						if(slideItem.reaction == AdvancedSlides.VIDEO_LIKED){
							holder.VidLikeBtn.setImageResource(slideConf.likeDrbl);
							holder.VidDislikeBtn.setImageResource(slideConf.dislDrbl);
							slideItem.reaction = AdvancedSlides.VIDEO_NO_REACTION;
						}else{
							holder.VidLikeBtn.setImageResource(slideConf.likeActDrbl);
							holder.VidDislikeBtn.setImageResource(slideConf.dislDrbl);
							slideItem.reaction = AdvancedSlides.VIDEO_LIKED;
						}
					}catch(Exception ignored){
						holder.VidLikeBtn.setVisibility(View.GONE);
						holder.VidDislikeBtn.setVisibility(View.GONE);
					}
				}
			}
		});
		holder.VidDislikeBtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				if(slideConf.likeDrbl != null && slideConf.dislDrbl != null){
					try{
						if(slideItem.reaction == AdvancedSlides.VIDEO_DISLIKED){
							holder.VidDislikeBtn.setImageResource(slideConf.dislDrbl);
							holder.VidLikeBtn.setImageResource(slideConf.likeDrbl);
							slideItem.reaction = AdvancedSlides.VIDEO_NO_REACTION;
						}else{
							holder.VidDislikeBtn.setImageResource(slideConf.dislActDrbl);
							holder.VidLikeBtn.setImageResource(slideConf.likeDrbl);
							slideItem.reaction = AdvancedSlides.VIDEO_DISLIKED;
						}
					}catch(Exception ignored){
						holder.VidLikeBtn.setVisibility(View.GONE);
						holder.VidDislikeBtn.setVisibility(View.GONE);
					}
				}
			}
		});
	}

	@Override
	public int getItemCount(){
		return slideItems.size();
	}

	public static class VideoViewHolder extends RecyclerView.ViewHolder{
		RelativeLayout ParentView;
		FrameLayout VidOverlay;
		VideoView MainVid;
		FrameLayout LoadingView;
		ImageView VidLikeBtn, VidDislikeBtn;
		TextView VidTitle, VidDescription;

		public VideoViewHolder(@NonNull View itemView){
			super(itemView);
			ParentView = itemView.findViewById(R.id.ParentView);
			VidOverlay = itemView.findViewById(R.id.VidOverlay);
			MainVid = itemView.findViewById(R.id.MainVid);
			LoadingView = itemView.findViewById(R.id.LoadingView);
			VidLikeBtn = itemView.findViewById(R.id.VidLikeBtn);
			VidDislikeBtn = itemView.findViewById(R.id.VidDislikeBtn);
			VidTitle = itemView.findViewById(R.id.VidTitle);
			VidDescription = itemView.findViewById(R.id.VidDescription);
		}
	}
}
