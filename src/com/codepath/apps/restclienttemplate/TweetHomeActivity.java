package com.codepath.apps.restclienttemplate;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetHomeActivity extends Activity {

	ListView lvTweetsTimeline;
	TweetsAdapter myArrayAdapter;
	List<Tweet> tweets;
	int tweetsPageNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet_home);
		Log.d("DEBUG", "TweetHomeActivity onCreate");

		tweets = new ArrayList<Tweet>();
		myArrayAdapter = new TweetsAdapter(this, tweets);
		lvTweetsTimeline = (ListView) findViewById(R.id.lvTweetsTimeline);
		lvTweetsTimeline.setAdapter(myArrayAdapter);
		myArrayAdapter.clear();
		tweetsPageNumber = 1;
		
		lvTweetsTimeline.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
		});
		
		fetchTweets();
	}
	
	public void fetchTweets() {

		RestClient client = RestClientApp.getRestClient();
		client.getHomeTimeline(tweetsPageNumber, new JsonHttpResponseHandler() {

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
