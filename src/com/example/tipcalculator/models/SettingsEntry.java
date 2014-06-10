package com.example.tipcalculator.models;

public class SettingsEntry {
	private String settingsKey;
	private String settingsEntry;
	
	public SettingsEntry(String key, String entry){
		this.settingsKey = key;
		this.settingsEntry = entry;
	}
	
	public String getSettingsEntry() {
		return settingsEntry;
	}
	public void setSettingsEntry(String settingsEntry) {
		this.settingsEntry = settingsEntry;
	}

	public String getSettingsKey() {
		return settingsKey;
	}

	public void setSettingsKey(String settingsKey) {
		this.settingsKey = settingsKey;
	}
	
	
}
