package com.codepath.apps.restclienttemplate.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/*
 * This is a temporary, sample model that demonstrates the basic structure
 * of a SQLite persisted Model object. Check out the ActiveAndroid wiki for more details:
 * https://github.com/pardom/ActiveAndroid/wiki/Creating-your-database-model
 * 
 */
@Table(name = "tweets")
public class Tweet extends Model {
//	@Column(name = "userId")
//	String userId;
	@Column(name = "userHandle")
	String userHandle;
	@Column(name = "timestamp")
	String timestamp;
	@Column(name = "body")
	String body;
	
	String name;
	String screen_name;
	String profile_image_url;
	
	JSONObject user;
	
	// Make sure to define this constructor with no arguments
	public Tweet() {
		super();
	}
	
	public String getBody() {
		return body;
	}
	
	public String getName() {
		return name;
	}
	
	public String getScreenName() {
		return screen_name;
	}
	
	public JSONObject getUser() {
		return user;
	}
	
	public String getProfileImageUrl() {
		return profile_image_url;
	}
	
	// And a constructor that creates an object from the JSON response
	public Tweet(JSONObject object){
		super();
	
		try {
			this.user = object.getJSONObject("user"); // JSON
			this.body   = object.getString("text");
			this.name   = this.user.getString("name");
			this.screen_name = this.user.getString("screen_name");
			this.profile_image_url = this.user.getString("profile_image_url");
			
	    } catch (JSONException e) {
	      e.printStackTrace();
	    }
	}
	
//	public Tweet(JSONArray json) {
//		super();
//		try {
//			this.userId = json.getString("user").getString("name");
//			this.body   = json.getString("text");
//	    } catch (JSONException e) {
//	      e.printStackTrace();
//	    }
//	}
	
	public static ArrayList<Tweet> fromJSONArray(
			JSONArray jsonArrayTweets) {
		ArrayList<Tweet> results = new ArrayList<Tweet>();
		
		for (int i=0; i< jsonArrayTweets.length(); i++) {
			try{
				results.add(new Tweet(jsonArrayTweets.getJSONObject(i)));
			} catch (JSONException e ) {
				e.printStackTrace();
			}
		}
		
		return results;
	}
	
//	// Define table fields
//	@Column(name = "name")
//	private String name;
//	
//	public SampleModel() {
//		super();
//	}
//	
//	// Parse model from JSON
//	public SampleModel(JSONObject object){
//		super();
//
//		try {
//			this.name = object.getString("title");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	// Getters
//	public String getName() {
//		return name;
//	}
//	
//	// Record Finders
//	public static SampleModel byId(long id) {
//	   return new Select().from(SampleModel.class).where("id = ?", id).executeSingle();
//	}
//	
//	public static ArrayList<SampleModel> recentItems() {
//      return new Select().from(SampleModel.class).orderBy("id DESC").limit("300").execute();
//	}
}
