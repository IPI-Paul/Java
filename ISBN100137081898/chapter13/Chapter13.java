package chapter13;

import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 13
 * @author Paul I Ighofose
 */
public class Chapter13 {
	public static final String CLASS_NAME = Chapter13.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String LINKED_LIST_TEST = "Listing 13.1 Linked List Test";
	private static final String SET_TEST = "Listing 13.2 Set Test";
	private static final String TREE_SET_TEST = "Listing 13.3-4 Tree Set Test";
	private static final String PRIORITY_QUEUE_TEST = "Listing 13.5 Proirity Queue Test";
	private static final String MAP_TEST = "Listing 13.6 Map Test";
	private static final String SHUFFLE_TEST = "Listing 13.7 Shuffle Test";
	private static final String SIEVE = "Listing 13.8 Sieve";
	private static final String SIEVE_CPP = "Listing 13.9 Sieve C++ Comparison";
	private static final String[] example = {LINKED_LIST_TEST, SET_TEST, TREE_SET_TEST, PRIORITY_QUEUE_TEST, 
			MAP_TEST, SHUFFLE_TEST, SIEVE, SIEVE_CPP}; 
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
					JOptionPane.QUESTION_MESSAGE, null, example, LINKED_LIST_TEST);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == LINKED_LIST_TEST) {
				/**
				 * LinkedListTest class Listing 13.1 <br />
				 */
				LinkedListTest.main(null);
				return;
			}
			else if (exampleType == SET_TEST) {
				/**
				 * SetTest class Listing 13.2 <br />
				 */
				SetTest.main(null);
				return;
			}
			else if (exampleType == TREE_SET_TEST) {
				/**
				 * TreeSetTest class Listing 13.3 <br />
				 * Item class Listing 13.4 <br />		
				 */
				TreeSetTest.main(null);
				return;
			}
			else if (exampleType == PRIORITY_QUEUE_TEST) {
				/**
				 * PriorityQueueTest class Listing 13.5 <br />
				 */
				PriorityQueueTest.main(null);
				return;
			}
			else if (exampleType == MAP_TEST) {
				/**
				 * MapTest class Listing 13.6<br />
				 * Employee class 
				 */
				MapTest.main(null);
				return;
			}
			else if (exampleType == SHUFFLE_TEST) {
				/**
				 * ShuffleTest class Listing 13.7 <br />
				 */
				ShuffleTest.main(null);
				return;
			}
			else if (exampleType == SIEVE) {
				/** 
				 * Sieve class Listing 13.8 <br />
				 */
				Sieve.main(null);
				return;
			}
			else if (exampleType == SIEVE_CPP) {
				/**
				 * sieve.exe C++ file Listing 13.9
				 */
				String exe = "sieve.exe";
				Threads.runCppProcess(Chapter13.class.getResourceAsStream(exe), exe, CLASS_NAME, title);
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
