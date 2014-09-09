package com.example.hangman;

import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Spill extends Activity {
    
	private MediaPlayer mediaPlayer, knappeLyd;
	private StringBuilder uferdigOrd;
	private String ferdigOrd, bokstavString, tempMulti, randomOrd;
	private String[] ordTabell;
	private int feilGjettTeller, riktigGjettTeller;
	public static int poeng, hiscore, riktigeOrd, ordTeller;
	private TextView ordFelt, bokstavFelt, multiFelt, poengFelt;
	private Button A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Rb, S, T, U, V, W, X, Y, Z;
	//private ImageButton muteKnapp;
	private ImageView imageHangman;
	private AlertDialog.Builder dialogBuilder;
	boolean forrigeRett, fForrigeRett;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spill);
		
		mediaPlayer = MediaPlayer.create(this, R.raw.riktig1);
		imageHangman = (ImageView)findViewById(R.id.imageHangman);
		
		
	/*	muteKnapp = (ImageButton)findViewById(R.id.muteButton);
		muteKnapp.setOnClickListener(new View.OnClickListener() {
	    	@Override
			public void onClick(View v) 
			{
	    		knappeLyd = MediaPlayer.create(Spill.this, R.raw.knapplyd);
	    		knappeLyd.start();
	    		
	    		if(!muteKnapp.isSelected())
	    		{
	    			mediaPlayer.setVolume(0, 0);
	    			muteKnapp.setSelected(true);
	    			muteKnapp.setBackgroundResource(R.drawable.mute_1);
	    			System.out.println("Lyd av.");
	    			
	    		}
	    		else
	    		{
	    			mediaPlayer.setVolume(1, 1);
	    			muteKnapp.setSelected(false);
	    			muteKnapp.setBackgroundResource(R.drawable.mute_0);
	    			System.out.println("Lyd på.");
	    		}
	    		
	    		knappeLyd.stop();
	        	knappeLyd.reset();
	        	knappeLyd.release();
	        	knappeLyd=null;
			}
	    }); */
		
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
		
		ordFelt = (TextView) findViewById(R.id.textRulesView);
		//bokstavFelt = (TextView) findViewById(R.id.textView2);
		multiFelt = (TextView) findViewById(R.id.textMulti);
		poengFelt = (TextView) findViewById(R.id.textPoeng);
		
		ordTabell = new String[5];
		Arrays.fill(ordTabell, null);
		
		/* riktigLyd1.setOnCompletionListener(onCompletionListener); */
		

		
		
		poeng = 0;
		nyttSpill();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	
	
	@Override
	public void onBackPressed() 
	{
		avsluttDialog();
	}

	private void avsluttDialog()
	{
		//Variabler
		dialogBuilder = new AlertDialog.Builder(this);
		
		//Process
		dialogBuilder.setTitle(getString(R.string.dialogAvsluttTittel));
		dialogBuilder.setMessage(getString(R.string.dialogAvslutt));
		
		dialogBuilder.setPositiveButton((getString(R.string.yes)), new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		dialogBuilder.setNegativeButton((getString(R.string.no)), null).show();
		
		//Output
		AlertDialog dialogAvslutt = dialogBuilder.create();
	}
	
	public void nyttSpill()
	{
		forrigeRett = false;
		fForrigeRett = false;
		bokstavString = " ";
		feilGjettTeller = 0;
		riktigGjettTeller = 0;
		ferdigOrd = trekkOrd();
		uferdigOrd = new StringBuilder();	
		uferdigOrd.append(ferdigOrd);
		
		for(int i = 0; i < uferdigOrd.length(); i++)
		{
			uferdigOrd.setCharAt(i, '_');
			i++;
		}
		System.out.println("Uferdig: " + uferdigOrd);
		System.out.println("Ferdig: " + ferdigOrd);
		ordFelt.setText(uferdigOrd);
		
	}
	
	public int getHiscore()
	{
		return hiscore;
	}
	
	public void gjett(char a, Button s)
	{
	//	bokstavString += a + "  ";
	//	bokstavFelt.setText(bokstavString);
		
		boolean riktigGjett = false;
		s.setEnabled(false);
		
		for(int i = 0; i < uferdigOrd.length(); i++)
		{
			if (ferdigOrd.charAt(i) == a)
			{
				int hjelp = (1000 / (uferdigOrd.length() / 2));
				
				if(forrigeRett == true)
				{
					if(fForrigeRett==true)
					{
						poeng += (hjelp * 4);
						visMultiplier("4x");
					}
					else
					{
						poeng += (hjelp * 2);
						fForrigeRett=true;
						visMultiplier("2x");
					}
				}
				else
				{
					poeng += hjelp;
				}
				
				riktigGjett = true;
				riktigGjettTeller++;
				System.out.println("Bokstaven " + a + " finnes i ordet!");
				System.out.println("Poeng: " + poeng);
				uferdigOrd.setCharAt(i, a);
				ordFelt.setText(uferdigOrd);
				poengFelt.setText(String.valueOf(poeng));
				s.setBackgroundResource(R.xml.rounded_green);
				forrigeRett = true;
				
				
				if(riktigGjettTeller >= 1)
				{
					switch(riktigGjettTeller){
		            case 1:	
		            	mediaPlayer.reset();
		            	mediaPlayer = MediaPlayer.create(this, R.raw.riktig1);
		            	mediaPlayer.start();
		            break;
		            case 2:	
		            	mediaPlayer.stop();
		            	mediaPlayer.reset();
		            	mediaPlayer.release();
		            	mediaPlayer = null;		            	
		            	mediaPlayer = MediaPlayer.create(this, R.raw.riktig2);
		            	mediaPlayer.start();
		            break;
		            case 3:	
		            	mediaPlayer.stop();
		            	mediaPlayer.reset();
		            	mediaPlayer.release();
		            	mediaPlayer = null;		            	
		            	mediaPlayer = MediaPlayer.create(this, R.raw.riktig3);
		            	mediaPlayer.start();	            	
		            break;
		            case 4:	
		            	mediaPlayer.stop();
		            	mediaPlayer.reset();
		            	mediaPlayer.release();
		            	mediaPlayer = null;		            	
		            	mediaPlayer = MediaPlayer.create(this, R.raw.riktig4);
		            	mediaPlayer.start();
		            break;
		            case 5:	
		            	mediaPlayer.stop();
		            	mediaPlayer.reset();
		            	mediaPlayer.release();
		            	mediaPlayer = null;		            	
		            	mediaPlayer = MediaPlayer.create(this, R.raw.riktig5);
		            	mediaPlayer.start();
		            break;
		            case 6:	
		            	mediaPlayer.stop();
		            	mediaPlayer.reset();
		            	mediaPlayer.release();
		            	mediaPlayer = null;		            	
		            	mediaPlayer = MediaPlayer.create(this, R.raw.riktig6);
		            	mediaPlayer.start();
		            break;
					}
				}
				else
				{
	            	mediaPlayer.stop();
	            	mediaPlayer.reset();
	            	mediaPlayer.release();
	            	mediaPlayer = null;		            	
	            	mediaPlayer = MediaPlayer.create(this, R.raw.riktig6);
	            	mediaPlayer.start();
				}
				
				if(ferdigOrd.toString().equals(uferdigOrd.toString()))
				{
	            	mediaPlayer.stop();
	            	mediaPlayer.reset();
	            	mediaPlayer.release();
	            	mediaPlayer = null;	
	            	
					System.out.println("DU VANT! Poeng:" + poeng);
					if(poeng > hiscore)
					{
						hiscore = poeng;
						
						spillFerdig();
					}
					else
					{
						spillFerdig();
					}
					
				}
			}
		}
		
		if(riktigGjett == false)
		{
			forrigeRett=false;
			fForrigeRett=false;
			feilGjettTeller++;
			System.out.println(feilGjettTeller);
			s.setBackgroundResource(R.xml.rounded_red);
			visMultiplier(" ");
			
        	mediaPlayer.stop();
        	mediaPlayer.reset();
        	mediaPlayer.release();
        	mediaPlayer = null;		            	
        	mediaPlayer = MediaPlayer.create(this, R.raw.feil);
        	mediaPlayer.start();
        	
        	switch(feilGjettTeller)
        	{
        	case 1:
        		imageHangman.setImageResource(R.drawable.h1);
        		imageHangman.setVisibility(View.VISIBLE);
            break;
        	case 2:
        		imageHangman.setImageResource(R.drawable.h2);
            break;
        	case 3:
        		imageHangman.setImageResource(R.drawable.h3);
            break;
        	case 4:
        		imageHangman.setImageResource(R.drawable.h4);
            break;
        	case 5:
        		imageHangman.setImageResource(R.drawable.h5);
            break;
        	case 6:
        		imageHangman.setImageResource(R.drawable.h6);
				System.out.println("Avslutter spill");
				finish();
            break;
            
        	}
		}
		
	}
	public void bestemOrd()
	{
		String[] ord = getResources().getStringArray(R.array.ordArray);
		randomOrd = ord[new Random().nextInt(ord.length)];
		
		for(int i=0; i < ordTabell.length; i++)
		{
			if(Arrays.asList(ordTabell).contains(randomOrd))
			{
				System.out.println(randomOrd + " er allerede trekt");
				break;
			}
			else if(ordTabell[i] == null)
			{
				ordTabell[i] = randomOrd;
				ordTeller++;
				return;
			}
		}
		bestemOrd();
	}
	public String trekkOrd()
	{
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + (Arrays.toString(ordTabell)));
		bestemOrd();
		return randomOrd;
	}
	
	public void spillFerdig()
	{   
    	mediaPlayer = MediaPlayer.create(this, R.raw.vinn);
    	mediaPlayer.start();
    	riktigeOrd++;
    	
		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
		   public void run() { 
		    	if(ordTeller == 5)
		    	{
		    		finish();
		    	}
		    	else
		    	{
		    		imageHangman.setImageResource(R.drawable.h0);
		    		tilbakestillKnapper();
		    		nyttSpill();
		    	}
		   } 
	    }, 3000); 
    	

		
	}
	
	public void tilbakestillKnapper()
	{
		A.setBackgroundResource(R.xml.rounded_blue); A.setEnabled(true);
		B.setBackgroundResource(R.xml.rounded_blue); B.setEnabled(true);
		C.setBackgroundResource(R.xml.rounded_blue); C.setEnabled(true);
		D.setBackgroundResource(R.xml.rounded_blue); D.setEnabled(true);
		E.setBackgroundResource(R.xml.rounded_blue); E.setEnabled(true);
		F.setBackgroundResource(R.xml.rounded_blue); F.setEnabled(true);
		G.setBackgroundResource(R.xml.rounded_blue); G.setEnabled(true);
		H.setBackgroundResource(R.xml.rounded_blue); H.setEnabled(true);
		I.setBackgroundResource(R.xml.rounded_blue); I.setEnabled(true);
		J.setBackgroundResource(R.xml.rounded_blue); J.setEnabled(true);
		K.setBackgroundResource(R.xml.rounded_blue); K.setEnabled(true);
		L.setBackgroundResource(R.xml.rounded_blue); L.setEnabled(true);
		M.setBackgroundResource(R.xml.rounded_blue); M.setEnabled(true);
		N.setBackgroundResource(R.xml.rounded_blue); N.setEnabled(true);
		O.setBackgroundResource(R.xml.rounded_blue); O.setEnabled(true);
		P.setBackgroundResource(R.xml.rounded_blue); P.setEnabled(true);
		Q.setBackgroundResource(R.xml.rounded_blue); Q.setEnabled(true);
		Rb.setBackgroundResource(R.xml.rounded_blue); Rb.setEnabled(true);
		S.setBackgroundResource(R.xml.rounded_blue); S.setEnabled(true);
		T.setBackgroundResource(R.xml.rounded_blue); T.setEnabled(true);
		U.setBackgroundResource(R.xml.rounded_blue); U.setEnabled(true);
		V.setBackgroundResource(R.xml.rounded_blue); V.setEnabled(true);
		W.setBackgroundResource(R.xml.rounded_blue); W.setEnabled(true);
		X.setBackgroundResource(R.xml.rounded_blue); X.setEnabled(true);
		Y.setBackgroundResource(R.xml.rounded_blue); Y.setEnabled(true);
		Z.setBackgroundResource(R.xml.rounded_blue); Z.setEnabled(true);
		
		
	}
	
	public void visMultiplier(String multi)
	{
		if(forrigeRett == true)
		{
			multiFelt.setText(multi);
			multiFelt.setVisibility(View.VISIBLE);
		}
		else
		{
			multiFelt.setVisibility(View.GONE);
		}
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
	
	
	
 /*   private OnCompletionListener onCompletionListener = new OnCompletionListener() 
    {
    	public void onCompletion(MediaPlayer mediaPlayer)
    	{
    		switch(mediaPlayer){
            case R.raw.vinn:
                 vinnLyd.release();
            break;
            case R.raw.riktig1:
                riktigLyd1.release();
            break;
            case R.raw.riktig2:
                riktigLyd2.release();
            break;
            case R.raw.riktig3:
                riktigLyd3.release();
            break;      
            case R.raw.riktig4:
                riktigLyd4.release();
            break;
            case R.raw.riktig5:
                riktigLyd5.release();
            break;
            case R.raw.riktig6:
                riktigLyd6.release();
            break;
            case R.raw.feil:
                feilLyd.release();
            break;
    		}
    	}	
    }; */
    
	
	
	

}
