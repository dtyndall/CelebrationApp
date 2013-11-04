package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Days extends Activity {

	DBTools dbTools = new DBTools(this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.day_list);

		ArrayList<HashMap<String, String>> dayList = dbTools.getDays();
		
		final ListView dayListView = (ListView) findViewById(R.id.dayList);

		dayListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					HashMap eventDate = (HashMap) (dayListView.
							getItemAtPosition(position));
					String event_date =  eventDate.get("date").toString();
					Bundle extras = new Bundle();
					Intent daySchedule = new Intent(Days.this,
							Schedule.class);
					
					extras.putString("event_date", event_date);
					extras.putString("parent","1");
					
					daySchedule.putExtras(extras);
					startActivity(daySchedule);
				}
			});

			ListAdapter adapter = new SimpleAdapter( Days.this,dayList, 
					R.layout.day_single, new String[] { "date"}, 
					new int[] {R.id.eventDate});
			
			dayListView.setAdapter(adapter);

	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.mainmenu, menu);
	    return true;
	  }
	//NEW
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_refresh:
	    	Intent home = new Intent(Days.this,
					MainActivity.class);
			startActivity(home);
	      break;
	    case R.id.action_settings:
	      Toast.makeText(this, "Action Settings selected", Toast.LENGTH_SHORT)
	          .show();
	      break;

	    default:
	      break;
	    }

	    return true;
	  }
}
