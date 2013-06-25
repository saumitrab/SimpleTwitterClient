package com.codepath.apps.restclienttemplate;

import org.json.JSONArray;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.Fragments.TwitterTimelineFragment;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetHomeActivity extends FragmentActivity {

	int tweetsPageNumber = 1;
	TweetsAdapter myArrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet_home);
		

		//Todo: fetch username and setTitle
		this.setTitle("Tweets");
		Log.d("DEBUG", "TweetHomeActivity onCreate");
		
		fetchTweets();
		
		// Only if this is a new activity, so fragments don't exist yet.
		if (savedInstanceState == null) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.flContainer, new TwitterTimelineFragment()); 
			ft.commit();
		}
		
	}
	
	public void fetchTweets() {
		RestClient client = RestClientApp.getRestClient();
		client.getHomeTimeline(tweetsPageNumber, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONArray json) {
				Log.d("DEBUG", "JSONARRAY" + json.toString());
				//myArrayAdapter.addAll(json.toString());
				
				TwitterTimelineFragment fragment =  (TwitterTimelineFragment) getSupportFragmentManager().findFragmentById(R.id.flContainer);
				myArrayAdapter = fragment.getTweetsAdapterFromFragment(); 
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.refresh) {
			Toast.makeText(this, "Item: " + item.toString(), Toast.LENGTH_SHORT).show();
			myArrayAdapter.clear();
			tweetsPageNumber = 1;
			fetchTweets();
		}
		if (item.getItemId() == R.id.compose_tweet ) {
			Toast.makeText(this, "Item: " + item.toString(), Toast.LENGTH_SHORT).show();
			Intent myPostIntent = new Intent();
			myPostIntent.setClass(getApplicationContext(), PostTweet.class);
			startActivityForResult(myPostIntent, 1);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Refresh tweets when user comes back
		myArrayAdapter.clear();
		tweetsPageNumber = 1;
		fetchTweets();
		super.onActivityResult(requestCode, resultCode, data);
	}

}
