package chapter12;

import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 12
 * @author Paul I Ighofose
 */
public class Chapter12 {
	public static final String CLASS_NAME = Chapter12.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String PAIR_TEST_1	= "Listing 12.1 Generic Methods String Pair Test";
	private static final String PAIR_TEST_2 = "Listing 12.2 Generic Methods Date Pair Test";
	private static final String PAIR_TEST_3	= "Listing 12.3 Wildcard Generic Methods String Pair Test";
	private static final String GENERIC_REFLECTION_TEST = "Listing 12.4 Generic Reflection Test";
	private static final String[] example = {PAIR_TEST_1, PAIR_TEST_2, PAIR_TEST_3, GENERIC_REFLECTION_TEST}; 
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
					JOptionPane.QUESTION_MESSAGE, null, example, PAIR_TEST_1);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == PAIR_TEST_1) {
				/**
				 * PairTest1 class Listing 12.1 <br />
				 * ArrayAlg class inner class <br />
				 * Pair<T> class generic inner class <br />
				 */
				PairTest1.main(null);
				return;
			}
			else if (exampleType == PAIR_TEST_2) {
				/**
				 * PairTest2 class Listing 12.2 <br />
				 * ArrayAlg1 class inner class <br />
				 * Pair1<T> generic inner class <br />
				 */
				PairTest2.main(null);
				return;
			}
			else if (exampleType == PAIR_TEST_3) {
				/**
				 * PairTest3 class Listing 12.3 <br />
				 * PairAlg class inner class <br />
				 * Pair2<T> generic inner class <br />
				 * Employee class  <br />
				 * Manager class <br />		
				 */
				PairTest3.main(null);
				return;
			}
			else if (exampleType == GENERIC_REFLECTION_TEST) {
				/**
				 * GenericReflectionTest class Listing 12.4 <br />
				 */
				GenericReflectionTest.main(null);
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
