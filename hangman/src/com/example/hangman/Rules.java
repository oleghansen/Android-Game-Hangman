package com.example.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class Rules extends Activity {

	Button tilbakeKnapp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rules);
		
		final TextView txtShow = (TextView) findViewById (R.id.textRulesView);
		tilbakeKnapp = (Button) findViewById(R.id.buttonTilbake);
		
		tilbakeKnapp.setOnClickListener(onClickListener);
		
		txtShow.startAnimation(AnimationUtils.loadAnimation(Rules.this, android.R.anim.slide_in_left));
		
	}
	
	
	
	private OnClickListener onClickListener = new OnClickListener() {
		 public void onClick(View v) {
			  finish();
		 }
	};

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	@Override
	public void onBackPressed() 
	{
		finish();
	}
	

}