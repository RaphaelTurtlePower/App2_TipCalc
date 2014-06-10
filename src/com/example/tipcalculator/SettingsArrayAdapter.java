package com.example.tipcalculator;

import java.util.ArrayList;

import com.example.tipcalculator.models.Settings;
import com.example.tipcalculator.models.SettingsEntry;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsArrayAdapter extends ArrayAdapter<SettingsEntry> {
    SettingsEntry settingsEntry;
	public SettingsArrayAdapter(Context context, ArrayList<SettingsEntry> settings) {
       super(context, R.layout.settings_item, settings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       settingsEntry = getItem(position);    
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.settings_item, parent, false);
          // Lookup view for data population
          TextView key = (TextView) convertView.findViewById(R.id.settingsKey);
          EditText entry = (EditText) convertView.findViewById(R.id.settingsEntry);
          // Populate the data into the template view using the data object
          key.setText(settingsEntry.getSettingsKey());
          entry.setText(settingsEntry.getSettingsEntry());
          
          // Modify the entry after text is changed
          entry.addTextChangedListener(new SettingsTextWatcher(convertView));   
       }

       //Return completed view to render on screen
       return convertView;
   }
}
