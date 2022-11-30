package com.workwithjson;

/**
 * Hello world!
 *
 */
//connecting to imdb and printing json 
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {

	public static void main(String[] args) throws InterruptedException {

		 
		try {
		

             //example url: https://api.wheretheiss.at/v1/satellites/25544
	         //example url: https://dog.ceo/api/breeds/list/all
		     //snippet expected query "https://imdb8.p.rapidapi.com/auto-complete?q=game%20of%20thr	
			
			
			//connect to api URL 
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.wheretheiss.at/v1/satellites/25544"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			String json_str_all_in_oneline = response.body().toString();  //all in one line String 
			
			//use library Gson to format spacing and \n 
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonElement je = JsonParser.parseString(json_str_all_in_oneline);
			String prettyJsonString = gson.toJson(je);
			//System.out.println(prettyJsonString);
			
			//get values from keys inside json object
		    JsonObject jsonObject = je.getAsJsonObject();
		    System.out.println( "latitud: " + jsonObject.get("latitude") );
		    System.out.println( "longitud: " + jsonObject.get("longitude") );

			

			
		} catch (IOException e) {
			System.out.println(e);
		}

	}

}
