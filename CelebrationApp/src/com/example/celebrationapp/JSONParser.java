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
			final String SURVEY = "survey";
			final String TRACK = "track";
			final String CONFYEAR = "Conf_year";
			
			
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
					String survey = (String) e.get(SURVEY);
					String track = (String) e.get(TRACK).toString();
					String confYear = (String) e.get(CONFYEAR).toString();
					
					
					map.put(EVENT_ID, eventId);
					map.put(EVENT_NAME, eventName);
					map.put(AUTHOR_NAME, authorName);
					map.put(EVENT_DESCRIPTION, eventDescription);
					map.put(EVENT_CATEGORY, eventCategory);
					map.put(SURVEY, survey);
					map.put(TRACK, track);
					map.put(CONFYEAR, confYear);
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
			final String SESSION_ID = "session_id";
			
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
					String sessionId = (String) e.get(SESSION_ID).toString();
					
					map.put(EVENT_ID, eventId);
					map.put(LOCATION, location);
					map.put(DATE, date);
					map.put(TIME, time);
					map.put(SESSION_ID, sessionId);
					
					response.add(map);
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(key.equals("conference")){
			JSONArray conference = null;
			final String CONF_ID = "Conf_Id";
			final String CONF_NAME = "Conf_Name";
			final String CONF_YEAR = "Conf_year";
			final String CONF_LOCATION = "Conf_location";
			final String CONF_MAP = "Conf_map";
			
			try {
				conference = request.getJSONArray("conference");
	
				for(int i = 0; i < conference.length(); i++)
				{
					HashMap<String, String> map = new HashMap<String, String>();
	
					JSONObject e = conference.getJSONObject(i);
					
					String conf_id = (String) e.get(CONF_ID);
					String conf_name = (String) e.get(CONF_NAME);
					String conf_year = (String) e.get(CONF_YEAR);
					String conf_location = (String) e.get(CONF_LOCATION);
					String conf_map = (String) e.get(CONF_MAP);
					
					map.put(CONF_ID, conf_id);
					map.put(CONF_NAME, conf_name);
					map.put(CONF_YEAR, conf_year);
					map.put(CONF_LOCATION, conf_location);
					map.put(CONF_MAP, conf_map);
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
