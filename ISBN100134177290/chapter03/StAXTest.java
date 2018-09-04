package chapter03;  // stax

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.xml.stream.*;
import ipi.*;

/**
 * {@code StAXTest} class Listing 3.9 <br />
 * This program demonstrates how to use a StAX parser. The program prints all hyperlinks of
 * an XHTML web page. <br />
 * Usage: java chapter03.StAXTest URL <br />
 * @version 1.0 2007-06-23 
 * @author Cay Horstmann
 */
public class StAXTest {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) throws Exception {
		String urlString = "";
		/**
		 * Altered to give the user a choice of loading a URL or opening a file when main(args) is null <br />
		if (args.length() == 0) {
			urlString = "http://www.w3c.org";
			System.out.println("Using " + urlString);
		} else
			urlString = args[0];
		 */
		Charset cs = StandardCharsets.UTF_8;
		int runType = 0;
		if (args == null || args.length == 0) {
			runType = file.setChoice("JS", "htm", "", "", cs, "Please give URL to web page here: ", 
					"http://www.w3c.org", "For Web Page");
			if (file.getChoice() == false) {
				Views.openWindowOpener(MAIN_CLASS, message);
				return;
			}
		}
		if (args != null && args.length > 0) 
			urlString = args[0];		
		else if (runType == 0) 
			urlString = file.getUrl();

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
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try { 
			XMLStreamReader parser = factory.createXMLStreamReader(in);
			while (parser.hasNext()) {
				int event = parser.next();
				if (event == XMLStreamConstants.START_ELEMENT) {
					if (parser.getLocalName().equals("a")) {
						String href = parser.getAttributeValue(null, "href");
						if (href != null)
							System.out.println(href);
					}
				}
			}
			Threads.closeStream(in);
		} catch (Exception ex) {
			message = "There was an error parsing this web page: " + ex.getCause();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
