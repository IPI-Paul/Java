package chapter08; // buttons3

import java.awt.*;
import javax.swing.*;
/**
import runtimeAnnotations.*;
 */

/**
 * {@code ButtonFrame3} class extends JFrame Listing 8.10 <br />
 * {@link ActionListenerInstaller} class Listing 8.9 <br />
 * {@link ActionListenerFor} annotated interface Listing 8.11 <br />
 * A frame with a button panel. <br />
 * @version 1.00 2004-008-17
 * @author Cay Horstmann
 */
public class ButtonFrame3 extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	private JPanel panel;
	private JButton yellowButton;
	private JButton blueButton;
	private JButton redButton;
	
	public ButtonFrame3() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		panel = new JPanel();
		add(panel);
		
		yellowButton = new JButton("Yellow");
		blueButton = new JButton("Blue");
		redButton = new JButton("Red");
		
		panel.add(yellowButton);
		panel.add(blueButton);
		panel.add(redButton);
		
		ActionListenerInstaller.processAnnotations(this);
	}
	
	@ActionListenerFor(source = "yellowButton")
	public void yellowBackground() {
		panel.setBackground(Color.YELLOW);
	}
	
	@ActionListenerFor(source = "blueButton")
	public void blueBackground() {
		panel.setBackground(Color.BLUE);
	}
	
	@ActionListenerFor(source = "redButton")
	public void redBackground() {
		panel.setBackground(Color.RED);
	}
}
