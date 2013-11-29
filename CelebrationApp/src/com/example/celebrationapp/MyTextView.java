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
	        // TODO Auto-generated method stub
	        setSelected(true);
	    }

	    private void init() {
	        if (!isInEditMode()) {

	        }
	    }


	  }