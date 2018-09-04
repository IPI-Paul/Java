package chapter03;  // sax

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import ipi.*;

/**
 * {@code SAXTest} class Listing 3.8 <br />
 * this program demonstrates how to use a SAX parser. The program prints all hyperlinks 
 * of an XHTML web page. <br />
 * Usage java chapter03.SAXTest URL <br />
 * @version 1.00 2001-09-29
 * @author Cay Horstmann
 */
public class SAXTest {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) throws Exception {
		String url = "";
		/**
		 * Altered to give the user a choice of loading a URL or opening a file when main(args) is null <br />
		if (args.length == 0) {
			url = "http://www.w3c.org";
			System.out.print("Using " + url);
		} else 
			url = args[0]
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
			url = args[0];		
		else if (runType == 0) 
			url = file.getUrl();

		// open reader for URL
		InputStream in;
		if ((args != null && args.length > 0) || runType == 0)
			try {
				in = new URL(url).openStream();
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
		
		DefaultHandler handler = new DefaultHandler() {
			public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) {
				if (lname.equals("a") && attrs != null) {
					for (int i = 0; i < attrs.getLength(); i++) {
						String aname = attrs.getLocalName(i);
						if (aname.equals("href")) System.out.println(attrs.getValue(i));
					}
				}
			}
		};
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		SAXParser saxParser = factory.newSAXParser();
		try {
			saxParser.parse(in, handler);
			Threads.closeStream(in);
		} catch (Exception ex) {
			message = "There was an error parsing this web page: " + ex.getCause();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
