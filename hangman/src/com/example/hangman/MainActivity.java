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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private MediaPlayer minSang, knappeLyd;
	private AlertDialog.Builder dialogBuilder;
	private Button startButton, rulesButton, languageButton;
	private TextView highscoreFelt;
	private Locale myLocale;
	private ImageView imageHangman;
	private boolean byttetSprak;
	public static int nyHiscore, nyHiscoreHjelp, nyspillTeller, nyspillVunnetTeller, nyspillTaptTeller, nyAntallOrdTeller, nyOrdRiktig, nyOrdFeil;
	final static int LAGRERESULTAT = 69;
	public final static int AVSLUTTAPPRESULTAT = 99;
	private Animation fadeIn, tittelAnim;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       

            minSang = MediaPlayer.create(MainActivity.this, R.raw.soundtrack);
            minSang.start();
            
            imageHangman = (ImageView)findViewById(R.id.imageTitle);
            
            highscoreFelt = (TextView)findViewById(R.id.textHighscore);
            highscoreFelt.setOnClickListener(onClickListener);

	        startButton = (Button)findViewById(R.id.buttonStart);
	        startButton.setOnClickListener(onClickListener);
	        
	        rulesButton = (Button)findViewById(R.id.buttonRegler);
	        rulesButton.setOnClickListener(onClickListener);
	        
	        languageButton = (Button)findViewById(R.id.buttonSprak);
	        languageButton.setOnClickListener(onClickListener);
	        
	        loadPrefs();
	        highscoreFelt.setText("Highscore: " + (nyHiscoreHjelp));
	        
	        byttetSprak = false;
	 
	        
			// Animasjoner ----------------------------------
			
			fadeIn = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in);
			tittelAnim = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left);
			tittelAnim.setDuration(2000);
			fadeIn.setDuration(2000);
			
			imageHangman.setAnimation(tittelAnim);
			highscoreFelt.startAnimation(fadeIn);
			startButton.startAnimation(fadeIn);
			rulesButton.startAnimation(fadeIn);
			languageButton.startAnimation(fadeIn);
			
			//------------------------------------------------
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
							startActivityForResult(startGame, LAGRERESULTAT);
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
							startActivityForResult(startRules, 0);
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
							startActivityForResult(startLanguageScreen, 0);
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
		
		if(minSang!=null)
		{
			minSang.stop();
	    	minSang.reset();
	    	minSang.release();
	    	minSang = null;
		}

  		nyHiscore = Game.hiscore;
  	    
  		savePrefs("NYHIGHSCORE", nyHiscore);
  		savePrefs("ANTALLSPILL", nyspillTeller);
  		savePrefs("SPILLVUNNET", nyspillVunnetTeller);
  		savePrefs("SPILLTAPT", nyspillTaptTeller);
  		savePrefs("ANTALLORD", nyAntallOrdTeller);
  		savePrefs("ORDRIKTIG", nyOrdRiktig);
  		savePrefs("ORDFEIL", nyOrdFeil); 
  		
  		
  		finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
			if(resultCode == AVSLUTTAPPRESULTAT)
			{
				onDestroy();
			}
			
			else if(requestCode == LAGRERESULTAT)
			{
			  		nyspillTeller = Game.spillTeller;
			  		nyspillVunnetTeller = Game.spillVunnetTeller;
			  		nyspillTaptTeller = Game.spillTaptTeller;
			  		nyAntallOrdTeller = Game.globOrdTeller;
			  		nyOrdRiktig = Game.ordRiktigTeller; 
			  	    nyOrdFeil = Game.ordFeilTeller;
			  	    
			  		savePrefs("ANTALLSPILL", nyspillTeller);
			  		savePrefs("SPILLVUNNET", nyspillVunnetTeller);
			  		savePrefs("SPILLTAPT", nyspillTaptTeller);
			  		savePrefs("ANTALLORD", nyAntallOrdTeller);
			  		savePrefs("ORDRIKTIG", nyOrdRiktig);
			  		savePrefs("ORDFEIL", nyOrdFeil); 
			  		
			  		
					  	if(Game.hiscore > nyHiscore)
					  	{
					  		nyHiscore = Game.hiscore;
					  		savePrefs("NYHIGHSCORE", nyHiscore);
					  	}
					loadPrefs();
			        startButton.setText(getString(R.string.start_game));
			        rulesButton.setText(getString(R.string.regler));
			        languageButton.setText(getString(R.string.statistikk));
				}
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
			byttetSprak = true;
		break;
		case R.id.language_en:
		changeLang("en_EN");
		break;
		case R.id.exit:
			avsluttDialog();
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
        startButton.setText(getString(R.string.start_game));
        rulesButton.setText(getString(R.string.regler));
        languageButton.setText(getString(R.string.statistikk));
	}
	
	/*
	 *  This method makes handles the screen orientation. It makes sure all buttons are loaded and enabled. It also
	 *  calls loadPrefs so the highscore field can be set.  This method is neccessary because when the screen orientation
	 *  is changed, all the contents of the layout are redrawn and needs to be given properties in order to work. 
	 */ 
	@Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
        
        loadPrefs();
        imageHangman = (ImageView)findViewById(R.id.imageTitle);
        
        highscoreFelt = (TextView)findViewById(R.id.textHighscore);
        highscoreFelt.setOnClickListener(onClickListener);

        startButton = (Button)findViewById(R.id.buttonStart);
        startButton.setOnClickListener(onClickListener);
        
        rulesButton = (Button)findViewById(R.id.buttonRegler);
        rulesButton.setOnClickListener(onClickListener);
        
        languageButton = (Button)findViewById(R.id.buttonSprak);
        languageButton.setOnClickListener(onClickListener);
        
        highscoreFelt.setText("Highscore: " + (nyHiscoreHjelp));
        
        
        if(byttetSprak)
        {
        	changeLang("no_NO");
        }
    }
	
	@Override
	public void onBackPressed() 
	{
		avsluttDialog();
	}
	
	@Override
	public void onResume()
	    {  // After a pause OR at startup
	    super.onResume();	    
	     }
	
	public void onStart()
    {  
    super.onStart();
     }
	
	public void onPause()
    {  
    super.onPause();
     }

	private void avsluttDialog()
	{
		//Variabler
		dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setCancelable(false);
		
		//Process
		dialogBuilder.setTitle(getString(R.string.dialogAvsluttTittel));
		dialogBuilder.setMessage(getString(R.string.dialogAvsluttApp));
		
		dialogBuilder.setPositiveButton((getString(R.string.yes)), new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				Toast.makeText(getApplicationContext(), "Good bye!", Toast.LENGTH_SHORT).show();
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
		nyHiscoreHjelp = hsValue;
		highscoreFelt.setText("Highscore: " + (String.valueOf(hsValue)));
	}
}
