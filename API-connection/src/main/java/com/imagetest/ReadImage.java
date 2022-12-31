package com.imagetest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

public class ReadImage {

	public static void main(String[] args) throws IOException {
		
		
		//example within my on computer
		//byte[] imageData = Files.readAllBytes(Paths.get("C:\\Users\\brandon0\\eclipse-workspace\\test_nothing\\src\\main\\java\\nothing_extrafiles\\empty_image.jpg"));
		//ImageIO.write(ImageIO.read(new ByteArrayInputStream(imageData)), "jpg", new File("output.jpg"));
		
		
		//example with just an url NOT an api 
		//send get request
	    String url = "https://images.dog.ceo/breeds/shiba/shiba-12.jpg";
	    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	    connection.setRequestMethod("GET");
	    //proccess input stream 
	    InputStream inputStream = connection.getInputStream();
	    byte[] imageData = IOUtils.toByteArray(inputStream);
	    ImageIO.write(ImageIO.read(new ByteArrayInputStream(imageData)), "jpg", new File("dog_output.jpg"));


	}

}
