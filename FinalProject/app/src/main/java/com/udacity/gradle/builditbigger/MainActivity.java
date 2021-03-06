package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jokedisplay.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.idling_resource.SimpleIdlingResource;


public class MainActivity extends AppCompatActivity implements JokeAsyncTask.JokeLoadedListener {
	
	private ProgressBar mProgressBar;
	
	@Nullable
	private SimpleIdlingResource mIdlingResource;
	
	/**
	 * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
	 */
	@VisibleForTesting
	@NonNull
	public IdlingResource getIdlingResource() {
		if (mIdlingResource == null) {
			mIdlingResource = new SimpleIdlingResource();
		}
		return mIdlingResource;
	}
	
	/**
	 * Prepare the InterstitialAd to be Displayed and create a Link to the ProgressBar
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	    AdUtils.prepareInterstitialAd(this);
	    mProgressBar = findViewById(R.id.loading_indicator);
	    getIdlingResource();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
	
	    
    }
	
	/**
	 * Gets called by clicking on the Button
	 * This Method displays the ProgressBar and starts a AsyncTask to load a Joke
	 * @param view
	 */
	public void tellJoke(View view) {
		mProgressBar.setVisibility(View.VISIBLE);
		if(mIdlingResource!=null){
			mIdlingResource.setIdleState(false);
		}
        JokeAsyncTask asyncTask = new JokeAsyncTask();
		asyncTask.setOnJokeLoadedListener(this);
		asyncTask.execute();
    }
	
	/**
	 * When the Joke is loaded the Progressbar can be hidden and the Interstitial Ad gets displayed.
	 * When the app is clicked away a new Activity is started with the Joke as Extra Parameter
	 * @param joke
	 */
	@Override
	public void onJokeLoaded(final String joke) {
		mProgressBar.setVisibility(View.GONE);
		AdUtils.showInterstitialAd(new AdUtils.AdClosedListener() {
			@Override
			public void onAdClosed() {
				if(mIdlingResource!=null) {
					mIdlingResource.setIdleState(true);
				}
				startActivity(JokeDisplayActivity.createIntent(MainActivity.this,joke));
			}
		});
	}
}
