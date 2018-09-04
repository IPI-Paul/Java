package chapter09;  // signed

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

/**
 * {@code FileReadApplet} class Listing 9.17 <br />
 * <a href="FileReadApplet.html">FileReadApplet.html</a> <br />
 * <a href="../sourceFiles/permissions/applet-ch09.policy">applet-ch09.policy</a> <br />
 * @version 1.13 2016-05-10
 * @author Cay Horstmann
 */
public class FileReadApplet extends JApplet {
	private static final long serialVersionUID = 1L;
	private JTextField fileNameField;
	private JTextArea fileText;
	
	public void init() {
		EventQueue.invokeLater(() -> {
			fileNameField = new JTextField(20);
			JPanel panel = new JPanel();
			panel.add(new JLabel("File name:"));
			panel.add(fileNameField);
			JButton openButton = new JButton("Open");
			panel.add(openButton);
			ActionListener listener = event -> loadFile(fileNameField.getText());
			fileNameField.addActionListener(listener);
			openButton.addActionListener(listener);
			add(panel, "North");
			fileText = new JTextArea();
			add(new JScrollPane(fileText), "Center");
		});
	}
	
	/**
	 * Loads the contents of a file into the text area. <br />
	 * @param filename the file name <br />
	 */
	public void loadFile(String filename) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/documents"));
		chooser.showOpenDialog(null);
		if (chooser.getSelectedFile() != null) {
			fileNameField.setText(chooser.getSelectedFile().getPath());
		}
		filename = fileNameField.getText();
		fileText.setText("");
		try {
			fileText.append(new String(Files.readAllBytes(Paths.get(filename))));
		} catch (IOException ex) {
			fileText.append(ex + "\n");
		} catch (SecurityException ex) {
			fileText.append("I am sorry, but I cannot do that.\n");
			fileText.append("\n");
			ex.printStackTrace();
		}
	}
}
