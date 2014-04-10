package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class TabbedPersonal extends Fragment {
	
	Listener listener;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		DBTools dbTools = new DBTools(getActivity().getBaseContext());
		ActionBar a = getActivity().getActionBar();
		a.show();
		a.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		a.setDisplayShowTitleEnabled(true);

		Tab tab;
		ArrayList<HashMap<String, String>> days = dbTools.getFavoriteDays();

		for (HashMap<String, String> date : days) {
			tab = a.newTab()
					.setText(date.get("public"))
					.setTabListener(
							new FragmentTabListener<Schedule>(getActivity(),
									"tab", Schedule.class, date.get("date")));

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
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (Listener) activity;
	}
	
	
	
	
	
	
	
	
	
	

//	public void onCreate(Bundle savedInstanceState){
//		super.onCreate(savedInstanceState);
//		
//		Intent theIntent = getIntent();
//		Bundle extra = theIntent.getExtras();
//		
//		//Get extra string "parent" passed in from another class
//		String parent = extra.getString("parent");
//		
//		DBTools dbTools = new DBTools(this);
//		
//		
//		TabHost tabHost = getTabHost();
//		
//		 if(parent.equals("personal")){
//			//Gets the list of days
//			ArrayList<HashMap<String, String>> days = dbTools.getFavoriteDays();
//			//For the list of days gathered above the same number of tabs are created
//			//Those tabs are then filled with information for the daily schedule
//			for (HashMap<String, String> date : days) {
//				//Creates tabs by the list of days
//				//Creates an intent for each tab which runs the Schedule class
//				//Passes several keywords to the Schedule class as extras
//				TabSpec tabBar = tabHost.newTabSpec(date.get("date"));
//				tabBar.setIndicator(date.get("public"));
//				Intent tabIntent = new Intent(this, Schedule.class);
//				Bundle extras = new Bundle();
//				extras.putString("event_date", date.get("date"));
//				extras.putString("parent","personal");
//				tabIntent.putExtras(extras);
//				tabBar.setContent(tabIntent);
//				tabHost.addTab(tabBar);
//			}
//		
//		}else if(parent.equals("filter")){
//			//Gets the list of days
//			ArrayList<HashMap<String, String>> days = dbTools.getFavoriteDays();
//			//Gets the extra for what to filter by
//			String filterBy = extra.getString("filter");
//			String type = extra.getString("type");
//			for (HashMap<String, String> date : days) {
//				//Creates tabs by the list of days
//				//Creates an intent for each tab which runs the Schedule class
//				//Passes several keywords to the Schedule class as extras
//				TabSpec tabBar = tabHost.newTabSpec(date.get("date"));
//				tabBar.setIndicator(date.get("public"));
//				Intent tabIntent = new Intent(this, Schedule.class);
//				Bundle extras = new Bundle();
//				extras.putString("event_date", date.get("date"));
//				extras.putString("parent","personalFilter");
//				extras.putString("key",filterBy);
//				extras.putString("type", type);
//				tabIntent.putExtras(extras);
//				tabBar.setContent(tabIntent);
//				tabHost.addTab(tabBar);
//			}
//		}
//		
//	}
//	public boolean onCreateOptionsMenu(Menu menu) {
//	    MenuInflater inflater = getMenuInflater();
//	    inflater.inflate(R.menu.options, menu);
//	    return true;
//	  }
//	  
//	  //NEW
//	  @Override
//	  public boolean onOptionsItemSelected(MenuItem item) {
//		Intent filter = new Intent(this, TabbedPersonal.class);
//		//When the user selects an option from the menu, the result 
//		//of their choice is run from the cases below 
//		switch (item.getItemId()) {
//	    case R.id.time:
//	    	finish();
//	    	filter.putExtra("parent", "personal");
//	    	startActivity(filter);
//	    	
//	      break;
//	    case R.id.leader:
//	    	finish();
//	    	filter.putExtra("parent", "filter");
//	    	filter.putExtra("filter", "track");
//	    	filter.putExtra("type", "Leadership");
//	    	startActivity(filter);
//	    	break;
//	    case R.id.technical:
//	    	finish();
//	    	filter.putExtra("parent", "filter");
//	    	filter.putExtra("filter", "track");
//	    	filter.putExtra("type", "Technical Excellence");
//	    	startActivity(filter);
//	    	break;
//	    case R.id.civic:
//	    	finish();
//	    	filter.putExtra("parent", "filter");
//	    	filter.putExtra("filter", "track");
//	    	filter.putExtra("type", "Civic Engagement");
//	    	startActivity(filter);
//	    	break;
//	    case R.id.corps:
//	    	finish();
//	    	filter.putExtra("parent", "filter");
//	    	filter.putExtra("filter", "track");
//	    	filter.putExtra("type", "Corps Practices");
//	    	startActivity(filter);
//	    	break;
//
//	    default:
//	      break;
//	    }
//
//	    return true;
//	  }
	  
	  
	  
	 
}

