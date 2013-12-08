package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, String>> list;
    private LayoutInflater mInflater;
    
    public CustomListAdapter(Context context, ArrayList<HashMap<String, String>> sessionList){
    	//When the class is started the sessionList sent in is stored in a global variable
    	list  = sessionList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    //Get view is ran for the number of items in list
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder;
    	
    	holder = new ViewHolder();
    	
    	if (convertView == null) {
    		
    		  convertView = mInflater.inflate(R.layout.event_single, null);
              
    		  convertView.setTag(holder);
    		 } else {
    		  holder = (ViewHolder) convertView.getTag();
    		 }
    	
    	  //The MyTextView allows for rotating text based off of how the XML layout is coded 
    	  TextView eventName = (TextView) convertView.findViewById(R.id.eventName);
    	  
    	  //Getting references to the other textViews on the layout
		  TextView authorName = (TextView) convertView.findViewById(R.id.authorName);
		  TextView eventTime = (TextView) convertView.findViewById(R.id.eventTime);
		  TextView eventLocation = (TextView) convertView.findViewById(R.id.eventLocation);
		  
		  HashMap<String, String> currentData = new HashMap<String, String>();
          
		  //The HashMap currentData is filled with information from the list based off of
		  //the position of the tile about to be made
		  currentData = list.get(position);
         
		  //Setting text for event name, author name, event time, and event location
          eventName.setText(currentData.get("event_name"));
          authorName.setText(currentData.get("author_name"));
          eventTime.setText(currentData.get("event_time"));
          eventLocation.setText(currentData.get("event_location"));
         
          //Depending on the track that the event is in, the event
          //name is displayed in various colors
          if(currentData.get("track").equals("Leadership")){
        	 eventName.setTextColor(0xffff0000);
          }
          if(currentData.get("track").equals("Civic Engagement")){
        	  eventName.setTextColor(0xffff00ff);
          }
          if(currentData.get("track").equals("Corps Practices")){
        	  eventName.setTextColor(0xff0000ff);
          }
          if(currentData.get("track").equals("Technical Excellence")){
        	  eventName.setTextColor(Color.rgb(0,100,0));
          }
    	  
          //Returns the view which translates to each individual tile in the list
    	 return convertView;
    	}


		static class ViewHolder {
    	}
}