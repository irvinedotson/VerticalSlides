package com.irvinedotson.verticalslides;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_BEHIND;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_FULL_USER;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LOCKED;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_NOSENSOR;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

/**
 * Fluent API for building media select specification.
 */
@SuppressWarnings ("unused")
public final class SelectionCreator{
	private final Matisse mMatisse;

	/**
	 * Constructs a new specification builder on the context.
	 *
	 * @param matisse   a requester context wrapper.
	 * @param mimeTypes MIME type set to select.
	 */
	SelectionCreator(Matisse matisse, @NonNull Set<MimeType> mimeTypes, boolean mediaTypeExclusive){
		mMatisse = matisse;

	}

	/**
	 * Start to select media and wait for result.
	 *
	 * @param requestCode Identity of the request Activity or Fragment.
	 */
	public void forResult(int requestCode){
		Activity activity = mMatisse.getActivity();
		if(activity == null){
			return;
		}


	}

	public SelectionCreator showPreview(boolean showPreview){
		return this;
	}

	@RequiresApi (api = Build.VERSION_CODES.JELLY_BEAN_MR2)
	@IntDef ({SCREEN_ORIENTATION_UNSPECIFIED, SCREEN_ORIENTATION_LANDSCAPE, SCREEN_ORIENTATION_PORTRAIT, SCREEN_ORIENTATION_USER, SCREEN_ORIENTATION_BEHIND, SCREEN_ORIENTATION_SENSOR, SCREEN_ORIENTATION_NOSENSOR, SCREEN_ORIENTATION_SENSOR_LANDSCAPE, SCREEN_ORIENTATION_SENSOR_PORTRAIT, SCREEN_ORIENTATION_REVERSE_LANDSCAPE, SCREEN_ORIENTATION_REVERSE_PORTRAIT, SCREEN_ORIENTATION_FULL_SENSOR, SCREEN_ORIENTATION_USER_LANDSCAPE, SCREEN_ORIENTATION_USER_PORTRAIT, SCREEN_ORIENTATION_FULL_USER, SCREEN_ORIENTATION_LOCKED})
	@Retention (RetentionPolicy.SOURCE)
	@interface ScreenOrientation{
	}
}