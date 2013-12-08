package com.example.celebrationapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView{

	 public MyTextView(Context context, AttributeSet attrs, int defStyle) {
	        super(context, attrs, defStyle);
	        init();
	        rotate();
	    }

	    public MyTextView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        init();
	        rotate();
	    }

	    public MyTextView(Context context) {
	        super(context);
	        init();
	        rotate();
	    }

	    private void rotate() {
	        //Sets the textview as selected, this allows the text to rotate
	        setSelected(true);
	    }

	    private void init() {
	        if (!isInEditMode()) {

	        }
	    }


	  }