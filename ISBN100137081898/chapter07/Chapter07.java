package chapter07;

import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 7
 * @author Paul I Ighofose
 */
public class Chapter07 {
	private static String title = "Example Of";

	private static final String SIMPLE_FRAME_TEST = "Listing 7.1 Simple Frame Test";
	private static final String SIZED_FRAME_TEST = "Listing 7.2 Sized Frame Test";
	private static final String NOT_HELLO_WORLD = "Listing 7.3 Not Hello World";
	private static final String DRAW_TEST = "Listing 7.4 Draw Test";
	private static final String FONT_TEST = "Listing 7.5 Font Test";
	private static final String IMAGE_TEST = "Listing 7.6 Image Test";
	private static final String[] example = {SIMPLE_FRAME_TEST, SIZED_FRAME_TEST, NOT_HELLO_WORLD,  
			DRAW_TEST, FONT_TEST, IMAGE_TEST}; 
	private static int runAgain;

	public static void main(String[] args) {
		Threads.setDefaultLocale();
		
		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null,"Please Select an Example!",title,
					JOptionPane.QUESTION_MESSAGE,null,example,SIMPLE_FRAME_TEST);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == DRAW_TEST) {
				/**
				 * DrawTest class Listing 7.4
				 * DrawFrame inner class
				 * DrawComponent inner class
				 */
				DrawTest.main(null);
				return;
			}
			else if (exampleType == FONT_TEST) {
				/**
				 * FontTest class Listing 7.5
				 * FontFrame inner class
				 * FontComponent inner class
				 */
				FontTest.main(null);
				return;
			}
			else if (exampleType == IMAGE_TEST) {
				/**
				 * ImageTest class Listing 7.6
				 * ImageFrame inner class
				 * ImageComponent inner class
				 */
				ImageTest.main(null);
				return;
			}
			else if (exampleType == NOT_HELLO_WORLD) {
				/**
				 * NotHelloWorld class Listing 7.3
				 * NotHelloFrame inner class
				 * NotHelloComponent inner class
				 */
				NotHelloWorld.main(null);
				return;
			}
			else if (exampleType == SIMPLE_FRAME_TEST) {
				/**
				 * SimpleFrameTest class Listing 7.1
				 * SimpleFrame inner class
				 */
				SimpleFrameTest.main(null);
				return;
			}
			else if (exampleType == SIZED_FRAME_TEST) {
				/**
				 * SizedFrameTest class Listing 7.2
				 * SizedFrame inner class
				 */
				SizedFrameTest.main(null);
				return;
			}
			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?", "Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeApplication();
	}

}
