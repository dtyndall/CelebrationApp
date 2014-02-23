package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainPage_Fragment extends Fragment {

	DBTools dbTools;
	OnButtonClick buttonClick;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		dbTools = new DBTools(getActivity().getBaseContext());


		clearData();

		// Runs the method to gather data from STARS database
		getData();

		// Creates button for map and starts intent
		// to run the viewMap class
		final Button map = (Button) getActivity().findViewById(R.id.getMap);
		map.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent viewMap = new Intent(getActivity(), ViewMap.class);
				viewMap.putExtra("url", dbTools.getMapURL());
				startActivity(viewMap);

			}

		});

		// Creates the button for the daily schedule
		// and starts intent to run TabbedSchedule
		final Button Tabbed = (Button) getActivity().findViewById(
				R.id.scheduleTabbedButton);
		Tabbed.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				 
				 buttonClick.LoadNextFragmentWithBackstack(new TabbedSchedule());

				//Intent eventList = new Intent(getActivity(),
				//		TabbedSchedule.class);
				//eventList.putExtra("parent", "1");
				//startActivity(eventList);
			}
		});

		// Creates the button for the daily schedule
		// and starts intent to run TabbedPersonal
		final Button showPersonalSchedule = (Button) getActivity()
				.findViewById(R.id.showPersonalSchedule);
		showPersonalSchedule.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent eventList = new Intent(getActivity(),
						TabbedPersonal.class);
				eventList.putExtra("parent", "personal");
				checkFavorite();
				startActivity(eventList);
			}
		});

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_main, container, false);
	}

	// This method is unfinished, will be implemented prior to final
	// presentation
	private void clearData() {
		// Will check if the phone has an Internet connection
		ConnectionDetector cd = new ConnectionDetector(getActivity()
				.getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet();

		// If Internet connection is present the phone will attempt to
		// download data from the STARS database
		if (isInternetPresent) {

			dbTools.itsDeadJim();

		}

	}

	// The following method is unfinished, will be implemented by the time of
	// presentation
	private void checkFavorite() {

		dbTools.checkFavorite();

		// if(message.equals("change")){
		// Toast.makeText(getApplicationContext(), "An event from your personal"
		// +
		// "schedule has been changed.", Toast.LENGTH_LONG).show();
		//
		// }

	}

	private void getData() {
		// Will check if the phone has an Internet connection
		ConnectionDetector cd = new ConnectionDetector(getActivity()
				.getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet();

		// If Internet connection is present the phone will attempt to
		// download data from the STARS database
		if (isInternetPresent) {

			// Creating the links which will get the data from the database
			RequestQueue queue = Volley.newRequestQueue(getActivity());
			final String urlEvent = "http://starscomputingcorps.org/celebrationApp/getAllEvents.php";
			final String urlSession = "http://starscomputingcorps.org/celebrationApp/getAllSession.php";
			final String urlConference = "http://starscomputingcorps.org/celebrationApp/getConference.php";

			// The three requests to gather data are prepared below and
			// will handle gathering the data from the appropriate url
			// before storing them into the database
			JsonObjectRequest getEvent = new JsonObjectRequest(
					Request.Method.GET, urlEvent, null,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {
							// display response
							JSONParser jParse = new JSONParser();
							ArrayList<HashMap<String, String>> events = jParse
									.getParsedJson(response, "event");
							for (HashMap<String, String> map : events) {

								dbTools.insertEvent(map);
							}
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.d("Error.Response", error.toString());

						}
					});
			JsonObjectRequest getConference = new JsonObjectRequest(
					Request.Method.GET, urlConference, null,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {

							// display response
							JSONParser jParse = new JSONParser();
							ArrayList<HashMap<String, String>> conference = jParse
									.getParsedJson(response, "conference");
							for (HashMap<String, String> map : conference) {
								dbTools.insertConference(map);
							}
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.d("Error.Response", error.toString());

						}
					});
			JsonObjectRequest getSession = new JsonObjectRequest(
					Request.Method.GET, urlSession, null,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {
							// display response
							JSONParser jParse = new JSONParser();
							ArrayList<HashMap<String, String>> session = jParse
									.getParsedJson(response, "session");

							for (HashMap<String, String> map : session) {
								dbTools.insertSession(map);
							}
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.d("Error.Response", error.toString());

						}
					});

			// Adds the three requests detailed above to the RequestQueue
			// These three lines are what causes the requests above to run
			queue.add(getEvent);
			queue.add(getSession);
			queue.add(getConference);
		} else {
			// Toast.makeText(getApplicationContext(), "No Internet",
			// Toast.LENGTH_SHORT).show();
		}

	}

	// The following two methods create a menu for the phone
	// but the menu for the start page has been disabled
	public boolean onCreateOptionsMenu(Menu menu) {
		// MenuInflater inflater = getMenuInflater();
		// inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}

	// NEW
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_refresh:
			break;
		case R.id.action_settings:
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		buttonClick = (OnButtonClick) activity;
	}


}
