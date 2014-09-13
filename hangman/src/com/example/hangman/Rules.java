package com.example.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Rules extends Activity {

	private Button tilbakeKnapp;
	private TextView txtShow;
	private ImageView regelLapp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rules);
		
		txtShow = (TextView) findViewById (R.id.textRulesView);
		regelLapp = (ImageView)findViewById (R.id.imageView1);
		tilbakeKnapp = (Button) findViewById(R.id.buttonTilbake);	
		tilbakeKnapp.setOnClickListener(onClickListener);
		
		Animation fadeIn = AnimationUtils.loadAnimation(Rules.this, android.R.anim.fade_in);
		fadeIn.setDuration(3000);
		txtShow.startAnimation(fadeIn);
		regelLapp.startAnimation(fadeIn);
		
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