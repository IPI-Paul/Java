package chapter08; // action

import java.awt.*;
import java.awt.event.*;
import java.security.AccessControlException;
import ipi.*;
import javax.swing.*;

/**
 * ActionFrame JFrame Listing 8.3 
 * A frame with a panel that demonstrates color change actions.
 * @author Cay Horstmann
 */
public class ActionFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MAIN_CLASS = "chapter08.Chapter08";
	private static String message = "";
	
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public ActionFrame() {
		// get images folder path
		String filePath;
		try {
			filePath = FolderPaths.getImagesFolder();
		} catch (AccessControlException ex) {
			filePath = ResourcePaths.getImagesResource(); 
		}

		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		buttonPanel = new JPanel();
		
		// get file names
		String[] fileName = {"blue-ball.gif", "red-ball.gif", "yellow-ball.gif"};
		
		// define actions 
		//Action yellowAction = new ColorAction("Yellow", new ImageIcon(filePath + fileName[2]), Color.YELLOW);
		//Action blueAction = new ColorAction("Blue", new ImageIcon(filePath + fileName[0]), Color.BLUE);
		//Action redAction = new ColorAction("Red", new ImageIcon(filePath + fileName[1]), Color.RED);
		Action yellowAction = new ColorAction("Yellow", Images.loadImage(filePath + fileName[2]), Color.YELLOW);
		Action blueAction = new ColorAction("Blue", Images.loadImage(filePath + fileName[0]), Color.BLUE);
		Action redAction = new ColorAction("Red", Images.loadImage(filePath + fileName[1]), Color.RED);
		
		// add buttons for these actions
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(blueAction));
		buttonPanel.add(new JButton(redAction));
		
		// add panel to frame
		add(buttonPanel);
		
		// associate the Y, B, and R keys with names
		InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
		
		// associate the names with the actions 
		ActionMap amap = buttonPanel.getActionMap();
		amap.put("panel.yellow", yellowAction);
		amap.put("panel.blue", blueAction);
		amap.put("panel.red", redAction);
		Views.openWindowOpenerListener(this, MAIN_CLASS, message);
	}
	
	public class ColorAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/** 
		 * Constructs a color action.
		 * @param name the name to show on the button
		 * @param icon the icon to display on the button
		 * @param c the background color
		 */
		public ColorAction(String name, Icon icon, Color c) {
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
			putValue("color", c);
		}
		
		public void actionPerformed(ActionEvent event) {
			Color c = (Color) getValue("color");
			buttonPanel.setBackground(c);
		}
	}
}
