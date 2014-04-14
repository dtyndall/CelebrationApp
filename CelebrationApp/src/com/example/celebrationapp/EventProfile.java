package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class EventProfile extends Fragment {

	Listener listener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (Listener) activity;
	}


	TextView event_id;
	TextView event_name;
	TextView event_description;
	TextView author_name;
	TextView event_track;
	HorizontalScrollView authorScroll;
	String eventId, sessionId;

	DBTools dbTools;
	
	public EventProfile(String eventId, String sessionId){
		this.eventId = eventId;
		this.sessionId = sessionId;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		dbTools = new DBTools(getActivity());
		
		// Creating references to all of the outputs on screen that will be used
				TextView eventCat = (TextView) getActivity().findViewById(R.id.event_category);
				TextView eventTime = (TextView) getActivity().findViewById(R.id.event_time);
				TextView eventName = (TextView) getActivity().findViewById(R.id.event_name);
				TextView authorName = (TextView) getActivity().findViewById(R.id.author_name);
				TextView eventDesc = (TextView) getActivity().findViewById(R.id.event_description);
				TextView eventLocation = (TextView) getActivity().findViewById(R.id.event_location);
				TextView eventDate = (TextView) getActivity().findViewById(R.id.event_date);
				TextView eventTrack = (TextView) getActivity().findViewById(R.id.event_track);

				HorizontalScrollView authorScroll = (HorizontalScrollView) getActivity().findViewById(R.id.authorScroll);

				final Button storeFavorite = (Button) getActivity().findViewById(R.id.storeFavorite);
				Button showSurvey = (Button) getActivity().findViewById(R.id.event_survey);

				final HashMap<String, String> eventList = dbTools.getEventInfo(eventId);
				final HashMap<String, String> sessionList = dbTools
						.getSessionInfo(sessionId);

				if (eventList.size() != 0) {

					// Setting the track textView text
					eventTrack.setText(eventList.get("track"));

					// The proceeding four if statements will change the text color
					// depending on the track which this event belongs under
					if (eventList.get("track").equals("Leadership")) {
						eventTrack.setTextColor(0xffff0000);
					} else if (eventList.get("track").equals("Civic Engagement")) {
						eventTrack.setTextColor(0xffff00ff);
					} else if (eventList.get("track").equals("Corps Practices")) {
						eventTrack.setTextColor(0xff0000ff);
					} else if (eventList.get("track").equals("Technical Excellence")) {
						eventTrack.setTextColor(Color.rgb(0, 100, 0));
					}

					eventCat.setText(eventList.get("event_category"));
					eventName.setText(eventList.get("event_name"));
					if (eventList.get("author_name").contains(",")) {

						authorName.setVisibility(View.INVISIBLE);

						final ArrayList<String> list = new ArrayList<String>();

						Collections.addAll(list, eventList.get("author_name")
								.split(","));

						LinearLayout authorLayout = (LinearLayout) getActivity().findViewById(R.id.authorLayout);

						for (int i = 0; i < list.size(); i++) {
							final int index = i;

							TextView tv = new TextView(getActivity());
							tv.setTextSize(getResources().getDimension(
									R.dimen.authorSize));
							tv.setText(list.get(i) + "|");
							tv.setTypeface(null, Typeface.BOLD);
							tv.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									
									Bundle bundle = new Bundle();
									bundle.putString("parent", "author");
									bundle.putString("many", "many");
									bundle.putString("author", list.get(index));
									
									Fragment fragment = new TabbedSchedule();
									fragment.setArguments(bundle);
									
									getActivity().getSupportFragmentManager().popBackStack();
									listener.LoadNextFragmentWithBackstack(fragment);
									
								}

							});

							authorLayout.addView(tv);
						}
					} else {

						authorScroll.setVisibility(View.INVISIBLE);

						authorName.setText(eventList.get("author_name"));
						authorName.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {

								Bundle bundle = new Bundle();
								bundle.putString("parent", "author");
								bundle.putString("author", eventList.get("author_name"));
								
								Fragment fragment = new TabbedSchedule();
								fragment.setArguments(bundle);
								
								getActivity().getSupportFragmentManager().popBackStack();
								listener.LoadNextFragmentWithBackstack(fragment);
								
							}

						});
					}
					eventDesc.setText(eventList.get("event_description"));
					eventDesc.setMovementMethod(new ScrollingMovementMethod());

					eventDate.setText(sessionList.get("event_date"));

					eventLocation.setText(sessionList.get("event_location"));
					eventLocation.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							
							Bundle bundle = new Bundle();
							bundle.putString("parent", "room");
							bundle.putString("room", sessionList.get("event_location"));
							
							Fragment fragment = new TabbedSchedule();
							fragment.setArguments(bundle);
							
							getActivity().getSupportFragmentManager().popBackStack();
							listener.LoadNextFragmentWithBackstack(fragment);
						}

					});

					// Setting the event time textView
					eventTime.setText(sessionList.get("event_time"));

					// The following conditional will determine is the event profile is
					// for an event
					// already in the favorite table. If the event is present than the
					// star on screen
					// will be filled. If the event is not present then the star is
					// unfilled
					if (dbTools.ifFavorite(sessionList.get("session_id"))) {
						storeFavorite
								.setBackgroundResource(android.R.drawable.btn_star_big_on);
					} else {
						storeFavorite
								.setBackgroundResource(android.R.drawable.btn_star_big_off);
					}

					// An onClickListener for the favorite star
					storeFavorite.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View view) {

							// All of the information needed to store an event in the
							// favorite table
							String message = dbTools.storeFavorite(sessionId,
									eventList.get("event_name"), eventId,
									sessionList.get("event_location"),
									sessionList.get("event_time"),
									eventList.get("author_name"),
									eventList.get("track"));

							// If the storing of the event comes back as add then the
							// event was added
							// and the star is filled in
							if (message.equals("add")) {
								storeFavorite
										.setBackgroundResource(android.R.drawable.btn_star_big_on);
							} else {
								// If the storing of the event comes back as something
								// else then the event
								// was already in the favorite table and the star is
								// unfilled
								storeFavorite
										.setBackgroundResource(android.R.drawable.btn_star_big_off);
							}
						}
					});

					// Setting on onClickListener for the survey button
					if (eventList.get("event_category") != "Poster") {
						showSurvey.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View view) {
								// Yet another intent is created upon clicking the
								// survey button, same as
								// when clicking author. Only one extras are packed a
								// long this time though
								Intent survey = new Intent(getActivity(),
										WebViewActivity.class);
								survey.putExtra("survey", eventList.get("survey"));
								startActivity(survey);

							}
						});
					} else {
						showSurvey.setVisibility(View.INVISIBLE);
					}
				} else {
					// If the event profile could not display for any reason then this
					// message will appear
					eventName.setText("did not pull from database");
				}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		getActivity().invalidateOptionsMenu();
		return inflater.inflate(R.layout.event_profile, container, false);
		
	}


	// NEW
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_refresh:
			Intent home = new Intent(getActivity(), MainActivity.class);
			startActivity(home);
			break;

		default:
			break;
		}

		return true;
	}
}
