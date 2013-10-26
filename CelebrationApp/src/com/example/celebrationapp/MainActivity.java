package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
		
		final Button viewEvent = (Button) findViewById(R.id.viewEvents);
		viewEvent.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent eventProfile = new Intent(MainActivity.this,
						EventProfile.class);
				eventProfile.putExtra("event_id", "5");
				startActivity(eventProfile);
			}
		});
		final SharedPreferences settings = getSharedPreferences("com.example.celebrationapp", MODE_PRIVATE);

		final Button makeFavorite = (Button) findViewById(R.id.makeFavorite);
		makeFavorite.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				 Editor editor = settings.edit();
					 editor.putString("key", "5");
				     editor.commit();
			}
		});
		
		final Button showSchedule = (Button) findViewById(R.id.showSchedule);
		showSchedule.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent eventList = new Intent(MainActivity.this,
						Days.class);
				startActivity(eventList);
			}
		});
		
		final TextView showFavorite = (TextView) findViewById(R.id.showFavorite);
		
		final Button viewFavorite = (Button) findViewById(R.id.viewFavorite);
		viewFavorite.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				String value = settings.getString("key","");
				System.out.println(value);
			}
		});
	}

	private void getData() {

		final TextView ifInternet = (TextView) findViewById(R.id.testInternet);

		ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		Boolean isInternetPresent = cd.isConnectingToInternet();

		if (isInternetPresent) {
			RequestQueue queue = Volley.newRequestQueue(this);
			final String urlEvent = "http://192.168.2.2/getAllEvents.php";
			final String urlSession = "http://192.168.2.2/getAllSession.php";
			final DBTools dbTools = new DBTools(this);

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
			JsonObjectRequest getSession = new JsonObjectRequest(
					Request.Method.GET, urlSession, null,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {
							// display response
							JSONParser jParse = new JSONParser();
							ArrayList<HashMap<String, String>> session = jParse
									.getParsedJson(response, "session");
							System.out.println(session.toString());

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
		} else {
			ifInternet.setText("no Internet");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
