package chapter10;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter10 {
	public static final String CLASS_NAME = Chapter10.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String[] example = {
			"Listing 10.1 List Frame",
			"Listing 10.2-3 Long List Frame",
			"Listing 10.4 Font Cell Renderer",
			"Listing 10.5 Table Test",
			"Listing 10.6 Investment Table",
			"Listing 10.7 Planet Table Frame",
			"Listing 10.8-11 Table Cell Render Frame",
			"Listing 10.12 Simple Tree",
			"Listing 10.13 Tree Edit Test",
			"Listing 10.14-15 Class Tree",
			"Listing 10.16-18 Object Inspector",
			"Listing 10.19 Colour Frame",
			"Listing 10.20-22 Format Test",
			"Listing 10.23-24 Spinner Test",
			"Listing 10.25 Editor Pane Test",
			"Listing 10.26 Progress Bar Test",
			"Listing 10.27 Progress Monitor Test",
			"Listing 10.28 Progress Monitor Input Stream Test",
			"Listing 10.29 Split Pane Test",
			"Listing 10.30 Tabbed Pane Test",
			"Listing 10.31 Internal Frame Test",
			"Listing 10.32 Color Frame Layer Test"
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
				 * {@link ListFrame} class Listing 10.1 <br />
				 * This frame contains a word list and a label that shows a sentence made up form the chosen 
				 * words. Note that you can select multiple words with Ctrl+click and Shift+click. <br />
				 */
				frame = Views.newFrame(new ListFrame(), "List Frame");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[1]) {
				/**
				 * {@link LongListFrame] class Listing 10.2 <br />
				 * {@link WordListModel} class Listing 10.3 <br />
				 * This frame contains a long word list and a label that shows a sentence made up from the 
				 * chosen word. <br />
				 */
				frame = Views.newFrame(new LongListFrame(), "Long List");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link ListRenderingTest} class Core Java 8th edition <br />
				 * {link ListRenderingFrame} inner class extends JFrame <br />
				 * {@link FontCellRenderer] class Listing 10.4 <br />
				 * This program demonstrates the use of cell renderers in a list box. <br />
				 */
				ListRenderingTest.main(null);
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link TableTest} class Listing 10.5 <br />
				 * This program demonstrates how to show a simple table. <br />  
				 */
				TableTest.main(null);
				return;
			}
			else if (exampleType == example[4]) {
				/**
				 * {@link InvestmentTable} class Listing 10.6 <br />
				 * This program shows how to build a table from a table model. <br />
				 */
				InvestmentTable.main(null);
				return;
			}
			else if (exampleType == example[5]) {
				/**
				 * {@link PlanetTableFrame1} class extends JFrame Listing 10.7 <br />
				 * This frame contains a table of planet data. <br />
				 */
				frame = Views.newFrame(new PlanetTableFrame1(), "Planet Table");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[6]) {
				/**
				 * {@link TableCellRenderFrame} class extends JFrame Listing 10.8 <br />
				 * {@link PlanetTableModel} class implements TableModel Listing 10.9 <br />
				 * {@link ColorTableCellRenderer} class extends JPanel implements TableCellRenderer Listing 10.10 <br />
				 * {@link ColorTableCellEditor} class extends AbstractCellEditor implements TableCellEditor  Listing 10.11 <br />
				 * This frame contains a table of planet data. <br />
				 */
				frame = Views.newFrame(new TableCellRenderFrame(), "Table Cell Render");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[7]) {
				/**
				 * {@link SimpleTreeFrame} class extends JFrame Listing 10.12 <br />
				 * This frame contains a simple tree that displays a manually constructed tree model. <br />
				 */
				frame = Views.newFrame(new SimpleTreeFrame(), "Simple Tree");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[8]) {
				/**
				 * {@link TreeEditFrame} class extends JFrame Listing 10.13 <br />
				 * A frame with a tree and buttons to edit the tree. <br />
				 */
				frame = Views.newFrame(new TreeEditFrame(), "Tree Edit Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[9]) {
				/**
				 * {@link ClassTreeFrame} class extends JFrame Listing 10.14 <br />
				 * {@link ClassNameTreeCellRenderer} class extends DefaultTreeCellRenderer Listing 10.15 <br />
				 * This frame displays the class tree, a text field and a "Add" button to add more classes
				 * into the tree. <br />
				 */
				frame = Views.newFrame(new ClassTreeFrame(), "Class Tree");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[10]) {
				/**
				 * {@link ObjectInspectorFrame} class extends JFrame Listing 10.16 <br />
				 * {@link ObjectTreeModel} class implements TreeModel Listing 10.17 <br />
				 * {@link Variable} class Listing 10.18 <br />
				 * This frame holds the object tree. 
				 */
				frame = Views.newFrame(new ObjectInspectorFrame(), "Object Inspector");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;				
			}
			else if (exampleType == example[11]) {
				/**
				 * {@link ColorFrame} class extends JFrame Listing 10.19 <br />
				 * A frame with three text fields to set the background color.
				 */
				frame = Views.newFrame(new ColorFrame(), "Colour Frame");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[12]) {
				/**
				 * {@link FormatTestFrame} class extends JFrame Listing 10.20 <br />
				 * {@link IntFilter} class extends DocumentFilter Listing 10.21 <br />
				 * {@link IPAddressFormatter} class extends DefaultFormatter Listing 10.22 <br />
				 * A frame with a collection of formatted text fields and a button that displays the field 
				 * values.
				 */
				frame = Views.newFrame(new FormatTestFrame(), "Format Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[13]) {
				/**
				 * {@link SpinnerFrame} class extends JFrame Listing 10.23 <br />
				 * {@link PermutationSpinnerModel} class extends AbstractSpinnerModel Listing 10.24 <br />
				 * A frame with a panel that contains several spinners and a button that displays the 
				 * spinner values. 
				 */
				frame = Views.newFrame(new SpinnerFrame(), "Spinner Testr");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[14]) {
				/**
				 * {@link EditorPaneFrame} class extends JFrame Listing 10.25 <br />
				 * This frame contains an editor pane, a text field and button to enter a URL and load a 
				 * document, and a Back button to return to a previously loaded document.
				 */
				frame = Views.newFrame(new EditorPaneFrame(), "Editor Pane Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[15]) {
				/**
				 * {@link ProgressBarFrame} class extends JFrame Listing 10.26 <br />
				 * {@link SimulatedActivity} inner class extends SwingWorker&lt;Void, Integer> <br /> 
				 * A frame that contains a button to launch a simulated activity, a progress bar, and a text
				 * area for the activity output.
				 */
				frame = Views.newFrame(new ProgressBarFrame(), "Progress Bar Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[16]) {
				/**
				 * {@link ProgressMonitorFrame} class extends JFrame Listing 10.27 <br />
				 * A frame that contains a button to launch a simulated activity and a text area for the 
				 * activity output.
				 */
				frame = Views.newFrame(new ProgressMonitorFrame(), "Progress Monitor Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[17]) {
				/**
				 * {@link TextFrame} class extends JFrame Listing 10.28 <br />
				 * A frame with a menu to load a text file and a text area to display its contents. The 
				 * text area is constructed when the file loaded and set as the content pane of the frame 
				 * when the loading is complete. That avoids flicker during loading.
				 */
				frame = Views.newFrame(new TextFrame(), "Progress Monitor Input Stream Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[18]) { 
				/**
				 * {@link SplitPaneFrame} class extends JFrame Listing 10.29 <br />
				 * {@link Planet} class <br />
				 * This frame consists of two nested split panes to demonstrate planet images and data.
				 */
				frame = Views.newFrame(new SplitPaneFrame(), "Split Pane Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[19]) {
				/**
				 * {@link TabbedPaneFrame} class extends JFrame Listing 10.30 <br />
				 * This frame shows a tabbed pane and radio buttons to switch between wrapped and 
				 * scrolling tab layout.
				 */
				frame = Views.newFrame(new TabbedPaneFrame(), "Tabbed Pane Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[20]) {
				/**
				 * {@link DesktopFrame} class extends JFrame Listing 10.31 <br />
				 * This desktop frame contains editor panes that show HTML documents.
				 */
				frame = Views.newFrame(new DesktopFrame(), "Internal Frame Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
				return;
			}
			else if (exampleType == example[21]) {
				/**
				 * {@link ColorFrame1} class extends JFrame Listing 10.32 <br />
				 * A frame with three text fields to set the background color.
				 */
				frame = Views.newFrame(new ColorFrame1(), "Color Frame Layer Test");
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, CLASS_NAME, message);
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

