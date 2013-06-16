package com.codepath.apps.restclienttemplate.models;

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
	@Column(name = "userId")
	String userId;
	@Column(name = "userHandle")
	String userHandle;
	@Column(name = "timestamp")
	String timestamp;
	@Column(name = "body")
	String body;
	
	// Make sure to define this constructor with no arguments
	public Tweet() {
		super();
	}
	
	// And a constructor that creates an object from the JSON response
	public Tweet(JSONObject object){
		super();
	
		try {
			this.userId = object.getString("userId");
			// ...
	    } catch (JSONException e) {
	      e.printStackTrace();
	    }
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
