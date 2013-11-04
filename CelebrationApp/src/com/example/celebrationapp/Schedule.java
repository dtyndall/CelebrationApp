package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Schedule extends Activity {

	DBTools dbTools = new DBTools(this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.event_list);
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		String parent = extras.getString("parent");

		
		if(parent.equals("1")){
			dailySchedule();
		}
		else{
			personalSchedule();
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
	    	Intent home = new Intent(Schedule.this,
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
	
	private void personalSchedule() {
		
		ArrayList<HashMap<String, String>> sessionList = dbTools.getFavorite();
		System.out.println(sessionList);
		final ListView eventListView = (ListView) findViewById(R.id.eventList);
		
		eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					HashMap eventId = (HashMap) (eventListView.
							getItemAtPosition(position));
					
					String event_id =  eventId.get("event_id").toString();

					Intent eventProfile = new Intent(Schedule.this,
							EventProfile.class);
					eventProfile.putExtra("event_id", event_id);

					startActivity(eventProfile);
				}
			});

			ListAdapter adapter = new SimpleAdapter( Schedule.this,sessionList, 
					R.layout.event_single, new String[] { "event_id","event_name",
					"event_time", "author_name", "event_location"}, new int[] {R.id.eventId,
					R.id.eventName,R.id.eventTime, R.id.authorName, R.id.eventLocation});
			
			eventListView.setAdapter(adapter);	
	}
	protected void dailySchedule(){
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		String eventDate = extras.getString("event_date");
		
		ArrayList<HashMap<String, String>> sessionList = dbTools.getSessions(eventDate);
		final ListView eventListView = (ListView) findViewById(R.id.eventList);
		eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					HashMap eventId = (HashMap) (eventListView.
							getItemAtPosition(position));
					String event_id =  eventId.get("event_id").toString();
					Intent eventProfile = new Intent(Schedule.this,
							EventProfile.class);
					eventProfile.putExtra("event_id", event_id);

					startActivity(eventProfile);
				}
			});

			ListAdapter adapter = new SimpleAdapter( Schedule.this,sessionList, 
					R.layout.event_single, new String[] { "event_id","event_name",
					"event_time", "author_name", "event_location"}, new int[] {R.id.eventId,
					R.id.eventName,R.id.eventTime, R.id.authorName, R.id.eventLocation});
			
			eventListView.setAdapter(adapter);

	}
}
