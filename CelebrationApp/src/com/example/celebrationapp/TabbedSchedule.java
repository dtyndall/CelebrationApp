package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class TabbedSchedule extends TabActivity {
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		Intent theIntent = getIntent();
		Bundle extra = theIntent.getExtras();
		String parent = extra.getString("parent");
		String author = extra.getString("author");
		String roomNum = extra.getString("room");
		String many = extra.getString("many");
		
		DBTools dbTools = new DBTools(this);
		
		
		TabHost tabHost = getTabHost();
		
		if(parent.equals("1")){
			ArrayList<HashMap<String, String>> days = dbTools.getDays();

			for (HashMap<String, String> date : days) {
				
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
		}else if(parent.equals("personal")){
			ArrayList<HashMap<String, String>> days = dbTools.getFavoriteDays();

			for (HashMap<String, String> date : days) {
				
				TabSpec tabBar = tabHost.newTabSpec(date.get("date"));
				tabBar.setIndicator(date.get("date"));
				Intent tabIntent = new Intent(this, Schedule.class);
				Bundle extras = new Bundle();
				extras.putString("event_date", date.get("date"));
				extras.putString("parent","personal");
				tabIntent.putExtras(extras);
				tabBar.setContent(tabIntent);
				tabHost.addTab(tabBar);
			}
		}else if(parent.equals("author")){
				
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
			ArrayList<HashMap<String, String>> days = dbTools.getDays();
			
			String filterBy = extra.getString("filter");
			
			for (HashMap<String, String> date : days) {
				
				TabSpec tabBar = tabHost.newTabSpec(date.get("date"));
				tabBar.setIndicator(date.get("public"));
				Intent tabIntent = new Intent(this, Schedule.class);
				Bundle extras = new Bundle();
				extras.putString("event_date", date.get("date"));
				extras.putString("parent","filter");
				extras.putString("key",filterBy);
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
	    	//filter.putExtra("type", "Leadership");
	    	startActivity(filter);
	    	break;
	    case R.id.technical:
	    	finish();
	    	filter.putExtra("parent", "filter");
	    	filter.putExtra("filter", "track");
	    	//filter.putExtra("type", "Technical");
	    	startActivity(filter);
	    	break;
	    case R.id.civic:
	    	finish();
	    	filter.putExtra("parent", "filter");
	    	filter.putExtra("filter", "track");
	    	//filter.putExtra("type", "Civic Engagement");
	    	startActivity(filter);
	    	break;
	    case R.id.corps:
	    	finish();
	    	filter.putExtra("parent", "filter");
	    	filter.putExtra("filter", "track");
	    	//filter.putExtra("type", "Corps Practices");
	    	startActivity(filter);
	    	break;

	    default:
	      break;
	    }

	    return true;
	  }
	  
	  
	  
	 
}
