package chapter09;  // permissions

import java.awt.*;
import javax.swing.*;

/**
 * {@Code PermissionTest} class Listing 9.6 <br />
 * {@link PermissionTestFrame} inner class extends JFrame <br />
 * {@link WordCheckTextArea} inner class extends JTextArea <br /> 
 * {@link WordCheckPermission} class extends Permission Listing 9.5 <br />
 * This program demonstrates the custom WordCheckPersmission. <br />
 * @version 1.04 2016-05-10
 * @author Cay Horstmann
 */
public class PermissionTest {
	public static void main(String[] args) {
		System.setProperty("java.security.policy", "sourceFiles/permissions/PermissionTest-ch09.policy");
		System.setSecurityManager(new SecurityManager());
		EventQueue.invokeLater(() -> {
			JFrame frame = new PermissionTestFrame();
			frame.setTitle("Permission Test");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

/** 
 * This frame contains a text field for inserting words into a text area that is protected from 
 * "bad Words". <br />
 */
class PermissionTestFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private WordCheckTextArea textArea;
	private static final int TEXT_ROWS = 20;
	private static final int TEXT_COLUMNS = 60;
	
	public PermissionTestFrame() {
		textField = new JTextField(20);
		JPanel panel = new JPanel();
		panel.add(textField);
		JButton openButton = new JButton("Insert");
		panel.add(openButton);
		openButton.addActionListener(event -> insertWords(textField.getText()));
		
		add(panel, BorderLayout.NORTH);
		
		textArea = new WordCheckTextArea();
		textArea.setRows(TEXT_ROWS);
		textArea.setColumns(TEXT_COLUMNS);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		pack();
	}
	
	/**
	 * Tries to insert words into the text area. Displays a dialog if the attempt fails. <br />
	 * @param words the words to insert <br />
	 */
	public void insertWords(String words) {
		try { 
			textArea.append(words + "\n");
			textField.setText("");
		} catch (SecurityException ex) {
			JOptionPane.showMessageDialog(this, "I am sorry, but I cannot do that.");
			ex.printStackTrace();
		}
	}
}

/**
 * A text area whose append method makes a security check to see that no bad words are added. <br />
 */
class WordCheckTextArea extends JTextArea {
	private static final long serialVersionUID = 1L;
	public void append(String text) {
		WordCheckPermission p = new WordCheckPermission(text, "insert");
		SecurityManager manager = System.getSecurityManager();
		if (manager != null) manager.checkPermission(p);
		super.append(text);
	}
}


