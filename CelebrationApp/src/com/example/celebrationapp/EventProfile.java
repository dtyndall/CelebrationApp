package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventProfile extends Activity {
	
	TextView event_id;
	TextView event_name;
	TextView event_description;
	TextView author_name;
	TextView event_track;
	HorizontalScrollView authorScroll;
	
	DBTools dbTools = new DBTools(this);
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_profile);
		
		//Creating references to all of the outputs on screen that will be used
		TextView eventCat = (TextView) findViewById(R.id.event_category);
		TextView eventTime = (TextView) findViewById(R.id.event_time);
		TextView eventName = (TextView) findViewById(R.id.event_name);
		TextView authorName = (TextView) findViewById(R.id.author_name);
		TextView eventDesc = (TextView) findViewById(R.id.event_description);
		TextView eventLocation = (TextView) findViewById(R.id.event_location);
		TextView eventDate = (TextView) findViewById(R.id.event_date);
		TextView eventTrack = (TextView) findViewById(R.id.event_track);
		
		HorizontalScrollView authorScroll = (HorizontalScrollView) findViewById(R.id.authorScroll);
		
		final Button storeFavorite = (Button) findViewById(R.id.storeFavorite);
		Button showSurvey = (Button) findViewById(R.id.event_survey);
		
		
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		//Getting the extra strings passed into this class
		final String eventId = extras.getString("event_id");
		final String sessionId = extras.getString("session_id");
		
		//Getting the information from the database based off of the extra strings
		//sent to this class upon startup. Those extra strings are the determining
		//factor in deciding which event profile gets shown
		final HashMap<String, String> eventList = dbTools.getEventInfo(eventId);
		final HashMap<String, String> sessionList = dbTools.getSessionInfo(sessionId);
		
		if(eventList.size() != 0){
			
			//Setting the track textView text
			eventTrack.setText(eventList.get("track"));
			
			//The proceeding four if statements will change the text color
			//depending on the track which this event belongs under
			 if(eventList.get("track").equals("Leadership")){
	        	 eventTrack.setTextColor(0xffff0000);
	          }
			 else if(eventList.get("track").equals("Civic Engagement")){
	        	  eventTrack.setTextColor(0xffff00ff);
	          }
			 else if(eventList.get("track").equals("Corps Practices")){
	        	  eventTrack.setTextColor(0xff0000ff);
	          }
			 else if(eventList.get("track").equals("Technical Excellence")){
	        	  eventTrack.setTextColor(Color.rgb(0,100,0));
	          }
			 
			 //Setting the event category and event name textViews
			eventCat.setText(eventList.get("event_category"));
			eventName.setText(eventList.get("event_name"));
			
			//Determining if the event being shown has one or more than one authors
			if(eventList.get("author_name").contains(",")){
				
				//If the event does have more than one author than the single author 
				//textView box is made invisible
				authorName.setVisibility(View.INVISIBLE);

				//Creates an ArrayList called list
				final ArrayList<String> list = new ArrayList<String>();

				//ArrayList list is filled with authors  by separating the multiple authors which
				//are split by commas
				Collections.addAll(list, eventList.get("author_name").split(","));
				
				//Gets a reference to the LinearLayout on screen
				LinearLayout authorLayout = (LinearLayout) findViewById(R.id.authorLayout);
				
				for(int i = 0; i< list.size(); i++){
					final int index = i;

					//At this point I am programmatically creating textViews on the screen because
					//pripr to the start of this page it is unclear how many authors need to be
					//displayed. I set the textsize, text, typeface, and create an onClickListener
					TextView tv = new TextView(this);
					tv.setTextSize(getResources().getDimension(R.dimen.authorSize));
					tv.setText(list.get(i) + "|");
					tv.setTypeface(null,Typeface.BOLD);
					tv.setOnClickListener(new View.OnClickListener() {
						  @Override
						  public void onClick(View v) {
							  //Once the author textView is clicked an intent for the tabbedSchedule
							  //class is created. Three extras are generated and stored in the intent before 
							  //the intent is started. Doing this allows the user to click on the author
							  //name he or she is interested in and see all of the events that author is
							  //taking place in
							  Intent authorList = new Intent(EventProfile.this,
										Schedule.class);
								authorList.putExtra("parent", "author");
								authorList.putExtra("many", "many");
								authorList.putExtra("author",list.get(index));
								startActivity(authorList);
						  }

						});
					
					//Adds the textViews to the linearlayout referenced earlier, this allows for horizontal
					//scrolling of authors
					authorLayout.addView(tv);
				}
			}else{
				
				//If there is only one author then the horizontal scroll view is made invisible
				authorScroll.setVisibility(View.INVISIBLE);
				
				//The author textView has it's text set to the author's name
				authorName.setText(eventList.get("author_name"));
				authorName.setOnClickListener(new View.OnClickListener() {
					  @Override
					  public void onClick(View v) {
						  //Yet another intent is created upon clicking the author name, same as 
						  //intent above this one. Only two extras are packed a long this time though
						  Intent authorList = new Intent(EventProfile.this,
									TabbedSchedule.class);
							authorList.putExtra("parent", "author");
							authorList.putExtra("author", eventList.get("author_name"));
							startActivity(authorList);
					  }

					});
			}
			
			//Setting the text for the event description and allowing for vertical scrolling
			eventDesc.setText(eventList.get("event_description"));
			eventDesc.setMovementMethod(new ScrollingMovementMethod());
			
			//Setting text for the event date
			eventDate.setText(sessionList.get("event_date"));
			
			//Setting text and an onClickListener for event location
			eventLocation.setText(sessionList.get("event_location"));
			eventLocation.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View v) {
					  //Yet another intent is created upon clicking the event location, same as 
					  //when clicking author. Only two extras are packed a long this time though
					  Intent roomList = new Intent(EventProfile.this,
								Schedule.class);
						roomList.putExtra("parent", "room");
						roomList.putExtra("room", sessionList.get("event_location"));
						startActivity(roomList);
				  }

				});
			
			//Setting the event time textView
			eventTime.setText(sessionList.get("event_time"));
			
			//The following conditional will determine is the event profile is for an event 
			//already in the favorite table. If the event is present than the star on screen
			//will be filled. If the  event is not present then the star is unfilled
			if(dbTools.ifFavorite(sessionList.get("session_id"))){
				storeFavorite.setBackgroundResource(android.R.drawable.btn_star_big_on);
			}else{
				storeFavorite.setBackgroundResource(android.R.drawable.btn_star_big_off);
			}
			
			//An onClickListener for the favorite star
			storeFavorite.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					
					//All of the information needed to store an event in the favorite table
					String message = dbTools.storeFavorite(
										sessionId,
										eventList.get("event_name"),eventId, 
										sessionList.get("event_location"),
										sessionList.get("event_time"),
										eventList.get("author_name"),
										eventList.get("track"));
					
					//If the storing of the event comes back as add then the event was added
					//and the star is filled in
					if(message.equals("add")){
						storeFavorite.setBackgroundResource(android.R.drawable.btn_star_big_on);
					}else{
					//If the storing of the event comes back as something else then the event
					//was already in the favorite table and the star is unfilled
						storeFavorite.setBackgroundResource(android.R.drawable.btn_star_big_off);
					}
				}
			});

			//Setting on onClickListener for the survey button
			if(eventList.get("event_category") != "Poster"){
			showSurvey.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					 //Yet another intent is created upon clicking the survey button, same as 
					  //when clicking author. Only one extras are packed a long this time though
					Intent survey = new Intent(EventProfile.this,
							WebViewActivity.class);
					survey.putExtra("survey", eventList.get("survey"));
					startActivity(survey);
					
				}
			});
		}else{
			showSurvey.setVisibility(View.INVISIBLE);
		}
		}
		else{
			//If the event profile could not display for any reason then this message will appear
			eventName.setText("did not pull from database");
		}
		

		
		
	}

	//The following two methods create and handle click requests for the menu. This menu for
	//the event profile is only a single button at the top right of the screen called refresh
	//When clicking refresh the user is taken back to the main activity, if an internet connection
	//is present then the data will also be refreshed
	@Override
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
	   

	    default:
	      break;
	    }

	    return true;
	  }
}
