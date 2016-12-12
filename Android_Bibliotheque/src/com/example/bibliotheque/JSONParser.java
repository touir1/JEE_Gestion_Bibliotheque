package com.example.bibliotheque;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JSONParser extends AsyncTask <String, Void, String>{

	static InputStream is = null;
	static String json = "";
	static JSONObject jObj = null;
	
	@Override
	protected String doInBackground(String... params) {
		String url=params[0];
		DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpPost = new HttpGet(url);
        HttpResponse getResponse;
		try {
			getResponse = httpClient.execute(httpPost);
			HttpEntity getResponseEntity = getResponse.getEntity();
			is = getResponseEntity.getContent(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(
	                is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	        is.close();
	        json = sb.toString();
		} catch (Exception e) {
			//nothing
			Log.e("ERROR", e.getMessage());
		}        
		return json;
	}

}
