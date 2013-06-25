package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Fragments.TweetsHomeTimelineFragment;

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
		
		// Only if this is a new activity, so fragments don't exist yet.
		if (savedInstanceState == null) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.flContainer, new TweetsHomeTimelineFragment()); 
			ft.commit();
		}
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
//			myArrayAdapter.clear();
//			tweetsPageNumber = 1;
//			fetchTweets();
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
//		myArrayAdapter.clear();
//		tweetsPageNumber = 1;
//		fetchTweets();
		super.onActivityResult(requestCode, resultCode, data);
	}

}
