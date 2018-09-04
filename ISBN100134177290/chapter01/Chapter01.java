package chapter01;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter01 {
	public static final String CLASS_NAME = Chapter01.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String[] example = {
			"Listing 1.1 Count Long Words from a Stream", 
			"Listing 1.2 Creating Streams", 
			"Listing 1.3 Stream Optional Test", 
			"Listing 1.4 Collecting Results from a Stream", 
			"Listing 1.5 Collecting Stream Results into Maps", 
			"Listing 1.6 Down Stream Collectors", 
			"Listing 1.7 Primitive Type Streams", 
			"Listing 1.8 Parallel Streams",
			"List all Sub Directories of a Folder"
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
				 * {@link CountLongWords} class Listing 1.1 <br />
				 * This program demonstrates a stream that is created with the <code>stream</code> or 
				 * <code>parallelStream</code> method. The <code>filter</code> method transforms it, and 
				 * <code>count</code> is the terminal operation <br />
				 */
				try {
					CountLongWords.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[1]) {
				/**
				 * {@link CreatingStreams} class Listing 1.2 <br />
				 * This program demonstrates the various ways of crating a stream.
				 */
				try {
					CreatingStreams.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link OptionalTest} class Listing 1.3 <br />
				 * This program demonstrates the <code>optional</code> API <br />
				 */
				try {
					OptionalTest.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link CollectingResults} class Listing 1.4 <br />
				 * This program demonstrates how to collect elements from a stream. <br />
				 */
				try {
					CollectingResults.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[4]) {
				/**
				 * {@link CollectingIntoMaps} class Listing 1.5 <br />
				 * {@link Person} static inner class <br />
				 * This program demonstrates collecting stream results into maps.
				 */
				try {
					CollectingIntoMaps.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[5]) {
				/**
				 * {@link DownStreamCollectors} class Listing 1.6 <br />
				 * City static inner class <br />
				 * This program demonstrates downstream collectors. <br />
				 */
				try {
					DownStreamCollectors.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[6]) {
				/**
				 * {@link PrimitiveTypeStreams} class Listing 1.7 <br />
				 * This program gives examples for the API of primitive type streams. <br />
				 */
				try {
					PrimitiveTypeStreams.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[7]) {
				/**
				 * {@link ParallelStreams} class Listing 1.8 <br />
				 * This program demonstrates how to work with parallel streams.
				 */
				try {
					ParallelStreams.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[8]) {
				Methods.listDirectories();
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

