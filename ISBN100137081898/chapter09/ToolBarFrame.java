package chapter09; // ToolBarFrame

import java.awt.*;
import java.awt.event.*;
import java.security.AccessControlException;

import javax.swing.*;
import ipi.*;

/**
 * Displays a tool bar frame with buttons
 */
public class ToolBarFrame extends JFrame {
	private static final String MAIN_CLASS = "chapter09.Chapter09";
	private static String message = "";
	private String filePath;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int DEFAULT_WIDTH = 300;
	private static int DEFAULT_HEIGHT = 200;
	
	public ToolBarFrame() {
		Views.openWindowOpenerListener(ToolBarFrame.this, MAIN_CLASS, message);
		// get images folder path
		try {
			filePath = FolderPaths.getImagesFolder();
		} catch (AccessControlException ex) {
			filePath = ResourcePaths.getImagesResource();
		}
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// create a top level menu 
		JMenu colorMenu = new JMenu("Color");
		
		// create a menu bar and add top level menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(colorMenu);

		final JPanel panel = new JPanel();
		add(panel);
		
		// Create toolbar buttons
		//ImageIcon blueBall = new ImageIcon(filePath + "blue-ball.gif");
		ImageIcon blueBall = Images.loadImage(filePath + "blue-ball.gif");
		JButton blueButton = new JButton(blueBall);
		//JButton yellowButton = new JButton(new ImageIcon(filePath + "yellow-ball.gif"));
		JButton yellowButton = new JButton(Images.loadImage(filePath + "yellow-ball.gif"));
		//JButton redButton = new JButton(new ImageIcon(filePath + "red-ball.gif"));
		JButton redButton = new JButton(Images.loadImage(filePath + "red-ball.gif"));
		//JButton exitButton = new JButton(resizeImage(filePath + "crosses.gif", blueBall.getIconWidth()));
		JButton exitButton = new JButton(Images.resizeImage(filePath + "crosses.gif", blueBall.getIconWidth()));
		
		// Add tooltips to each button
		blueButton.setToolTipText("Blue background");
		yellowButton.setToolTipText("Yellow background");
		redButton.setToolTipText("Red background");
		exitButton.setToolTipText("Exit");
		
		// Add exit button Action
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.exit(0);
				Views.closeWindow(ToolBarFrame.this);
			}
		});
		
		// Add ActionListener for toolbar buttons
		class ColorAction implements ActionListener {
			private Color backgroundColour;
			
			public ColorAction(Color c) {
				backgroundColour = c;
			}
			
			public void actionPerformed(ActionEvent event) {
				panel.setBackground(backgroundColour);
			}
		}
		
		// create button actions
		//ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		ColorAction redAction = new ColorAction(Color.RED);
		
		// associate actions with buttons
		blueButton.addActionListener(new ColorAction(Color.BLUE));
		yellowButton.addActionListener(yellowAction);
		redButton.addActionListener(redAction);
		
		// Add a new Toolbar
		String titleString = "Toolbar Buttons";
		JToolBar bar = new JToolBar(titleString);
		bar.add(blueButton);
		bar.add(yellowButton);
		bar.add(redButton);
		bar.addSeparator();
		bar.add(exitButton);
		
		add(bar, BorderLayout.NORTH);
	}
}
