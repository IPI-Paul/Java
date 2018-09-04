package chapter02;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter02 {
	public static final String CLASS_NAME = Chapter02.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String[] example = {
			"Listing 2.1 Text File I/O Test",
			"Listing 2.2 Random Access Test",
			"Listing 2.3 Object Stream Test",
			"Listing 2.4 Serail Clone Test",
			"Listing 2.5 Memory Map Test CRC32",
			"Listing 2.6 Regular Expression Test (regex)",
			"Listing 2.7 Href Match using a regular Expression (regex)",
			"Read Zip File using ZipInputStream and ZipFile",
			"Read Zip File using FileSystem",
			"List All Sub Directories in a Folder using WalkFileTree"
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
				 * {@link TextFileTest} class Listing 2.1 <br />
				 * Employee inner class <br />
				 * This program demonstrates storing an array of <code>Employee</code> records in a text 
				 * file <br />
				 */
				try {
					TextFileTest.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if(exampleType == example[1]) {
				/**
				 * {@link RandomAccessTest} class Listing 2.2 <br />
				 * Employee1 inner class <br />
				 * DataIO inner class <br />
				 * This program writes three records into a data file and then reads them from the file 
				 * in reverse order <br> 
				 */
				try {
					RandomAccessTest.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link ObjectStreamTest} class Listing 2.3 <br />
				 * Employee2 Serializable inner class <br />
				 * Manager Serializable inner class extends Employee2 <br />
				 * This program saves and reloads a network of <code>Employee</code> and <code>Manager</code>
				 * objects (some of which share the same employee as a secretary). <br />
				 */
				try {
					ObjectStreamTest.main(null);
				} catch (IOException | ClassNotFoundException ex) {
				}
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link SerailCloneTest} class Listing 2.4 <br />
				 * {@link SerialCloneable} inner class implements {@link Cloneable}, {@link Serializable} <br />
				 * {@link Employee3} inner class extends {@link SerialCloneable} <br />
				 * This program demonstrates how to clone a serializable class by serializing it to an 
				 * output stream and reading it back in. <br />
				 */
				try {
					SerailCloneTest.main(null);
				} catch (CloneNotSupportedException ex) {
				}
				return;
			}
			else if (exampleType == example[4]) {
				/**
				 * {@link MemoryMapTest} class Listing 2.5 <br />
				 * This program computes the 32-bit cyclic redundancy checksum (CRC) of a file four 
				 * different was <br />
				 */
				try {
					MemoryMapTest.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[5]) {
				/**
				 * {@link RegexTest} class Listing 2.6 <br />
				 * This program prompts for a pattern test expression matching. Enter a pattern and 
				 * strings to match, or hit Cancel to exit. If the pattern contains groups, the grouping
				 * boundaries are displayed. <br />
				 */
				try {
					RegexTest.main(null);
				} catch (PatternSyntaxException ex) {
				}
				return;
			}
			else if (exampleType == example[6]) {
				/**
				 * {@link HrefMatch} class Listing 2.7 <br />
				 * This program displays all URLs in a web page by matching a regular expression
				 * that describes the &lt;a href=...> HTML tag. You can start the program as <br />
				 * <em>java chapter02.HrefMatch URL</em> <br />
				 */
				HrefMatch.main(null);
				return;
			}
			else if (exampleType == example[7]) {
				Methods.readZipInputStream();
				return;
			}
			else if (exampleType == example[8]) {
				Methods.readZipFileSystem();
				return;
			}
			else if (exampleType == example[9]) {
				Methods.listSubDirectories();
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

