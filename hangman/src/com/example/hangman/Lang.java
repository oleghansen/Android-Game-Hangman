package com.example.hangman;

import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



public class Lang extends Activity {

	
	private ImageButton langEn, langNo;
	private ImageView tittel;
	private Button tilbakeKnapp;
	private TextView txtVelg;
	private Locale myLocale;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.lang);
		
		langEn = (ImageButton)findViewById(R.id.enButton);
		langNo = (ImageButton)findViewById(R.id.noButton);
		txtVelg = (TextView)findViewById(R.id.langText);
		tittel = (ImageView)findViewById(R.id.imageView1);
		
		
		tilbakeKnapp = (Button)findViewById(R.id.buttonTilbake);
		
		langEn.setOnClickListener(onClickListener);
		langNo.setOnClickListener(onClickListener);
		tilbakeKnapp.setOnClickListener(onClickListener);
	}
	
	public void changeLang(String lang)
	{
		myLocale = new Locale(lang); 
		Locale.setDefault(myLocale);
		Configuration config = new Configuration();
		config.locale = myLocale;
		this.getApplicationContext().getResources().updateConfiguration(config, null);
		System.out.println("Bytter språk");
	}
	
	
	
	private OnClickListener onClickListener = new OnClickListener() {
		 @Override
		 public void onClick(View v) {
		  if(v.getId() == R.id.enButton)
		  {
			  changeLang("en_US");
			  txtVelg.setText(getString(R.string.velglang));
		      tilbakeKnapp.setText(getString(R.string.tilbake));
		      tittel.setImageResource(R.drawable.languages);
		      

		  }
		  else if(v.getId() == R.id.noButton)
		  {
			  changeLang("no_NO");
			  txtVelg.setText(getString(R.string.velglang));
		      tilbakeKnapp.setText(getString(R.string.tilbake));
		      tittel.setImageResource(R.drawable.languages_no);
		      
			  
		  }
		  else if(v.getId() == R.id.buttonTilbake)
		  {
			  finish();
		  }
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