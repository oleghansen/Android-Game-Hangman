package com.example.hangman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Rules extends Activity {

	private TextView txtShow;
	private ImageView regelLapp;
	private AlertDialog.Builder dialogBuilder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rules);
		
		txtShow = (TextView) findViewById (R.id.textRulesView);
		regelLapp = (ImageView)findViewById (R.id.imageView1);
		
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
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.rules);
        
		txtShow = (TextView) findViewById (R.id.textRulesView);
		regelLapp = (ImageView)findViewById (R.id.imageView1);
    }
	
	@Override
	public void onBackPressed() 
	{
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.exitmain, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		if(item.getItemId() == R.id.exit)
		{
			avsluttAppDialog();
		}
		else if(item.getItemId() == R.id.home)
		{
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void avsluttAppDialog()
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

				onDestroy();
			}
		});
		
		dialogBuilder.setNegativeButton((getString(R.string.no)), null).show();
		
		//Output
		AlertDialog dialogAvsluttApp = dialogBuilder.create();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
		
	}
	

}