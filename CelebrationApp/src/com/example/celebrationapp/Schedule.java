package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Schedule extends android.app.Fragment implements OnItemClickListener {

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
		String filterBy = extras.getString("filter");
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

//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		String parent = listener.getVal("parent");
//		if (!parent.equals("author") && !parent.equals("room"))
//			inflater.inflate(R.menu.options, menu);
//	}

	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		//TODO:ADD CHECK FOR ACTUAL ITEM CLICK
//		
//		switch (item.getItemId()) {
//	    case R.id.time:
//	    	System.out.println("SOMOWETNIONGS");
//	    	extras.remove("parent");
//	    	extras.putString("parent", "1");
//	    	
//	      break;
//	    case R.id.author: 
//	    	extras.remove("parent");
//	    	extras.putString("parent", "filter");
//	    	extras.putString("filter", "author_name");
//	    	break;
//	    case R.id.leader:
//	    	extras.remove("parent");
//	    	extras.putString("parent", "filter");
//	    	extras.putString("filter", "track");
//	    	extras.putString("type", "Leadership");
//	    	break;
//	    case R.id.technical:
//	    	extras.remove("parent");
//	    	extras.putString("parent", "filter");
//	    	extras.putString("filter", "track");
//	    	extras.putString("type", "Technical Excellence");
//	    	break;
//	    case R.id.civic:
//	    	extras.remove("parent");
//	    	extras.putString("parent", "filter");
//	    	extras.putString("filter", "track");
//	    	extras.putString("type", "Civic Engagement");
//	    	break;
//	    case R.id.corps:
//	    	extras.remove("parent");
//	    	extras.putString("parent", "filter");
//	    	extras.putString("filter", "track");
//	    	extras.putString("type", "Corps Practices");
//	    	break;
//
//	    default:
//	      break;
//	    }
//
//		checkStuff();
//		return true;
//	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		// Gets the HashMap stored in the tile at the selected position
		HashMap<?, ?> eventId = (HashMap<?, ?>) eventListView
				.getItemAtPosition(position);

		String event_id = eventId.get("event_id").toString();
		String session_id = eventId.get("session_id").toString();
	
		Fragment fragment = new EventProfile(event_id, session_id);

		listener.LoadNextFragmentWithBackstack(fragment);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (Listener) activity;
	}
}
