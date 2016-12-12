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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import metier.entities.Client;
import metier.entities.Livre;
import metier.entities.Historique;

public class AfficherHistorique extends Activity{
	
	private Client client = null;
	private List<Historique> historique = null;
	private JSONParser jsonParser = null;
	private ListView list_historique = null;
	
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
		
		setContentView(R.layout.afficher_historique);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
		
		init();
		listeners();
	}
	
	private void init(){
		
		list_historique = (ListView) findViewById(R.id.list_historique);
		
		client = (Client) getIntent().getSerializableExtra("client");
		
		jsonParser = new JSONParser();
		jsonParser.execute(new String[] { "http://127.0.0.1:8100/BibliothequeRestService/historique/"+ client.getNum_client()});
		try{
			String rep = jsonParser.get();
			ObjectMapper mapper = new ObjectMapper();
			historique = mapper.readValue(rep, new TypeReference<List<Historique>>(){});
			if(historique!=null){
				ArrayList<String> tmp = new ArrayList<String>();
				for(int i=0;i<historique.size();i++){
					Historique l = historique.get(i);
					tmp.add("Date: "+l.getDateCommande().toString()
						+"\nId: "+l.getIdLivre()
						+"\nnom: "+l.getNomLivre()
						+"\nprix unitaire: "+l.getPrixUnitaire()
						+"\nquantité: "+l.getQuantite()
						+"\nmode paiement: "+l.getModePaiement());
				}
				
				String[] books = tmp.toArray(new String[tmp.size()]);
				
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview, books);
				list_historique.setAdapter(adapter);
			
			}
		}
		catch(Exception e){
			Log.e("ERROR", e.getMessage());
			afficherMsg("ERROR",e.getMessage());
		}
	}
	
	private void listeners(){
		
	}
}
