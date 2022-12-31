package com.imagetest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

public class DogAPI {
	
	public static void main(String[] args) throws IOException, InterruptedException {
			
		//<1>get an "endpoint" (resource url) should be at your api docs
		//i.e. api docs "https://dog.ceo/dog-api/documentation/"
        //i.e. endpoint "https://dog.ceo/api/breeds/image/random"  
		
		
		//<2>connect to endpoint
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://dog.ceo/api/breeds/image/random"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		
		//<3> for this example we expect the response to be an image , so ........
		
		
		
		String json_str_all_in_oneline = response.body().toString();  //all in one line String 
		//use library Gson to format spacing and \n 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement je = JsonParser.parseString(json_str_all_in_oneline);
		String prettyJsonString = gson.toJson(je);
		//System.out.println(prettyJsonString);

		//get values from keys inside json object
	    JsonObject jsonObject = je.getAsJsonObject();
	    
	    //System.out.println( jsonObject.get("message") ); //prints key message inside json obj 
		String str_imageUrl = jsonObject.get("message").getAsString();

		// Open a connection to the image URL
		URL imageUrl = new URL(str_imageUrl);
		InputStream in = imageUrl.openStream();
		
	    byte[] imageData = IOUtils.toByteArray(in);
	    ImageIO.write(ImageIO.read(new ByteArrayInputStream(imageData)), "jpg", new File("dog_from_api_conn.jpg"));
		
		/*//idk if this is better below 
		// Save the image to a local file
		FileOutputStream out = new FileOutputStream("image.jpg");
		byte[] buffer = new byte[2048];
		int length;
		while ((length = in.read(buffer)) != -1) {
		    out.write(buffer, 0, length);
		}
		out.close();
		*/
		// Print a message to the user
		//System.out.println("Image saved to image.jpg");


	
	 
		

		
		
		
		
		
	    /*
	     //for url example 
	    //use gson to trans is to jsonobj
	    Gson gson = new Gson();
	    InputStreamReader reader = new InputStreamReader(connection.getInputStream());
		JsonObject json = gson.fromJson(reader, JsonObject.class);
		
		
		// Get the URL of the random image from the JSON response
		String str = json.get("message").getAsString();
		
		System.out.println(str);
	    */
	    



	    
	}
	
	
	
	
}













