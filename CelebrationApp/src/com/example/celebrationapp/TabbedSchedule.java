package com.example.celebrationapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class TabbedSchedule extends Fragment {

	Listener listener;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		DBTools dbTools = new DBTools(getActivity().getBaseContext());
		ActionBar a = getActivity().getActionBar();
		a.show();
		a.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		a.setDisplayShowTitleEnabled(true);

		Tab tab;
		ArrayList<HashMap<String, String>> days = dbTools.getDays();

		for (HashMap<String, String> date : days) {
			tab = a.newTab()
					.setText(date.get("public"))
					.setTabListener(
							new FragmentTabListener<Schedule>(getActivity(),
									"tab", Schedule.class, date.get("date")));

			a.addTab(tab);
		}
	}

	@Override
	public void onDestroy() {

		super.onDestroy();
		getActivity().getActionBar().removeAllTabs();
		getActivity().getActionBar().hide();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (Listener) activity;
	}
}
