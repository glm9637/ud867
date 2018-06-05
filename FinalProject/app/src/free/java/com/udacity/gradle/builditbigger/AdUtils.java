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
	
	public static void prepareInterstitialAd(@NonNull Context context){
		if(mInterstitialAd == null) {
			mInterstitialAd = new InterstitialAd(context);
			mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
		}
		mInterstitialAd.loadAd(generateRequest());
	}
	
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
	
	private static AdRequest generateRequest(){
		return new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.build();
	}
}