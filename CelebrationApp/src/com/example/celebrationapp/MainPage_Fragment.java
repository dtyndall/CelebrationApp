package com.example.celebrationapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainPage_Fragment extends Fragment {

	DBTools dbTools;
	Listener listener;
	final static String ScreenName = "episod";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		dbTools = new DBTools(getActivity().getBaseContext());
		ActionBar a = getActivity().getActionBar();
		a.show();
		a.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		a.setDisplayShowTitleEnabled(true);

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

				Bundle bundle = new Bundle();
				bundle.putString("parent", "1");
				
				Fragment fragment = new TabbedSchedule();
				fragment.setArguments(bundle);
				listener.LoadNextFragmentWithBackstack(fragment);
			}
		});

		// Creates the button for the daily schedule
		// and starts intent to run TabbedPersonal
		final Button showPersonalSchedule = (Button) getActivity()
				.findViewById(R.id.showPersonalSchedule);
		showPersonalSchedule.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Bundle bundle = new Bundle();
				bundle.putString("parent", "personal");
				
				Fragment fragment = new TabbedSchedule();
				fragment.setArguments(bundle);
				listener.LoadNextFragmentWithBackstack(fragment);
			}
		});
		
//		downloadTweets();
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
	public void downloadTweets() {
		ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		System.out.println("this ran");
		if (networkInfo != null && networkInfo.isConnected()) {
			new DownloadTwitterTask().execute(ScreenName);
		} else {
		}
	}

	// Uses an AsyncTask to download a Twitter user's timeline
	private class DownloadTwitterTask extends AsyncTask<String, Void, String> {
		final static String CONSUMER_KEY = "HmU2CEDJ4lhpMCMWw490tckYk";
		final static String CONSUMER_SECRET = "nNUCwiqJwikMBXirIe7gpzZQ0bbnlBmEFf0lezUf4kTd4qAa9J";
		final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";
		final static String TwitterStreamURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";

		@Override
		protected String doInBackground(String... screenNames) {
			String result = null;

			if (screenNames.length > 0) {
				result = getTwitterStream(screenNames[0]);
			}
			return result;
		}

		// onPostExecute convert the JSON results into a Twitter object (which is an Array list of tweets
		@Override
		protected void onPostExecute(String result) {
			Twitter twits = jsonToTwitter(result);

			for (Tweet tweet : twits) {
				Log.i("RNC", tweet.getText());
			}

			// send the tweets to the adapter for rendering
			//ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(getActivity(), android.R.layout.simple_list_item_1, twits);
			//setListAdapter(adapter);
		}

		// converts a string of JSON data into a Twitter object
		private Twitter jsonToTwitter(String result) {
			Twitter twits = null;
			if (result != null && result.length() > 0) {
				try {
					Gson gson = new Gson();
					twits = gson.fromJson(result, Twitter.class);
				} catch (IllegalStateException ex) {
					// just eat the exception
				}
			}
			return twits;
		}

		// convert a JSON authentication object into an Authenticated object
		private Authenticated jsonToAuthenticated(String rawAuthorization) {
			Authenticated auth = null;
			if (rawAuthorization != null && rawAuthorization.length() > 0) {
				try {
					Gson gson = new Gson();
					auth = gson.fromJson(rawAuthorization, Authenticated.class);
				} catch (IllegalStateException ex) {
					// just eat the exception
				}
			}
			return auth;
		}

		private String getResponseBody(HttpRequestBase request) {
			StringBuilder sb = new StringBuilder();
			try {

				DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
				HttpResponse response = httpClient.execute(request);
				int statusCode = response.getStatusLine().getStatusCode();
				String reason = response.getStatusLine().getReasonPhrase();

				if (statusCode == 200) {

					HttpEntity entity = response.getEntity();
					InputStream inputStream = entity.getContent();

					BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
					String line = null;
					while ((line = bReader.readLine()) != null) {
						sb.append(line);
					}
				} else {
					sb.append(reason);
				}
			} catch (UnsupportedEncodingException ex) {
			} catch (ClientProtocolException ex1) {
			} catch (IOException ex2) {
			}
			return sb.toString();
		}

		private String getTwitterStream(String screenName) {
			String results = null;

			// Step 1: Encode consumer key and secret
			try {
				// URL encode the consumer key and secret
				String urlApiKey = URLEncoder.encode(CONSUMER_KEY, "UTF-8");
				String urlApiSecret = URLEncoder.encode(CONSUMER_SECRET, "UTF-8");

				// Concatenate the encoded consumer key, a colon character, and the
				// encoded consumer secret
				String combined = urlApiKey + ":" + urlApiSecret;

				// Base64 encode the string
				String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);

				// Step 2: Obtain a bearer token
				HttpPost httpPost = new HttpPost(TwitterTokenURL);
				httpPost.setHeader("Authorization", "Basic " + base64Encoded);
				httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
				httpPost.setEntity(new StringEntity("grant_type=client_credentials"));
				String rawAuthorization = getResponseBody(httpPost);
				Authenticated auth = jsonToAuthenticated(rawAuthorization);

				// Applications should verify that the value associated with the
				// token_type key of the returned object is bearer
				if (auth != null && auth.token_type.equals("bearer")) {

					// Step 3: Authenticate API requests with bearer token
					HttpGet httpGet = new HttpGet(TwitterStreamURL + screenName);

					// construct a normal HTTPS request and include an Authorization
					// header with the value of Bearer <>
					httpGet.setHeader("Authorization", "Bearer " + auth.access_token);
					httpGet.setHeader("Content-Type", "application/json");
					// update the results with the body of the response
					results = getResponseBody(httpGet);
				}
			} catch (UnsupportedEncodingException ex) {
			} catch (IllegalStateException ex1) {
			}
			return results;
		}
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
		listener = (Listener) activity;
	}

}
