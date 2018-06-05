package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.jokedispenser.JokeDispenser;
import com.example.jokedisplay.JokeDisplayActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Erzeugt von M. Fengels am 05.06.2018.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	@Rule
	public IntentsTestRule<MainActivity> mActivityTestRule =
			new IntentsTestRule<>(MainActivity.class);
	
	private IdlingResource mIdlingResource;
	
	@Before
	public void registerIdlingResource() {
		mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
		IdlingRegistry.getInstance().register(mIdlingResource);
	}
	
	/**
	 * Checks if the Intent has the correct Extra
	 */
	@Test
	public void clickTellJokeButton() {
		onView(withId(R.id.btn_tell_joke)).perform(click());
		intended(hasExtra(JokeDisplayActivity.INTENT_EXTRA_JOKE, new JokeDispenser().getJoke()));
	}
	
	@After
	public void unregisterIdlingResource(){
		if(mIdlingResource != null){
			IdlingRegistry.getInstance().unregister(mIdlingResource);
		}
	}

}
