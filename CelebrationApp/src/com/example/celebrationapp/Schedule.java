package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Schedule extends Fragment implements OnItemClickListener {

	DBTools dbTools;
	Bundle extras;
	ListView eventListView;
	Listener listener;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.event_list, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		dbTools = new DBTools(getActivity());

		eventListView = (ListView) getActivity().findViewById(R.id.area);
		eventListView.setOnItemClickListener(this);

		extras = getArguments();

		checkStuff();
	}

	public void checkStuff() {
		String parent = extras.getString("parent");
		String eventDate = extras.getString("event_date");
		String authorName = extras.getString("author");
		String many = extras.getString("many");
		String roomNum = extras.getString("room");
		String filterBy = extras.getString("key");
		String type = extras.getString("type");

		ListAdapter adapter = null;

		if (parent.equals("1")) {
			ArrayList<HashMap<String, String>> sessionList = dbTools
					.getSessions(eventDate);
			adapter = new CustomListAdapter(getActivity(), sessionList);
		} else if (parent.equals("personal")) {
			ArrayList<HashMap<String, String>> sessionList = dbTools
					.getFavorite(eventDate);
			adapter = new CustomListAdapter(getActivity(), sessionList);
		} else if (parent.equals("author")) {
			ArrayList<HashMap<String, String>> authorList = dbTools
					.getAuthorList(authorName, many);
			adapter = new CustomListAdapter(getActivity(), authorList);
		} else if (parent.equals("room")) {
			ArrayList<HashMap<String, String>> roomList = dbTools
					.getRoomListing(roomNum);
			adapter = new CustomListAdapter(getActivity(), roomList);
		} else if (parent.equals("filter")) {
			ArrayList<HashMap<String, String>> sessionList = dbTools
					.getFilteredSessions(eventDate, filterBy, type);
			adapter = new CustomListAdapter(getActivity(), sessionList);
		} else if (parent.equals("personalFilter")) {
			ArrayList<HashMap<String, String>> sessionList = dbTools
					.getFilteredFavorite(eventDate, filterBy, type);
			adapter = new CustomListAdapter(getActivity(), sessionList);
		}
		eventListView.setAdapter(adapter);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		String parent = listener.getVal("parent");
		if (!parent.equals("author") && !parent.equals("room"))
			inflater.inflate(R.menu.options, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//TODO:ADD CHECK FOR ACTUAL ITEM CLICK
		extras.remove("parent");
		extras.putString("parent", "room");
		extras.putString("room", "Room 404");

		checkStuff();
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		// Gets the HashMap stored in the tile at the selected position
		HashMap<?, ?> eventId = (HashMap<?, ?>) eventListView
				.getItemAtPosition(position);

		String event_id = eventId.get("event_id").toString();
		String session_id = eventId.get("session_id").toString();

		// Creates an intent which starts EventProfile
		// Intent eventProfile = new Intent(Schedule.this, EventProfile.class);

		// Stores two strings in the intent created
		// these two strings are sent to EventProfile
		// eventProfile.putExtra("session_id", session_id);
		// eventProfile.putExtra("event_id", event_id);
		// EvenProfile is run
		// startActivity(eventProfile);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (Listener) activity;
	}
}
