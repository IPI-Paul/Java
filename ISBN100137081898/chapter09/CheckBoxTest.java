package chapter09;  // checkBox

import java.awt.*;
import javax.swing.*;
import ipi.*;

/**
 * CheckBoxTest class Listing 9.3
 * CheckBoxFrame JFrame
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
 */
public class CheckBoxTest {
	private static final String MAIN_CLASS = "chapter09.Chapter09";
	private static String message = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new CheckBoxFrame();
				frame.setTitle("Check Box Test");
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
			}
		});
	}

	/**
	 * A checkbox frame
	 */
}
