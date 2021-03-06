package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AdUtils {
	
	private static InterstitialAd mInterstitialAd;
	
	
	public interface AdClosedListener{
		void onAdClosed();
	}
	
	public static void createAd(View adView){
		AdView mAdView = (AdView) adView;
		// Create an ad request. Check logcat output for the hashed device ID to
		// get test ads on a physical device. e.g.
		// "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
		
		mAdView.loadAd(generateRequest());
	}
	
	/**
	 * prepares the InterstitialAd so it is ready to be shown as early as possible
	 */
	public static void prepareInterstitialAd(@NonNull Context context){
		if(mInterstitialAd == null) {
			mInterstitialAd = new InterstitialAd(context);
			mInterstitialAd.setAdUnitId(context.getString(R.string.banner_ad_unit_id));
		}
		mInterstitialAd.loadAd(generateRequest());
	}
	
	
	/**
	 * Displays the Interstitial Ad
	 * @param adClosedListener the Listener which gets notified when the app gets closed
	 */
	public static void showInterstitialAd(final AdClosedListener adClosedListener){
		if(mInterstitialAd.isLoaded()){
			mInterstitialAd.setAdListener(new AdListener() {
				public void onAdClosed(){
					mInterstitialAd.loadAd(generateRequest());
					adClosedListener.onAdClosed();
				}
			});
			mInterstitialAd.show();
		}else {
			adClosedListener.onAdClosed();
		}
	}
	
	/**
	 * generates a Ad Request
	 */
	private static AdRequest generateRequest(){
		return new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.build();
	}
}