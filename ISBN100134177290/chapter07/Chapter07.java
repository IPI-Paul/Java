package chapter07;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter07 {
	public static final String CLASS_NAME = Chapter07.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String[] example = {
			"Listing 7.1 Number Format Test",
			"Listing 7.2-3 Date Time Formatter Test",
			"Listing 7.4 Collation Test",
			"Listing 7.5-11 Retirement Calculator"
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
				 * {@link NumberFormatTest} class Listing 7.1 <br />
				 * {@link NumberFormatFrame} inner class extends {@link JFrame} <br />
				 * {@link GBC} GridBagConstraints copied from Listing 9.11 project ISBN100137081898 <br />
				 * {@link FontFrame} JFrame copied from Listing 9.10 project ISBN100137081898 <br />
				 * This program demonstrates formatting numbers under various locales. <br />
				 */
				NumberFormatTest.main(null);
				return;
			}
			else if (exampleType == example[1]) {
				/**
				 * {@link DateTimeFormatterTest} class Listing 7.2 <br />
				 * {@link DateTimeFormatterFrame} inner class extends {@link JFrame} <br /> 
				 * {@link EnumCombo<T>} class extends {@link JComboBox}&lt;String> listing 7.3 <br />
				 * This program demonstrates formatting dates under various locales. <br />
				 */
				DateTimeFormatterTest.main(null);
				return;
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link CollationTest} class Listing 7.4 <br />
				 * {@link CollationFrame} inner class extends JFrame <br />
				 * This program demonstrates collating strings under various locales. <br />
				 */
				CollationTest.main(null);
				return;
			}
			else if (exampleType ==  example[3]) {
				/**
				 * {@link Retire} class Listing 7.5 <br />
				 * {@link RetireFrame} inner class extends JFrame <br />
				 * {@link RetireInfo} inner class <br /> 
				 * {@link RetireComponent} inner class extends JComponent <br />
				 * {@link LocaleCombo}&lt;T> class extends {@link JComboBox}&lt;String> copied from EnumCombo in listing 7.3 <br />
				 * {@link RetireResources} class extends java.util.ListResourceBundle Listing 7.6 <br />
				 * {@link RetireResources_de} class extends java.util.ListResourceBundle Listing 7.7 <br />
				 * {@link RetireResources_zh} class extends java.util.ListResourceBundle Listing 7.8 <br />
				 * <a href="../sourceFiles/properties/RetireStrings.properties">
				 * RetireStrings.properties</a> <br />
				 * <a href="../sourceFiles/properties/RetireStrings_de.properties">
				 * RetireStrings_de.properties</a> <br />
				 * <a href="../sourceFiles/properties/RetireStrings_zh.properties">
				 * RetireStrings_zh.properties</a> <br />
				 * This program shows a retirement calculator. The UI is displayed in English, German and
				 * Chinese. <br />
				 */
				Retire.main(null);
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

