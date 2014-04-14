package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class TabbedSchedule extends Fragment {

	Listener listener;
	DBTools dbTools;
	ActionBar a;
	Bundle bundle;
	String parent="";
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		bundle = getArguments();
		
		if(bundle.containsKey("parent"))
			parent = bundle.getString("parent");
		
		
		dbTools = new DBTools(getActivity().getBaseContext());
		a = getActivity().getActionBar();
		a.removeAllTabs();
		a.show();
		a.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		a.setDisplayShowTitleEnabled(true);
		this.setHasOptionsMenu(true);
		

		makeList();
	}
	
	public void makeList(){
		Tab tab;
		ArrayList<HashMap<String,String>> list = null;
		
		if (parent.equals("1")){
			list = dbTools.getDays();
		}
		else if (parent.equals("personal")){
			list = dbTools.getFavoriteDays();
		}else if(parent.equals("author")){
			list = dbTools.getDays();
		}
		
		for (HashMap<String, String> date : list) {
		Bundle extras = new Bundle();	
		extras.putString("parent",parent);
		extras.putString("event_date", date.get("date"));
			tab = a.newTab()
					.setText(date.get("public"))
					.setTabListener(
							new FragmentTabListener<Schedule>(getActivity(),
									"tab", Schedule.class, extras));

			a.addTab(tab);
		}
	}
	
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (!parent.equals("author") && !parent.equals("room")){
			Log.d("debug","called");
			inflater.inflate(R.menu.options, menu);
			super.onCreateOptionsMenu(menu,inflater);
		}
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		//TODO:ADD CHECK FOR ACTUAL ITEM CLICK
		Bundle extras = new Bundle();
		switch (item.getItemId()) {
	    case R.id.time:
	    	extras.remove("parent");
	    	extras.putString("parent", "1");
	    	
	      break;
	    case R.id.author: 
	    	listener.setVar("parent", "filter");
	    	extras.putString("filter", "author_name");
	    	break;
	    case R.id.leader:
	    	listener.setVar("parent", "filter");
	    	extras.putString("filter", "track");
	    	extras.putString("type", "Leadership");
	    	break;
	    case R.id.technical:
	    	listener.setVar("parent", "filter");
	    	extras.putString("filter", "track");
	    	extras.putString("type", "Technical Excellence");
	    	break;
	    case R.id.civic:
	    	listener.setVar("parent", "filter");
	    	extras.putString("filter", "track");
	    	extras.putString("type", "Civic Engagement");
	    	break;
	    case R.id.corps:
	    	listener.setVar("parent", "filter");
	    	extras.putString("filter", "track");
	    	extras.putString("type", "Corps Practices");
	    	break;

	    default:
	      break;
	    }
		makeList();
		return true;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().getActionBar().removeAllTabs();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (Listener) activity;
	}
}
