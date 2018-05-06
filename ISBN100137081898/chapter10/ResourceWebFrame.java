package chapter10; // webstart

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.*;

import ipi.Views;

/**
 * ResourceWebFrame JFrame Listing 10.1extended
 * A frame that loads image and text resources.
 */
class ResourceWebFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	private static final String MAIN_CLASS = "chapter10.Chapter10";
	private static String message = "";
	
	public ResourceWebFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		URL aboutURL = getClass().getResource("/sourceFiles/images/about.gif");
		Image img = new ImageIcon(aboutURL).getImage();
		setIconImage(img);
		
		JLabel label = new JLabel();
		InputStream stream = getClass().getResourceAsStream("/sourceFiles/html/about.html");
		Scanner in = new Scanner(stream);
		while (in.hasNext()) {
			label.setText(label.getText() + in.nextLine());
		}
		in.close();
		add(label, BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea();
		stream = getClass().getResourceAsStream("/sourceFiles/text/about.txt");
		in = new Scanner(stream);
		while (in.hasNext()) {
			textArea.append(in.nextLine() + "\n");
		}
		in.close();
		add(textArea);
		Views.openWindowOpenerListener(this, MAIN_CLASS, message);
	}
}

