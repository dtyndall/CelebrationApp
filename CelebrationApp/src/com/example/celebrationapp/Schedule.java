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
		}else if(parent.equals("personal")){
			personalSchedule();
		}else if(parent.equals("author")){
			authorListing();
		}else if(parent.equals("room")){
			roomListing();
		}else if(parent.equals("filter")){
			filteredListing();
		}else if(parent.equals("personalFilter")){
			peronsalFilteredListing();
		}
	}
	
	  
	
	private void personalSchedule() {
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		String eventDate = extras.getString("event_date");
		
		ArrayList<HashMap<String, String>> sessionList = dbTools.getFavorite(eventDate);
		final ListView eventListView = (ListView) findViewById(R.id.eventList);
		
		eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					HashMap eventId = (HashMap) (eventListView.
							getItemAtPosition(position));
					System.out.println(eventId);
					String event_id =  eventId.get("event_id").toString();
					String session_id = eventId.get("session_id").toString();
					Intent eventProfile = new Intent(Schedule.this,
							EventProfile.class);
					
					eventProfile.putExtra("event_id", event_id);
					eventProfile.putExtra("session_id", session_id);
					

					startActivity(eventProfile);
				}
			});
		ListAdapter adapter = new CustomListAdapter(this, sessionList);
//			ListAdapter adapter = new SimpleAdapter( Schedule.this,sessionList, 
//					R.layout.event_single, new String[] { "event_id","event_name",
//					"event_time", "author_name", "event_location"}, new int[] {R.id.eventId,
//					R.id.eventName,R.id.eventTime, R.id.authorName, R.id.eventLocation});
//			
			eventListView.setAdapter(adapter);	
	}
	protected void dailySchedule(){
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		String eventDate = extras.getString("event_date");
		
		ArrayList<HashMap<String, String>> sessionList = dbTools.getSessions(eventDate);
		//System.out.println("Session list" + sessionList);
		
		final ListView eventListView = (ListView) findViewById(R.id.eventList);
		eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					HashMap eventId = (HashMap) (eventListView.
							getItemAtPosition(position));
					//System.out.println("LISTVIEW LINE " + eventId);
					String event_id =  eventId.get("event_id").toString();
					String session_id =  eventId.get("session_id").toString();
					Intent eventProfile = new Intent(Schedule.this,
							EventProfile.class);
					eventProfile.putExtra("event_id", event_id);
					eventProfile.putExtra("session_id", session_id);

					startActivity(eventProfile);
				}
			});
		ListAdapter adapter = new CustomListAdapter(this, sessionList);
		
		
//			ListAdapter adapter = new SimpleAdapter( Schedule.this,sessionList, 
//					R.layout.event_single, new String[] { "event_id","event_name",
//					"event_time", "author_name", "event_location"}, new int[] {R.id.eventId,
//					R.id.eventName,R.id.eventTime, R.id.authorName, R.id.eventLocation});
//				
//			eventListView.setAdapter(adapter);
			
			eventListView.setAdapter(adapter);

	}
	
	private void authorListing() {
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		String authorName = extras.getString("author");
		String many = extras.getString("many");
		
		ArrayList<HashMap<String, String>> authorList = dbTools.getAuthorList(authorName, many);
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
					String session_id = eventId.get("session_id").toString();
					eventProfile.putExtra("session_id", session_id);
					eventProfile.putExtra("event_id", event_id);

					startActivity(eventProfile);
				}
			});

			ListAdapter adapter = new SimpleAdapter( Schedule.this,authorList, 
					R.layout.event_single, new String[] { "event_id","event_name",
					"event_time", "event_location","event_date"}, new int[] {R.id.eventId,
					R.id.eventName,R.id.eventTime, R.id.eventLocationAuthor,R.id.eventDate});
			
			eventListView.setAdapter(adapter);
		
	}
	private void roomListing() {
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		String roomNum = extras.getString("room");
		
		ArrayList<HashMap<String, String>> roomList = dbTools.getRoomListing(roomNum);
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
					String session_id = eventId.get("session_id").toString();
					eventProfile.putExtra("session_id", session_id);
					eventProfile.putExtra("event_id", event_id);

					startActivity(eventProfile);
				}
			});

			ListAdapter adapter = new SimpleAdapter( Schedule.this,roomList, 
					R.layout.event_single, new String[] { "event_id","event_name",
					"event_time","author_name", "event_date"}, new int[] {R.id.eventId,
					R.id.eventName,R.id.eventTime, R.id.authorName, R.id.eventDate});
			
			eventListView.setAdapter(adapter);
		
	}
	
	private void filteredListing() {
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		String eventDate = extras.getString("event_date");
		String filterBy = extras.getString("key");
		String type = extras.getString("type");
		
		ArrayList<HashMap<String, String>> sessionList = 
					dbTools.getFilteredSessions(eventDate, filterBy);
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
					String session_id = eventId.get("session_id").toString();
					eventProfile.putExtra("session_id", session_id);
					eventProfile.putExtra("event_id", event_id);

					startActivity(eventProfile);
				}
			});

//			ListAdapter adapter = new SimpleAdapter( Schedule.this,sessionList, 
//					R.layout.event_single, new String[] { "event_id","event_name",
//					"event_time", "author_name", "event_location"}, new int[] {R.id.eventId,
//					R.id.eventName,R.id.eventTime, R.id.authorName, R.id.eventLocation});
//				
			ListAdapter adapter = new CustomListAdapter(this, sessionList);
			eventListView.setAdapter(adapter);
		
	}
	
	private void peronsalFilteredListing() {
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		String eventDate = extras.getString("event_date");
		String filterBy = extras.getString("key");
//		String type = extras.getString("type");
		
		ArrayList<HashMap<String, String>> sessionList = 
					dbTools.getFilteredFavorite(eventDate, filterBy);
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
					String session_id = eventId.get("session_id").toString();
					eventProfile.putExtra("session_id", session_id);
					eventProfile.putExtra("event_id", event_id);

					startActivity(eventProfile);
				}
			});
			ListAdapter adapter = new CustomListAdapter(this, sessionList);
			
//			ListAdapter adapter = new SimpleAdapter( Schedule.this,sessionList, 
//					R.layout.event_single, new String[] { "event_id","event_name",
//					"event_time", "author_name", "event_location"}, new int[] {R.id.eventId,
//					R.id.eventName,R.id.eventTime, R.id.authorName, R.id.eventLocation});
//				
			eventListView.setAdapter(adapter);
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.options, menu);
	    return true;
	  }
	  
	  //NEW
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {

	    return true;
	  }
}
