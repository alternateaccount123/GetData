package com.example;
//AI GENERATED : solves my question as to how to use gson library to convert 
//from inputstream to JsonObject
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class IsToJson {

  public static void main(String[] args) throws IOException {
	  
	  
    // Make a HTTP request to the dog.ceo API to get the list of all dog breeds
    URL url = new URL("https://dog.ceo/api/breeds/list/all");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");

    
    //use gson library to transform inputstream into JsonObject
    Gson gson = new Gson();
    InputStreamReader reader = new InputStreamReader(connection.getInputStream());
    //the method fromJson has overloaded methods here we choose ...(reader,Class); 
    JsonObject json = gson.fromJson(reader, JsonObject.class);
    
    /*
    // use gson library to get JSON obj "message" from within obj above
    JsonObject breedsJson = json.getAsJsonObject("message");

    // Loop through the breeds and print them out
    for (String breed : breedsJson.keySet()) {
      System.out.println(breed);
    }
    */
    
    
    /*
     if you print the string variable in the example code,
      it will not be a valid JSON string because the toJson() 
      method is being used to convert a regular string, not a 
      JSON object, into a JSON string. In order to create a valid
       JSON string, you would need to convert a JSON object
     */
    String str = gson.toJson("abcd");
    System.out.println(str);
  }
}

