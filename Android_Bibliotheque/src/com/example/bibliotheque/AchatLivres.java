package com.example.bibliotheque;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import metier.entities.Compte;
import metier.entities.Livre;

public class AchatLivres extends Activity{
	
	private Compte compte = null;
	private boolean exit = false;
	private Button btn_historique = null;
	private EditText txt_search = null;
	private ListView list_livres = null;
	private List<Livre> livres = null;
	private List<Livre> afficheLivre = null;
	private JSONParser jsonParser = null;
	
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
		
		setContentView(R.layout.achat_livres);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
		
		compte = (Compte) getIntent().getSerializableExtra("compte");
		
		Toast.makeText(this, "Bienvenue Mr "+compte.getClient().getNom(), Toast.LENGTH_SHORT).show();
		
		init();
		listeners();
		
	}
	
	private void init(){
		btn_historique = (Button) findViewById(R.id.btn_historique);
		list_livres = (ListView) findViewById(R.id.listeLivres);
		txt_search = (EditText) findViewById(R.id.SearchBar);
		
		String[] books;
		jsonParser = new JSONParser();
		jsonParser.execute(new String[] { "http://127.0.0.1:8100/BibliothequeRestService/livres"});
		try{
			String rep = jsonParser.get();
			//afficherMsg("test","get success");
			ObjectMapper mapper = new ObjectMapper();
			livres = mapper.readValue(rep, new TypeReference<List<Livre> >(){});
			afficheLivre = new ArrayList<Livre>();
			if(livres!=null){
				ArrayList<String> tmp = new ArrayList<String>();
				for(int i=0;i<livres.size();i++){
					Livre l = livres.get(i);
					afficheLivre.add(l);
					tmp.add("nom: "+l.getNomLivre()
						+"\nauteur: "+l.getAuteur().getNom()+" "+l.getAuteur().getPrenom()
						+"\nediteur: "+l.getEditeur().getNom());
				}
				
				books = tmp.toArray(new String[tmp.size()]);
				
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview, books);
				list_livres.setAdapter(adapter);
			
			}
		}
		catch(Exception e){
			afficherMsg("ERROR","Exception: "+e.getMessage());
		}
	}
	
	private void listeners(){
		btn_historique.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AchatLivres.this, AfficherHistorique.class);
				intent.putExtra("client", compte.getClient());
				startActivity(intent);
			}
			
		});
		list_livres.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Livre l = afficheLivre.get(position);
				Intent intent = new Intent(AchatLivres.this, DetailLivre.class);
				intent.putExtra("livre", l);
				intent.putExtra("client", compte.getClient());
				startActivity(intent);
			}
			
		});
		
		TextWatcher watcher= new TextWatcher() {
	        
			@Override
			public void afterTextChanged(Editable s) { 
				String[] books;
				ArrayList<String> tmp = new ArrayList<String>();
				afficheLivre = new ArrayList<Livre>();
				for(int i=0;i<livres.size();i++){
					Livre l = livres.get(i);
					if(l.getNomLivre().contains(txt_search.getText().toString())){
						afficheLivre.add(l);
						tmp.add("nom: "+l.getNomLivre()
						+"\nauteur: "+l.getAuteur().getNom()+" "+l.getAuteur().getPrenom()
						+"\nediteur: "+l.getEditeur().getNom());
					}
				}
				
				books = tmp.toArray(new String[tmp.size()]);
				
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AchatLivres.this, R.layout.listview, books);
				list_livres.setAdapter(adapter);
				list_livres.refreshDrawableState();
	        }

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
	    };
	    txt_search.addTextChangedListener(watcher);
	}
	
	@Override
	public void onBackPressed() {
		if (exit) {
			finish(); // finish activity
		} else {
			Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
			exit = true;
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					exit = false;
				}
			}, 3 * 1000);

		}
    }
}
