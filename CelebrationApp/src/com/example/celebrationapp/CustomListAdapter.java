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
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder;
    	
    	TextView eventName = null; 
    	TextView eventTime = null; 
    	TextView authorName = null; 
    	TextView eventLocation = null;
    	holder = new ViewHolder();
    	if (convertView == null) {
    		
    		  convertView = mInflater.inflate(R.layout.event_single, null);
    		 
    		  for(int i=0; i<((ViewGroup)convertView).getChildCount(); ++i) {
    			  
	    			  View nextChild =((ViewGroup)convertView).getChildAt(i);
	    			  TextView next;
	    			  next = (TextView) nextChild;
	    			  //System.out.println("ID OF CHILD " + next.getId());
	    			 if(next.getId() == 2131296276){
	    				 eventName = next;
	    			 }
	    			 if(next.getId() ==  2131296277){
	    				 eventTime = next;
	    			 }
	    			 if(next.getId() == 2131296278){
	    				 authorName = next;
	    			 }
	    			 if(next.getId() == 2131296261){
	    				 eventLocation = next;
	    			 }
    			
    			}
    		  
    		  HashMap<String, String> currentData = new HashMap<String, String>();
              currentData = list.get(position);
              //System.out.println(currentData);
             
              eventName.setText(currentData.get("event_name"));
              authorName.setText(currentData.get("author_name"));
              eventTime.setText(currentData.get("event_time"));
              
              eventLocation.setText(currentData.get("event_location"));
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
            	  eventName.setTextColor(0xffffc800);
              }
              
    		  convertView.setTag(holder);
    		 } else {
    		  holder = (ViewHolder) convertView.getTag();
    		 }
    	  
		  holder.eventName = (TextView) convertView.findViewById(R.id.event_name);

    	  
    	 return convertView;
    	}


		static class ViewHolder {
			TextView eventName;
    	}
}