package com.example.hangman;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Startskjerm extends Activity {
	
	private MediaPlayer startLyd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startskjerm);
		
		startLyd = MediaPlayer.create(Startskjerm.this, R.raw.startskjermlyd);
		
		Thread timer = new Thread()
		{
			public void run()
			{
				try
				{
					startLyd.start();
					sleep(3000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					
					startLyd.stop();
					startLyd.reset();
					startLyd.release();
		        	startLyd = null;
					Intent openStartskjerm = new Intent("com.example.hangman.STARTSKJERM");
					startActivity(openStartskjerm);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.startskjerm);
    }
	

}
