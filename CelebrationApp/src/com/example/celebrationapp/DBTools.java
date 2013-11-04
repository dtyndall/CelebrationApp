package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBTools extends SQLiteOpenHelper{
	
	public DBTools(Context applicationContext) {

		super(applicationContext, "starsConference.db", null, 1);

	}
	
	String table1 = "event";
	String table2 = "session";
	String table3 = "favorite";
	String table4 = "conference";
	
	final String CREATE_TABLE_1 = 
			"CREATE TABLE IF NOT EXISTS " + table1 + " (event_id INTEGER PRIMARY KEY, event_name TEXT, "
			+ "author_name TEXT,conf_year TEXT, survey TEXT, track TEXT, event_description TEXT, event_category TEXT)";
	final String CREATE_TABLE_2 = 
			"CREATE TABLE IF NOT EXISTS " + table2 + " (sevent_id INTEGER PRIMARY KEY , location TEXT, "
			+ "date TEXT , time TEXT, session_id TEXT)";
	final String CREATE_TABLE_3 =
			"CREATE TABLE IF NOT EXISTS " + table3 + " (event_id INTEGER PRIMARY KEY, event_name TEXT," 
			+ "event_location TEXT, event_time TEXT, author_name TEXT, track TEXT)";
	final String CREATE_TABLE_4 =
			"CREATE TABLE IF NOT EXISTS " + table4 + " (conf_id INTEGER PRIMARY KEY, conf_name TEXT," 
					+ "conf_year TEXT, conf_location TEXT, conf_map TEXT)";

	
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_TABLE_1);
		db.execSQL(CREATE_TABLE_2);
		db.execSQL(CREATE_TABLE_3);
		db.execSQL(CREATE_TABLE_4);
			
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query = "DROP TABLE IF EXISTS event";
		String query1 = "DROP TABLE IF EXISTS session";
		String query3 = "DROP TABLE IF EXISTS conferece";
		
		db.execSQL(query);
		db.execSQL(query1);
		db.execSQL(query3);
		onCreate(db);
		
	}
	
	public void insertEvent(HashMap<String, String> queryValues) {

			SQLiteDatabase database = this.getWritableDatabase();
			String selectQuery = "SELECT * FROM event WHERE event_id= '" + 
								queryValues.get("event_id") + "';";
			Cursor cursor = database.rawQuery(selectQuery, null);
			if(cursor.moveToFirst()){
				updateEvent(queryValues);
				return;
			}

			ContentValues values = new ContentValues();
	
			values.put("event_id", queryValues.get("event_id"));
			values.put("event_name", queryValues.get("event_name"));
			values.put("author_name", queryValues.get("author_name"));
			values.put("event_description", queryValues.get("event_description"));
			values.put("event_category", queryValues.get("event_category"));
			values.put("conf_year", queryValues.get("Conf_year"));
			values.put("survey", queryValues.get("survey"));
			values.put("track", queryValues.get("track"));
			database.insert("event", null, values);
	
			database.close();
	}
	public void insertSession(HashMap<String, String> queryValues) {

			SQLiteDatabase database = this.getWritableDatabase();
			String selectQuery = "SELECT * FROM session WHERE sevent_id= '" + 
					queryValues.get("event_id") + "';";
			Cursor cursor = database.rawQuery(selectQuery, null);
			if(cursor.moveToFirst()){
				updateSession(queryValues);
				return;
			}

			ContentValues values = new ContentValues();
			values.put("sevent_id", queryValues.get("event_id"));
			values.put("location", queryValues.get("Location"));
			values.put("date", queryValues.get("Date"));
			values.put("time", queryValues.get("Time"));
			values.put("session_id", queryValues.get("session_id"));
			
			database.insert("session", null, values);
		
			database.close();
}
	public void insertConference(HashMap<String, String> queryValues) {
			SQLiteDatabase database = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
		
			values.put("conf_id", queryValues.get("conf_id"));
			values.put("conf_name", queryValues.get("conf_name"));
			values.put("conf_year", queryValues.get("conf_year"));
			values.put("conf_location", queryValues.get("conf_location"));
			values.put("conf_map", queryValues.get("conf_map"));
			
			database.insert("conference", null, values);
		
			database.close();
		
	}
	public int updateEvent(HashMap<String, String> queryValues){


		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put("event_id", queryValues.get("event_id"));
		values.put("event_name", queryValues.get("event_name"));
		values.put("author_name", queryValues.get("author_name"));
		values.put("event_description", queryValues.get("event_description"));
		values.put("event_category", queryValues.get("event_category"));
		values.put("conf_year", queryValues.get("Conf_year"));
		values.put("survey", queryValues.get("survey"));
		values.put("track", queryValues.get("track"));


		
		return database.update("event", values, "event_id" + " = ?",
				new String[] { queryValues.get("event_id") });
	}
	public int updateSession(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		
		values.put("sevent_id", queryValues.get("sevent_id"));
		values.put("location", queryValues.get("Location"));
		values.put("date", queryValues.get("Date"));
		values.put("time", queryValues.get("Time"));
		values.put("session_id", queryValues.get("session_id"));
		
		return database.update("session", values, "sevent_id" + " = ?",
				new String[] { queryValues.get("sevent_id") });
	}



	public HashMap<String, String> getEventInfo(String id) {
		HashMap<String, String> eventmap = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM event WHERE event_id= '" + id + "';";
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				eventmap.put("event_id", cursor.getString(0));
				eventmap.put("event_name", cursor.getString(1));
				eventmap.put("author_name", cursor.getString(2));
				eventmap.put("conf_year", cursor.getString(3));
				eventmap.put("survey", cursor.getString(4));
				eventmap.put("track", cursor.getString(5));
				eventmap.put("event_description", cursor.getString(6));
				eventmap.put("event_category", cursor.getString(7));

			} while (cursor.moveToNext());

		}
		return eventmap;

	}
	public HashMap<String, String> getSessionInfo(String id) {
		HashMap<String, String> sessionmap = new HashMap<String, String>();

		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM session WHERE sevent_id= '" + id + "';";
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				sessionmap.put("sevent_id", cursor.getString(0));
				sessionmap.put("event_location", cursor.getString(1));
				sessionmap.put("event_date", cursor.getString(2));
				sessionmap.put("event_time", cursor.getString(3));
				sessionmap.put("session_id", cursor.getString(4));

			} while (cursor.moveToNext());

		}
		return sessionmap;

	}
	
	public ArrayList<HashMap<String, String>> getDays() {
		
		ArrayList<HashMap<String, String>> dayList = new ArrayList<HashMap<String, String>>();
		
		String selectAllQuery = "Select DISTINCT date FROM session";
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		Cursor cursor = database.rawQuery(selectAllQuery, null);
		if (cursor.moveToFirst()) {
			do {
				
				HashMap<String, String> daymap = new HashMap<String, String>();
				
				daymap.put("date", cursor.getString(0));
				
				dayList.add(daymap);
			} while (cursor.moveToNext());
			
		}
		return dayList;
		
		
		
	}
	
	
	
	public ArrayList<HashMap<String, String>> getSessions(String eventDate) {
		ArrayList<HashMap<String, String>> sessionArrayList = new ArrayList<HashMap<String, String>>();
		
		String selectAllQuery = "Select * from session inner join event where " +
				"session.sevent_id=event.event_id and date ='" + eventDate + "'";
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		Cursor cursor = database.rawQuery(selectAllQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				
				HashMap<String, String> sessionmap = new HashMap<String, String>();
				
				sessionmap.put("event_id", cursor.getString(0));
				sessionmap.put("event_location", cursor.getString(1));
				sessionmap.put("event_date", cursor.getString(2));
				sessionmap.put("event_time", cursor.getString(3));
				sessionmap.put("session_id", cursor.getString(4));
				sessionmap.put("sevent_id", cursor.getString(5));
				sessionmap.put("event_name", cursor.getString(6));
				sessionmap.put("author_name", cursor.getString(7));
				sessionmap.put("conf_year", cursor.getString(8));
				sessionmap.put("survey", cursor.getString(9));
				sessionmap.put("track", cursor.getString(10));
				sessionmap.put("event_description", cursor.getString(11));
				sessionmap.put("event_category", cursor.getString(12));
				
				
				sessionArrayList.add(sessionmap);
			} while (cursor.moveToNext());
			
		}
		return sessionArrayList;
		
	}
	public ArrayList<HashMap<String, String>> getEvents() {

		ArrayList<HashMap<String, String>> eventsArrayList = new ArrayList<HashMap<String, String>>();

		String selectQuery = "SELECT * FROM event ORDER BY event_id";

		SQLiteDatabase database = this.getWritableDatabase();

		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {

			do {

				HashMap<String, String> contactMap = new HashMap<String, String>();

				contactMap.put("event_id", cursor.getString(0));
				contactMap.put("event_name", cursor.getString(1));
				contactMap.put("author_name", cursor.getString(2));
				contactMap.put("track", cursor.getString(3));
				contactMap.put("survey", cursor.getString(4));
				contactMap.put("track", cursor.getString(5));
				contactMap.put("event_description", cursor.getString(6));
				contactMap.put("conf_year", cursor.getString(7));
				eventsArrayList.add(contactMap);

			} while (cursor.moveToNext());

		}
		return eventsArrayList;

	}
	
	
	public String storeFavorite(String name, String id, String location, String time, String authorName){
		String selectQuery = "SELECT * FROM favorite WHERE event_id='" + id + "'";
		
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if (!cursor.moveToFirst()) {
			ContentValues values = new ContentValues();

			values.put("event_id", id);
			values.put("event_name", name);
			values.put("event_location", location);
			values.put("event_time", time);
			values.put("author_name", authorName);
			
			database.insert("favorite", null, values);

			database.close();
			return "add";
			
		}else
		{
			database.delete("favorite", "event_id" + "=" + id, null);
			return "delete";
		}

	}
	public ArrayList<HashMap<String, String>> getFavorite() {

		ArrayList<HashMap<String, String>> eventsArrayList = new ArrayList<HashMap<String, String>>();

		String selectQuery = "SELECT * FROM favorite	";

		SQLiteDatabase database = this.getWritableDatabase();

		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {

			do {

				HashMap<String, String> contactMap = new HashMap<String, String>();

				contactMap.put("event_id", cursor.getString(0));
				contactMap.put("event_name", cursor.getString(1));
				contactMap.put("event_location", cursor.getString(2));
				contactMap.put("event_time", cursor.getString(3));
				contactMap.put("author_name", cursor.getString(4));

				eventsArrayList.add(contactMap);

			} while (cursor.moveToNext());

		}
		return eventsArrayList;

	}

	

}
