package chapter05;

import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 5
 * @author Paul I Ighofose
 */
public class Chapter05 {
	private static String title = "Example Of";

	private static final String MANAGER_TEST = "5.1-3 Manager Test";
	private static final String PERSON_TEST = "Listing 5.4-7 Person Test";
	private static final String EQUALS_TEST = "Listing 5.8-10 Equals Test";
	private static final String ARRAY_LIST_TEST = "Listing 5.11 Array List Test";
	private static final String ENUM_TEST = "Listing 5.12 Enum Test";
	private static final String REFLECTION_TEST = "Listing 5.13 Reflection Test";
	private static final String OBJECT_ANALYZER_TEST = "Listing 5.14-15 Object Analyzer Test";
	private static final String COPY_OF_TEST = "Listing 5.16 Copy Of Test";
	private static final String METHOD_TABLE_TEST = "Listing 5.17 Method Table Test";
	private static final String[] example = {MANAGER_TEST, PERSON_TEST, EQUALS_TEST, ARRAY_LIST_TEST,      
			ENUM_TEST, REFLECTION_TEST, OBJECT_ANALYZER_TEST, COPY_OF_TEST, METHOD_TABLE_TEST}; 
	private static int runAgain;
	
	public static void main(String[] args) {
		Threads.setDefaultLocale();

		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null,"Please Select an Example!",title,
					JOptionPane.QUESTION_MESSAGE,null,example,MANAGER_TEST);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == ARRAY_LIST_TEST) {
				/**
				 * ArrayListTest class Listing 5.11
				 */
				ArrayListTest.main(null);
				return;
			}
			else if (exampleType == COPY_OF_TEST) {
				/**
				 * CopyOfTest class Listing 5.16
				 */
				CopyOfTest.main(null);
				return;
			}
			else if (exampleType == ENUM_TEST) {
				/**
				 * EnumTest class Listing 5.12
				 */
				EnumTest.main(null);
				return;
			}
			else if (exampleType == EQUALS_TEST) {
				/**
				 * EqualsTest class Listing 5.8
				 * Employee2 class Listing 5.9
				 * Manager1 Employee Listing 5.10
				 */
				EqualsTest.main(null);
				return;
			}
			else if (exampleType == MANAGER_TEST) {
				/**
				 * ManagerTest class Listing 5.1
				 * Employee class Listing 5.2
				 * Manager class Listing 5.3
				 */
				ManagerTest.main(null);
				return;
			}
			else if (exampleType == METHOD_TABLE_TEST) {
				try {
					/**
					 * MethodTableTest class Listing 5.17
					 */
					MethodTableTest.main(null);
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (exampleType == OBJECT_ANALYZER_TEST) {
				/** 
				 * ObjectAnalyzerTest class Listing 5.14
				 * ObjectAnalyzer class Listing 5.15
				 */
				ObjectAnalyzerTest.main(null);
				return;
			}
			else if (exampleType == PERSON_TEST) {
				/**
				 * PersonTest class Listing 5.4
				 * Person abstract class Listing 5.5
				 * Employee1 Person Listing 5.6
				 * Student class Listing 5.7
				 */
				PersonTest.main(null);
				return;
			}
			else if (exampleType == REFLECTION_TEST) {
				/**
				 * ReflectionTest class Listing 5.13
				 */
				ReflectionTest.main(args);
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
