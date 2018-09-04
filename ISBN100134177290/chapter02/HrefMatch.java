package chapter02;  // match

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.regex.*;
import ipi.*;

/**
 * {@code HrefMatch} class Listing 2.7 <br />
 * This program displays all URLs in a web page by matching a regular expression
 * that describes the &lt;a href=...> HTML tag. You can start the program as <br />
 * <em>java chapter02.HrefMatch URL</em> <br />
 * @version 1.02 2016-07-14
 * @author Cay Horstmann
 */
public class HrefMatch {
	private static final String MAIN_CLASS = "chapter02.Chapter02";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) {
		/**
		 * Altered to give the user a choice of loading a URL or opening a file when main(args) is null <br />
		 */
		Charset cs = StandardCharsets.UTF_8;
		int runType = 0;
		if (args == null || args.length == 0) {
			runType = file.setChoice("JS", "htm", "", "", cs, "Please give URL to web page here: ", 
					"http://java.sun.com", "For Web Page");
			if (file.getChoice() == false) {
				Views.openWindowOpener(MAIN_CLASS, message);
				return;
			}
		}

		try {
			// get URL string from command line or use
			String urlString = "";
			if (args != null && args.length > 0) urlString = args[0];
			else if (runType == 0) 
				urlString = file.getUrl();
			/**
			 * Removed default string <em>http://java.sun.com</em> to allow user to enter URL from a 
			 * dialog input box. <br />
			 */
			// open reader for URL
			InputStream in;
			if ((args != null && args.length > 0) || runType == 0)
				try {
					in = new URL(urlString).openStream();
				} catch (MalformedURLException ex) {
					System.out.println(ex.getCause());
					in = null;
				}
			else {
				/**
				 * Added to enable opening file from dialog in either normal or web start mode <br />
				 */
				in = file.getInputStream(file.getPath());
			}
			
			// read contents into string builder
			StringBuilder input = new StringBuilder();
			int ch;
			try {
				while ((ch = in.read()) != -1) 
					input.append((char) ch);
				
				// search for all occurrences of pattern
				String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
				Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(input);
				
				while (matcher.find()) {
					String match = matcher.group();
					System.out.println(match);
				}
			} catch (NullPointerException ex) {
				message = "There was an error parsing this web page: " + ex.getCause();
			}
		} catch (IOException | PatternSyntaxException e) {
			message = "" + e.getCause();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
