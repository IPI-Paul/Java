package chapter07; // simpleFrame

import java.awt.*;
import javax.swing.*;
import ipi.*;

/**
 * SimpleFrameTest class Listing 7.1 
 * This program uses a simple JFrame from Swing
 * @version 1.32 2007-06-12
 * @author Cay Horstmann
 */
public class SimpleFrameTest {
	private static final String MAIN_CLASS = "chapter07.Chapter07";
	private static String message = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SimpleFrame frame = new SimpleFrame();
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
			}
		});
	}
}

class SimpleFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public SimpleFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}