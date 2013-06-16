package com.codepath.apps.restclienttemplate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class PostTweet extends Activity {

	Button btnCancel;
	Button btnPost;
	EditText etComposeTweet;
	RestClient client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_tweet);
		client = RestClientApp.getRestClient();
		
		
		this.setTitle("Saumitra");
		
		etComposeTweet = (EditText) findViewById(R.id.etComposeTweet);
		
		btnCancel = (Button) findViewById(R.id.btnCancel);
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Todo: Store unfinied tweet in sql
				finish();	
			}
		});
		
		btnPost = (Button) findViewById(R.id.btnPost);
		btnPost.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				client.postTweet(etComposeTweet.toString(), new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(String arg0) {
						Toast.makeText(PostTweet.this, "Posted Tweet!", Toast.LENGTH_SHORT).show();
						super.onSuccess(arg0);
					}


					@Override
					public void onFailure(Throwable arg0, String arg1) {
						Toast.makeText(PostTweet.this, "Error Posting Tweet." + arg0.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
						super.onFailure(arg0, arg1);
					}

				}); 

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.post_tweet, menu);
		return true;
	}

}
