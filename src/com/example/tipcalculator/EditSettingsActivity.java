package com.example.tipcalculator;

import java.util.ArrayList;

import com.example.tipcalculator.models.Settings;
import com.example.tipcalculator.models.SettingsEntry;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class EditSettingsActivity extends Activity {
	ListView lvUsers;
	SettingsArrayAdapter adapterSettings;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_settings);
		Settings settings = Settings.getInstance();
		ArrayList<SettingsEntry> sList = settings.generateSettingsList();
		
		//Initialize the adapter
		adapterSettings = new SettingsArrayAdapter(this, sList);
				
		//Connecting the adapter to the listview
		lvUsers = (ListView) findViewById(R.id.listView1);
		lvUsers.setAdapter(adapterSettings);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.edit_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    if(item.getItemId() == R.id.check) {
	        //return to main menu
	    	Settings s = Settings.getInstance();
	    	s.saveSettings();
	    }else if(item.getItemId() == R.id.cancel){
	    	//return to main menu
	    }
	    finish();
	    return true;
	}
	
}
