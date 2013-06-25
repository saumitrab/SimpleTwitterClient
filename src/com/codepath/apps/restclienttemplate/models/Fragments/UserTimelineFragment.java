package com.codepath.apps.restclienttemplate.models.Fragments;

import org.json.JSONArray;

import com.codepath.apps.restclienttemplate.RestClient;
import com.codepath.apps.restclienttemplate.RestClientApp;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;

public class UserTimelineFragment extends TwitterTimelineFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RestClient client = RestClientApp.getRestClient();
		client.getUserTimeline(new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONArray json) {
				Log.d("DEBUG", "JSONARRAY" + json.toString());
				
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
