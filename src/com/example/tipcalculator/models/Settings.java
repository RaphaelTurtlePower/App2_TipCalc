package com.example.tipcalculator.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Settings {
	public static final Integer DEFAULTPERCENTAGE  = 15;
	public static final Double DEFAULTTRANSACTION = 10.0;
	public static final Integer MAXSPLITCOUNT = 100;
	public static final Integer MINSPLITCOUNT = 1;
	
	public static String SETTINGS_FILE;
	private HashMap<String, String> props;
	
	public static Settings instance;
	
	public static Settings getInstance(){
		if(instance == null){
			instance = new Settings();
		}
		return instance;
	}
	
	public HashMap<String, String> getProperties() {
		return props;
	}

	public void setProperty(String key, String value) {
		System.out.println("Set Property called with Key:" + key + " and Value: " + value);
		this.props.put(key, value);
	}

	private Settings(){
		this.props = new HashMap<String, String>();
		initializeSettingsFile();
	}

	public void initializeSettingsFile(){
		File f = new File(SETTINGS_FILE);
		if(!f.exists()){
			this.props.put("Default Percentage", DEFAULTPERCENTAGE.toString());
			this.props.put("Default Transaction", DEFAULTTRANSACTION.toString());
			this.props.put("Maximum Split Count", MAXSPLITCOUNT.toString());
			this.props.put("Minimum Split Count", MINSPLITCOUNT.toString());
			saveSettings();
		}else{
			readSettings();
		}
	}
	
	public Integer getDefaultPercentage(){
		return Integer.parseInt(this.props.get("Default Percentage"));
	}
	
	public Double getDefaultTransaction(){
		return Double.parseDouble(this.props.get("Default Transaction"));
	}
	
	public Integer getMaxSplitCount(){
		return Integer.parseInt(this.props.get("Maximum Split Count"));
	}
	
	public Integer getMinSplitCount(){
		return Integer.parseInt(this.props.get("Minimum Split Count"));
	}
	public void setDefaultPercentage(Integer percentage){
		this.props.put("Default Percentage", percentage.toString());
	}
	
	public ArrayList<SettingsEntry> generateSettingsList(){
		ArrayList<SettingsEntry> settingsList = new ArrayList<SettingsEntry>();
		for (Map.Entry<String, String> propEntry : this.props.entrySet()){
       		settingsList.add(new SettingsEntry(propEntry.getKey(), propEntry.getValue()));
       	}
		return settingsList;
	}
	
	private void readSettings() {
		 FileInputStream fstream;
	     try{
	       	 fstream = new FileInputStream(SETTINGS_FILE);
	       	 Scanner sc = new Scanner(new InputStreamReader(fstream));
	       	 while(sc.hasNext()){
	       		 String line = sc.nextLine();
	       		 String[] arr = line.split(":");
	       		 this.props.put(arr[0], arr[1]);
	       	}
	     }catch(IOException e){
	       	 e.printStackTrace();
	     }
	}
	       
	public void saveSettings() {
		try{
	        FileWriter fw = new FileWriter(SETTINGS_FILE);
	       	BufferedWriter bw = new BufferedWriter(fw);
	       	for (Map.Entry<String, String> propEntry : this.props.entrySet()){
	       		System.out.println(propEntry.getKey() + ":" + propEntry.getValue());
	       		bw.write(propEntry.getKey() + ":" + propEntry.getValue());
	       		bw.newLine();
	       	}
	       	bw.close();
	       		
	    }catch(IOException e){
	       		e.printStackTrace();
	    }
	}
	
	
	
}
