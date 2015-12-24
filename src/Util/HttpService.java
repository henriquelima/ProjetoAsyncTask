package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.util.Log;

public class HttpService {
	
	private static final String URL_CONTEXT = "http://192.168.56.1:8080/rest-servlet-service/";
	
	public static HttpURLConnection sendPostRequest(String service)
		throws MalformedURLException, IOException{
		
		HttpURLConnection connection = null;
		
		try{
					
		URL url = new URL(URL_CONTEXT + service);
		
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.connect();
		
		
	} finally{		
		
		connection.disconnect();
	}
	
	return connection;
	}
	
	public static void sendJsonPostRequest(String service, JSONObject json){
		try{
			HttpClient httpclient = new DefaultHttpClient();
			
			HttpPost httpPost = new HttpPost(URL_CONTEXT + service);
			
			String jsons = json.toString();
			
			StringEntity se =  new StringEntity(json);
			
			httpPost.setEntity(se);
			
			httpPost.setHeader("Acepted", "appliction/json");			
			httpPost.setHeader("Content-type", "aplication/json");
			
			HttpResponse httpResponse = httpclient.execute(httpPost);
			
		} catch (Exception e){
			Log.e("InputStream",e.getLocalizedMessage());
		}
							
	}
	
	public static String getHttpContent(Connection connection) {
		
		StringBuilder biulder = new StringBuilder();
		
		try{
			
			InputStream content = connection .getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(content, "iso-8859-1"),8);
			
			String line;
			
			while ((line = reader.readLine())!= null){
				biulder.append(line);
			}
			
			content.close();
			
		} catch (IOException e){
			
			Log.e("NotficationWearApp", "IoExcepition" + e);			
		}

		return biulder.toString();
	}

	public static HttpURLConnection sendGetRequest() {
		// TODO Auto-generated method stub
		return null;
	}

}
