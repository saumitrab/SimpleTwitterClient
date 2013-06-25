package com.codepath.apps.restclienttemplate.models.Fragments;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TweetsAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;


public class TwitterTimelineFragment extends Fragment {

	ListView lvTweetsTimeline;
	TweetsAdapter myArrayAdapter;
	List<Tweet> tweets;
	int tweetsPageNumber;
	
	public TwitterTimelineFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("DEBUG","Reached onCreateView of Fragment");
		
		return inflater.inflate(R.layout.fragment_twitter_timeline, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		tweets = new ArrayList<Tweet>();
		myArrayAdapter = new TweetsAdapter(getActivity(), tweets);
		lvTweetsTimeline = (ListView) getActivity().findViewById(R.id.lvTweetsTimeline);
		lvTweetsTimeline.setAdapter(myArrayAdapter);
		myArrayAdapter.clear();
		tweetsPageNumber = 1;
	}
	
	public TweetsAdapter getTweetsAdapterFromFragment() {
		return myArrayAdapter;
	}
	
	
}
