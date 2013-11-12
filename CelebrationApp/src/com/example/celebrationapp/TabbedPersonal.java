package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class TabbedPersonal extends TabActivity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		
		Intent theIntent = getIntent();
		Bundle extra = theIntent.getExtras();
		String parent = extra.getString("parent");
		
		DBTools dbTools = new DBTools(this);
		
		
		TabHost tabHost = getTabHost();
		
		 if(parent.equals("personal")){
			ArrayList<HashMap<String, String>> days = dbTools.getFavoriteDays();

			for (HashMap<String, String> date : days) {
				
				TabSpec tabBar = tabHost.newTabSpec(date.get("date"));
				tabBar.setIndicator(date.get("public"));
				Intent tabIntent = new Intent(this, Schedule.class);
				Bundle extras = new Bundle();
				extras.putString("event_date", date.get("date"));
				extras.putString("parent","personal");
				tabIntent.putExtras(extras);
				tabBar.setContent(tabIntent);
				tabHost.addTab(tabBar);
			}
		
		}else if(parent.equals("filter")){
			ArrayList<HashMap<String, String>> days = dbTools.getFavoriteDays();

			String filterBy = extra.getString("filter");
			
			for (HashMap<String, String> date : days) {
				
				TabSpec tabBar = tabHost.newTabSpec(date.get("date"));
				tabBar.setIndicator(date.get("public"));
				Intent tabIntent = new Intent(this, Schedule.class);
				Bundle extras = new Bundle();
				extras.putString("event_date", date.get("date"));
				extras.putString("parent","personalFilter");
				extras.putString("key",filterBy);
				tabIntent.putExtras(extras);
				tabBar.setContent(tabIntent);
				tabHost.addTab(tabBar);
			}
		}
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.options, menu);
	    return true;
	  }
	  
	  //NEW
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		Intent filter = new Intent(this, TabbedPersonal.class);
		
		switch (item.getItemId()) {
	    case R.id.time:
	    	finish();
	    	filter.putExtra("parent", "personal");
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
	    	//filter.putExtra("type", "leadership");
	    	startActivity(filter);
	    	break;
	    case R.id.doer:
	    	finish();
	    	filter.putExtra("parent", "filter");
	    	filter.putExtra("filter", "track");
	    	//filter.putExtra("type", "teacher");
	    	startActivity(filter);
	    	break;

	    default:
	      break;
	    }

	    return true;
	  }
	  
	  
	  
	 
}

