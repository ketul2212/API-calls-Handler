package com.ketul.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.ketul.demo.module.User;

@Component
public class ServiceImp {
	
	private static HttpURLConnection con;
	
	private static Set<User> userList = new HashSet<User>();
	
	public Set<User> getUsers() {
		
		BufferedReader reader;
		String line, response = "";
		
		try {
			URL url = new URL("https://reqres.in/api/users?page=2");
			
			con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("GET");
			
			int status = con.getResponseCode();
			System.out.println(status);
			
			if(status > 299) {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				
				while((line = reader.readLine()) != null)
					response += line;
				reader.close();
			} 
			else {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				while((line = reader.readLine()) != null)
					response += line;
				reader.close();
			}
			System.out.println(response);
			
			parse(response);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			con.disconnect();
		}
		
		return userList;
		
	}

	public Set<User> getUsersHttpClient() {
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder(URI.create("https://reqres.in/api/users?page=2")).build();
		
	
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(ServiceImp::parse).join();
		
		
		return userList;
	}
	
	
	public static String parse(String response) {
		JSONObject jsonObject = new JSONObject(response);
		System.out.println(jsonObject);
		
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		System.out.println(jsonArray);

		for(int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = jsonArray.getJSONObject(i);
			int id = json.getInt("id");
			String email = json.getString("email");
			userList.add(new User(id, email));
			System.out.println(id + "    " + email);
		}

		System.out.println(userList);
		return null;
	}
	
}
