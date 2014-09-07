package com.example.hangman;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends Activity {

	MediaPlayer minSang, knappeLyd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            minSang = MediaPlayer.create(MainActivity.this, R.raw.soundtrack);
            minSang.start();
        
        

    
        
        final Button startButton = (Button)findViewById(R.id.buttonStart);
        final Button rulesButton = (Button)findViewById(R.id.buttonRegler);
        final Button languageButton = (Button)findViewById(R.id.buttonSprak);
        final Button quitButton = (Button)findViewById(R.id.buttonTilbake);
        
        final ImageButton muteKnapp = (ImageButton)findViewById(R.id.muteButton);
        
        
        muteKnapp.setOnClickListener(new View.OnClickListener() {
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
        });
        
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
							sleep(0);
							if(minSang!=null)
							{
								minSang.stop();
					        	minSang.reset();
					        	minSang.release();
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
							if(minSang!=null)
							{
								minSang.stop();
					        	minSang.reset();
					        	minSang.release();
							}
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
			finish();
		super.onDestroy();
	}

}
