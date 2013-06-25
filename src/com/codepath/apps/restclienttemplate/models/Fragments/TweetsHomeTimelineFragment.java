package com.codepath.apps.restclienttemplate.models.Fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.restclienttemplate.RestClient;
import com.codepath.apps.restclienttemplate.RestClientApp;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetsHomeTimelineFragment extends TwitterTimelineFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fetchTweets();
	}
	
	
	public void fetchTweets() {
		RestClient client = RestClientApp.getRestClient();
		client.getHomeTimeline(tweetsPageNumber, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONArray json) {
				Log.d("DEBUG", "JSONARRAY" + json.toString());
				//myArrayAdapter.addAll(json.toString());
				
				//TwitterTimelineFragment fragment =  (TwitterTimelineFragment) getSupportFragmentManager().findFragmentById(R.id.flContainer);
				//myArrayAdapter = fragment.getTweetsAdapterFromFragment(); 
				//myArrayAdapter.addAll(Tweet.fromJSONArray(json));
				
				getTweetsAdapterFromFragment().addAll(Tweet.fromJSONArray(json));
				super.onSuccess(json);
			}
	
			@Override
			public void onFailure(Throwable arg0, JSONArray arg1) {
				Log.d("DEBUG", "onFailure :( ");
				super.onFailure(arg0, arg1);
			}  
		});
	}

}
