package com.example.celebrationapp;

import android.support.v4.app.Fragment;

public interface Listener {

	public void LoadNextFragment(Fragment nextFragment);
	void LoadNextFragmentWithBackstack(Fragment nextFragment);
	public void setVar(String var, String val);
	public String getVal(String var);
}
