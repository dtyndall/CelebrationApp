package com.example.celebrationapp;

import android.support.v4.app.Fragment;

public interface OnButtonClick {

	public void LoadNextFragment(Fragment nextFragment);

	void LoadNextFragmentWithBackstack(Fragment nextFragment);
}
