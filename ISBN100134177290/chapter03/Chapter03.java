package chapter03;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter03 {
	public static final String CLASS_NAME = Chapter03.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String[] example = {
			"Listing 3.1 XML Tree Viewer",
			"Listing 3.2-6 Grid Bag Test with XML",
			"Listing 3.7 XPath Tester",
			"Listing 3.8 SAX Test",
			"Listing 3.9 StAX Test",
			"Listing 3.10-12 XML Writer Test",
			"Listing 3.13-15 Transform Test"
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
				 * {@link TreeViewer} class Listing 3.1 <br />
				 * {@link DOMTreeFrame} inner class extends JFrame <br />
				 * {@link DOMTreeModel} inner class implements TreeModel <br />
				 * {@link DOMTreeCellRenderer} inner class extends {@link DefaultTreeCellRenderer} <br />
				 * This program displays an XML document as a tree. <br />
				 */
				TreeViewer.main(null);
				return;
			}
			else if (exampleType == example[1]) {
				/**
				 * {@link GridBagTest} class Listing 3.2 <br />
				 * {@link FontFrame} inner class extends JFrame <br />
				 * {@link GridBagPane} class extends JPanel Listing 3.3 <br />
				 * <a href="fontdialog.xml">
				 * fontdialog.xml
				 * </a> Listing 3.4 <br />
				 * <a href="gridbag.dtd">
				 * gridbag.dtd
				 * </a> Listing 3.5 <br />
				 * <a href="gridbag.xsd">
				 * gridbag.xsd
				 * </a> listing 3.6 <br />
				 * This program shows how to use an XML file to describes gridbag layout. <br />
				 */
				GridBagTest.main(null);
				return;
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link XPathTester} class Listing 3.7 <br />
				 * {@link XPathFrame} inner class extends JFrame <br />
				 * This program evaluates {@link XPath} expressions. <br />
				 */
				XPathTester.main(null);
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link SAXTest} class Listing 3.8 <br />
				 * this program demonstrates how to use a SAX parser. The program prints all hyperlinks 
				 * of an XHTML web page. <br />
				 * Usage java chapter03.SAXTest URL <br />
				 */
				try {
					SAXTest.main(null);
				} catch (Exception ex) {
				}
				return;
			}
			else if (exampleType == example[4]) {
				/**
				 * {@link StAXTest} class Listing 3.9 <br />
				 * This program demonstrates how to use a StAX parser. The program prints all hyperlinks of
				 * an XHTML web page. <br />
				 * Usage: java chapter03.StAXTest URL <br /> 
				 */
				try {
					StAXTest.main(null);
				} catch (Exception ex) {
				}
				return;
			}
			else if (exampleType == example[5]) {
				/**
				 * {@link XMLWriterTest} class Listing 3.10 <br />
				 * {@link XMLWriteFrame} class Listing 3.11 <br />
				 * {@link RectangleComponent} class extends {@link JComponent} Listing 3.12 <br />
				 * This program shows how to write an XML file. It saves a file describing a modern 
				 * drawing in SVG format. <br /> 
				 */
				XMLWriterTest.main(null);
				return;
			}
			else if (exampleType == example[6]) {
				/**
				 * {@link TransformTest} class Listing 3.15 <br />
				 * {@link EmployeeReader} inner class implements {@link XMLReader} <br />
				 * <a href="makehtml.xsd">makehtml.xsd</a> Listing 3.13 <br />
				 * <a href="makeprop.xsl">makeprop.xsl</a> Listing 3.14 <br />
				 * This program demonstrates XSL transformations. It applies a transformation to a 
				 * set of employee records. The records are stored in the file 
				 * <a href="sourceFiles/dat/employee.dat">employee.dat</a> and turned 
				 * into XML format. Specify the stylesheet on the command line, e.g.
				 * java chapter03.TransformTest chapter03/makeprop.xsl <br /> 
				 */
				try {
					TransformTest.main(null);
				} catch (Exception ex) {
				}
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

