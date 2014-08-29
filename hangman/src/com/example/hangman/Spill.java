package com.example.hangman;

import java.util.Random;
import java.lang.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Spill extends Activity {
    
	StringBuilder uferdigOrd;
	String ferdigOrd;
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
		Button G = (Button) findViewById(R.id.bG);
		Button H = (Button) findViewById(R.id.bH);
		Button I = (Button) findViewById(R.id.bI);
		Button J = (Button) findViewById(R.id.bJ);
		Button K = (Button) findViewById(R.id.bK);
		Button L = (Button) findViewById(R.id.bL);
		Button M = (Button) findViewById(R.id.bM);
		Button N = (Button) findViewById(R.id.bN);
		Button O = (Button) findViewById(R.id.bO);
		Button P = (Button) findViewById(R.id.bP);
		Button Q = (Button) findViewById(R.id.bQ);
		Button R = (Button) findViewById(R.id.bR);
		Button S = (Button) findViewById(R.id.bS);
		Button T = (Button) findViewById(R.id.bT);
		Button U = (Button) findViewById(R.id.bU);
		Button V = (Button) findViewById(R.id.bV);
		Button W = (Button) findViewById(R.id.bW);
		Button X = (Button) findViewById(R.id.bX);
		Button Y = (Button) findViewById(R.id.bY);
		Button Z = (Button) findViewById(R.id.bZ);
		
		// Hei 
		
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
		R.setOnClickListener(onClickListener);
		S.setOnClickListener(onClickListener);
		T.setOnClickListener(onClickListener);
		U.setOnClickListener(onClickListener);
		V.setOnClickListener(onClickListener);
		W.setOnClickListener(onClickListener);
		X.setOnClickListener(onClickListener);
		Y.setOnClickListener(onClickListener);
		Z.setOnClickListener(onClickListener);
		
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
	
	public void gjett(char a)
	{
		System.out.println("Ferdig ord: " + ferdigOrd);
		System.out.println("Uferdig ord: " + uferdigOrd);
		boolean riktigGjett = false;
		for(int i = 0; i < uferdigOrd.length(); i++)
		{
			if (ferdigOrd.charAt(i) == a)
			{
				
				riktigGjett = true;
				System.out.println("Bokstaven " + a + " finnes i ordet!");
				uferdigOrd.setCharAt(i, a);
				tekst.setText(uferdigOrd);
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
	             case R.id.bG:
	                  gjett('G');
	             break;
	             case R.id.bH:
	            	 gjett('H');
	             break;
	             case R.id.bI:
	            	 gjett('I');
	             break;
	             case R.id.bJ:
	                  gjett('J');
	             break;
	             case R.id.bK:
	            	 gjett('K');
	             break;
	             case R.id.bL:
	            	 gjett('L');
	             break;
	             case R.id.bM:
	                  gjett('M');
	             break;
	             case R.id.bN:
	            	 gjett('N');
	             break;
	             case R.id.bO:
	            	 gjett('O');
	             break;
	             case R.id.bP:
	                  gjett('P');
	             break;
	             case R.id.bQ:
	            	 gjett('Q');
	             break;
	             case R.id.bR:
	            	 gjett('R');
	             break;
	             case R.id.bS:
	                  gjett('S');
	             break;
	             case R.id.bT:
	            	 gjett('T');
	             break;
	             case R.id.bU:
	            	 gjett('U');
	             break;
	             case R.id.bV:
	                  gjett('V');
	             break;
	             case R.id.bX:
	            	 gjett('X');
	             break;
	             case R.id.bY:
	            	 gjett('Y');
	             break;
	             case R.id.bZ:
	            	 gjett('Z');
	             break;
	         }

	   }
	};
	
	
	

}
