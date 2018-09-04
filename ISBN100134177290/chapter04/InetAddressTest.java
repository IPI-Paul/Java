package chapter04;  // inetAddress

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ipi.*;

/**
 * {@code InetAddressTest} class Listing 4.2 <br />
 * This program demonstrates the {@link InetAddress} cache. Supply a host name as command-line
 * argument, or run without command-line arguments to see the address of the local host. <br />
 * @version 1.02 2012-06-05
 * @author Cay Horstmann
 */
public class InetAddressTest {
	private static final String MAIN_CLASS = "chapter04.Chapter04";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) throws IOException {
		/**
		 * Altered to enable web start mode and a dialog input option <br />
		if (args != null && args.length > 0) {
			String host = args[0];
			InetAddress[] addresses = InetAddress.getAllByName(host);
			for (InetAddress a : addresses) 
				System.out.println(a);
		} else {
			InetAddress localHostAddress = InetAddress.getLocalHost();
			System.out.println(localHostAddress);
		}
		 */
		Charset cs = StandardCharsets.UTF_8;
		int runType = 0;
		if (args == null || args.length == 0) {
			runType = file.setChoice("DJ", "htm", "", "", cs, "Please give URL to web page here: ", 
					"localhost", "For Web Page");
			if (file.getChoice() == false) {
				Views.openWindowOpener(MAIN_CLASS, message);
				return;
			}
		}
		String host;
		InetAddress[] addresses;
		if (args != null && args.length > 0) 
			host = args[0];
		else if (runType == 0) 
			host = InetAddress.getLocalHost().getHostName();
		else 
			host = file.getUrl();		
		try {
			addresses = InetAddress.getAllByName(host);
			for (InetAddress a : addresses) 
				System.out.println(a);
		} catch (UnknownHostException ex) {
			message = ex.toString();
		}

		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
