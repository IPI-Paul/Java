package chapter04;  // urlConnection

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import ipi.*;

/**
 * {@code URLConnectionTest} class Listing 4.6 <br />
 * This program connects to an URL and displays the response header data and the first 10 
 * lines of the requested data. <br />
 * Supply the URL and an optional username and password (for HTTP basic authentication)
 * on the command line. <br /> 
 * @version 1.11 2007-06-26
 * @author Cay Horstmann
 */
public class URLConnectionTest {
	private static final String MAIN_CLASS = "chapter04.Chapter04";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) {
		try {
			String urlName;
			/**
			 * Altered to enable web start mode and a dialog input option <br />
			if (args.length > 0) urlName = args[0];
			else urlName = "http://horstmann.com";
			 */
			Charset cs = StandardCharsets.UTF_8;
			if (args == null || args.length == 0) {
				file.setChoice("DJ", "htm", "", "", cs, "Please give URL to web page here: ", 
						"http://horstmann.com", "For Web Page");
				if (file.getChoice() == false) {
					Views.openWindowOpener(MAIN_CLASS, message);
					return;
				}
			}
			if (args != null && args.length > 0) 
				urlName = args[0];
			else 
				urlName = file.getUrl();		

			URL url = new URL(urlName);
			URLConnection connection = url.openConnection();
			
			// set username, password if specified on the command line
			/**
			 * Web start requires null check
			if (args.length > 2) {
			 */
			if (args != null && args.length > 2) {
				String username = args[1];
				String password = args[2];
				String input = username + ":" + password;
				Base64.Encoder encoder = Base64.getEncoder();
				String encoding = 
						encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
				connection.setRequestProperty("Authorization", "Basic " + encoding);
			}
			
			connection.connect();
			
			// print header fields
			Map<String, List<String>> headers = connection.getHeaderFields();
			for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
				String key = entry.getKey();
				for (String value : entry.getValue()) 
					System.out.println(key + ": " + value);
			}
			
			// print convenience functions
			System.out.println("---------- ");
			System.out.println("getContentType: " + connection.getContentType());
			System.out.println("getContentLength: " + connection.getContentLength());
			System.out.println("getContentEncoding: " + connection.getContentEncoding());
			System.out.println("getDate: " + connection.getDate());
			System.out.println("getExpiration: " + connection.getExpiration());
			System.out.println("getLastModified: " + connection.getLastModified());
			System.out.println("---------- ");
			
			String encoding = connection.getContentEncoding();
			if(encoding == null) encoding = "UTF-8";
			try (Scanner in = new Scanner(connection.getInputStream(), encoding)){
				// print first ten lines of contents
				for (int n = 1; in.hasNextLine() && n <= 10; n++) 
					System.out.println(in.nextLine());
				if (in.hasNextLine()) System.out.println("...");
			} 
		} catch (UnknownHostException e) {
			message = e.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
