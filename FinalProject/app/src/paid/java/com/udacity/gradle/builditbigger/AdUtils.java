package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Since this is in the Paid flavor no ads should be displayed.
 */
public class AdUtils {
	
	public interface AdClosedListener{
		public void onAdClosed();
	}
	
	public static void createAd(View rootView){
	
	}
	
	public static void prepareInterstitialAd(@NonNull Context context){

	}
	
	public static void showInterstitialAd(final AdClosedListener adClosedListener){
			adClosedListener.onAdClosed();
	}
	
}