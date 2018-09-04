package chapter06;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter06 {
	public static final String CLASS_NAME = Chapter06.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String[] example = {
			"Listing 6.1 Time Line",
			"Listing 6.2 Local Dates",
			"Listing 6.3 Zoned Times",
			"Listing 6.4 Formatting"
			}; 
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
			Object exampleType = JOptionPane.showInputDialog(null, "Please Select an Example!", title,
					JOptionPane.QUESTION_MESSAGE, null, example, example[0]);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == example[0]) {
				/**
				 * {@link TimeLine} class Listing 6.1 <br />
				 * This program shows how to use the {@link Instant} and {@link Duration} classes for timing 
				 * two algorithms. <br />
				 */
				TimeLine.main(null);
				return;
			}
			else if (exampleType == example[1]) {
				/**
				 * {@link LocalDates} class Listing 6.2 <br />
				 * This program shows how to work with the {@link LocalDate} class. <br />
				 */
				LocalDates.main(null);
				return;
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link ZonedTimes} class Listing 6.3 <br />
				 * This program demonstrates the {@link ZonedDateTime} class. <br />
				 */
				ZonedTimes.main(null);
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link Formatting} class Listing 6.4 <br />
				 * This shows how to format and parse dates and times. <br />
				 */
				Formatting.main(null);
				return;
			}

			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?", "Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeApplication(CLASS_NAME);
	}
}

