package com.example.hangman;

import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Spill extends Activity {
    
	MediaPlayer riktigLyd1, riktigLyd2, riktigLyd3, riktigLyd4, riktigLyd5, riktigLyd6, vinnLyd;
	StringBuilder uferdigOrd;
	String ferdigOrd;
	int feilGjettTeller, riktigGjettTeller;
	EditText tekst;
	Button A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Rb, S, T, U, V, W, X, Y, Z;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spill);
		
		A = (Button) findViewById(R.id.bA);
		B = (Button) findViewById(R.id.bB);
		C = (Button) findViewById(R.id.bC);
		D = (Button) findViewById(R.id.bD);
		E = (Button) findViewById(R.id.bE);
		F = (Button) findViewById(R.id.bF);
		G = (Button) findViewById(R.id.bG);
		H = (Button) findViewById(R.id.bH);
		I = (Button) findViewById(R.id.bI);
		J = (Button) findViewById(R.id.bJ);
		K = (Button) findViewById(R.id.bK);
		L = (Button) findViewById(R.id.bL);
		M = (Button) findViewById(R.id.bM);
		N = (Button) findViewById(R.id.bN);
		O = (Button) findViewById(R.id.bO);
		P = (Button) findViewById(R.id.bP);
		Q = (Button) findViewById(R.id.bQ);
		Rb = (Button) findViewById(R.id.bR);
		S = (Button) findViewById(R.id.bS);
		T = (Button) findViewById(R.id.bT);
		U = (Button) findViewById(R.id.bU);
		V = (Button) findViewById(R.id.bV);
		W = (Button) findViewById(R.id.bW);
		X = (Button) findViewById(R.id.bX);
		Y = (Button) findViewById(R.id.bY);
		Z = (Button) findViewById(R.id.bZ);
		
		A.setOnClickListener(onClickListener);
		B.setOnClickListener(onClickListener);
		C.setOnClickListener(onClickListener);
		D.setOnClickListener(onClickListener);
		E.setOnClickListener(onClickListener);
		F.setOnClickListener(onClickListener);
		G.setOnClickListener(onClickListener);
		H.setOnClickListener(onClickListener);
		I.setOnClickListener(onClickListener);
		J.setOnClickListener(onClickListener);
		K.setOnClickListener(onClickListener);
		L.setOnClickListener(onClickListener);
		M.setOnClickListener(onClickListener);
		N.setOnClickListener(onClickListener);
		O.setOnClickListener(onClickListener);
		P.setOnClickListener(onClickListener);
		Q.setOnClickListener(onClickListener);
		Rb.setOnClickListener(onClickListener);
		S.setOnClickListener(onClickListener);
		T.setOnClickListener(onClickListener);
		U.setOnClickListener(onClickListener);
		V.setOnClickListener(onClickListener);
		W.setOnClickListener(onClickListener);
		X.setOnClickListener(onClickListener);
		Y.setOnClickListener(onClickListener);
		Z.setOnClickListener(onClickListener);
		
		tekst = (EditText) findViewById(R.id.editText1);
		
		riktigLyd1 = MediaPlayer.create(this, R.raw.riktig1);
		riktigLyd2 = MediaPlayer.create(this, R.raw.riktig2);
		riktigLyd3 = MediaPlayer.create(this, R.raw.riktig3);
		riktigLyd4 = MediaPlayer.create(this, R.raw.riktig4);
		riktigLyd5 = MediaPlayer.create(this, R.raw.riktig5);
		riktigLyd6 = MediaPlayer.create(this, R.raw.riktig6);
		vinnLyd = MediaPlayer.create(this, R.raw.vinn);
		

		nyttSpill();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	public void nyttSpill()
	{
		
		feilGjettTeller = 0;
		riktigGjettTeller = 0;
		ferdigOrd = trekkOrd();
		uferdigOrd = new StringBuilder();
		
    /*	System.out.println("Ferdig Før loop: " + ferdigOrd);
		System.out.println("Uferdig Før loop: " + uferdigOrd);
		
		String input = ferdigOrd;
	    String hjelpeord = "";
		
		for (int i = 0; i < input.length(); i++)
		{
			if(i > 0)
			{
				hjelpeord += " ";
			}
			hjelpeord += (input.charAt(i));
			System.out.println("etter mellomrom: " + hjelpeord);
		} */
		
		uferdigOrd.append(ferdigOrd);
		
		for(int i = 0; i < uferdigOrd.length(); i++)
		{
			uferdigOrd.setCharAt(i, '_');
			//System.out.println("I loop: " + uferdigOrd);
		}
		tekst.setText(uferdigOrd);
		
	}
	
	public void gjett(char a, Button s)
	{
		System.out.println("Ferdig ord: " + ferdigOrd);
		System.out.println("Uferdig ord: " + uferdigOrd);
		boolean riktigGjett = false;
		s.setEnabled(false);
		
		for(int i = 0; i < uferdigOrd.length(); i++)
		{
			if (ferdigOrd.charAt(i) == a)
			{
				
				riktigGjett = true;
				riktigGjettTeller++;
				System.out.println("Bokstaven " + a + " finnes i ordet!");
				uferdigOrd.setCharAt(i, a);
				tekst.setText(uferdigOrd);
				s.setBackgroundResource(R.xml.rounded_green);
				
				if(uferdigOrd.equals(ferdigOrd))
				{
					spillFerdig();
				}
				
				if(riktigGjettTeller >= 1)
				{
					switch(riktigGjettTeller){
		            case 1:	
		            	riktigLyd1.start();
		            break;
		            case 2:	
		            	riktigLyd2.start();
		            break;
		            case 3:	
		            	riktigLyd3.start();
		            break;
		            case 4:	
		            	riktigLyd4.start();
		            break;
		            case 5:	
		            	riktigLyd5.start();
		            break;
		            case 6:	
		            	riktigLyd6.start();
		            break;
					}
				}
				else
					riktigLyd6.start();
			}
		}
		
		if(riktigGjett == false)
		{
			// Kode for å vise bokstav som var feil, samt tegne hangman
			// feilGjett();
			feilGjettTeller++;
			System.out.println(feilGjettTeller);
			s.setBackgroundResource(R.xml.rounded_red);
		}
		
		if(feilGjettTeller == 6)
		{
			// jalla balla
			
			System.out.println("Avslutter spill");
			finish();
		}
		
	}
	
	public String trekkOrd()
	{
		String[] ord = getResources().getStringArray(R.array.ordArray);
		String randomOrd = ord[new Random().nextInt(ord.length)];
		return randomOrd;
	}
	
	public void spillFerdig()
	{
		vinnLyd.start();
		Intent openStartskjerm = new Intent("com.example.hangman.STARTSKJERM");
		startActivity(openStartskjerm);
		finish();
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
		 @Override
	     public void onClick(View v) 
	     {
	         switch(v.getId()){
	             case R.id.bA:
	                  gjett('A', A);
	          
	             break;
	             case R.id.bB:
	            	 gjett('B', B);
	            
	             break;
	             case R.id.bC:
	            	 gjett('C', C);
	            
	             break;
	             case R.id.bD:
	                  gjett('D', D);
	        
	             break;
	             case R.id.bE:
	            	 gjett('E', E);
	            	
	             break;
	             case R.id.bF:
	            	 gjett('F', F);
	          
	             break;
	             case R.id.bG:
	                  gjett('G', G);
	           
	             break;
	             case R.id.bH:
	            	 gjett('H', H);
	        
	             break;
	             case R.id.bI:
	            	 gjett('I', I);
	            	
	             break;
	             case R.id.bJ:
	                  gjett('J', J);
	             break;
	             case R.id.bK:
	            	 gjett('K', K);
	             break;
	             case R.id.bL:
	            	 gjett('L', L);
	             break;
	             case R.id.bM:
	                  gjett('M', M);
	             break;
	             case R.id.bN:
	            	 gjett('N', N);
	             break;
	             case R.id.bO:
	            	 gjett('O', O);
	             break;
	             case R.id.bP:
	                  gjett('P', P);
	             break;
	             case R.id.bQ:
	            	 gjett('Q', Q);
	             break;
	             case R.id.bR:
	            	 gjett('R', Rb);
	             break;
	             case R.id.bS:
	                  gjett('S', S);
	             break;
	             case R.id.bT:
	            	 gjett('T', T);
	             break;
	             case R.id.bU:
	            	 gjett('U', U);
	             break;
	             case R.id.bV:
	                  gjett('V', V);
	             break;
	             case R.id.bW:
	                  gjett('W', W);
	             break;
	             case R.id.bX:
	            	 gjett('X', X);
	             break;
	             case R.id.bY:
	            	 gjett('Y', Y);            	 
	             break;
	             case R.id.bZ:
	            	 gjett('Z', Z);
	             break;
	         }

	   }
	};
	
	
	

}
