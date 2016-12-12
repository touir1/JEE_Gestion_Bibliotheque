package com.example.bibliotheque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JSONParserO extends AsyncTask<String, Void, String> {

	public static JSONObject getjObj() {
		return jObj;
	}

	public static void setjObj(JSONObject jObj) {
		JSONParserO.jObj = jObj;
	}

	static InputStream is = null;
	static JSONObject jObj = null;
	static JSONArray jArray = null;
	static String json = "";

	@Override
	protected String doInBackground(String... params) {
		String url = params[0];
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse getResponse = httpClient.execute(httpGet);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url);
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			is = getResponseEntity.getContent();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("IO", e.getMessage().toString());
			e.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Erreur", "Erreur de conversion" + e.toString());
		}
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jObj.toString();
	}

}