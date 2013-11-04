package com.example.celebrationapp;


import java.util.HashMap;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
/** This is a listfragment class */
public class AppleFragment extends ListFragment {
 
    /** An array of items to display in ArrayList */
    String apple_versions[] = new String[]{
        "Mountain Lion",
        "Lion",
        "Snow Leopard",
        "Leopard",
        "Tiger",
        "Panther",
        "Jaguar",
        "Puma"
    };
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Creating array adapter to set data in listview */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
        		android.R.layout.simple_list_item_1, apple_versions);
 
        /** Setting the array adapter to the listview */
        setListAdapter(adapter);
 
        return super.onCreateView(inflater, container, savedInstanceState);
    }
 
    @Override
    public void onStart() {
        super.onStart();

        /** Setting the multiselect choice mode for the listview */
        getListView().setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					 System.out.println();
					
				}
        });
    }
 
}
