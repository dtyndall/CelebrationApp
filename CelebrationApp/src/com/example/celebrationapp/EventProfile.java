package com.example.celebrationapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
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
		
		Button storeFavorite = (Button) findViewById(R.id.storeFavorite);
		
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		final String eventId = extras.getString("event_id");
		
		final HashMap<String, String> eventList = dbTools.getEventInfo(eventId);
		final HashMap<String, String> sessionList = dbTools.getSessionInfo(eventId);
		
		
		if(eventList.size() != 0){
			eventCat.setText(eventList.get("event_category"));
			eventName.setText(eventList.get("event_name"));
			authorName.setText(eventList.get("author_name"));
			eventDesc.setText(eventList.get("event_description"));
			
			eventDate.setText(sessionList.get("event_date"));
			eventLocation.setText(sessionList.get("event_location"));
			eventTime.setText(sessionList.get("event_time"));
			
			storeFavorite.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					String message = dbTools.storeFavorite(
										eventList.get("event_name"),eventId, 
										sessionList.get("event_location"),
										sessionList.get("event_time"),
										eventList.get("author_name"));
					
					if(message.equals("add")){
						Toast.makeText(getApplicationContext(), "Event added to personal schedule"
											, Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(getApplicationContext(), "Event removed from personal schedule"
								, Toast.LENGTH_SHORT).show();
					}
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
