package chapter08;  // mouse

import javax.swing.*;

import ipi.Views;

/**
 * MouseFrame JFrame Listing 8.4 
 * MouseComponent JComponent Listing 8.5
 * A frame containing a panel for testing mouse operations
 * @author Cay Horstmann
 */
public class MouseFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MAIN_CLASS = "chapter08.Chapter08";
	private static String message = "";
	
	public MouseFrame() {
		add(new MouseComponent());
		// pack();
		setSize(300, 200);
		Views.openWindowOpenerListener(this, MAIN_CLASS, message);
	}
}
