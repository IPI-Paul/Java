package chapter08;

import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 8
 * @author Paul I Ighofose
 */
public class Chapter08 {
	private static String title = "Example Of";

	private static final String BUTTON_FRAME = "Listing 8.1 Button Frame";
	private static final String PLAF_FRAME = "Listing 8.2 Panel Look-And-Feel Frame";
	private static final String ACTION_FRAME = "Listing 8.3 Action Frame";
	private static final String MOUSE_FRAME = "Listing 8.4-5 Mouse Listener Frame";
	private static final String[] example = {BUTTON_FRAME, PLAF_FRAME, 
			ACTION_FRAME, MOUSE_FRAME}; 
	private static int runAgain;
	
	public static void main(String[] args) {
		Threads.setDefaultLocale();
		
		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null,"Please Select an Example!",title,
					JOptionPane.QUESTION_MESSAGE,null,example,BUTTON_FRAME);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == ACTION_FRAME) {
				/**
				 * ActionFrame JFrame Listing 8.3
				 * ColorAction inner class
				 */
				newFrame(new ActionFrame(), "Key Action Frame"); 
				return;
			}
			else if (exampleType == BUTTON_FRAME) {
				/**
				 * ButtonFrame JFrame Listing 8.1
				 * ColorAtction inner class
				 */
				newFrame(new ButtonFrame(), "Button Frame"); 
				return;
			}
			else if (exampleType == MOUSE_FRAME) {
				/**
				 * MouseFrame JFrame Listing 8.4
				 * MouseComponent JComponent Listing 8.5
				 */
				newFrame(new MouseFrame(), "Mouse Listener Frame");
				return;
			}
			else if (exampleType == PLAF_FRAME) {
				/**
				 * PlafFrame JFrame Listing 8.2
				 */
				newFrame(new PlafFrame(), "Panel Look-and-Feel Frame");
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

	/**
	 * @param frame variable frame to load
	 * @param title of frame 
	 */
	private static void newFrame(JFrame frame, String title) {
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
