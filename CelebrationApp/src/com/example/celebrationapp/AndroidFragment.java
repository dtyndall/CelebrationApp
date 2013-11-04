package com.example.celebrationapp;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

/** This is a listfragment class */
public class AndroidFragment extends ListFragment {

   /** An array of items to display in ArrayList */

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, 
		   Bundle savedInstanceState) {
       /** Creating array adapter to set data in listview */
       DBTools dbTools = new DBTools(getActivity().getBaseContext());

       ArrayList<HashMap<String, String>> sessionList = dbTools.getFavorite();

       SimpleAdapter adapter = new SimpleAdapter( 
    		   getActivity(), 
    		   sessionList,
    		   android.R.layout.two_line_list_item,
    		   new String[] { "event_name","author_name" },
    		   new int[] { android.R.id.text1, android.R.id.text2 } );

       /** Setting the array adapter to the listview */
       setListAdapter(adapter);

       return super.onCreateView(inflater, container, savedInstanceState);
   }

   @Override
   public void onStart() {
       super.onStart();
//       DBTools dbTools = new DBTools(getActivity().getBaseContext());
//       
//       final ListView eventListView = (ListView) getActivity().findViewById(R.id.scheduleTabbed);
//       ArrayList<HashMap<String, String>> sessionList = dbTools.getFavorite();
//
//		eventListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				System.out.println("pony");
//			}
//		});
//		
//       ListAdapter adapter = new SimpleAdapter(getActivity(),sessionList, 
//				R.layout.event_single, new String[] { "event_id","event_name",
//				"event_time", "author_name", "event_location"}, new int[] {R.id.eventId,
//				R.id.eventName,R.id.eventTime, R.id.authorName, R.id.eventLocation});
//		
//      /** Setting the array adapter to the listview */
//		eventListView.setAdapter(adapter);
//       /** Setting the multiselect choice mode for the listview */
  }

}