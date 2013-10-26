package com.example.celebrationapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EventProfile extends Activity {
	
	TextView event_id;
	TextView event_name;
	TextView event_description;
	TextView author_name;
	
	DBTools dbTools = new DBTools(this);
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_profile);
		
		
		TextView eventCat = (TextView) findViewById(R.id.event_category);
		TextView eventName = (TextView) findViewById(R.id.event_name);
		TextView authorName = (TextView) findViewById(R.id.author_name);
		TextView eventDesc = (TextView) findViewById(R.id.event_description);
		TextView eventLocation = (TextView) findViewById(R.id.event_location);
		TextView eventTime = (TextView) findViewById(R.id.event_time);
		TextView eventDate = (TextView) findViewById(R.id.event_date);
		
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		String eventId = extras.getString("event_id");
		
		final HashMap<String, String> eventList = dbTools.getEventInfo(eventId);
		HashMap<String, String> sessionList = dbTools.getSessionInfo(eventId);
		
		
		if(eventList.size() != 0){
			eventCat.setText(eventList.get("event_category"));
			eventName.setText(eventList.get("event_name"));
			authorName.setText(eventList.get("author_name"));
			eventDesc.setText(eventList.get("event_description"));
			
			eventDate.setText(sessionList.get("event_date"));
			eventLocation.setText(sessionList.get("event_location"));
			eventTime.setText(sessionList.get("event_time"));
		}
		else{
			eventName.setText("did not pull from database");
		}
		
		
	}

}
