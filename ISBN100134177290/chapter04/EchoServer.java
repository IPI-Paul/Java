package chapter04; // server

import java.io.*;
import java.net.*;
import java.util.*;
import ipi.*;

/**
 * {@code EchoServer} class Listing 4.3 <br />
 * This program implements a simple server that listens to port 8189 and echoes back
 * all client input. <br />
 * @version 1.21 2012-05-19
 * @author Cay Horstmann
 */
public class EchoServer {
	private static final String MAIN_CLASS = "chapter04.Chapter04";
	private static String message = "";
	
	public static void main(String[] args) throws IOException {
		// establish server socket
		try (ServerSocket s = new ServerSocket(8189)) {
			// wait for client connection
			try (Socket incoming = s.accept()) {
				InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();
				
				try (Scanner in = new Scanner(inStream, "UTF-8")) {
					PrintWriter out = new PrintWriter(
							new OutputStreamWriter(outStream, "UTF-8"),
							true /* autoflush */
							);
					out.println("Hello! Enter BYE to exit.");
					
					// echo client input
					boolean done = false;
					while (!done && in.hasNextLine()) {
						String line = in.nextLine();
						out.println("Echo: " + line);
						if (line.trim().equals("BYE")) done = true;
					}
				}
			}
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
