package com.example.tipcalculator;

import java.text.DecimalFormat;
import java.util.Formatter;

import com.example.tipcalculator.models.Calculation;
import com.example.tipcalculator.models.Settings;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextSwitcher;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	TextSwitcher tipAmount;
	TextSwitcher totalAmount;
	TextSwitcher individualAmount;
	TextSwitcher individualTipAmount;
	TextSwitcher percentage;
	TextView splitCount;
	String fileString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//on load, initialize Settings Object
		Settings.SETTINGS_FILE = getFilesDir() + "settings.txt";	
		Settings s = Settings.getInstance();
		
		//initialize Calculation Object
		Calculation calc = Calculation.getInstance();
		Typeface face = Typeface.createFromAsset(getAssets(), "Squares.otf");
		
		//initialize View elements
		splitCount = (TextView) findViewById(R.id.splitCount);
		splitCount.setTypeface(face);
		splitCount.setText(calc.getSplitNumber().toString());
		RelativeLayout inputView = (RelativeLayout) findViewById(R.id.InputView);
		RelativeLayout detailedView = (RelativeLayout) findViewById(R.id.DetailedView);
		
		initializeLabels(inputView);
		initializeLabels(detailedView);
		initializeDetailedView();
		
		//Prepopulate Detailed View
		updateDetailedView(calc);
		 
		//add the button listeners
		EditText transactionAmount = (EditText) findViewById(R.id.transactionAmount);
		transactionAmount.setText(calc.getTransactionAmount().toString());
		Button addSplit = (Button)  findViewById(R.id.addSplit);
		Button removeSplit = (Button) findViewById(R.id.removeSplit);
		SeekBar percentageBar = (SeekBar) findViewById(R.id.percentageBar);
		percentageBar.setProgress(calc.getPercentage());
		
		addSplit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Calculation calc = Calculation.getInstance();
				calc.incSplitNumber();
				updateDetailedView(calc);
			}
		});
		
		removeSplit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Calculation calc = Calculation.getInstance();
				calc.decSplitNumber();
				updateDetailedView(calc);
			}
			
		});
		transactionAmount.setOnKeyListener(new EditText.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				EditText edt = (EditText) findViewById(R.id.transactionAmount);
				String text = edt.getText().toString();
				Double amount = Double.parseDouble(text);
				Calculation calc = Calculation.getInstance();
				calc.setTransactionAmount(amount);
				updateDetailedView(calc);
				return false;
			}
	    });
		
		percentageBar.setMax(100); //max percentage
		percentageBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub		
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				Calculation calc = Calculation.getInstance();
				calc.setPercentage(progress);
				updateDetailedView(calc);
				System.out.println("Progress:" + progress);
			}
		});
	
	}
	
	public void initializeDetailedView(){
		Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
	    Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);
	    
		//Initialize TextSwitcher
		tipAmount = (TextSwitcher) findViewById(R.id.tipAmount);
		totalAmount = (TextSwitcher) findViewById(R.id.totalAmount);
		individualAmount = (TextSwitcher) findViewById(R.id.individualAmount);		
		percentage = (TextSwitcher) findViewById(R.id.percent);
		individualTipAmount = (TextSwitcher) findViewById(R.id.individualTipAmount);
				
		//Specify the View Factory
		tipAmount.setFactory(new CalculatorViewFactory(MainActivity.this));
		totalAmount.setFactory(new CalculatorViewFactory(MainActivity.this));
		individualAmount.setFactory(new CalculatorViewFactory(MainActivity.this));
		percentage.setFactory(new CalculatorViewFactory(MainActivity.this));
		individualTipAmount.setFactory(new CalculatorViewFactory(MainActivity.this));
				
		//set animation
		tipAmount.setInAnimation(in);
		totalAmount.setInAnimation(in);
		individualAmount.setInAnimation(in);
		percentage.setInAnimation(in);
		individualTipAmount.setInAnimation(in);

		tipAmount.setOutAnimation(out);
		totalAmount.setOutAnimation(out);
		individualAmount.setOutAnimation(out);
		percentage.setOutAnimation(out);
		individualTipAmount.setOutAnimation(out);
	}
	
	public void initializeLabels(RelativeLayout layout){
		Typeface square = Typeface.createFromAsset(getAssets(), "Squares.otf");
		for(int i=0; i<layout.getChildCount(); i++){
			View v = layout.getChildAt(i);
			if(v instanceof TextView){
				((TextView) v).setTypeface(square);
			}
		}
	}
	
	public void updateDetailedView(Calculation calc){
		Formatter fm = new Formatter();
	
		tipAmount.setText('$' + formatForOutput(calc.getTipAmount()));
		totalAmount.setText('$' + formatForOutput(calc.getTotal()));
		individualAmount.setText('$'+formatForOutput(calc.getIndividualTotal()));
		percentage.setText(calc.getPercentage().toString() + '%');
		splitCount.setText(calc.getSplitNumber().toString() + " person(s)");
		individualTipAmount.setText('$' + formatForOutput(calc.getIndividualTipAmount()));
	}
	
	
	
	public String formatForOutput(Double data){
		return new DecimalFormat("#.00").format(data);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    if(item.getItemId() == R.id.gear) {
	          //handle setup activity here  openSettingsActivity();
	    	editSettingsActivity();
	    }
	    return true;
	}
	
	  public void editSettingsActivity(){
	    	Intent editIntent = new Intent();
	    	editIntent.setClass(this, EditSettingsActivity.class);
	    	startActivityForResult(editIntent, 5);
	  }
	  
	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data ){
	    	if(requestCode == 5){
	    		System.out.println("Successful return of activity.");
	    	}
	    }
}
