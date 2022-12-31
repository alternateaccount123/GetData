package com.startingpoint;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//connect to API with no arguments  
//this json is made out of one key containing a json that contains key and arrays as values {k:{k:[]}}
public class ConnectingToEndpointDogBreeds {

	public static void main(String[] args) throws InterruptedException {

		 
		try {
		
			//<1>get an "endpoint" (resource url) should be at your api docs
			//i.e. api docs "https://dog.ceo/dog-api/documentation/"
            //i.e. endpoint "https://dog.ceo/api/breeds/list/all"  
					
			//<2>connect to endpoint
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://dog.ceo/api/breeds/list/all"))  //connect to endpoint using get 
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
			//<3> for this example we expect the response to be a json obj , so we parse it using gson lib		
			
			printPrettyJson(response);   //uses external library gson
			getJsonKeyMessage(response);   //uses external library gson
			getJsonData(response);         //uses external library gson


			
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


	private static void getJsonKeyMessage(HttpResponse<String> response) {
		
		//<1>convert response to string then to JsonElement object
		String json_str_all_in_oneline = response.body().toString();  //all json data will be in one line no "\n" 		
		JsonElement je = JsonParser.parseString(json_str_all_in_oneline);  //cast to JsonElement (gson lib)
		
		//<2>convert JsonElement to JsonObject
	    JsonObject jsonObject = je.getAsJsonObject();    //cast to JsonObject (gson lib)
	    //print key values 
	    System.out.println( "message: " + jsonObject.get("message") );
		
	}



	private static void getJsonData(HttpResponse<String> response) {
		
		//<1>convert response to string then to JsonElement object
		String json_str_all_in_oneline = response.body().toString();  //all json data will be in one line no "\n"
		JsonElement je = JsonParser.parseString(json_str_all_in_oneline);  //cast to JsonElement (gson lib)	
		//<2>convert JsonElement to JsonObject
	    JsonObject jsonObject = je.getAsJsonObject();    //cast to JsonObject (gson lib)

		
	    //<1>convert key  to JsonElement object
	    JsonElement messageElement = jsonObject.get("message");	    
	    //<2>convert JsonElement to JsonObject
	    JsonObject messageObject = messageElement.getAsJsonObject();  // cast message field to JsonObject
	    
	    //print all keys and values inside key message 
	    Set<Map.Entry<String, JsonElement>> messageEntries = messageObject.entrySet();  // collection of maps with only 1K and 1V each 
	    for (Map.Entry<String, JsonElement> entry : messageEntries) {  // iterate iterate over collection 
	      //get key and value from each entry
	      String key = entry.getKey();  // get key   
	      JsonElement value = entry.getValue();  // get value
	      System.out.println(key + ": " + value);  // print key and value
	    }	
	}

}