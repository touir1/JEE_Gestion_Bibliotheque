package com.example.bibliotheque;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	DatabaseHelper db;
	Button btnMain;
	Button btnAfficher;
	EditText e1;
	EditText e2;
	EditText e3;
	EditText e4;
	Button btn;
	
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
		setContentView(R.layout.ajouterlivre);
		db=new DatabaseHelper(this);
		
		e1 = (EditText) findViewById(R.id.SearchBar);
		e2 = (EditText) findViewById(R.id.editText2);
		e3 = (EditText) findViewById(R.id.editText3);
		e4 = (EditText) findViewById(R.id.editText4);
		btn = (Button) findViewById(R.id.buttonMain);
		btnMain = (Button) findViewById(R.id.buttonMain);
		btnAfficher = (Button) findViewById(R.id.buttonAfficher);
		
		addData();
		viewAll();
	}
	
	public void addData(){
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean isInserted = db.insertData(e2.getText().toString(),e3.getText().toString(),e4.getText().toString());
				if(isInserted)
					Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public void viewAll(){
		btnAfficher.setOnClickListener(
			new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
						Cursor cur = db.getALLData();
						if(cur.getCount()==0){
							Toast.makeText(MainActivity.this, "Liste vide de livres", Toast.LENGTH_LONG).show();
						}
						else{
							StringBuffer buff = new StringBuffer();
							while(cur.moveToNext()){
								buff.append("Id: "+cur.getString(0)+"\n");
								buff.append("Titre: "+cur.getString(1)+"\n");
								buff.append("Auteur: "+cur.getString(2)+"\n");
								buff.append("Date d'apparition: "+cur.getString(3)+"\n\n");
							}
							afficherMsg("Data",buff.toString());
						}
					
					
				}
			}
			);
		}
	}

