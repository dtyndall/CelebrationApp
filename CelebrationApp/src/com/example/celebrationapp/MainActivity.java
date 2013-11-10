package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getData();

		final Button showSchedule = (Button) findViewById(R.id.showSchedule);
		showSchedule.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent eventList = new Intent(MainActivity.this,
						Days.class);
				startActivity(eventList);
			}
		});
		final Button Tabbed = (Button) findViewById(R.id.scheduleTabbedButton);
		Tabbed.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent eventList = new Intent(MainActivity.this,
						TabbedSchedule.class);
				eventList.putExtra("parent", "1");
				startActivity(eventList);
			}
		});
		final Button showPersonalSchedule = (Button) findViewById(R.id.showPersonalSchedule);
		showPersonalSchedule.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent eventList = new Intent(MainActivity.this,
						TabbedSchedule.class);
				eventList.putExtra("parent", "personal");
				startActivity(eventList);
			}
		});
		
	}

	private void getData() {
		
		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet();

		if (isInternetPresent) {
			final DBTools dbTools = new DBTools(this);
			
			RequestQueue queue = Volley.newRequestQueue(this);
			final String urlEvent = "http://192.168.167.195/getAllEvents.php";
			final String urlSession = "http://192.168.167.195/getAllSession.php";
			final String urlConference = "http://192.168.167.195/getConference.php";
			
			
			
			// prepare the Request

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
			// add it to the RequestQueue
			queue.add(getEvent);
			queue.add(getSession);
			queue.add(getConference);
		} else {
			Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_SHORT).show();
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
	      break;
	    case R.id.action_settings:
	      break;

	    default:
	      break;
	    }

	    return true;
	  }
	  


}
