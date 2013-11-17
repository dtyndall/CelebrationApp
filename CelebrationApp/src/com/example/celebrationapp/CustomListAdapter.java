package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    	
    	if (convertView == null) {
    		  convertView = mInflater.inflate(R.layout.event_single, null);
    		  holder = new ViewHolder();
    		  for(int i=0; i<((ViewGroup)convertView).getChildCount(); ++i) {
    			  View nextChild =((ViewGroup)convertView).getChildAt(i);
    			  TextView next;
    			  next = (TextView) nextChild;
    			  //System.out.println("ID OF CHILD " + next.getId());
    			 if(next.getId() == 2131230739){
    				 eventName = next;
    			 }
    			 if(next.getId() ==  2131230740){
    				 eventTime = next;
    			 }
    			 if(next.getId() == 2131230741){
    				 authorName = next;
    			 }
    			 if(next.getId() == 2131230724){
    				 eventLocation = next;
    			 }
    			
    			}
    		  
    		  HashMap<String, String> currentData = new HashMap<String, String>();
              currentData = list.get(position);
              System.out.println(currentData);
              eventName.setText(currentData.get("event_name"));
              authorName.setText(currentData.get("author_name"));
              eventTime.setText(currentData.get("event_time"));
              eventLocation.setText(currentData.get("event_location"));
              if(currentData.get("track").equals("technical")){
            	 eventName.setTextColor(0xffff0000);
              }
              if(currentData.get("track").equals("leadership")){
            	  eventName.setTextColor(0xff00ff00);
              }
              if(currentData.get("track").equals("teacher")){
            	  eventName.setTextColor(0xff00ffff);
              }
              
//              if (currentData != null) {
//                 
//            	  System.out.println(currentData.get("event_name"));
//                  System.out.println(currentData.get("event_location"));
//                  System.out.println(currentData.get("event_time"));
//                  System.out.println(currentData.get("author_name"));
//              } 

    		  convertView.setTag(holder);
    		 } else {
    		  holder = (ViewHolder) convertView.getTag();
    		 }
    	  
		  

    	  
    	 return convertView;
    	}


		static class ViewHolder {
    	 TextView eventName;
    	 TextView eventId;
    	}
}