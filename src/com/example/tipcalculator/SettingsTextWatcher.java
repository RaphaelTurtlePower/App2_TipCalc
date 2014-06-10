package com.example.tipcalculator;

import com.example.tipcalculator.models.Settings;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsTextWatcher implements TextWatcher {
	private View view;
	
	public SettingsTextWatcher(View v){
		this.view = v;
	}
	
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		//DO NOTHING
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		//DO NOTHING
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		EditText et = (EditText) view.findViewById(R.id.settingsEntry);
		TextView tv = (TextView) view.findViewById(R.id.settingsKey);
		Settings st = Settings.getInstance();
		System.out.println("AFter Text Changed called.");
		System.out.println(tv.getText().toString()+ ":" + et.getText().toString());
		st.setProperty(tv.getText().toString(), et.getText().toString());
	}

}
