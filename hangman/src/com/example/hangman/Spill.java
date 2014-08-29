package com.example.hangman;

import java.util.Random;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Spill extends Activity {

	String trektOrd;
	int feilGjett;
	EditText tekst;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spill);
		
		Button A = (Button) findViewById(R.id.bA);
		Button B = (Button) findViewById(R.id.bB);
		Button C = (Button) findViewById(R.id.bC);
		Button D = (Button) findViewById(R.id.bD);
		Button E = (Button) findViewById(R.id.bE);
		Button F = (Button) findViewById(R.id.bF);
		
		A.setOnClickListener(onClickListener);
		B.setOnClickListener(onClickListener);
		C.setOnClickListener(onClickListener);
		D.setOnClickListener(onClickListener);
		E.setOnClickListener(onClickListener);
		F.setOnClickListener(onClickListener);
		
		tekst= (EditText) findViewById(R.id.editText1);

		nyttSpill();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	public void nyttSpill()
	{
		feilGjett = 0;
		trektOrd = trekkOrd();
		
		for(int i = 0; i < trektOrd.length(); i++)
		{
			tekst.append("_   ");
		}
	}
	
	public void gjett(char a)
	{
		
		boolean riktigGjett = false;
		for(int i = 0; i < trektOrd.length(); i++)
		{
			if (trektOrd.charAt(i) == a)
			{
				trektOrd.replace("_", Character.toString(a));
				riktigGjett = true;
			}
		}
		
		if(riktigGjett == false)
		{
			// Kode for å vise bokstav som var feil, samt tegne hangman
			// feilGjett();
			feilGjett++;
			System.out.println(feilGjett);
		}
		
		if(feilGjett == 6)
		{
			// jalla balla
			// Avslutt spillet
			System.out.println("Avslutter spill");
		}
	}
	
	public String trekkOrd()
	{
		String[] ord = getResources().getStringArray(R.array.ordArray);
		String randomOrd = ord[new Random().nextInt(ord.length)];
		return randomOrd;
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
		 @Override
	     public void onClick(View v) 
	     {
	         switch(v.getId()){
	             case R.id.bA:
	                  gjett('A');
	             break;
	             case R.id.bB:
	            	 gjett('B');
	             break;
	             case R.id.bC:
	            	 gjett('C');
	             break;
	             case R.id.bD:
	                  gjett('D');
	             break;
	             case R.id.bE:
	            	 gjett('E');
	             break;
	             case R.id.bF:
	            	 gjett('F');
	             break;
	         }

	   }
	};
	
	
	

}
