package com.example.bibliotheque;

import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import metier.entities.Compte;

public class Login extends Activity implements OnClickListener {
	private ImageButton btn_login;
	private EditText login;
	private EditText password;
	private JSONParser jsonParser = null;
	private boolean exit=false;

	@SuppressWarnings("deprecation")
	public void afficherMsg(String title, String msg) {
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
		
		setContentView(R.layout.login);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		btn_login = (ImageButton) findViewById(R.id.imageButton1);
		login = (EditText) findViewById(R.id.SearchBar);
		password = (EditText) findViewById(R.id.editText2);

		listeners();
	}

	public void listeners() {
		btn_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == btn_login) {
			// afficherMsg("success",login.getText()+" : "+password.getText());
			jsonParser = new JSONParser();
			// 10.0.2.2 for emulator
			// 127.0.0.1 for usb android
			jsonParser.execute(new String[] { "http://127.0.0.1:8100/BibliothequeRestService/connection/"
					+ login.getText() + "/" + password.getText().toString() });
			try {
				String rep = jsonParser.get();
				ObjectMapper mapper = new ObjectMapper();
				Compte compte = mapper.readValue(rep, Compte.class);
				if (compte != null) {
					// afficherMsg("success","Bienvenue
					// "+compte.getClient().getNom());
					
					Intent intent = new Intent(Login.this, AchatLivres.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
					intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					intent.putExtra("compte", compte);
					startActivity(intent);
				} else
					afficherMsg("ERROR", "username or password incorrect");
				// afficherMsg("success",rep);
			} catch (Exception e) {
				// nothing
				Log.e("ERROR", e.getMessage());
				afficherMsg("ERROR", "nom d'utilisateur ou mot de passe incorrect");
			}
		}

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
