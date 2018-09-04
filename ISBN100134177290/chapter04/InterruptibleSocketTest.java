package chapter04; // interruptible

import java.awt.*;
/**
import java.awt.event.*;
 */
import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.channels.*;
import javax.swing.*;

import ipi.Views;

/**
 * {@code InterruptibleSocketTest} class Listing 4.5 <br />
 * {@link InterruptibleSocketFrame} inner class extends JFrame <br />
 * {@link TestServerHandler} private inner class implements Runnable <br />
 * {@link TestServer} private inner class implements Runnable <br />
 * This program shows how to interrupt a socket channel. <br />
 * @version 1.04 2016-04-27
 * @author Cay Horstmann
 */
public class InterruptibleSocketTest {
	private static final String MAIN_CLASS = "chapter04.Chapter04";
	private static String message = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new InterruptibleSocketFrame();
			frame.setTitle("Interruptible Socket Test");
			/**
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 */
			frame.setVisible(true);
			Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
		});
	}
}

class InterruptibleSocketFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Scanner in;
	private JButton interruptibleButton;
	private JButton blockingButton;
	private JButton cancelButton;
	private JTextArea messages;
	private TestServer server;
	private Thread connectThread;
	
	public InterruptibleSocketFrame() {
		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		
		final int TEXT_ROWS = 20;
		final int TEXT_COLUMNS = 60;
		messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		add(new JScrollPane(messages));
		
		interruptibleButton = new JButton("Interruptible");
		blockingButton = new JButton("Blocking");
		
		northPanel.add(interruptibleButton);
		northPanel.add(blockingButton);
		
		interruptibleButton.addActionListener(event -> {
			interruptibleButton.setEnabled(false);
			blockingButton.setEnabled(false);
			cancelButton.setEnabled(true);
			connectThread = new Thread(() -> {
				try {
					connectInterruptibly();
				} catch (IOException e) {
					messages.append("\nInterruptibleSocketTest.connectInterruptibly: " + e);
				}
			});
			connectThread.start();
		});
		
		blockingButton.addActionListener(event -> {
			interruptibleButton.setEnabled(false);
			blockingButton.setEnabled(false);
			cancelButton.setEnabled(true);
			connectThread = new Thread(() -> {
				try {
					connectBlocking();
				} catch (IOException e) {
					messages.append("\nInterruptibleSocketTest.connectBlocking: "+ e);
				}
			});
			connectThread.start();
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(false);
		northPanel.add(cancelButton);
		cancelButton.addActionListener(event -> {
			connectThread.interrupt();
			cancelButton.setEnabled(false);
		});
		server = new TestServer();
		new Thread(server).start();
		pack();
	}
	
	/**
	 * Connects to the server, using intteruptible I/O. <br />
	 */
	public void connectInterruptibly() throws IOException {
		messages.append("Interruptible:\n");
		try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189))) {
			in = new Scanner(channel, "UTF-8");
			while (!Thread.currentThread().isInterrupted()) {
				messages.append("Reading ");
				if (in.hasNextLine()) {
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		} finally {
			EventQueue.invokeLater(() -> {
				messages.append("Channel closed\n");
				interruptibleButton.setEnabled(true);
				blockingButton.setEnabled(true);
			});
		}
	}
	
	/**
	 * Connects to the test server, using blocking I/O. <br />
	 */
	public void connectBlocking() throws IOException {
		messages.append("Blocking:\n");
		try (Socket sock = new Socket("localhost", 8189)) {
			in= new Scanner(sock.getInputStream(), "UTF-8");
			while (!Thread.currentThread().isInterrupted()) {
				messages.append("Reading ");
				if (in.hasNextLine()) {
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		} finally {
			EventQueue.invokeLater(() -> {
				messages.append("Socket closed\n");
				interruptibleButton.setEnabled(true);
				blockingButton.setEnabled(true);
			});
		}
	}
	
	/**
	 * A multithreaded server that listens to port 8189 and sends numbers to the client, simulating
	 * a hanging server after 10 numbers. <br />
	 */
	class TestServer implements Runnable {
		public void run( ) {
			try(ServerSocket s = new ServerSocket(8189)) {
				while (true) {
					Socket incoming = s.accept();
					Runnable r = new TestServerHandler(incoming);
					Thread t = new Thread(r);
					t.start();
				}
			} catch (IOException e) {
				messages.append("\nTestServer.run: " + e);
			}
		}
	}
	
	/**
	 * This class handles the client input for one server socket connection. <br />
	 */
	class TestServerHandler implements Runnable {
		private Socket incoming;
		private int counter;
		
		/**
		 * constructs a handler. <br />
		 * @param i the incoming socket <br />
		 */
		public TestServerHandler(Socket i) {
			incoming = i;
		}
		
		public void run() {
			try {
				try {
					OutputStream outStream = incoming.getOutputStream();
					PrintWriter out = new PrintWriter(
							new OutputStreamWriter(outStream, "UTF-8"),
							true /* autoflush */
							);
					while (counter < 100) {
						counter ++;
						if (counter <= 10) out.println(counter);
						Thread.sleep(100);
					}
				} finally {
					incoming.close();
					messages.append("Closing server\n");
				}
			} catch (Exception e) {
				messages.append("\nTestServerHandler.run: " + e);
			}
		}
	}
}
