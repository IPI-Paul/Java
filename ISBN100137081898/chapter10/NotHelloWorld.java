package chapter10;  // applet

import java.awt.*;
import javax.swing.*;

/**
 * NotHelloWorld JApplet Listing 10.3
 * NotHelloWorld.html HTML
 * @version 1.23 2012-05-14
 * @author Cay Horstmann
 */
public class NotHelloWorld extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JLabel label = new JLabel("Not a Hello, World applet", SwingConstants.CENTER);
				add(label);
			}
		});
	}
}
