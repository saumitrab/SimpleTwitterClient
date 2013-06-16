package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetHomeActivity extends Activity {

	ListView lvTweetsTimeline;
	TweetsAdapter myArrayAdapter;
	List<Tweet> tweets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet_home);
		Log.d("DEBUG", "TweetHomeActivity onCreate");

		tweets = new ArrayList<Tweet>();
		myArrayAdapter = new TweetsAdapter(this, tweets);
		lvTweetsTimeline = (ListView) findViewById(R.id.lvTweetsTimeline);
		lvTweetsTimeline.setAdapter(myArrayAdapter);
		
		RestClient client = RestClientApp.getRestClient();
		client.getHomeTimeline(1, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONArray json) {
				Log.d("DEBUG", "JSONARRAY" + json.toString());
				//myArrayAdapter.addAll(json.toString());
				myArrayAdapter.addAll(Tweet.fromJSONArray(json));
				super.onSuccess(json);
			}
	
			@Override
			public void onFailure(Throwable arg0, JSONArray arg1) {
				Log.d("DEBUG", "onFailure :( ");
				super.onFailure(arg0, arg1);
			}  
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweet_home, menu);
		return true;
	}
}
