package chapter09;  // circleLayout

import javax.swing.*;
import ipi.*;

/**
 * CircleLayoutFrame JFrame Listing 9.14
 * CircleLayout LayoutManager listing 9.13
 * A frame that shows buttons arrange along a circle.
 * @author Cay Horstmann
 */
public class CircleLayoutFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MAIN_CLASS = "chapter09.Chapter09";
	private static String message = "";
	
	public CircleLayoutFrame() {
		setLayout(new CircleLayout());
		add(new JButton("Yellow"));
		add(new JButton("Blue"));
		add(new JButton("Red"));
		add(new JButton("Green"));
		add(new JButton("Orange"));
		add(new JButton("Fuchsia"));
		pack();
		setSize(300, 200);
		Views.openWindowOpenerListener(this, MAIN_CLASS, message);
	}
}
