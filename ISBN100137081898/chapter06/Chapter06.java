package chapter06;

import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 6
 * @author Paul I Ighofose
 */
public class Chapter06 {
	private static String title = "Example Of";

	private static final String EMPLOYEE_SORT_TEST = "Listing 6.1-2 Employee Sort Test";
	private static final String CLONE_TEST = "Listing 6.3-4 Clone Test";
	private static final String TIMER_TEST = "Listing 6.5 Timer Test";
	private static final String INNER_CLASS_TEST = "Listing 6.6 Inner Class Test";
	private static final String ANONYMOUS_INNER_CLASS_TEST = "Listing 6.7 Anonymous Inner Class Test";
	private static final String STATIC_INNER_CLASS_TEST = "Listing 6.8 Static Inner Class Test";
	private static final String PROXY_TEST = "Listing 6.9 Proxy Test";
	private static final String[] example = {EMPLOYEE_SORT_TEST, CLONE_TEST, TIMER_TEST, 
			INNER_CLASS_TEST, ANONYMOUS_INNER_CLASS_TEST, STATIC_INNER_CLASS_TEST, PROXY_TEST}; 
	private static int runAgain;

	public static void main(String[] args) {
		Threads.setDefaultLocale();
		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null,"Please Select an Example!",title,
					JOptionPane.QUESTION_MESSAGE,null,example,EMPLOYEE_SORT_TEST);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == ANONYMOUS_INNER_CLASS_TEST) {
				/**
				 * AnonymousInnerClassTest class Listing 6.7
				 * TalkingClock1 inner class
				 */
				AnonymousInnerClassTest.main(null);
				return;
			}
			else if (exampleType == CLONE_TEST) {
				/**
				 * CloneTest class Listing 6.3
				 * Employee1 Cloneable Listing 6.4
				 */
				CloneTest.main(null);
				return;
			}
			else if (exampleType == EMPLOYEE_SORT_TEST) {
				/**
				 * EmployeeSortTest class Listing 6.1
				 * Employee Comparable Listing 6.2
				 */
				EmployeeSortTest.main(null);
				return;
			}
			else if (exampleType == INNER_CLASS_TEST) {
				/**
				 * InnerClassTest class Listing 6.6
				 * TalkingClock inner class
				 */
				InnerClassTest.main(null);
				return;
			}
			else if (exampleType == PROXY_TEST) {
				/**
				 * ProxyTest class Listing 6.9
				 * TraceHandler inner class
				 */
				ProxyTest.main(null);
				return;
			}
			else if (exampleType == STATIC_INNER_CLASS_TEST) {
				/**
				 * StaticInnerClassTest class Listing 6.8
				 * ArrayAlg inner class
				 */
				StaticInnerClassTest.main(null);
				return;
			}
			else if (exampleType == TIMER_TEST) {
				/**
				 * TimerTest class Listing 6.5
				 * TimePrinter inner class
				 */
				TimerTest.main(null);
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
