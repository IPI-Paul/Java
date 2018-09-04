package chapter04;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter04 {
	public static final String CLASS_NAME = Chapter04.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String[] example = {
			"Listing 4.1 Socket Test", 
			"Listing 4.2 Internet Address Test",
			"Listing 4.3 Echo Server",
			"Listing 4.4 Threaded Echo Server",
			"Listing 4.5 Interruptible Socket Test",
			"Listing 4.5 URL Connection Test", 
			"Listing 4.7 POST Test to a Web Server",
			"LIsting 4.8 Mail Test using JavaMail"
			}; 
	private static int runAgain;
	private static String title = "Example Of";

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			Views.runMethod(CLASS_NAME, args[0]);
			return;
		}

		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null, "Please Select an Example!", title,
					JOptionPane.QUESTION_MESSAGE, null, example, example[0]);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == example[0]) {
				/**
				 * {@link SocketTest} class Listing 4.1 <br />
				 * This program makes a socket connection to the atomic clock in Boulder Colorado and prints
				 * the time that the server sends. <br />
				 */
				try {
					SocketTest.main(null);
				} catch (IOException e) {
				}
				return;
			}
			else if (exampleType== example[1]) {
				/**
				 * {@link InetAddressTest} class Listing 4.2 <br />
				 * This program demonstrates the {@link InetAddress} cache. Supply a host name as command-line
				 * argument, or run without command-line arguments to see the address of the local host. <br />
				 */
				try {
					InetAddressTest.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link EchoServer} class Listing 4.3 <br />
				 * This program implements a simple server that listens to port 8189 and echoes back
				 * all client input. <br />
				 */
				try {
					EchoServer.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link ThreadedEchoServer} class Listing 4.4 <br />
				 * This program implements a multi-threaded server that listens to port 8189 and echoes back
				 * all client input. <br /> 
				 */
				ThreadedEchoServer.main(null);
				return;
			}
			else if (exampleType == example[4]) {
				/**
				 * {@link InterruptibleSocketTest} class Listing 4.5 <br />
				 * {@link InterruptibleSocketFrame} inner class extends JFrame <br />
				 * This program shows how to interrupt a socket channel. <br />
				 */
				InterruptibleSocketTest.main(null);
				return;
			}
			else if (exampleType == example[5]) {
				/**
				 * {@link URLConnectionTest} class Listing 4.6 <br />
				 * This program connects to an URL and displays the response header data and the first 10 
				 * lines of the requested data. <br />
				 * Supply the URL and an optional username and password (for HTTP basic authentication)
				 * on the command line. <br /> 
				 */
				URLConnectionTest.main(null);
				return;
			}
			else if (exampleType == example[6]) {
				/**
				 * {@link PostTest} class Listing 4.7 <br />
				 * <a href="../sourceFiles/properties/posttest-ch04.properties">posttest-ch04.properties</a> 
				 * contains url and variables for the action script <br />
				 * <a href="../sourceFiles/properties/posttest1-ch04.properties">posttest1-ch04.properties</a> contains 
				 * url and variables for the action script <br />
				 * This program demonstrates how to use the {@link URLConnection} class for a POST
				 * request. <br />
				 */
				try {
					PostTest.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[7]) {
				/**
				 * {@link MailTest} class Listing 4.8 <br />
				 * <a href="../sourceFiles/properties/mailtest-ch04.properties">mailtest-ch04.properties</a> contains 
				 * smtp connection settings <br />
				 * This program shows how to use <code>JavaMail</code> to send mail messages. <br />
				 */
				try {
					MailTest.main(null);
				} catch (Exception ex) {
				}
				return;
			}

			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?", "Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeApplication(CLASS_NAME);
	}
}

