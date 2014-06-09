package com.example.tipcalculator;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class CalculatorViewFactory implements ViewFactory {
	private Context context;
	
	public CalculatorViewFactory(Context c){
		this.context = c;
	}
	
	@Override
	public View makeView() {
		TextView myText = new TextView(this.context);
		myText.setGravity(Gravity.TOP);
		myText.setTextColor(Color.BLUE);
		return myText;
	}

}
