package com.example.bibliotheque;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import metier.entities.Client;
import metier.entities.Historique;
import metier.entities.Livre;
import metier.entities.ModePaiement;
import metier.entities.Panier;

public class DetailLivre extends Activity{
	
	private Client client = null;
	private Livre livre = null;
	private JSONParser jsonParser = null;
	private Button confirmer_achat = null;
	private TextView afficher_livre = null;
	private List<ModePaiement> modes = null;
	private Spinner mode_paiement = null;
	
	@SuppressWarnings("deprecation")
	public void afficherMsg(String title, String msg){
		AlertDialog alertDialog;
    	alertDialog = new AlertDialog.Builder(this).create();
    	alertDialog.setTitle(title);
    	alertDialog.setMessage(msg);
    	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    		@Override
	    	public void onClick(final DialogInterface dialog, final int which) {	
    			dialog.dismiss();
    		}
    	});

    	alertDialog.show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.detail_livre);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
		
		init();
		listeners();
	}
	
	private void init(){
		confirmer_achat = (Button) findViewById(R.id.confirmer_achat);
		afficher_livre = (TextView) findViewById(R.id.afficher_livre);
		mode_paiement = (Spinner) findViewById(R.id.mode_paiement);
		client = (Client) getIntent().getSerializableExtra("client");
		livre = (Livre) getIntent().getSerializableExtra("livre");
		
		afficher_livre.setText("Nom du livre: "+livre.getNomLivre()
								+"\nPrix: "+livre.getPrix()
								+"\nAuteur: "+livre.getAuteur().getNom()+" "+livre.getAuteur().getPrenom()
								+"\nEditeur: "+livre.getEditeur().getNom()
								+"\nDate d'apparition: "+livre.getDateApparition().getDay()+"/"
								+livre.getDateApparition().getMonth()+"/"
								+livre.getDateApparition().getYear());
		
		jsonParser = new JSONParser();
		jsonParser.execute(new String[] {"http://127.0.0.1:8100/BibliothequeRestService/modepaiement" });
		try{
			String rep = jsonParser.get();
			ObjectMapper mapper = new ObjectMapper();
			modes = mapper.readValue(rep, new TypeReference<List<ModePaiement>>(){});
			if(modes!=null){
				List<String> md = new ArrayList<String>();
				for(int i=0;i<modes.size();i++){
					md.add(modes.get(i).getName());
				}
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
						R.layout.spinner_item, md);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				mode_paiement.setAdapter(dataAdapter);
			}
		}
		catch(Exception e){
			Log.e("ERROR", e.getMessage());
		}
	}
	
	private void listeners(){
		confirmer_achat.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				ObjectMapper mapper = new ObjectMapper();
				
				ModePaiement mode = modes.get(mode_paiement.getSelectedItemPosition());
				
				Long _l = livre.getID_livre();
				Long _c = client.getNum_client();
				Long _m = mode.getId();
				
				jsonParser = new JSONParser();
				jsonParser.execute(new String[] { "http://127.0.0.1:8100/BibliothequeRestService/passerCommande"
												 +"/"+ _l 
												 +"/"+ _c 
												 +"/"+ _m});
				try{
					String rep = jsonParser.get();
					Boolean validation=mapper.readValue(rep,Boolean.class);
					if(validation.booleanValue()){
						Toast.makeText(DetailLivre.this, "La commande a été effectué", Toast.LENGTH_SHORT).show();
						finish();
					}
				}
				catch(Exception e){
					Log.e("ERROR", e.getMessage());
				}
				
			}
			
		});
	}
}
