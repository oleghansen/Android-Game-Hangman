package com.example.hangman;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private MediaPlayer minSang, knappeLyd;
	private AlertDialog.Builder dialogBuilder;
	private Button startButton, rulesButton, languageButton, quitButton;
	private TextView highscoreFelt;
	private Locale myLocale;
	public static int nyHiscore;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            minSang = MediaPlayer.create(MainActivity.this, R.raw.soundtrack);
            minSang.start();
            
            highscoreFelt = (TextView)findViewById(R.id.textHighscore);
            highscoreFelt.setOnClickListener(onClickListener);

	        startButton = (Button)findViewById(R.id.buttonStart);
	        startButton.setOnClickListener(onClickListener);
	        
	        rulesButton = (Button)findViewById(R.id.buttonRegler);
	        rulesButton.setOnClickListener(onClickListener);
	        
	        languageButton = (Button)findViewById(R.id.buttonSprak);
	        languageButton.setOnClickListener(onClickListener);
	        
	        quitButton = (Button)findViewById(R.id.buttonTilbake);
	        quitButton.setOnClickListener(onClickListener);
    }
    
    private void restartMediaPlayer()
    {
		minSang.stop();
    	minSang.reset();
    	minSang.release();
    	minSang = null;
        minSang = MediaPlayer.create(MainActivity.this, R.raw.soundtrack);
        minSang.start();
    }
    
	        private OnClickListener onClickListener = new OnClickListener() {
	      		 @Override
	      	     public void onClick(View v) 
	      	     {
	      	         switch(v.getId()){
	      	             case R.id.buttonStart:
	      					knappeLyd = MediaPlayer.create(MainActivity.this, R.raw.knapplyd);
	      					knappeLyd.start();
	      					
							if(minSang!=null)
							{
								restartMediaPlayer();
							}
							else
							{
					            minSang = MediaPlayer.create(MainActivity.this, R.raw.soundtrack);
					            minSang.start();
							}
							Intent startGame = new Intent("com.example.hangman.SPILL");
							startActivity(startGame);
	      	             break;
	      	             case R.id.buttonRegler:
	      					knappeLyd = MediaPlayer.create(MainActivity.this, R.raw.knapplyd);
	      					knappeLyd.start();
	      					
							if(minSang!=null)
							{
								restartMediaPlayer();
							}
							else
							{
					            minSang = MediaPlayer.create(MainActivity.this, R.raw.soundtrack);
					            minSang.start();
							}
							Intent startRules = new Intent("com.example.hangman.RULES");
							startActivity(startRules);
	      	             break;
	      	             case R.id.buttonSprak:
	      					knappeLyd = MediaPlayer.create(MainActivity.this, R.raw.knapplyd);
	      					knappeLyd.start();
	      					
							if(minSang!=null)
							{
								restartMediaPlayer();
							}
							else
							{
					            minSang = MediaPlayer.create(MainActivity.this, R.raw.soundtrack);
					            minSang.start();
							}
							Intent startLanguageScreen = new Intent("com.example.hangman.LANG");
							startActivity(startLanguageScreen);
	      	             break;
	      	             case R.id.buttonTilbake:
	        				onDestroy();
	      	             break;
	      	             case R.id.textHighscore:
	      	            	 resetDialog();
	      	             break;
	      	         }
	      	     }
	        };
	      		 
	@Override
	protected void onDestroy() {
		super.onDestroy();
  		nyHiscore = Spill.hiscore;
  		savePrefs("NYHIGHSCORE", nyHiscore);
  		savePrefs("ANTALLSPILL", Spill.spillTeller);
  		savePrefs("SPILLVUNNET", Spill.spillVunnetTeller);
  		savePrefs("SPILLTAPT", Spill.spillTaptTeller);
  		savePrefs("ANTALLORD", Spill.globOrdTeller);
  		savePrefs("ORDRIKTIG", Spill.ordRiktigTeller);
  		savePrefs("ORDFEIL", Spill.ordFeilTeller); 
  		
		android.os.Process.killProcess(android.os.Process.myPid());
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId())
		{
		case R.id.language_no:
			changeLang("no_NO");
		break;
		case R.id.language_en:
		changeLang("en_EN");
		break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void changeLang(String lang)
	{
		myLocale = new Locale(lang); 
		Locale.setDefault(myLocale);
		Configuration config = new Configuration();
		config.locale = myLocale;
		this.getApplicationContext().getResources().updateConfiguration(config, null);
		System.out.println("Bytter språk");
        startButton.setText(getString(R.string.start_game));
        rulesButton.setText(getString(R.string.regler));
        languageButton.setText(getString(R.string.statistikk));
        quitButton.setText(getString(R.string.avslutt));
	}
	
	@Override
	public void onBackPressed() 
	{
		avsluttDialog();
	}
	
	@Override
	public void onResume()
	    {  // After a pause OR at startup
		Log.d("MAINACTIVITY", "Er i onResume");
	    super.onResume();	    
	    
  		savePrefs("ANTALLSPILL", Spill.spillTeller);
  	    savePrefs("SPILLVUNNET", Spill.spillVunnetTeller);
  		savePrefs("SPILLTAPT", Spill.spillTaptTeller);
  		savePrefs("ANTALLORD", Spill.globOrdTeller);
  		savePrefs("ORDRIKTIG", Spill.ordRiktigTeller);
  		savePrefs("ORDFEIL", Spill.ordFeilTeller);
  		
		  	if(Spill.hiscore > nyHiscore)
		  	{
		  		nyHiscore = Spill.hiscore;
		  		savePrefs("NYHIGHSCORE", nyHiscore);
		  		
		  		System.out.println("HER SKAL DET LAGRES");
		  		
		  		// Lagrer ny hiscore
		  	}
		loadPrefs();
        startButton.setText(getString(R.string.start_game));
        rulesButton.setText(getString(R.string.regler));
        languageButton.setText(getString(R.string.statistikk));
        quitButton.setText(getString(R.string.avslutt));
	     }
	
	public void onStart()
    {  
    super.onStart();
    Log.d("MAINACTIVITY", "Er i onStart");
	loadPrefs();
     }
	
	public void onPause()
    {  
    super.onPause();
    Log.d("MAINACTIVITY", "Er i onPause");
     }

	private void avsluttDialog()
	{
		//Variabler
		dialogBuilder = new AlertDialog.Builder(this);
		
		//Process
		dialogBuilder.setTitle(getString(R.string.dialogAvsluttTittel));
		dialogBuilder.setMessage(getString(R.string.dialogAvsluttApp));
		
		dialogBuilder.setPositiveButton((getString(R.string.yes)), new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
				onDestroy();
			}
		});
		
		dialogBuilder.setNegativeButton((getString(R.string.no)), null).show();
	}
	
	private void resetDialog()
	{
		//Variabler
		dialogBuilder = new AlertDialog.Builder(this);
		
		//Process
		dialogBuilder.setTitle(getString(R.string.tilbakestill));
		dialogBuilder.setMessage(getString(R.string.tilbakestillDialogTekst));
		
		dialogBuilder.setPositiveButton((getString(R.string.yes)), new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
				tilbakestillStatistikk();
			}
		});
		
		dialogBuilder.setNegativeButton((getString(R.string.no)), null).show();
	}
	
	public void savePrefs(String key, int value)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.commit();
	}
	
	private void tilbakestillStatistikk()
	{
		savePrefs("NYHIGHSCORE", 0);
		savePrefs("ANTALLSPILL", 0);
		savePrefs("SPILLVUNNET", 0);
		savePrefs("SPILLTAPT", 0);
		savePrefs("ANTALLORD", 0);
		savePrefs("ORDRIKTIG", 0);
		savePrefs("ORDFEIL", 0);
		loadPrefs();
	}
	
	private void loadPrefs()
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		int hsValue = sp.getInt("NYHIGHSCORE", 0);
		highscoreFelt.setText("Highscore: " + (String.valueOf(hsValue)));
	}
}
