package com.example.hangman;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	MediaPlayer minSang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minSang = MediaPlayer.create(MainActivity.this, R.raw.aidsmachine);
        minSang.start();
        
        final Button startKnapp = (Button)findViewById(R.id.buttonStart);
        
        startKnapp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Thread timer = new Thread()
				{
					public void run()
					{
						try
						{
							sleep(10);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
