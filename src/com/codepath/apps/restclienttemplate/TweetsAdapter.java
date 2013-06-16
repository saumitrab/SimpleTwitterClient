/**
 * 
 */
package com.codepath.apps.restclienttemplate;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author saumitra
 *
 */
public class TweetsAdapter extends ArrayAdapter {

	/**
	 * @param context
	 * @param textViewResourceId
	 */
	public TweetsAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param resource
	 * @param textViewResourceId
	 */
	public TweetsAdapter(Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param textViewResourceId
	 * @param objects
	 */
	public TweetsAdapter(Context context, int textViewResourceId,
			Object[] objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param textViewResourceId
	 * @param objects
	 */
	public TweetsAdapter(Context context, int textViewResourceId, List objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param resource
	 * @param textViewResourceId
	 * @param objects
	 */
	public TweetsAdapter(Context context, int resource, int textViewResourceId,
			Object[] objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param resource
	 * @param textViewResourceId
	 * @param objects
	 */
	public TweetsAdapter(Context context, int resource, int textViewResourceId,
			List objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//	    View view = convertView;
//	    if (view == null) {
//	    	LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	    	view = inflater.inflate(R.layout.tweet_item, null);
//	    }
//
//        Tweet tweet = getItem(position);
//        
//        ImageView imageView = (ImageView) view.findViewById(R.id.ivProfile);
//        ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), imageView);
//        
//        TextView nameView = (TextView) view.findViewById(R.id.tvName);
//        String formattedName = "<b>" + tweet.getUser().getName() + "</b>" + " <small><font color='#777777'>@" +
//                tweet.getUser().getScreenName() + "</font></small>";
//        nameView.setText(Html.fromHtml(formattedName));
//
//        TextView bodyView = (TextView) view.findViewById(R.id.tvBody);
//        bodyView.setText(Html.fromHtml(tweet.getBody()));
//        
//        return view;
//	}
}
