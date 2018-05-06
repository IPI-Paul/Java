package chapter07;  // notHelloWorld

import javax.swing.*;
import ipi.*;
import java.awt.*;

/**
 * NotHelloWorld class Listing 7.3
 * This program demonstrates painting a JFrame component
 * @version 1.32 2007-06-12 
 * @author Cay Horstmann
 */
public class NotHelloWorld {
	private static final String MAIN_CLASS = "chapter07.Chapter07";
	private static String message = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new NotHelloWorldFrame();
				frame.setTitle("Not Hello World");
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
			}
		});
	}
}

/** 
 * A frame that contains a message panel
 */
class NotHelloWorldFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotHelloWorldFrame() {
		add(new NotHelloWorldComponent());
		pack();
	}
}

/**
 * A component that displays a message.
 */
class NotHelloWorldComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public void paintComponent(Graphics g) {
		g.drawString("Not a Hello, World program", MESSAGE_X, MESSAGE_Y);
	}
	
	public Dimension getPreferredSize() { 
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}