package chapter11; // dnd

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import ipi.Views;

/**
 * {@code SwingDnDTest} class Listing 11.20 <br />
 * {@link SampleComponents} class Listing 11.21 <br />
 * {@link SwingDnDFrame} extends JFrame <br />
 * This program demonstrates the basic Swing support for drag and drop.
 * @version 1.11 2016-05-10
 * @author Cay Horstmann
 */
public class SwingDnDTest {
	private static final String MAIN_CLASS = "chapter11.Chapter11";
	private static String message = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new SwingDnDFrame();
			frame.setTitle("Swing DnD Test");
			/**
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			*/
			frame.setVisible(true);
			Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
		});
	}
}

class SwingDnDFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public SwingDnDFrame() {
		JTabbedPane tabbedPane = new JTabbedPane();
		JList<String> list = SampleComponents.list();
		tabbedPane.addTab("List", list);
		JTable table = SampleComponents.table();
		tabbedPane.addTab("Table", table);
		JTree tree = SampleComponents.tree();
		tabbedPane.addTab("Tree", tree);
		JFileChooser fileChooser = new JFileChooser();
		tabbedPane.addTab("File Chooser", fileChooser);
		JColorChooser colorChooser = new JColorChooser();
		tabbedPane.addTab("Color Chooser", colorChooser);
		
		final JTextArea textArea = new JTextArea(4, 40);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(), "DragtText here"));
		
		JTextField textField = new JTextField("Drag color here");
		textField.setTransferHandler(new TransferHandler("background"));
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textArea.setText("");
			}
		});
		
		tree.setDragEnabled(true);
		table.setDragEnabled(true);
		list.setDragEnabled(true);
		fileChooser.setDragEnabled(true);
		colorChooser.setDragEnabled(true);
		textField.setDragEnabled(true);
		
		add(tabbedPane, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		pack();
	}
}
