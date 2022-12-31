package com.startingpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

public class ConnToDogBreedsImageRandom {

	public static void main(String[] args) throws IOException, InterruptedException {

		// <1>get an "endpoint" (resource url) should be at your api docs
		// i.e. api docs "https://dog.ceo/dog-api/documentation/"
		// i.e. endpoint "https://dog.ceo/api/breeds/image/random"

		
		
		// <2>connect to endpoint
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://dog.ceo/api/breeds/image/random"))
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		
		
		// <3> for this example we expect the response to be an image , so we get the url string from the response
		// then download the image online to our pc using URL build in java obj 
		printPrettyJson(response); // uses external library gson
		String strImageUrl = getJsonKeyMessage(response); // uses external library gson
		getImageFromImageUrl(strImageUrl);  
		
		
		

	}
	
	
	private static void printPrettyJson(HttpResponse<String> response) {
		// <1> get response as String
		String json_str_all_in_oneline = response.body().toString(); // all json data will be in one line no "\n"

		// <2>use library Gson to format space "\n" after each line and identation
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement je = JsonParser.parseString(json_str_all_in_oneline); // cast to JsonElement (gson lib)
		String prettyJsonString = gson.toJson(je); // all json data is now formated
		// print formated json
		System.out.println(prettyJsonString);

	}
	
	
	private static String getJsonKeyMessage(HttpResponse<String> response) {
		// <1>convert response to string then to JsonElement object
		String json_str_all_in_oneline = response.body().toString(); // all json data will be in one line no "\n"
		JsonElement je = JsonParser.parseString(json_str_all_in_oneline); // cast to JsonElement (gson lib)

		// <2>convert JsonElement to JsonObject
		JsonObject jsonObject = je.getAsJsonObject(); // cast to JsonObject (gson lib)
		//print 
		System.out.println(jsonObject.get("message").getAsString());
		// return key value
		return jsonObject.get("message").getAsString(); // note the getAsString method

	}
	

	private static void getImageFromImageUrl(String strImageUrl) {

		try {
			//<1> connect to the image URL
			URL imageUrl = new URL(strImageUrl); 
			//<2> retrieve the image from the online url as a byte array
			InputStream in = imageUrl.openStream();
			byte[] imageData = IOUtils.toByteArray(in);
			//<3> convert byte array to a file in your system 
			ImageIO.write(ImageIO.read(new ByteArrayInputStream(imageData)), "jpg", new File("dog_from_api_conn.jpg"));

		} catch (IOException e) {
			System.out.println(e);
		}

	}





}
