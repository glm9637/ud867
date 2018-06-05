package com.example.jokedisplay;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {
	
    private static final String INTENT_EXTRA_JOKE = "joke";
	
	/**
	 * takes a joke and displays it in a Textview
	 */
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        String joke = getIntent().getStringExtra(INTENT_EXTRA_JOKE);
        TextView view = findViewById(R.id.txt_joke);
        view.setText(joke);
    }

    public static Intent createIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeDisplayActivity.class);
        intent.putExtra(INTENT_EXTRA_JOKE, joke);
        return intent;
    }
}
