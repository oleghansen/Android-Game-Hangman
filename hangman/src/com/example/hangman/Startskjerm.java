package com.example.hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Startskjerm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startskjerm);
		
		Thread timer = new Thread()
		{
			public void run()
			{
				try
				{
					sleep(1000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
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
	

}
