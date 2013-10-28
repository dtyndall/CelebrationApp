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
	//String table3 = "author";
	
	final String CREATE_TABLE_1 = 
			"CREATE TABLE IF NOT EXISTS " + table1 + " (event_id INTEGER PRIMARY KEY, event_name TEXT, "
			+ "author_name TEXT, event_description TEXT, event_category TEXT)";
	final String CREATE_TABLE_2 = 
			"CREATE TABLE IF NOT EXISTS " + table2 + " (event_id INTEGER PRIMARY KEY , location TEXT, "
			+ "date TEXT , time TEXT)";
	final String CREATE_TABLE_3 =
			"CREATE TABLE IF NOT EXISTS " + table3 + " (event_id INTEGER PRIMARY KEY, event_name TEXT," 
			+ "event_location TEXT, event_time TEXT, author_name TEXT)";

	
	//	final String CREATE_TABLE_3 = 
//			"CREATE TABLE IF NOT EXISTS " + table3 + " (author_name TEXT , event_name TEXT)";


	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_TABLE_1);
		db.execSQL(CREATE_TABLE_2);
		db.execSQL(CREATE_TABLE_3);
			
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query = "DROP TABLE IF EXISTS event";
		String query1 = "DROP TABLE IF EXISTS session";
		String query2 = "DROP TABLE IF EXISTS favorite";
		
		db.execSQL(query);
		db.execSQL(query1);
		db.execSQL(query2);
		onCreate(db);
		
	}
	
	public void insertEvent(HashMap<String, String> queryValues) {

		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("event_id", queryValues.get("event_id"));
		values.put("event_name", queryValues.get("event_name"));
		values.put("author_name", queryValues.get("author_name"));
		values.put("event_description", queryValues.get("event_description"));
		values.put("event_category", queryValues.get("event_category"));

		database.insert("event", null, values);

		database.close();

	}


public void insertSession(HashMap<String, String> queryValues) {

	SQLiteDatabase database = this.getWritableDatabase();

	ContentValues values = new ContentValues();

	values.put("event_id", queryValues.get("event_id"));
	values.put("location", queryValues.get("Location"));
	values.put("date", queryValues.get("Date"));
	values.put("time", queryValues.get("Time"));


	database.insert("session", null, values);

	database.close();

}
//
//public void insertAuthor(HashMap<String, String> queryValues) {
//
//	SQLiteDatabase database = this.getWritableDatabase();
//
//	ContentValues values = new ContentValues();
//
//	values.put("author_name", queryValues.get("author_name"));
//	values.put("event", queryValues.get("event"));
//
//	database.insert("author", null, values);
//
//	database.close();
//
//}
	
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
				eventmap.put("event_description", cursor.getString(3));
				eventmap.put("event_category", cursor.getString(4));

			} while (cursor.moveToNext());

		}
		return eventmap;

	}
	public HashMap<String, String> getSessionInfo(String id) {
		HashMap<String, String> sessionmap = new HashMap<String, String>();

		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM session WHERE event_id= '" + id + "';";
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				sessionmap.put("event_id", cursor.getString(0));
				sessionmap.put("event_location", cursor.getString(1));
				sessionmap.put("event_date", cursor.getString(2));
				sessionmap.put("event_time", cursor.getString(3));

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
				"session.event_id=event.event_id and date ='" + eventDate + "'";
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		Cursor cursor = database.rawQuery(selectAllQuery, null);
		
		if (cursor.moveToFirst()) {
			
			do {
				
				HashMap<String, String> sessionmap = new HashMap<String, String>();
				
				sessionmap.put("event_id", cursor.getString(0));
				sessionmap.put("event_location", cursor.getString(1));
				sessionmap.put("event_date", cursor.getString(2));
				sessionmap.put("event_time", cursor.getString(3));
				sessionmap.put("event_name", cursor.getString(5));
				sessionmap.put("author_name", cursor.getString(6));
				sessionmap.put("event_description", cursor.getString(7));
				sessionmap.put("event_category", cursor.getString(8));
				
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
				contactMap.put("event_description", cursor.getString(3));
				contactMap.put("event_category", cursor.getString(4));

				eventsArrayList.add(contactMap);

			} while (cursor.moveToNext());

		}
		return eventsArrayList;

	}
	
	
	public String storeFavorite(String name, String id, String location, String time, String authorName){
		String selectQuery = "SELECT * FROM favorite WHERE event_id='" + id + "'";
		
		SQLiteDatabase database = this.getWritableDatabase();
		System.out.println(name+ " " +id+ " " +location+ " " +time+ " " +authorName);
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
