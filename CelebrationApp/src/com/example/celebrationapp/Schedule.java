package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Schedule extends Activity {

	DBTools dbTools = new DBTools(this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.event_list);
		//Gathers the parent extra sent from other classes
		
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		String parent = extras.getString("parent");
		

		//Depending on the parent extra gathered above the appropriate method will run
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
		//Get the event_date extra sent from another class
		String eventDate = extras.getString("event_date");
		//Gather an ArrayList of HashMaps filled with all information from the database
		ArrayList<HashMap<String, String>> sessionList = dbTools.getFavorite(eventDate);
		//Get a reference to the ListView on screen
		final ListView eventListView = (ListView) findViewById(R.id.eventList);
		//Set a click listener for the ListView. This will allow users to click 
		//individual tiles in the listview
		eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					//Gets the HashMap stored in the tile at the selected position
					HashMap<?, ?> eventId = (HashMap<?, ?>) (eventListView.
							getItemAtPosition(position));
					String event_id =  eventId.get("event_id").toString();
					String session_id = eventId.get("session_id").toString();
					//Creates an intent which starts EventProfile
					Intent eventProfile = new Intent(Schedule.this,
							EventProfile.class);
					//Stores two strings in the intent created
					//these two strings are sent to EventProfile
					eventProfile.putExtra("event_id", event_id);
					eventProfile.putExtra("session_id", session_id);
					
					//EventProfile is run
					startActivity(eventProfile);
				}
			});
		//The list of event information is sent to the CustomListAdapter class I created
		//This class creates each individual tile that shows up in the ListView
		ListAdapter adapter = new CustomListAdapter(this, sessionList);

		//Displays the ListView 
		eventListView.setAdapter(adapter);	
	}
	protected void dailySchedule(){
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		//Get the event_date extra sent from another class
		String eventDate = extras.getString("event_date");
		
		//Gather an ArrayList of HashMaps filled with all information from the database
		ArrayList<HashMap<String, String>> sessionList = dbTools.getSessions(eventDate);
		
		//Get a reference to the ListView on screen
		final ListView eventListView = (ListView) findViewById(R.id.eventList);
		
		//Set a click listener for the ListView. This will allow users to click 
		//individual tiles in the listview
		eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					//Gets the HashMap stored in the tile at the selected position
					HashMap<?, ?> eventId = (HashMap<?, ?>) (eventListView.
							getItemAtPosition(position));
					
					String event_id =  eventId.get("event_id").toString();
					String session_id =  eventId.get("session_id").toString();
					
					//Creates an intent which starts EventProfile
					Intent eventProfile = new Intent(Schedule.this,
							EventProfile.class);
					
					//Stores two strings in the intent created
					//these two strings are sent to EventProfile
					eventProfile.putExtra("event_id", event_id);
					eventProfile.putExtra("session_id", session_id);
					
					//EvenProfile is run
					startActivity(eventProfile);
				}
			});
			
			//The list of event information is sent to the CustomListAdapter class I created
			//This class creates each individual tile that shows up in the ListView
			ListAdapter adapter = new CustomListAdapter(this, sessionList);
		
			//Displays the ListView 
			eventListView.setAdapter(adapter);

	}
	
	private void authorListing() {
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		//Get the event_date and keyword many extra sent from another class
		String authorName = extras.getString("author");
		String many = extras.getString("many");
		
		//Gather an ArrayList of HashMaps filled with all information from the database, uses the keyword many to determine
		//which type of sql query is ran
		ArrayList<HashMap<String, String>> authorList = dbTools.getAuthorList(authorName, many);
		
		//Get a reference to the ListView on screen
		final ListView eventListView = (ListView) findViewById(R.id.eventList);
		
		//Set a click listener for the ListView. This will allow users to click 
		//individual tiles in the listview
		eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
		
					//Gets the HashMap stored in the tile at the selected position
					HashMap<?, ?> eventId = (HashMap<?, ?>) (eventListView.
							getItemAtPosition(position));
					String event_id =  eventId.get("event_id").toString();
					String session_id = eventId.get("session_id").toString();
					
					//Creates an intent which starts EventProfile
					Intent eventProfile = new Intent(Schedule.this,
							EventProfile.class);
					
					//Stores two strings in the intent created
					//these two strings are sent to EventProfile
					eventProfile.putExtra("session_id", session_id);
					eventProfile.putExtra("event_id", event_id);
					
					//EvenProfile is run
					startActivity(eventProfile);
				}
			});
			//The list of event information is sent to the CustomListAdapter class I created
			//This class creates each individual tile that shows up in the ListView
			ListAdapter adapter = new CustomListAdapter(this, authorList);
		
			//Displays the ListView 
			eventListView.setAdapter(adapter);
		
	}
	private void roomListing() {
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		//Get the room extra sent from another class
		String roomNum = extras.getString("room");
		
		//Gather an ArrayList of HashMaps filled with all information from the database
		ArrayList<HashMap<String, String>> roomList = dbTools.getRoomListing(roomNum);
		
		//Get a reference to the ListView on screen
		final ListView eventListView = (ListView) findViewById(R.id.eventList);
		
		//Set a click listener for the ListView. This will allow users to click 
		//individual tiles in the listview
		eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					//Gets the HashMap stored in the tile at the selected position
					HashMap<?, ?> eventId = (HashMap<?, ?>) (eventListView.
							getItemAtPosition(position));
					String event_id =  eventId.get("event_id").toString();
					String session_id = eventId.get("session_id").toString();
					
					//Creates an intent which starts EventProfile
					Intent eventProfile = new Intent(Schedule.this,
							EventProfile.class);
					
					//Stores two strings in the intent created
					//these two strings are sent to EventProfile
					eventProfile.putExtra("session_id", session_id);
					eventProfile.putExtra("event_id", event_id);
					
					//EvenProfile is run
					startActivity(eventProfile);
				}
			});
			
			//The list of event information is sent to the CustomListAdapter class I created
			//This class creates each individual tile that shows up in the ListView
			ListAdapter adapter = new CustomListAdapter(this, roomList);
			
			//Displays the ListView
			eventListView.setAdapter(adapter);
		
	}
	
	private void filteredListing() {
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		//Get the event_date, filterBy keyword, and type keyword extra sent from another class
		String eventDate = extras.getString("event_date");
		String filterBy = extras.getString("key");
		String type = extras.getString("type");
		
		//Gather an ArrayList of HashMaps filled with all information from the database
		ArrayList<HashMap<String, String>> sessionList = 
					dbTools.getFilteredSessions(eventDate, filterBy, type);
		
		//Get a reference to the ListView on screen
		final ListView eventListView = (ListView) findViewById(R.id.eventList);
		
		//Set a click listener for the ListView. This will allow users to click 
		//individual tiles in the listview
		eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					//Gets the HashMap stored in the tile at the selected position
					HashMap<?, ?> eventId = (HashMap<?, ?>) (eventListView.
							getItemAtPosition(position));
					
					String event_id =  eventId.get("event_id").toString();
					String session_id = eventId.get("session_id").toString();
					
					//Creates an intent which starts EventProfile
					Intent eventProfile = new Intent(Schedule.this,
							EventProfile.class);
					
					//Stores two strings in the intent created
					//these two strings are sent to EventProfile
					eventProfile.putExtra("session_id", session_id);
					eventProfile.putExtra("event_id", event_id);
					
					//EvenProfile is run
					startActivity(eventProfile);
				}
			});
			
			//The list of event information is sent to the CustomListAdapter class I created
			//This class creates each individual tile that shows up in the ListView
			ListAdapter adapter = new CustomListAdapter(this, sessionList);
			
			//Displays the ListView
			eventListView.setAdapter(adapter);
		
	}
	
	private void peronsalFilteredListing() {
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		//Get the event_date, filterBy keyword, and type keyword extra sent from another class
		String eventDate = extras.getString("event_date");
		String filterBy = extras.getString("key");
		String type = extras.getString("type");
		
		//Gather an ArrayList of HashMaps filled with all information from the database
		ArrayList<HashMap<String, String>> sessionList = 
					dbTools.getFilteredFavorite(eventDate, filterBy, type);
		
		//Get a reference to the ListView on screen
		final ListView eventListView = (ListView) findViewById(R.id.eventList);
		
		//Set a click listener for the ListView. This will allow users to click 
		//individual tiles in the listview
		eventListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					//Gets the HashMap stored in the tile at the selected position
					HashMap<?, ?> eventId = (HashMap<?, ?>) (eventListView.
							getItemAtPosition(position));
					
					String event_id =  eventId.get("event_id").toString();
					String session_id = eventId.get("session_id").toString();
					
					//Creates an intent which starts EventProfile
					Intent eventProfile = new Intent(Schedule.this,
							EventProfile.class);
					
					//Stores two strings in the intent created
					//these two strings are sent to EventProfile
					eventProfile.putExtra("session_id", session_id);
					eventProfile.putExtra("event_id", event_id);
					//EvenProfile is run
					startActivity(eventProfile);
				}
			});
			//The list of event information is sent to the CustomListAdapter class I created
			//This class creates each individual tile that shows up in the ListView
			ListAdapter adapter = new CustomListAdapter(this, sessionList);

			//Displays the ListView
			eventListView.setAdapter(adapter);
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    
	    return true;
	  }
	  
	  //NEW
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {

	    return true;
	  }
}
