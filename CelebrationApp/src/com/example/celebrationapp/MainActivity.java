package com.example.celebrationapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements OnButtonClick, Listener{
	
	String parent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		Fragment fragment = new MainPage_Fragment();
		if (savedInstanceState != null) {
			fragment = getSupportFragmentManager().getFragment(
					savedInstanceState, null);
		}
		fragmentTransaction.add(R.id.main_page, fragment);
		fragmentTransaction.commit();
	}

	@Override
	public void LoadNextFragment(Fragment nextFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.main_page, nextFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void LoadNextFragmentWithBackstack(Fragment nextFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.main_page, nextFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}
	public void setVar(String var, String val) {
		if (var.equals("parent"))
			parent = val;
	}
	public String getVal(String var) {
		if (var.equals("parent"))
			return parent;
		return null;
	}

}
