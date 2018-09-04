package chapter04;  // socket

import java.io.*;
import java.net.*;
import java.util.*;
import ipi.*;

/**
 * {@code SocketTest} class Listing 4.1 <br />
 * This program makes a socket connection to the atomic clock in Boulder Colorado and prints
 * the time that the server sends. <br />
 * @version 1.21 2016-04-27
 * @author Cay Horstmann
 */
public class SocketTest {
	private static final String MAIN_CLASS = "chapter04.Chapter04";
	private static String message = "";

	public static void main(String[] args) throws IOException {
		try (Socket s = new Socket("time-a.nist.gov", 13);
				Scanner in = new Scanner(s.getInputStream(), "UTF-8")) {
			while (in.hasNext()) {
				String line = in.nextLine();
				System.out.println(line);
			}
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
