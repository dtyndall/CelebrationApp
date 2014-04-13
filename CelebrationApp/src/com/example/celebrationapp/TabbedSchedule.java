package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

public class TabbedSchedule extends Fragment {

	Listener listener;
	Bundle extras;
	DBTools dbTools;
	ActionBar a;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		dbTools = new DBTools(getActivity().getBaseContext());
		a = getActivity().getActionBar();
		a.show();
		a.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		a.setDisplayShowTitleEnabled(true);

		
	}
	
	public void makeList(){
		Tab tab;

		String parent = listener.getVal("parent");
		ArrayList<HashMap<String,String>> list = null;
		
		if (parent.equals("1")){
			list = dbTools.getDays();
			//filter = generateFilterList\??;
		}
		else if (parent.equals("personal"))
			list = dbTools.getFavoriteDays();
		
		for (HashMap<String, String> date : list) {
			tab = a.newTab()
					.setText(date.get("public"))
					.setTabListener(
							new FragmentTabListener<Schedule>(getActivity(),
									"tab", Schedule.class, date.get("date"), parent));

			a.addTab(tab);
		}
	}
	
	
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		//TODO:ADD CHECK FOR ACTUAL ITEM CLICK
		
		switch (item.getItemId()) {
	    case R.id.time:
	    	System.out.println("SOMOWETNIONGS");
	    	extras.remove("parent");
	    	extras.putString("parent", "1");
	    	
	      break;
	    case R.id.author: 
	    	extras.remove("parent");
	    	extras.putString("parent", "filter");
	    	extras.putString("filter", "author_name");
	    	break;
	    case R.id.leader:
	    	extras.remove("parent");
	    	extras.putString("parent", "filter");
	    	extras.putString("filter", "track");
	    	extras.putString("type", "Leadership");
	    	break;
	    case R.id.technical:
	    	extras.remove("parent");
	    	extras.putString("parent", "filter");
	    	extras.putString("filter", "track");
	    	extras.putString("type", "Technical Excellence");
	    	break;
	    case R.id.civic:
	    	extras.remove("parent");
	    	extras.putString("parent", "filter");
	    	extras.putString("filter", "track");
	    	extras.putString("type", "Civic Engagement");
	    	break;
	    case R.id.corps:
	    	extras.remove("parent");
	    	extras.putString("parent", "filter");
	    	extras.putString("filter", "track");
	    	extras.putString("type", "Corps Practices");
	    	break;

	    default:
	      break;
	    }

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
