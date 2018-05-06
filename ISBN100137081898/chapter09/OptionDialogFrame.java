package chapter09;  // optionDialog

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.security.AccessControlException;
import java.util.*;
import javax.swing.*;
import ipi.*;

/**
 * OptionDialogFrame JFrame Listing 9.15
 * ButtonPanel JPanel Listing 9.16
 * A frame that contains settings for selecting various option dialogs.
 * @author Cay Horstmann
 */
public class OptionDialogFrame extends JFrame {
	private static final String MAIN_CLASS = "chapter09.Chapter09";
	private static String message = "";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ButtonPanel typePanel;
	private ButtonPanel messagePanel;
	private ButtonPanel messageTypePanel;
	private ButtonPanel optionTypePanel;
	private ButtonPanel optionsPanel;
	private ButtonPanel inputPanel;
	private String filePath;
	private Icon messageIcon;
	private String messageString = "Message";
	private Object messageObject = new Date();
	private Component messageComponent = new SampleComponent();
	
	public OptionDialogFrame() {
		// get images folder path
		try { 
			filePath = FolderPaths.getImagesFolder();
		} catch (AccessControlException ex) {
			filePath = ResourcePaths.getImagesResource();
		}
		//messageIcon = new ImageIcon(filePath + "blue-ball.gif");
		messageIcon = Images.loadImage(filePath + "blue-ball.gif");

		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(2, 3));
		
		typePanel = new ButtonPanel("Type", "Message", "Confirm", "Option", 
				"Input");
		messageTypePanel = new ButtonPanel("Message Type", "ERROR_MESSAGE", 
				"INFORMATION_MESSAGE", "WARNING_MESSAGE", "QUESTION_MESSAGE", 
				"PLAIN_MESSAGE");
		messagePanel = new ButtonPanel("Message", "String", "Icon", "Component", 
				"Other", "Object[]");
		optionTypePanel = new ButtonPanel("Confirm", "DEFAULT_OPTION", "YES_NO_OPTION", 
				"YES_NO_CANCEL_OPTION", "OK_CANCEL_OPTION");
		optionsPanel = new ButtonPanel("Option", "String[]", "Icon[]", "Object[]");
		inputPanel = new ButtonPanel("Input", "Text field", "Combo box");
		
		gridPanel.add(typePanel);
		gridPanel.add(messageTypePanel);
		gridPanel.add(messagePanel);
		gridPanel.add(optionTypePanel);
		gridPanel.add(optionsPanel);
		gridPanel.add(inputPanel);
		
		// add a panel with a Show button
		JPanel showPanel = new JPanel();
		JButton showButton = new JButton("Show");
		showButton.addActionListener(new ShowAction());
		showPanel.add(showButton);
		
		add(gridPanel, BorderLayout.CENTER);
		add(showPanel, BorderLayout.SOUTH);
		pack();
		Views.openWindowOpenerListener(this, MAIN_CLASS, message);
	}
	
	/**
	 * Gets the currently selected message.
	 * @return a string, icon, component, or object array, depending on the 
	 * Message panel selection	
	 */
	public Object getMessage() {
		String s = messagePanel.getSelection();
		if (s.equals("String")) return messageString;
		else if (s.equals("Icon")) return messageIcon;
		else if (s.equals("Component")) return messageComponent;
		else if (s.equals("Object[]")) return new Object[] {
				messageString, messageIcon, messageComponent, messageObject};
		else if (s.equals("Other")) return messageObject;
		else return null;
		}
	
	/**
	 * Gets the currently selected options.
	 * @return an array of strings, icons, or objects, depending on the option panel selection 
	 */
	public Object[] getOptions() {
		String s = optionsPanel.getSelection();
		if (s.equals("String[]")) return new String[] {
				"Yellow", "Blue", "Red"
			};
		else if (s.equals("Icon[]"))  
				try {
					return new Icon[] {
					new ImageIcon(filePath + "yellow-ball.gif"), 
					new ImageIcon(filePath + "blue-ball.gif"), 
					new ImageIcon(filePath + "red-ball.gif")
					};
				} catch (AccessControlException ex) {
					return new Icon[] {
					new ImageIcon(getClass().getResource(filePath + "yellow-ball.gif")), 
					new ImageIcon(getClass().getResource(filePath + "blue-ball.gif")), 
					new ImageIcon(getClass().getResource(filePath + "red-ball.gif"))
					};
				}
		else if (s.contentEquals("Object[]")) return new Object[] {
			messageString, messageIcon, messageComponent, messageObject
		};
		else return null;
	}
	
	/**
	 * Gets the selected message or option type
	 * @param panel the Message Type or Confirmation panel
	 * @return the selected XXX_MESSAGE or XXX_OPTION constant from the JOptionPane class  
	 */
	public int getType(ButtonPanel panel) {
		String s = panel.getSelection();
		try {
			return JOptionPane.class.getField(s).getInt(null);
		}
		catch (Exception e) {
			return -1;
		}
	}
	
	/**
	 * The action listener for the Show button shows a Confirm, Input, Message, or option dialog
	 * depending on the Type panel selection.  
	 */
	private class ShowAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (typePanel.getSelection().equals("Confirm")) JOptionPane.showConfirmDialog(
					OptionDialogFrame.this, getMessage(), "Title", getType(optionTypePanel),
					getType(messageTypePanel));
			else if (typePanel.getSelection().equals("Input")) {
				if (inputPanel.getSelection().equals("Text field")) JOptionPane.showInputDialog(
						OptionDialogFrame.this, getMessage(), "Title", getType(messageTypePanel));
				else JOptionPane.showInputDialog(OptionDialogFrame.this, 
						getMessage(), "Title", getType(messageTypePanel), null, new String[] {
								"Yellow", "Blue", "Red"
				}, "Blue");
			}
			else if (typePanel.getSelection().equals("Message")) JOptionPane.showMessageDialog(
					OptionDialogFrame.this, getMessage(), "Title", getType(messageTypePanel));
			else if (typePanel.getSelection().equals("Option")) JOptionPane.showOptionDialog(
					OptionDialogFrame.this, getMessage(), "Title", getType(optionTypePanel), 
					getType(messageTypePanel), null, getOptions(), getOptions()[0]);
		}
	}
}

/**
 * A component with a painted surface
 */
class SampleComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
		g2.setPaint(Color.YELLOW);
		g2.fill(rect);
		g2.setPaint(Color.RED);
		g2.draw(rect);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(10, 10);
	}
}