package com.codepath.apps.restclienttemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.models.Fragments.TwitterTimelineFragment;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {

	TwitterTimelineFragment fragmentTweetsList;
	RestClient client;
	User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		loadProfile();
	}

	private void loadProfile() {
		client = RestClientApp.getRestClient();
		client.getMyInfo(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				user = User.fromJson(json);
				getActionBar().setTitle("@" + user.getScreenName());
				TextView tvName = (TextView) findViewById(R.id.tvFullName);
				TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
				TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
				TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
				ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
				tvName.setText(user.getName());
				tvTagline.setText(user.getTagline());
				tvFollowers.setText(user.getFollowersCount() + " Followers");
				tvFollowing.setText(user.getFriendsCount() + " Following");
				ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
			}
			
		});
		
		fragmentTweetsList = (TwitterTimelineFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragmentUserTimeline);

		client.getUserTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray json) {
				fragmentTweetsList.getTweetsAdapterFromFragment().addAll(Tweet.fromJSONArray(json));
				//Log.d("DEBUG", "USERTIMELINE:" + json);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
