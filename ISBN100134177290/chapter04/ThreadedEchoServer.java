package chapter04; // threaded

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

import ipi.*;

/**
 * {@code ThreadedEchoServer} class Listing 4.4 <br />
 * ThreadedEchoHandler inner class implements Runnable <br />
 * This program implements a multi-threaded server that listens to port 8189 and echoes back
 * all client input. <br /> 
 * @version 1.22 2016-04-27
 * @author Cay Horstmann
 */
public class ThreadedEchoServer {
	private static final String MAIN_CLASS = "chapter04.Chapter04";
	private static String message = "";
	private static ServerSocket s;
	private static int i = 1;
	private static String[][][] letsTalk = new String[5][5][2];
	
	public static void main(String[] args) {
		/**
		try(ServerSocket s = new ServerSocket(8189)) {
		 * Moved up to allow closing server if only 1 connection exits <br />
		int i = 1;
		 */
		try {	
			s = new ServerSocket(8189);
			while (true && s.isClosed() == false) {
				Socket incoming = s.accept();
				System.out.println("Spawning " + i);
				Runnable r = new ThreadedEchoHandler(incoming, i);
				Thread t = new Thread(r);
				t.start();
				i++;			}
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	/**
	 * Added to enable closing server when only one session is open and is terminated
	 * by the user <br />
	 */
	public static void closeConnection() {
		i -= 1;
	}
	
	public static int getOpenConnections() {
		return i;
	}
	
	public static String receiveText(int from, int to, int messageId){
		return letsTalk[from][to][messageId];
	}
	
	public static void sendText(int from, int to, int messageId, String message){
		letsTalk[to][from][messageId] = message;
	}
	
	public static void closeSession(int session) {
		if (JOptionPane.showConfirmDialog(null, "Connection " + (session) + " is attempting to close session!" + 
				System.lineSeparator() + "Do you want to shut down the Server?", "Close Server Request", 
				JOptionPane.YES_NO_OPTION) == 
				JOptionPane.YES_OPTION) {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * This class handles the client input for one server socket connection <br />
 */
class ThreadedEchoHandler implements Runnable {
	private Socket incoming;
	private int sessionId;
	private boolean isTalking = false;
	private int talkTo;
	private int messageTo = 0;
	private int messageFrom = 0;
	
	/**
	 * Constructs a handler. <br />
	 * @param incominSocket the incoming socket <br />
	 */
	public ThreadedEchoHandler(Socket incomingSocket, int session) {
		incoming = incomingSocket;
		sessionId = session;
	}
	
	public void run() {
		try (InputStream inStream = incoming.getInputStream(); 
				OutputStream outStream = incoming.getOutputStream()) {
			Scanner in = new Scanner(inStream, "UTF-8");	
			PrintWriter out = new PrintWriter(
						new OutputStreamWriter(outStream, "UTF-8"),
						true /* autoFlush */
						);
				
				out.println("Hello visitor No." + sessionId + "! Enter BYE to exit." + System.lineSeparator() +
						System.lineSeparator() + "Only the 1st 5 visitors can talk to each other! " +
						System.lineSeparator() + System.lineSeparator() + "To start a conversation type "
						+ "in TALKTO(n), n being the number of the other visitor! "+ System.lineSeparator() + 
						"Use the Enter key to see if the other party has commented before writing!");
				
				// echo client input
				boolean done = false;
				while (!done && in.hasNextLine()) {
					String line = in.nextLine();
					out.println("You: " + line);
					if (line.trim().equals("BYE")) {
						/**
						 * Added to enable closing server when only one session is open and is terminated
						 * by the user <br />
						 */
						out.println("Goodbye!");
						ThreadedEchoServer.closeConnection();
						if (ThreadedEchoServer.getOpenConnections() == 1) 
							ThreadedEchoServer.closeSession(sessionId);
						incoming.shutdownOutput();
						done = true;
					}
					if (isTalking == true) {
						if (line.trim().length() > 0) {
							ThreadedEchoServer.sendText(sessionId, talkTo, messageTo, line);
							messageTo++;
							if (messageTo == 1) messageTo = 0;
						}
						if (ThreadedEchoServer.receiveText(sessionId, talkTo, messageFrom) != null) {
							out.println("Visitor" + talkTo + ": " + 
									ThreadedEchoServer.receiveText(sessionId, talkTo, messageFrom));
							messageFrom++;
							if (messageFrom == 1) {
								messageFrom = 0;
								for (int j = 0; j < 2; j++)
									ThreadedEchoServer.sendText(talkTo, sessionId, j, null);
							}
						}
					}
					else if (line.trim().startsWith("TALKTO") && Integer.parseInt(line.substring(6)) != 
							sessionId) {
						isTalking = true;
						talkTo = Integer.parseInt(line.substring(6));
						out.println("Now Starting a conversation with User No." + talkTo);
					}
					else if (line.trim().startsWith("TALKTO") && Integer.parseInt(line.substring(6)) == 
							sessionId) {
						out.println("You know what they say about talking to yourself;D");
					}
					if (line.trim().startsWith("TALKTO") && Integer.parseInt(line.substring(6)) != 
							sessionId && Integer.parseInt(line.substring(6)) != 
							talkTo) {
						isTalking = true;
						talkTo = Integer.parseInt(line.substring(6));
						out.println("Now Starting a conversation with User No." + talkTo + "!" +
						System.lineSeparator() + System.lineSeparator() + "Remember to get back to "
								+ "your other conversation or say goodbye to them!");
					}
				}
				in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
