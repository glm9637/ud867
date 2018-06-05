package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
	private JokeLoadedListener mJokeLoadedListener;
	
	/**
	 * Interface to Notify the Ui that the Joke was loaded
	 */
	public interface JokeLoadedListener {
		void onJokeLoaded(String joke);
	}
	
	/**
	 * Retrieve the Data from the Api
	 * @return the loaded Joke
	 */
	@Override
    protected String doInBackground(Void... voids) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
           return myApiService.dispenseJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
	
	/**
	 * Notify the UI that the joke was loaded
	 * @param result the loaded Joke
	 */
	@Override
    protected void onPostExecute(final String result) {
		mJokeLoadedListener.onJokeLoaded(result);
    }
	
	public void setOnJokeLoadedListener(JokeLoadedListener onJokeLoadedListener) {
		this.mJokeLoadedListener = onJokeLoadedListener;
	}
}
