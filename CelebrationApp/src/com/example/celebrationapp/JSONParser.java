package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	
	public ArrayList<HashMap<String, String>> getParsedJson(JSONObject request, String key){
		
		ArrayList<HashMap<String, String>> response = new ArrayList<HashMap<String, String>>();
		
		if(key.equals("event")){
			
			JSONArray events = null;
			
			final String AUTHOR_NAME = "author_name";
			final String EVENT_CATEGORY = "event_category";
			final String EVENT_DESCRIPTION = "event_description";
			final String EVENT_ID = "event_id";
			final String EVENT_NAME = "event_name";
			
			try {
				events = request.getJSONArray("events");
				
	
				for(int i = 0; i < events.length(); i++)
				{
					HashMap<String, String> map = new HashMap<String, String>();
	
					JSONObject e = events.getJSONObject(i);
					
					String eventId = (String) e.get(EVENT_ID);
					String eventName = (String) e.get(EVENT_NAME);
					String authorName = (String) e.get(AUTHOR_NAME);
					String eventDescription = (String) e.get(EVENT_DESCRIPTION);
					String eventCategory = (String) e.get(EVENT_CATEGORY);
					
					map.put(EVENT_ID, eventId);
					map.put(EVENT_NAME, eventName);
					map.put(AUTHOR_NAME, authorName);
					map.put(EVENT_DESCRIPTION, eventDescription);
					map.put(EVENT_CATEGORY, eventCategory);
					
					response.add(map);
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(key.equals("session")){
			JSONArray session = null;
			
			final String LOCATION = "Location";
			final String DATE = "Date";
			final String TIME = "Time";
			final String EVENT_ID = "event_id";
			
			try {
				session = request.getJSONArray("session");
				
	
				for(int i = 0; i < session.length(); i++)
				{
					HashMap<String, String> map = new HashMap<String, String>();
	
					JSONObject e = session.getJSONObject(i);
					
					String eventId = (String) e.get(EVENT_ID);
					String location = (String) e.get(LOCATION);
					String date = (String) e.get(DATE);
					String time = (String) e.get(TIME);
					
					map.put(EVENT_ID, eventId);
					map.put(LOCATION, location);
					map.put(DATE, date);
					map.put(TIME, time);
					
					response.add(map);
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(key.equals("author")){
			JSONArray author = null;
			
			final String AUTHOR_NAME = "author_name";
			final String EVENT = "event";
			
			try {
				author = request.getJSONArray("Author");
				
	
				for(int i = 0; i < author.length(); i++)
				{
					HashMap<String, String> map = new HashMap<String, String>();
	
					JSONObject e = author.getJSONObject(i);
					
					String authorName = (String) e.get(AUTHOR_NAME);
					String event = (String) e.get(EVENT);
					
					map.put(AUTHOR_NAME, authorName);
					map.put(EVENT, event);
					
					response.add(map);
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//System.out.println(response);
		return response;
		
	}

}
