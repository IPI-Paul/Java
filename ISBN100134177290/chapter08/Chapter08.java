package chapter08;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter08 {
	public static final String CLASS_NAME = Chapter08.class.getName();
	public static String message = "";
	public static JFrame frame;
	private static Loaders file = new Loaders();
	
	private static final String[] example = {
			"Listing 8.1-2 Scripting Test",
			"Listing 8.3-8 Compiler Test",
			"Listing 8.9-11 Annotated Button Frame",
			"Listing 8.12-14 Set Test & Entry Logger",
			"Listing 8.14 Set Test Only",
			"Listing 8.15 Entry Logging Agent"
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
				 * {@link ScriptTest} class Listing 8.1 <br />
				 * {@link ButtonFrame} extends JFrame Listing 8.2 <br />
				 * This program demonstrates how to use scripting for Java GUI programming. <br />
				 */
				ScriptTest.main(null);
				return;
			}
			else if (exampleType == example[1]) {
				/**
				 * {@link CompilerTest} class Listing 8.7 <br />
				 * {@link StringBuilderJavaSource} class extends SimpleJavaFileObject Listing 8.3 <br />
				 * {@link ByteArrayJavaClass} class extends SimpleJavaFileObject Listing 8.4 <br />
				 * {@link ButtonFrame2} abstract class extends JFrame Listing 8.5 <br />
				 * <a href="../sourceFiles/properties/action-ch08.properties">action-ch08.properties</a>
				 * Listing 8.6 <br />
				 * {@link MapClassLoader} class extends ClassLoader Listing 8.8 <br /> 
				 * The buildSource method in this program builds up this code and places it into a 
				 * StringBuilderJavaSource object. That object is passed to the Java compiler. <br /> 
				 */
				try {
					CompilerTest.main(null);
				} catch (IOException | ClassNotFoundException ex) {
				}
				return;
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link ButtonFrame3} class extends JFrame Listing 8.10 <br />
				 * {@link ActionListenerInstaller} class Listing 8.9 <br />
				 * {@link ActionListenerFor} annotated interface Listing 8.11 <br />
				 * This program demonstrates the mechanics of annotating a program and of analysing the 
				 * annotations. <br />
				 */
				frame = Views.newFrame(new ButtonFrame3(), "Annotated Button Frame");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link SetTest} class Listing 8.14 <br />
				 * {@link EntryLogger} class extends ClassVisitor Listing 8.12 <br />
				 * {@link Item} class Listing 8.13 <br />
				 * This program demonstrated the power of bytecode engineering. Annotations are used to add 
				 * directives to a program, and a bytecode editing tool picks up the directives and modifies 
				 * the virtual machine instructions. <br />
				 */
				try {
					List<String> params = new ArrayList<>();
					params.add("javac");
					params.add("chapter08/Item.java");
					ProcessBuilder pb = new ProcessBuilder(params);
					pb.start();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				try {
					file.setChoice("D", "", "chapter08/", "Item.class", StandardCharsets.UTF_8, "", "",  
							"For Annotated Class File");
					String[] itemPath = {file.getFilePath()};
					EntryLogger.main(itemPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
				SetTest.main(args);
				Threads.waitFor(3);
			}
			else if (exampleType == example[4]) {
				/**
				 * {@link SetTest} class Listing 8.14 <br />
				 * {@link Item} class Listing 8.13 <br />
				 * This program demonstrates the original output before bytecode engineering. <br />
				 */
				try {
					List<String> params = new ArrayList<>();
					params.add("javac");
					params.add("chapter08/Item.java");
					ProcessBuilder pb = new ProcessBuilder(params);
					pb.start();
					params.clear();
					params.add("java");
					params.add("chapter08.SetTest");
					pb = new ProcessBuilder(params);
					pb.inheritIO();
					pb.start();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			else if  (exampleType == example[5]) {
				/**
				 * {@link SetTest} class Listing 8.14 <br />
				 * {@link EntryLogger} class extends ClassVisitor Listing 8.12 <br />
				 * {@link Item} class Listing 8.13 <br />
				 * This program demonstrates the power of bytecode engineering using a javaagent to load a 
				 * bytecode editing tool. Annotations are used to add directives to a program, and a  
				 * bytecode editing tool picks up the directives and modifies the virtual machine 
				 * instructions. <br />
				 */
				try {
					List<String> params = new ArrayList<>();
					params.add("java");
					params.add("-javaagent:webapps/EntryLoggingAgent.jar=chapter08/Item");
					params.add("-classpath");
					params.add(".;drivers/asm/*;drivers/*");
					params.add("chapter08.SetTest");
					ProcessBuilder pb = new ProcessBuilder(params);
					pb.inheritIO();
					pb.start();
					Threads.waitFor(5);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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

