package chapter09;  // dialog

import java.awt.event.*;
import javax.swing.*;
import ipi.*;

/**
 * DialogFrame JFrame Listing 9.17 
 * AboutDialog JDialog Listing 9.18
 * A frame with a menu whose file->About action shows a dialog.
 * @author Cay Horstmann
 */
public class DialogFrame extends JFrame {
	private static final String MAIN_CLASS = "chapter09.Chapter09";
	private static String message = "";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private AboutDialog dialog;
	
	public DialogFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// Construct a File menu.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		// Add About and Exit Menu items.

		// The About item shows the About dialog.
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (dialog == null) // first time
					dialog = new AboutDialog(DialogFrame.this);
				dialog.setVisible(true);  // pop up dialog
			}
		});
		fileMenu.add(aboutItem);
		
		// The Exit item exits the program.
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.exit(0);
				Views.closeWindow(DialogFrame.this);
			}
		});
		fileMenu.add(exitItem);
		Views.openWindowOpenerListener(this, MAIN_CLASS, message);
	}
}
