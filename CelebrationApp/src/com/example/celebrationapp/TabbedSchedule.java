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

		String parent = listener.getVal("parent");
		ArrayList<HashMap<String,String>> list = null;
		
		if (parent.equals("1")){
			list = dbTools.getDays();
			//filter = generateFilterList\??;
		}
		else if (parent.equals("personal"))
			list = dbTools.getFavoriteDays();
		
		
		
		
		
		
		
		
		for (HashMap<String, String> date : list) {
			tab = a.newTab()
					.setText(date.get("public"))
					.setTabListener(
							new FragmentTabListener<Schedule>(getActivity(),
									"tab", Schedule.class, date.get("date"), parent));

			a.addTab(tab);
		}
	}

	
	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().getActionBar().removeAllTabs();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (Listener) activity;
	}
}
