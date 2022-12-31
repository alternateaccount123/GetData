package com.startingpoint;

//connect to API with no arguments  
//this json is made out of key and values 
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

//note: until java 11 there was a class called JsonObject for parsing json but is does not exist no more 
//      so the only way is using external libraries like gson  
public class ConnectingToEndpointWhereISS {

	public static void main(String[] args) throws InterruptedException {

		 
		try {
		
			//<1>get an "endpoint" (resource url) should be at your api docs
			//i.e. api docs "https://wheretheiss.at/w/developer"
            //i.e. endpoint "https://api.wheretheiss.at/v1/satellites/25544"  
					
			//<2>connect to endpoint
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.wheretheiss.at/v1/satellites/25544"))  //connect to endpoint using get 
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
			//<3> for this example we expect the response to be a json obj , so we parse it using gson lib		
			
			//printPrettyJson(response);   //uses external library gson
			getJsonData(response);       //uses external library gson


			
		} catch (IOException e) {
			System.out.println(e);
		}

	}



	private static void printPrettyJson(HttpResponse<String> response) {
		//<1> get response as String 
		String json_str_all_in_oneline = response.body().toString();  //all json data will be in one line no "\n" 
		
		//<2>use library Gson to format space "\n" after each line and identation  
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement je = JsonParser.parseString(json_str_all_in_oneline);  //cast to JsonElement (gson lib)
		String prettyJsonString = gson.toJson(je);   //all json data is now formated 		
		//print formated json 
		System.out.println(prettyJsonString);
		
	}



	private static void getJsonData(HttpResponse<String> response) {
		//<1>convert response to string then to JsonElement object
		String json_str_all_in_oneline = response.body().toString();  //str all json data will be in one line no "\n" 	
		JsonElement je = JsonParser.parseString(json_str_all_in_oneline);  //cast str to JsonElement (gson lib)
		
		//<2>convert JsonElement to JsonObject
	    JsonObject jsonObject = je.getAsJsonObject();    //cast to JsonObject (gson lib)
	    //print key values 
	    System.out.println( "latitud: " + jsonObject.get("latitude") );
	    System.out.println( "longitud: " + jsonObject.get("longitude") );		
	}

}
