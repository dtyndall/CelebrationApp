package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class TabbedSchedule extends TabActivity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		DBTools dbTools = new DBTools(this);
		
		ArrayList<HashMap<String, String>> days = dbTools.getDays();
		
		TabHost tabHost = getTabHost();
		
		for (HashMap<String, String> date : days) {

			TabSpec tabBar = tabHost.newTabSpec(date.get("date"));
			tabBar.setIndicator(date.get("date"));
			Intent tabIntent = new Intent(this, Schedule.class);
			Bundle extras = new Bundle();
			extras.putString("event_date", date.get("date"));
			extras.putString("parent","1");
			tabIntent.putExtras(extras);
			tabBar.setContent(tabIntent);
			tabHost.addTab(tabBar);
		}
		
		
			
		
		
		
	}
	 
}
