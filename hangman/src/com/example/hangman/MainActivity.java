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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private MediaPlayer minSang, knappeLyd;
	private AlertDialog.Builder dialogBuilder;
	private Button startButton, rulesButton, languageButton, quitButton;
	private ImageButton muteKnapp;
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

	        startButton = (Button)findViewById(R.id.buttonStart);
	        rulesButton = (Button)findViewById(R.id.buttonRegler);
	        languageButton = (Button)findViewById(R.id.buttonSprak);
	        quitButton = (Button)findViewById(R.id.buttonTilbake);
	        
	        
      // muteKnapp = (ImageButton)findViewById(R.id.muteButton);
        
        
     /*   muteKnapp.setOnClickListener(new View.OnClickListener() {
        	@Override
			public void onClick(View v) 
			{
        		knappeLyd = MediaPlayer.create(MainActivity.this, R.raw.knapplyd);
        		
        		if(!muteKnapp.isSelected())
        		{
        			minSang.setVolume(0, 0);
        			knappeLyd.setVolume(0, 0);
        			muteKnapp.setSelected(true);
        			muteKnapp.setBackgroundResource(R.drawable.mute_1);
        			System.out.println("Lyd av.");
        			knappeLyd.start();
        			
        		}
        		else
        		{
        			minSang.setVolume(1, 1);
        			knappeLyd.setVolume(1, 1);
        			muteKnapp.setSelected(false);
        			muteKnapp.setBackgroundResource(R.drawable.mute_0);
        			System.out.println("Lyd på.");
        			knappeLyd.start();
        		}
        		
        		knappeLyd.stop();
            	knappeLyd.reset();
            	knappeLyd.release();
            	knappeLyd=null;
			}
        }); */
        
        startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				knappeLyd = MediaPlayer.create(MainActivity.this, R.raw.knapplyd);
				knappeLyd.start();
				
				Thread timer = new Thread()
				{
					public void run()
					{
						try
						{
							System.out.println(minSang.toString());
							sleep(0);
							if(minSang!=null)
							{
								minSang.stop();
					        	minSang.reset();
					        	minSang.release();
					        	minSang = null;
					            minSang = MediaPlayer.create(MainActivity.this, R.raw.soundtrack);
					            minSang.start();
							}
							else
							{
					            minSang = MediaPlayer.create(MainActivity.this, R.raw.soundtrack);
					            minSang.start();
							}
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
						finally
						{
							
							Intent startGame = new Intent("com.example.hangman.SPILL");
							startActivity(startGame);
							
						}
					}
				};
				timer.start();
				
				
				
				
			}
		});
        
        rulesButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				knappeLyd = MediaPlayer.create(MainActivity.this, R.raw.knapplyd);
				knappeLyd.start();
				
				Thread timer = new Thread()
				{
					public void run()
					{
						try
						{
							sleep(0);
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
						finally
						{
							
							Intent startRules = new Intent("com.example.hangman.RULES");
							startActivity(startRules);
							
						}
					}
				};
				timer.start();
				
				
				
				
			}
		});
        
        quitButton.setOnClickListener(new View.OnClickListener() {
			
    			@Override
    			public void onClick(View v) 
    			{
    				knappeLyd = MediaPlayer.create(MainActivity.this, R.raw.knapplyd);
    				knappeLyd.start();
    				onDestroy();
    			}
    		});
        
        	
        languageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				knappeLyd = MediaPlayer.create(MainActivity.this, R.raw.knapplyd);
				knappeLyd.start();
				
				Thread timer = new Thread()
				{
					public void run()
					{
						try
						{
							sleep(0);
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
						finally
						{
							
							Intent startLanguageScreen = new Intent("com.example.hangman.LANG");
							startActivity(startLanguageScreen);
							
						}
					}
				};
				timer.start();
				
				
				
				
			}
		});
    }
	@Override
	protected void onDestroy() {
		super.onDestroy();
  		nyHiscore = Spill.hiscore;
  		savePrefs("NYHIGHSCORE", nyHiscore);
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
        languageButton.setText(getString(R.string.sprak));
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
        languageButton.setText(getString(R.string.sprak));
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
		
		//Output
		AlertDialog dialogAvslutt = dialogBuilder.create();
		dialogAvslutt.show();
	}
	private void savePrefs(String key, int value)
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.commit();
	}
	
	private void loadPrefs()
	{
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		int hsValue = sp.getInt("NYHIGHSCORE", 0);
		highscoreFelt.setText("Highscore: " + (String.valueOf(hsValue)));
	}
}
