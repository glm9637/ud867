package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
	
	/**
	 * Creates the Banner Ad
	 * @return the RootView of the Fragment
	 */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
		View adView = root.findViewById(R.id.adView); //This View only exists in the free flavor
        AdUtils.createAd(adView);
        
        return root;
    }
}
