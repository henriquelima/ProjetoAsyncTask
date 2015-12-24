package br.edu.ifpb.conection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.sql.Connection;

import org.json.JSONException;
import org.json.JSONObject;

import Util.HttpService;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MinhaAsyncTask extends AsyncTask<String,Void,HttpURLConnection> {
	
	Context context;
	
	public MinhaAsyncTask(Context activity){
		this.context = activity;
	}
	
	protected void onPreExecute(){
		
		Log.i("NotificationWearApp", "OnPreExecute");
	}

	@Override
	protected HttpURLConnection doInBackground(String... valores) {
		
		Log.i("NotificationWearApp","doInBackground" + valores[0]);
		
		HttpURLConnection connection = null;
		
		JSONObject js = new JSONObject();
		
		try{
			
			js.put("nome", valores[0]);
			js.put("senha", valores[1]);
			
		} catch (Exception e){
			Log.e("","" + e.getMessage());
		}
		
		try{
			
			connection = HttpService.sendGetRequest();
			HttpService.sendJsonPostRequest("servicoservlet", js);
			
		} catch (MalformedURLException ex){
			
			Log.e("NotificationWearApp", "MalformedURLException");
			
		} catch (IOException ex){
			
			Log.e("NotificationWearApp","MalformedURLException");
			
		}
			

		return connection;
	}
	
	protected void onPostExecute(Connection connection){
		
		try{
			
			int status = ((HttpURLConnection) connection).getResponseCode();
			
			String contentValue = HttpService.getHttpContent(connection);
			JSONObject json = new JSONObject(contentValue);
			
			String senha = json.getString("key");
			if (status==20) {
				String key = json.getString("key");
				Toast.makeText(context, "Key: "+key , Toast.LENGTH_LONG).show();
			}else{
				String erro = json.getString("mensagem");
				Toast.makeText(context, "Mensagem "+erro , Toast.LENGTH_LONG).show();
			}
		} catch (IOException e){
			
			e.printStackTrace();
					
		} catch (JSONException e){
			Log.e("NotificationWearApp", "JSONException");
		}
	}
	

}
