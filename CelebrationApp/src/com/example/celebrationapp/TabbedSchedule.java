package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class TabbedSchedule extends Fragment implements TabListener {

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		DBTools dbTools = new DBTools(getActivity().getBaseContext());
		ActionBar a = getActivity().getActionBar();
		a.show();
		a.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		a.setDisplayShowTitleEnabled(true);

		Tab tab;
		ArrayList<HashMap<String, String>> days = dbTools.getDays();
		
		for (HashMap<String, String> date : days) {
			tab = a.newTab().setText(date.get("public")).setTabListener(this);
			a.addTab(tab);
				
		}
		
	}

	@Override
	public void onDestroy() {
		
		super.onDestroy();
		getActivity().getActionBar().removeAllTabs();
		getActivity().getActionBar().hide();
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	/*
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//Gather the extras sent in from other classes
		Intent theIntent = getIntent();
		Bundle extra = theIntent.getExtras();
		String parent = extra.getString("parent");
		String author = extra.getString("author");
		String roomNum = extra.getString("room");
		String many = extra.getString("many");
		
		
		
		DBTools dbTools = new DBTools(this);
		
		
		TabHost tabHost = getTabHost();
		
		if(parent.equals("1")){
			//Gets the list of days
			ArrayList<HashMap<String, String>> days = dbTools.getDays();
			
			//For the list of days gathered above the same number of tabs are created
			//Those tabs are then filled with information for the daily schedule
			for (HashMap<String, String> date : days) {
				//Creates tabs by the list of days
				//Creates an intent for each tab which runs the Schedule class
				//Passes several keywords to the Schedule class as extras
				TabSpec tabBar = tabHost.newTabSpec(date.get("date"));
				tabBar.setIndicator(date.get("public"));
				Intent tabIntent = new Intent(this, Schedule.class);
				Bundle extras = new Bundle();
				extras.putString("event_date", date.get("date"));
				extras.putString("parent","1");
				tabIntent.putExtras(extras);
				tabBar.setContent(tabIntent);
				tabHost.addTab(tabBar);
			}
		
		}else if(parent.equals("author")){
			//Creates a tab which takes in the author name gathered above
			//This method only runs if another class starts this class and sends in the 
			//author name		
				TabSpec tabBar = tabHost.newTabSpec(author);
				tabBar.setIndicator(author);
				Intent tabIntent = new Intent(this, Schedule.class);
				Bundle extras = new Bundle();
				extras.putString("author", author);
				extras.putString("parent","author");
				extras.putString("many", many);
				tabIntent.putExtras(extras);
				tabBar.setContent(tabIntent);
				tabHost.addTab(tabBar);
				
		}else if(parent.equals("room")){
			//Creates a tab which takes in the room name gathered above
			//This method only runs if another class starts this class and sends in the 
			//room name	
					TabSpec tabBar = tabHost.newTabSpec(roomNum);
					tabBar.setIndicator(roomNum);
					Intent tabIntent = new Intent(this, Schedule.class);
					Bundle extras = new Bundle();
					extras.putString("room", roomNum);
					extras.putString("parent","room");
					tabIntent.putExtras(extras);
					tabBar.setContent(tabIntent);
					tabHost.addTab(tabBar);
					
		}else if(parent.equals("filter")){
			//Gets the list of days
			ArrayList<HashMap<String, String>> days = dbTools.getDays();
			//Gets the extra for what to filter by
			String filterBy = extra.getString("filter");
			String type = extra.getString("type");
			for (HashMap<String, String> date : days) {
				//Creates tabs by the list of days
				//Creates an intent for each tab which runs the Schedule class
				//Passes several keywords to the Schedule class as extras
				TabSpec tabBar = tabHost.newTabSpec(date.get("date"));
				tabBar.setIndicator(date.get("public"));
				Intent tabIntent = new Intent(this, Schedule.class);
				Bundle extras = new Bundle();
				extras.putString("event_date", date.get("date"));
				extras.putString("parent","filter");
				extras.putString("key",filterBy);
				extras.putString("type", type);
				tabIntent.putExtras(extras);
				tabBar.setContent(tabIntent);
				tabHost.addTab(tabBar);
			}
		}
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    Intent theIntent = getIntent();
		Bundle extra = theIntent.getExtras();
		//Depending on the extra gathered at the beginning the menu will not show up
		//if the schedule displayed is for author or room
	    if(extra.get("parent").equals("author") || extra.get("parent").equals("room")){
	    	
	    }else{
	    inflater.inflate(R.menu.options, menu);
	    }
	    return true;
	  }

	
	//NEW
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		Intent filter = new Intent(this, TabbedSchedule.class);
		//When the user selects an option from the menu, the result 
		//of their choice is run from the cases below 
		switch (item.getItemId()) {
	    case R.id.time:
	    	finish();
	    	filter.putExtra("parent", "1");
	    	startActivity(filter);
	    	
	      break;
	    case R.id.author: 
	    	finish();
	    	filter.putExtra("parent", "filter");
	    	filter.putExtra("filter", "author_name");
	    	startActivity(filter);
	    	break;
	    case R.id.leader:
	    	finish();
	    	filter.putExtra("parent", "filter");
	    	filter.putExtra("filter", "track");
	    	filter.putExtra("type", "Leadership");
	    	startActivity(filter);
	    	break;
	    case R.id.technical:
	    	finish();
	    	filter.putExtra("parent", "filter");
	    	filter.putExtra("filter", "track");
	    	filter.putExtra("type", "Technical Excellence");
	    	startActivity(filter);
	    	break;
	    case R.id.civic:
	    	finish();
	    	filter.putExtra("parent", "filter");
	    	filter.putExtra("filter", "track");
	    	filter.putExtra("type", "Civic Engagement");
	    	startActivity(filter);
	    	break;
	    case R.id.corps:
	    	finish();
	    	filter.putExtra("parent", "filter");
	    	filter.putExtra("filter", "track");
	    	filter.putExtra("type", "Corps Practices");
	    	startActivity(filter);
	    	break;

	    default:
	      break;
	    }

	    return true;
	  }
	  */
	  
	  
	 
}
