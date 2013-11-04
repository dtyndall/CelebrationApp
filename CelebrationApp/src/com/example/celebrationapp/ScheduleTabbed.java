package com.example.celebrationapp;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;

public class ScheduleTabbed extends Activity{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule_tabbed);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        actionBar.setDisplayShowTitleEnabled(true);
 
        /** Creating ANDROID Tab */
        Tab tab = actionBar.newTab()
            .setText("Android")
            .setTabListener(new CustomTabListener<AndroidFragment>(this, "android", AndroidFragment.class));
 
        actionBar.addTab(tab);
 
        /** Creating APPLE Tab */
        tab = actionBar.newTab()
            .setText("Apple")
            .setTabListener(new CustomTabListener<AppleFragment>(this, "apple", AppleFragment.class));
 
        actionBar.addTab(tab);
    }
}
