package com.codepath.apps.restclienttemplate;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetHomeActivity extends Activity {

	ListView lvTweetsTimeline;
	ArrayAdapter<String> myArrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet_home);
		Log.d("DEBUG", "TweetHomeActivity onCreate");

		myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		lvTweetsTimeline = (ListView) findViewById(R.id.lvTweetsTimeline);
		lvTweetsTimeline.setAdapter(myArrayAdapter);
		
		RestClient client = RestClientApp.getRestClient();
		client.getHomeTimeline(1, new JsonHttpResponseHandler() {
			/* (non-Javadoc)
			 * @see com.loopj.android.http.JsonHttpResponseHandler#onSuccess(org.json.JSONArray)
			 */
			@Override
			public void onSuccess(JSONArray json) {
				Log.d("DEBUG", json.toString());
				myArrayAdapter.addAll(json.toString());
				super.onSuccess(json);
			}
	
			/* (non-Javadoc)
			 * @see com.loopj.android.http.JsonHttpResponseHandler#onFailure(java.lang.Throwable, org.json.JSONArray)
			 */
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
