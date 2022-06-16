package com.irvinedotson.verticalslides;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * Entry for Matisse's media selection.
 */
public final class Matisse{

	private final WeakReference<Activity> mContext;
	private final WeakReference<Fragment> mFragment;

	private Matisse(Activity activity){
		this(activity, null);
	}

	private Matisse(Fragment fragment){
		this(fragment.getActivity(), fragment);
	}

	private Matisse(Activity activity, Fragment fragment){
		mContext = new WeakReference<>(activity);
		mFragment = new WeakReference<>(fragment);
	}

	/**
	 * Start Matisse from an Activity.
	 * <p>
	 * This Activity's  will be called when user
	 * finishes selecting.
	 *
	 * @param activity Activity instance.
	 * @return Matisse instance.
	 */
	public static Matisse from(Activity activity){
		return new Matisse(activity);
	}

	/**
	 * Start Matisse from a Fragment.
	 * <p>
	 * This Fragment's {@link Fragment#onActivityResult(int, int, Intent)} will be called when user
	 * finishes selecting.
	 *
	 * @param fragment Fragment instance.
	 * @return Matisse instance.
	 */
	public static Matisse from(Fragment fragment){
		return new Matisse(fragment);
	}


	@Nullable
	Activity getActivity(){
		return mContext.get();
	}

	@Nullable
	Fragment getFragment(){
		return mFragment != null ? mFragment.get() : null;
	}

}