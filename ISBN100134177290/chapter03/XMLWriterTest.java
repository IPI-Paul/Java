package chapter03;  // write

import java.awt.*;
import javax.swing.*;
import ipi.*;

/**
 * {@code XMLWriterTest} class Listing 3.10 <br />
 * {@link XMLWriteFrame} class Listing 3.11 <br />
 * {@link RectangleComponent} class extends {@link JComponent} Listing 3.12 <br />
 * This program shows how to write an XML file. It saves a file describing a modern 
 * drawing in SVG format. <br /> 
 * @version 1.12 2016-04-27
 * @author Cay Horstmann
 */
public class XMLWriterTest {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new XMLWriteFrame();
			frame.setTitle("XML Write Test");
			/**
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 */
			frame.setVisible(true);
			Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
		});
	}
}
