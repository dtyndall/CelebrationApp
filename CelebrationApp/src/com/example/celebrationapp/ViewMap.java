package com.example.celebrationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ViewMap extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_map);
		Intent theIntent = getIntent();
		Bundle extras = theIntent.getExtras();
		
		String url = extras.getString("url");
		 int loader = R.drawable.ic_launcher;
         
	        // Imageview to show
	        TouchImageView image = (TouchImageView) findViewById(R.id.image);
	         
	        // Image url
	        String image_url = url;
	        
	        // ImageLoader class instance
	        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
	         
	        // whenever you want to load an image from url
	        // call DisplayImage function
	        // url - image url to load
	        // loader - loader image, will be displayed before getting image
	        // image - ImageView 
	        imgLoader.DisplayImage(image_url, loader, image);
	
	}

}
