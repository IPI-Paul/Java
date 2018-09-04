package chapter08;  // buttons2

import javax.swing.*;

/**
 * {@code ButtonFrame2} abstract class extends JFrame Listing 8.5 <br />
 * {@link CompilerTest} class Listing 8.7 <br />
 * {@link StringBuilderJavaSource} class extends SimpleJavaFileObject Listing 8.3 <br />
 * {@link ByteArrayJavaClass} class extends SimpleJavaFileObject Listing 8.4 <br />
 * <a href="../sourceFiles/properties/action-ch08.properties">action-ch08.properties</a>
 * Listing 8.6 <br />
 * {@link MapClassLoader} class extends ClassLoader Listing 8.8 <br /> 
 * A frame with a button panel. <br />
 * @version 1.00 2007-11-02
 * @author Cay Horstmann
 */
public abstract class ButtonFrame2 extends JFrame{
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	protected JPanel panel;
	protected JButton yellowButton;
	protected JButton blueButton;
	protected JButton redButton;
	
	protected abstract void addEventHandlers();
	
	public ButtonFrame2() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		panel = new JPanel();
		add(panel);
		
		yellowButton = new JButton("Yellow");
		blueButton = new JButton("Blue");
		redButton = new JButton("Red");
		
		panel.add(yellowButton);
		panel.add(blueButton);
		panel.add(redButton);
		
		addEventHandlers();
	}
}
