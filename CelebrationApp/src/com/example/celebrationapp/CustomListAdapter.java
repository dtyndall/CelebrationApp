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
    	
    	holder = new ViewHolder();
    	
    	if (convertView == null) {
    		
    		  convertView = mInflater.inflate(R.layout.event_single, null);
              
    		  convertView.setTag(holder);
    		 } else {
    		  holder = (ViewHolder) convertView.getTag();
    		 }
    	
    	
    	  MyTextView eventName = (MyTextView) convertView.findViewById(R.id.eventName);
		  TextView authorName = (TextView) convertView.findViewById(R.id.authorName);
		  TextView eventTime = (TextView) convertView.findViewById(R.id.eventTime);
		  TextView eventLocation = (TextView) convertView.findViewById(R.id.eventLocation);
		  
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
    	  
    	 return convertView;
    	}


		static class ViewHolder {
			TextView eventName;
    	}
}