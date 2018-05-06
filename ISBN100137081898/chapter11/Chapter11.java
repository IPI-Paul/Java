package chapter11;

import java.security.AccessControlException;

import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 11
 * @author Paul I Ighofose
 */
public class Chapter11 {
	public static final String CLASS_NAME = Chapter11.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String STACK_TRACE_TEST = "Listing 11.1 Stack Trace Test";
	private static final String LOGGING_IMAGE_VIEWER = "Listing 11.2 Logging Image Viewer";
	private static final String EVENT_TRACER = "Listing 11.3 Event Tracer";
	private static final String ROBOT_TEST = "Listing 11.4 Robot Test";
	private static final String BUGGY_BUTTON_TEST = "Listing 11.5 Buggy Button Test";
	private static final String[] example = {STACK_TRACE_TEST, LOGGING_IMAGE_VIEWER, EVENT_TRACER, 
			ROBOT_TEST, BUGGY_BUTTON_TEST}; 
	private static int runAgain;
	private static String title = "Example Of";

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			Views.runMethod(CLASS_NAME, args[0]);
			return;
		}

		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null,"Please Select an Example!",title,
					JOptionPane.QUESTION_MESSAGE,null,example,STACK_TRACE_TEST);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == STACK_TRACE_TEST) {
				/**
				 * StackTraceTest class Listing 11.1
				 */
				StackTraceTest.main(null);
				return;
			}
			else if (exampleType == LOGGING_IMAGE_VIEWER) {
				/**
				 * LoggingImageViewer class Listing 11.2
				 * WindowHandler inner class <br />
				 * ImageViewerFrame inner class <br />
				 * FileOpenListener inner class <br />
				 */
				LoggingImageViewer.main(null);
				return;
			}
			else if (exampleType == EVENT_TRACER) {
				/**
				 * EventTracer class Listing 11.3
				 */
				try {
					frame = Views.newFrame(new JFrame(), "Event Tracer");
					frame.setSize(300, 500);
					EventTracer tracer = new EventTracer();
					tracer.add(frame);
					Views.openWindowOpenerListener(frame, CLASS_NAME, title);
					frame.setVisible(true);
					return;
				} catch (AccessControlException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, Threads.getEventTracerJnlpMessage(title));
				}
			}
			else if (exampleType == ROBOT_TEST) {
				/**
				 * RobotTest class Listing 11.4 <br />
				 * ButtonFrame JFrame class <br />
				 * ImageFrame inner class <br />
				 */
				RobotTest.main(null);
				return;
			}
			else if (exampleType == BUGGY_BUTTON_TEST) {
				/**
				 * BuggyButtonTest class Listing 11.5 <br />
				 * BuggyButtonFrame JFrame inner class <br />
				 * BuggyButtonPanel JPanel inner class <br />
				 * ButtonListener ActionListener private class <br />
				 */
				BuggyButtonTest.main(null);
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
	 * 
	 */
}
