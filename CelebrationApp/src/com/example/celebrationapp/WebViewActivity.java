package com.example.celebrationapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {

	private WebView webView;

	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		//Gets the URL from the extras passed into this class
		final String eventURL = extras.getString("survey");
		
		//Creates reference to WebView on screen
		webView = (WebView) findViewById(R.id.webView1);
		
		webView.getSettings().setJavaScriptEnabled(true);
		
		//Loads URL gotten earlier onto the webview. This makes the surveys that the 
		//users think the survey is taken from inside the application 
		webView.loadUrl("http://www.starscomputingcorps.org/survey?sid=" + eventURL);

	}

}