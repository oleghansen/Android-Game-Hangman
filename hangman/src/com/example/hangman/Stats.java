package com.example.hangman;


import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;



public class Stats extends Activity {

	
	private TextView tittel, antSpillTxt, antOrdTxt, antSpillVunnetTxt, antSpillTaptTxt, antOrdRiktigTxt, antOrdFeilTxt;
	private Button tilbakeKnapp;
	private Animation fadeIn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats);
		
		tittel = (TextView)findViewById(R.id.textTittel);
		antSpillTxt = (TextView)findViewById(R.id.inAntallSpill);
		antOrdTxt = (TextView)findViewById(R.id.inAntallOrd);
		antSpillVunnetTxt = (TextView)findViewById(R.id.inAntallSpillVunnet);
		antSpillTaptTxt = (TextView)findViewById(R.id.inAntallSpillTapt);
		antOrdRiktigTxt = (TextView)findViewById(R.id.inAntallOrdRiktig);
		antOrdFeilTxt = (TextView)findViewById(R.id.inAntallOrdFeil);
		tilbakeKnapp = (Button)findViewById(R.id.buttonTilbake);
		
		
		// Animasjoner ----------------------------------
		
		fadeIn = AnimationUtils.loadAnimation(Stats.this, android.R.anim.fade_in);
		fadeIn.setDuration(2000);
		
		tittel.startAnimation(fadeIn);
		antSpillTxt.startAnimation(fadeIn);
		antSpillVunnetTxt.startAnimation(fadeIn);
		antSpillTaptTxt.startAnimation(fadeIn);
		antOrdTxt.startAnimation(fadeIn);
		antOrdRiktigTxt.startAnimation(fadeIn);
		antOrdFeilTxt.startAnimation(fadeIn);
		
		//------------------------------------------------
		
		
		tilbakeKnapp.setOnClickListener(onClickListener);
		loadPrefs();
	}
	
	
	private void loadPrefs()
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		int spillTeller = sp.getInt("ANTALLSPILL", 0);
		int ordTeller = sp.getInt("ANTALLORD", 0);
		int spillVunnet = sp.getInt("SPILLVUNNET", 0);
		int spillTapt = sp.getInt("SPILLTAPT", 0);
		int ordRiktig = sp.getInt("ORDRIKTIG", 0);
		int ordFeil = sp.getInt("ORDFEIL", 0);
		
		antSpillTxt.setText(getString(R.string.antallspill) + ": " + String.valueOf(spillTeller));
		antOrdTxt.setText(getString(R.string.antallord) + ": " + String.valueOf(ordTeller));
		antSpillVunnetTxt.setText(getString(R.string.vunnet) + ": " + String.valueOf(spillVunnet) + " (" + lagProsent(spillTeller, spillVunnet) + "%)");
		antSpillTaptTxt.setText(getString(R.string.tapt) + ": " + String.valueOf(spillTapt) + " (" + lagProsent(spillTeller, spillTapt) + "%)");
		antOrdRiktigTxt.setText(getString(R.string.riktig) + ": " + String.valueOf(ordRiktig) + " (" + lagProsent(ordTeller, ordRiktig) + "%)");
		antOrdFeilTxt.setText(getString(R.string.feil) + ": " + String.valueOf(ordFeil) + " (" + lagProsent(ordTeller, ordFeil) + "%)");
	}
	
	private double lagProsent(int total, int a)
	{
		if(total == 0)
		{
			return 0;
		}
		else
		{
			double j = (((double)a / (double)total) * 100);	
			System.out.println(j + "%");
			j = Math.round(j * 10) / 10; // Rounding of so we get one decimals in the
			return j;
		}
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
		 @Override
		 public void onClick(View v) {
			 
		  if(v.getId() == R.id.buttonTilbake)
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