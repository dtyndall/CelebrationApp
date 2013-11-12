package com.example.celebrationapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventProfile extends Activity {
	
	TextView event_id;
	TextView event_name;
	TextView event_description;
	TextView author_name;
	TextView event_track;
	
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
		TextView eventTrack = (TextView) findViewById(R.id.event_track);
		
		Button storeFavorite = (Button) findViewById(R.id.storeFavorite);
		Button showSurvey = (Button) findViewById(R.id.event_survey);
		
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		final String eventId = extras.getString("event_id");
		
		final HashMap<String, String> eventList = dbTools.getEventInfo(eventId);
		final HashMap<String, String> sessionList = dbTools.getSessionInfo(eventId);
		
		
		if(eventList.size() != 0){
			eventTrack.setText(eventList.get("track"));
			eventCat.setText(eventList.get("event_category"));
			eventName.setText(eventList.get("event_name"));
			authorName.setText(eventList.get("author_name"));
			authorName.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View v) {
					  Intent authorList = new Intent(EventProfile.this,
								TabbedSchedule.class);
						authorList.putExtra("parent", "author");
						authorList.putExtra("author", eventList.get("author_name"));
						startActivity(authorList);
				  }

				});
			eventDesc.setText(eventList.get("event_description"));
			eventDesc.setMovementMethod(new ScrollingMovementMethod());
			eventDate.setText(sessionList.get("event_date"));
			eventLocation.setText(sessionList.get("event_location"));
			eventLocation.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View v) {
					  Intent roomList = new Intent(EventProfile.this,
								TabbedSchedule.class);
						roomList.putExtra("parent", "room");
						roomList.putExtra("room", sessionList.get("event_location"));
						startActivity(roomList);
				  }

				});
			eventTime.setText(sessionList.get("event_time"));
			
			storeFavorite.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					String message = dbTools.storeFavorite(
										eventList.get("event_name"),eventId, 
										sessionList.get("event_location"),
										sessionList.get("event_time"),
										eventList.get("author_name"),
										eventList.get("track"));
					
					if(message.equals("add")){
						Toast.makeText(getApplicationContext(), "Event added to personal schedule"
											, Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(getApplicationContext(), "Event removed from personal schedule"
								, Toast.LENGTH_SHORT).show();
					}
				}
			});
			showSurvey.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					Intent survey = new Intent(EventProfile.this,
							WebViewActivity.class);
					survey.putExtra("survey", eventList.get("survey"));
					startActivity(survey);
					
				}
			});
		}
		else{
			eventName.setText("did not pull from database");
		}
		
		
		
		
	}

	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.mainmenu, menu);
	    return true;
	  }
	//NEW
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_refresh:
	    	Intent home = new Intent(EventProfile.this,
					MainActivity.class);
			startActivity(home);
	      break;
	    case R.id.action_settings:
	      Toast.makeText(this, "Action Settings selected", Toast.LENGTH_SHORT)
	          .show();
	      break;

	    default:
	      break;
	    }

	    return true;
	  }
}
