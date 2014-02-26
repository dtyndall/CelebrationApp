package com.example.celebrationapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBTools extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 3;


	public DBTools(Context applicationContext) {

		super(applicationContext, "starsConference.db", null, DATABASE_VERSION);
		Log.d("dbTools CONSTRUCTOR", "TABLE CONSTRUCTED"); 
	}
	
	String table1 = "event";
	String table2 = "session";
	String table3 = "favorite";
	String table4 = "conference";
	
	final String CREATE_TABLE_1 = 
			"CREATE TABLE IF NOT EXISTS " + table1 + " (event_id INTEGER PRIMARY KEY, event_name TEXT, "
			+ "author_name TEXT,conf_year TEXT, survey TEXT, track TEXT, event_description TEXT, event_category TEXT)";
	final String CREATE_TABLE_2 = 
			"CREATE TABLE IF NOT EXISTS " + table2 + " (sevent_id INTEGER, location TEXT, "
			+ "date TEXT , time TEXT, session_id INTEGER PRIMARY KEY)";
	final String CREATE_TABLE_3 =
			"CREATE TABLE IF NOT EXISTS " + table3 + " (event_id INTEGER, event_name TEXT," 
			+ "event_location TEXT, event_time TEXT, author_name TEXT, track TEXT, " +
			"session_id INTEGER PRIMARY KEY)";
	final String CREATE_TABLE_4 =
			"CREATE TABLE IF NOT EXISTS " + table4 + " (conf_id INTEGER PRIMARY KEY, conf_name TEXT," 
					+ "conf_year TEXT, conf_location TEXT, conf_map TEXT)";

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//Default method, this creates the tables based off of the inputted SQL query
		Log.d("dbTools CREATE", "TABLE CREATED");
		db.execSQL(CREATE_TABLE_1);
		db.execSQL(CREATE_TABLE_2);
		db.execSQL(CREATE_TABLE_3);
		db.execSQL(CREATE_TABLE_4);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//When the version number of the database changes the database is updated. This is never used.
		String query = "DROP TABLE IF EXISTS event";
		String query1 = "DROP TABLE IF EXISTS session";
		String query3 = "DROP TABLE IF EXISTS conference";
		Log.d("dbTools UPGRADE", "TABLE UPGRADED");
		db.execSQL(query);
		db.execSQL(query1);
		db.execSQL(query3);
		onCreate(db);
		
	}
	
	public void insertEvent(HashMap<String, String> queryValues) {
			//Once MainActivity is passed back the parsed JSON it sends the elements of the ArrayList one at
			//a time to this method. This method will run a query on the database to see if the entry already exists,
			//if it exists then the row is updated instead of created. This process will be the same for
			//insertSession and insertConference. Any confusing differences are commented in the following
			//two methods
			
			//Open write connection to the database
			SQLiteDatabase database = this.getWritableDatabase();
			
			//Query to run on the database
			String selectQuery = "SELECT * FROM event WHERE event_id= '" + 
								queryValues.get("event_id") + "';";
		
			//The pointer for the database connection
			Cursor cursor = database.rawQuery(selectQuery, null);
			
			if(cursor.moveToFirst()){
				//An empty variable which will be filled with the values to go into the database
				ContentValues values = new ContentValues();
				
				//Uses a key/value pairing to store data
				values.put("event_id", queryValues.get("event_id"));
				values.put("event_name", queryValues.get("event_name"));
				values.put("author_name", queryValues.get("author_name"));
				values.put("event_description", queryValues.get("event_description"));
				values.put("event_category", queryValues.get("event_category"));
				values.put("conf_year", queryValues.get("Conf_year"));
				values.put("survey", queryValues.get("survey"));
				values.put("track", queryValues.get("track"));

				//The table "event" is updated with "values" where "event_id" equals the event_id of the values sent along with the method call
				database.update("event", values, "event_id" + " = ?",
						new String[] { queryValues.get("event_id") });
				//Blank return to exit the method
				return;
			}
			
			//An empty variable which will be filled with the values to go into the database
			ContentValues values = new ContentValues();
			
			//Uses a key/value pairing to store data
			values.put("event_id", queryValues.get("event_id"));
			values.put("event_name", queryValues.get("event_name"));
			values.put("author_name", queryValues.get("author_name"));
			values.put("event_description", queryValues.get("event_description"));
			values.put("event_category", queryValues.get("event_category"));
			values.put("conf_year", queryValues.get("Conf_year"));
			values.put("survey", queryValues.get("survey"));
			values.put("track", queryValues.get("track"));
			
			//The table "event" has "values" inserted into it
			database.insert("event", null, values);
	
			//Close the database
			database.close();
	}
	public void insertSession(HashMap<String, String> queryValues) {
			
			SQLiteDatabase database = this.getWritableDatabase();
			
			String selectQuery = "SELECT * FROM session WHERE session_id= '" + 
					queryValues.get("session_id") + "';";
			
			Cursor cursor = database.rawQuery(selectQuery, null);

			if(cursor.moveToFirst()){
				ContentValues values = new ContentValues();
				
				values.put("sevent_id", queryValues.get("event_id"));
				values.put("location", queryValues.get("Location"));
				values.put("date", queryValues.get("Date"));
				values.put("time", queryValues.get("Time"));
				values.put("session_id", queryValues.get("session_id"));
				
				
				
				//The table "session" is updated with "values" where "session_id" equals the session_id of the values sent along with the method call
				database.update("session", values, "session_id" + " = ?",
						new String[] { queryValues.get("session_id") });
				return;
			}

			ContentValues values = new ContentValues();
			values.put("sevent_id", queryValues.get("event_id"));
			values.put("location", queryValues.get("Location"));
			values.put("date", queryValues.get("Date"));
			values.put("time", queryValues.get("Time"));
			values.put("session_id", queryValues.get("session_id"));
			
			//The table "session" has "values" inserted into it
			database.insert("session", null, values);
		
			database.close();
}
	public void insertConference(HashMap<String, String> queryValues) {
		
			SQLiteDatabase database = this.getWritableDatabase();
			
			String selectQuery = "SELECT * FROM conference WHERE conf_id= '" + 
					queryValues.get("Conf_Id") + "';";
			
			Cursor cursor = database.rawQuery(selectQuery, null);
			
			if(cursor.moveToFirst()){
				ContentValues values = new ContentValues();
				
				values.put("conf_id", queryValues.get("Conf_Id"));
				values.put("conf_name", queryValues.get("Conf_Name"));
				values.put("conf_year", queryValues.get("Conf_year"));
				values.put("conf_location", queryValues.get("Conf_location"));
				values.put("conf_map", queryValues.get("Conf_map"));
				
				//The table "conference" is updated with "values" where "conf_id" equals the conf_id of the values sent along with the method call
				database.update("conference", values, "conf_id" + " = ?",
						new String[] { queryValues.get("Conf_id") });
				return;
			}
			
			ContentValues values = new ContentValues();
		
			values.put("conf_id", queryValues.get("Conf_Id"));
			values.put("conf_name", queryValues.get("Conf_Name"));
			values.put("conf_year", queryValues.get("Conf_year"));
			values.put("conf_location", queryValues.get("Conf_location"));
			values.put("conf_map", queryValues.get("Conf_map"));
			
			//The table "conference" has "values" inserted into it
			database.insert("conference", null, values);
			
			database.close();
		
	}

	public HashMap<String, String> getEventInfo(String id) {
		
		HashMap<String, String> eventmap = new HashMap<String, String>();

		//Open a read database connection
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM event WHERE event_id= '" + id + "';";
		
		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				
				//The local HashMap is filled with key/value pairs
				eventmap.put("event_id", cursor.getString(0));
				eventmap.put("event_name", cursor.getString(1));
				eventmap.put("author_name", cursor.getString(2));
				eventmap.put("conf_year", cursor.getString(3));
				eventmap.put("survey", cursor.getString(4));
				eventmap.put("track", cursor.getString(5));
				eventmap.put("event_description", cursor.getString(6));
				eventmap.put("event_category", cursor.getString(7));

				//As long as the pointer keeps pointing to a next object, the loop continues
			} while (cursor.moveToNext());

		}
		//Returns a HashMap filled with key/value pairs
		return eventmap;

	}
	public HashMap<String, String> getSessionInfo(String id) {
		
		HashMap<String, String> sessionmap = new HashMap<String, String>();
		
		//Open a read database connection
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM session WHERE session_id= '" + id + "';";

		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				//The local HashMap is filled with key/value pairs
				sessionmap.put("sevent_id", cursor.getString(0));
				sessionmap.put("event_location", cursor.getString(1));
				sessionmap.put("session_id", cursor.getString(4));
				
				try {
					
					//Creates a SimpleDateFormat with military time format
					SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm:ss"); 
				
					//Stores the timeFmt from the database to variable time
					Date time = timeFmt.parse(cursor.getString(3));
					
					//Creates a SimpleDateFormat with 12-hour time format
					SimpleDateFormat timeOut = new SimpleDateFormat("hh:mm a");
					
					//Stores the key/value pairing of the 12-hour SimplDateFormat variable after
					//it has been formatted out of timeDate, which is holding the military
					//time from the database
					sessionmap.put("event_time", timeOut.format(time));
					
					//Creates a SimpleDateFormat with format equal to the database
					SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
					
					//Stores the dateFmt from the database to variable date
					Date date = dateFmt.parse(cursor.getString(2));

					//Creates a SimpleDateFormat with the day-of-week format
					SimpleDateFormat fmtOut = new SimpleDateFormat("EEEE");
					
					//Stores the key/value pairing of the day SimplDateFormat variable after
					//it has been formatted out of dateFmt, which is holding the yearly
					//date from the database
					sessionmap.put("event_date", fmtOut.format(date));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			} while (cursor.moveToNext());
			
		}
		//Returns a HashMap filled with key/value pairs
		return sessionmap;

	}
	
	public ArrayList<HashMap<String, String>> getDays() {
		
		ArrayList<HashMap<String, String>> dayList = new ArrayList<HashMap<String, String>>();
		
		String selectAllQuery = "Select DISTINCT date FROM session ORDER BY date ASC";
		
		//Open a write database connection
		SQLiteDatabase database = this.getWritableDatabase();
		
		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectAllQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				
				HashMap<String, String> daymap = new HashMap<String, String>();
				
				daymap.put("date", cursor.getString(0));
				try {
					
					//Creates a SimpleDateFormat with format equal to the database
					SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
					
					//Stores the dateFmt from the database to variable date
					Date date = dateFmt.parse(cursor.getString(0));

					//Creates a SimpleDateFormat with the day-of-week format
					SimpleDateFormat fmtOut = new SimpleDateFormat("EEEE");
					
					//Stores the key/value pairing of the day SimplDateFormat variable after
					//it has been formatted out of dateFmt, which is holding the yearly
					//time from the database
					daymap.put("public", fmtOut.format(date));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Adds a new HashMap to the ArrayList
				dayList.add(daymap);
			} while (cursor.moveToNext());
			
		}
		//Returns an ArrayList of HashMaps filled with key/value pairs
		return dayList;
		
	}
	public ArrayList<HashMap<String, String>> getFavoriteDays() {
		
		ArrayList<HashMap<String, String>> dayList = new ArrayList<HashMap<String, String>>();
		
		String selectAllQuery = "Select DISTINCT date FROM session inner join" +
		" favorite where session.session_id=favorite.session_id ORDER BY date ASC";
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectAllQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				
				HashMap<String, String> daymap = new HashMap<String, String>();
				
				
				daymap.put("date", cursor.getString(0));
				try {
					
					//Creates a SimpleDateFormat with format equal to the database
					SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
					
					//Stores the dateFmt from the database to variable date
					Date date = dateFmt.parse(cursor.getString(0));

					//Creates a SimpleDateFormat with the day-of-week format
					SimpleDateFormat fmtOut = new SimpleDateFormat("EEEE");
					
					//Stores the key/value pairing of the day SimplDateFormat variable after
					//it has been formatted out of dateFmt, which is holding the yearly
					//time from the database
					daymap.put("public", fmtOut.format(date));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Adds a new HashMap to the ArrayList
				dayList.add(daymap);
			} while (cursor.moveToNext());
			
		}
		//Returns an ArrayList of HashMaps filled with key/value pairs
		return dayList;
		
	}
	
	
	public ArrayList<HashMap<String, String>> getSessions(String eventDate) {
		
		ArrayList<HashMap<String, String>> sessionArrayList = new ArrayList<HashMap<String, String>>();
		
		String selectAllQuery = "Select * from session inner join event where " +
				"session.sevent_id=event.event_id and date ='" + eventDate + "'ORDER BY time ASC";
		
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectAllQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				
				HashMap<String, String> sessionmap = new HashMap<String, String>();
				
				//The local HashMap is filled with key/value pairs
				sessionmap.put("event_id", cursor.getString(0));
				sessionmap.put("event_location", cursor.getString(1));
				sessionmap.put("event_date", cursor.getString(2));
				sessionmap.put("session_id", cursor.getString(4));
				sessionmap.put("sevent_id", cursor.getString(5));
				sessionmap.put("event_name", cursor.getString(6));
				sessionmap.put("author_name", cursor.getString(7));
				sessionmap.put("conf_year", cursor.getString(8));
				sessionmap.put("survey", cursor.getString(9));
				sessionmap.put("track", cursor.getString(10));
				sessionmap.put("event_description", cursor.getString(11));
				sessionmap.put("event_category", cursor.getString(12));
				
				try {
					
					//Creates a SimpleDateFormat with military time format
					SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss"); 
					
					//Stores the timeFmt from the database to variable time
					Date timeDate = time.parse(cursor.getString(3));
					
					//Creates a SimpleDateFormat with 12-hour time format
					SimpleDateFormat timeOut = new SimpleDateFormat("hh:mm a");
					
					//Stores the key/value pairing of the 12-hour SimplDateFormat variable after
					//it has been formatted out of timeDate, which is holding the military
					//time from the database
					sessionmap.put("event_time", timeOut.format(timeDate));
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Adds a new HashMap to the ArrayList
				sessionArrayList.add(sessionmap);
			} while (cursor.moveToNext());
			
		}
		
		//Returns an ArrayList of HashMaps filled with key/value pairs
		return sessionArrayList;
		
	}
	
	
	public String storeFavorite(String sessionId, String name, String id, String location, 
			String time, String authorName, String track){
		
		String selectQuery = "SELECT * FROM favorite WHERE session_id='" + sessionId + "'";
		
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		
		if (!cursor.moveToFirst()) {
			ContentValues values = new ContentValues();
			//The local HashMap is filled with key/value pairs
			values.put("event_id", id);
			values.put("event_name", name);
			values.put("event_location", location);
			values.put("event_time", time);
			values.put("author_name", authorName);
			values.put("track", track);
			values.put("session_id", sessionId);
			
			database.insert("favorite", null, values);

			database.close();
			
			//If there is no favorite in the favorite table matching the Id sent in
			//then the event is stored in this table the same way events are stored in the first 
			//few functions. A message is sent back to let the caller know the event was added
			return "add";
			
		}else
		{
			//If this method is called and the event already existed in the favorite table
			//then the even is deleted bassed off of the Id passed in
			database.delete("favorite", "session_id" + "=" + sessionId, null);
			return "delete";
		}

	}
	public ArrayList<HashMap<String, String>> getFavorite(String eventDate) {

		ArrayList<HashMap<String, String>> eventsArrayList = new ArrayList<HashMap<String, String>>();

		String selectQuery = "Select * from session inner join favorite where " +
				"session.session_id=favorite.session_id and date ='" + eventDate + "' ORDER BY time ASC";

		SQLiteDatabase database = this.getWritableDatabase();

		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {

			do {

				HashMap<String, String> contactMap = new HashMap<String, String>();
				//The local HashMap is filled with key/value pairs
				contactMap.put("event_id", cursor.getString(0));
				contactMap.put("event_location", cursor.getString(1));
				contactMap.put("event_name", cursor.getString(6));
				contactMap.put("author_name", cursor.getString(9));
				contactMap.put("session_id", cursor.getString(4));
				contactMap.put("track", cursor.getString(10));

				try {
					
					//Refer to method GetSessionInfo to see how these four lines work
					SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss"); 
					Date timeDate = time.parse(cursor.getString(3));
					SimpleDateFormat timeOut = new SimpleDateFormat("hh:mm a");
					contactMap.put("event_time", timeOut.format(timeDate));
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Adds a new HashMap to the ArrayList
				eventsArrayList.add(contactMap);

			} while (cursor.moveToNext());

		}
		
		//Returns the ArrayList 
		return eventsArrayList;

	}

	public ArrayList<HashMap<String, String>> getAuthorList(String authorName, String many) {
		ArrayList<HashMap<String, String>> authorArrayList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "";
		if(many != null){
			 selectQuery = "Select * from session inner join event where " +
					"session.sevent_id=event.event_id and author_name LIKE '%" + authorName + "%' ORDER BY time ASC";

		}else{
			 selectQuery = "Select * from session inner join event where " +
						"session.sevent_id=event.event_id and author_name LIKE '%" + authorName + "%' ORDER BY time ASC";
		}
		
		SQLiteDatabase database = this.getWritableDatabase();

		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {

			do {

				HashMap<String, String> contactMap = new HashMap<String, String>();
				//The local HashMap is filled with key/value pairs
				contactMap.put("event_id", cursor.getString(0));
				contactMap.put("event_location", cursor.getString(1));
				contactMap.put("event_date", cursor.getString(2));
				contactMap.put("session_id", cursor.getString(4));
				contactMap.put("event_name", cursor.getString(6));
				contactMap.put("author_name", cursor.getString(7));
				contactMap.put("track",cursor.getString(10));
				
				try {
					
					//Refer to method GetSessionInfo to see how these four lines work
					SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss"); 
					Date timeDate = time.parse(cursor.getString(3));
					SimpleDateFormat timeOut = new SimpleDateFormat("hh:mm a");
					contactMap.put("event_time", timeOut.format(timeDate));
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Adds a new HashMap to the ArrayList
				authorArrayList.add(contactMap);

			} while (cursor.moveToNext());

		}
		
		//Returns the ArrayList 
		return authorArrayList;

	}

	public ArrayList<HashMap<String, String>> getRoomListing(String roomNum) {
		ArrayList<HashMap<String, String>> roomArrayList =
						new ArrayList<HashMap<String, String>>();
		String selectQuery = "Select * from session inner join event where " +
				"session.sevent_id=event.event_id and session.location ='" + roomNum + "'ORDER BY time ASC";

		SQLiteDatabase database = this.getWritableDatabase();

		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {

			do {

				HashMap<String, String> contactMap = new HashMap<String, String>();
				//The local HashMap is filled with key/value pairs
				contactMap.put("event_id", cursor.getString(0));
				contactMap.put("event_location", cursor.getString(1));
				contactMap.put("event_date", cursor.getString(2));
				contactMap.put("event_name", cursor.getString(6));
				contactMap.put("author_name", cursor.getString(7));
				contactMap.put("session_id", cursor.getString(4));
				contactMap.put("track", cursor.getString(10));
				
				try {
					
					//Refer to method GetSessionInfo to see how these four lines work
					SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss"); 
					Date timeDate = time.parse(cursor.getString(3));
					SimpleDateFormat timeOut = new SimpleDateFormat("hh:mm a");
					contactMap.put("event_time", timeOut.format(timeDate));
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//Adds a new HashMap to the ArrayList
				roomArrayList.add(contactMap);

			} while (cursor.moveToNext());

		}
		
		//Returns the ArrayList 
		return roomArrayList;
	}

	public ArrayList<HashMap<String, String>> getFilteredSessions(
			String eventDate, String filterBy, String type) {
		
		ArrayList<HashMap<String, String>> sessionArrayList = new ArrayList<HashMap<String, String>>();
		String selectAllQuery ="";
		
		if(type !=null){
			System.out.println(type);
			selectAllQuery = "Select * from session inner join event where " +
					"session.sevent_id=event.event_id and date ='" + eventDate + "' and " +
							"track = '" + type + "' ORDER BY time  ASC";
			
		}else{
			selectAllQuery = "Select * from session inner join event where " +
				"session.sevent_id=event.event_id and date ='" + eventDate + "'ORDER BY " + filterBy + " ASC";
		}
		SQLiteDatabase database = this.getWritableDatabase();
		
		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectAllQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				
				HashMap<String, String> sessionmap = new HashMap<String, String>();
				
				//The local HashMap is filled with key/value pairs
				sessionmap.put("event_id", cursor.getString(0));
				sessionmap.put("event_location", cursor.getString(1));
				sessionmap.put("event_date", cursor.getString(2));
				sessionmap.put("session_id", cursor.getString(4));
				sessionmap.put("sevent_id", cursor.getString(5));
				sessionmap.put("event_name", cursor.getString(6));
				sessionmap.put("author_name", cursor.getString(7));
				sessionmap.put("conf_year", cursor.getString(8));
				sessionmap.put("survey", cursor.getString(9));
				sessionmap.put("track", cursor.getString(10));
				sessionmap.put("event_description", cursor.getString(11));
				sessionmap.put("event_category", cursor.getString(12));
				
				try {
					
					//Refer to method GetSessionInfo to see how these four lines work
					SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss"); 
					Date timeDate = time.parse(cursor.getString(3));
					SimpleDateFormat timeOut = new SimpleDateFormat("hh:mm a");
					sessionmap.put("event_time", timeOut.format(timeDate));
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Adds a new HashMap to the ArrayList
				sessionArrayList.add(sessionmap);
			} while (cursor.moveToNext());
			
		}
		
		//Returns the ArrayList 
		return sessionArrayList;
	}
	public ArrayList<HashMap<String, String>> getFilteredFavorite(
			String eventDate, String filterBy, String type) {
		
		ArrayList<HashMap<String, String>> sessionArrayList = new ArrayList<HashMap<String, String>>();
		
		String selectAllQuery ="";
		
		if(type !=null){
			System.out.println(type);
			selectAllQuery = "Select * from session inner join favorite where " +
					"session.sevent_id=favorite.event_id and date ='" + eventDate + "' and " +
							"track = '" + type + "' ORDER BY time  ASC";
			
		}else{
			selectAllQuery = "Select * from session inner join favorite where " +
				"session.sevent_id=favorite.event_id and date ='" + eventDate + 
				"'ORDER BY " + filterBy + " ASC";
		}
		SQLiteDatabase database = this.getWritableDatabase();
		
		//The pointer for the database connection
		Cursor cursor = database.rawQuery(selectAllQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				
				HashMap<String, String> sessionmap = new HashMap<String, String>();
				//The local HashMap is filled with key/value pairs
				sessionmap.put("event_id", cursor.getString(0));
				sessionmap.put("event_location", cursor.getString(1));
				sessionmap.put("event_date", cursor.getString(2));
				sessionmap.put("event_name", cursor.getString(6));
				sessionmap.put("author_name", cursor.getString(9));
				sessionmap.put("track", cursor.getString(10));
				try {
					//Refer to method GetSessionInfo to see how these four lines work
					SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss"); 
					Date timeDate = time.parse(cursor.getString(3));
					SimpleDateFormat timeOut = new SimpleDateFormat("hh:mm a");
					sessionmap.put("event_time", timeOut.format(timeDate));
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Adds the local HashMap to the ArrayList
				sessionArrayList.add(sessionmap);
			} while (cursor.moveToNext());
			
		}
		
		//Returns the ArrayList 
		return sessionArrayList;
	}

	//The following method is unfinished, will be implemented by the time of presentation
	public void itsDeadJim() {
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "DROP TABLE IF EXISTS event";
		String query1 = "DROP TABLE IF EXISTS session";
		String query3 = "DROP TABLE IF EXISTS conference";
		Log.d("dbTools UPGRADE", "TABLE UPGRADED");
		db.execSQL(query);
		db.execSQL(query1);
		db.execSQL(query3);
		onCreate(db);
		db.close();
		
	}

	
	//The following method is unfinished, will be implemented by the time of presentation
	public String checkFavorite() {
		SQLiteDatabase database = this.getReadableDatabase();
		
		String favoriteQuery = "SELECT event_id FROM favorite";
		String eventQuery = "SELECT event_id FROM event";
		
		
		Cursor eventCursor = database.rawQuery(eventQuery, null);
		Cursor favoriteCursor = database.rawQuery(favoriteQuery, null);
		ArrayList<String> favIds = new ArrayList<String>();
		if(favoriteCursor.moveToFirst()){
			
			
			do{
				favIds.add(favoriteCursor.getString(0));
				
			}while(favoriteCursor.moveToNext());
		}	
		ArrayList<String> eventIds = new ArrayList<String>();
		if(eventCursor.moveToFirst()){
				
			do{
				eventIds.add(eventCursor.getString(0));
			}while(eventCursor.moveToNext());
		
		}
		for (int i=0; i<favIds.size(); i++) {
		    if (!eventIds.contains(favIds.get(i))) {
		    	database.delete("favorite", "event_id" + "=" + favIds.get(i), null); 
		    }
		}
		return null;
	}

	//This method will determine in an event is already stored on the favorite table and 
	//will return the corresponding boolean
	public boolean ifFavorite(String eventId) {
		
		SQLiteDatabase database = this.getReadableDatabase();
		
		String checkQuery = "SELECT * FROM favorite WHERE session_id = '"+ eventId +"';";
		
		//The pointer for the database connection
		Cursor checkCursor = database.rawQuery(checkQuery, null);
		
		//Determines if the event is present in the favorite table or not
		if(checkCursor.moveToFirst()){
			return true;
		}else{
			return false;
		}
	}

	public String getMapURL() {
		
		SQLiteDatabase database = this.getReadableDatabase();
		
		String mapQuery = "SELECT * FROM conference;";
		
		//The pointer for the database connection
		Cursor mapCursor = database.rawQuery(mapQuery, null);
		
		//Returns the map URL if it is stored in the database
		if(mapCursor.moveToFirst()){
			return mapCursor.getString(4);
		}
		return "notPresent";
	}
	
	

}
