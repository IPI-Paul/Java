package chapter10; // resource

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import ipi.*;

/**
 * ResourceTest class Listing 10.1
 * @version 1.4 2007-04-30
 * @author Cay Horstmann
 */
public class ResourceTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new ResourceTestFrame();
				frame.setTitle("ResourceTest");
				// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}			
		});
	}
}

/**
 * A frame that loads image and text resources.
 */
class ResourceTestFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// get folder paths
	String imageResource = ResourcePaths.getImagesResource();
	String textResource = ResourcePaths.getTextResource();
	String htmlResource = ResourcePaths.getHTMLResource();
	
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	private static final String MAIN_CLASS = "chapter10.Chapter10";
	private static String message = "";
	
	public ResourceTestFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		URL aboutURL = getClass().getResource(imageResource + "about.gif");
		Image img = new ImageIcon(aboutURL).getImage();
		setIconImage(img);
		
		JLabel label = new JLabel();
		InputStream stream = getClass().getResourceAsStream(htmlResource + "about.html");
		Scanner in = new Scanner(stream);
		while (in.hasNext()) {
			label.setText(label.getText() + in.nextLine());
		}
		in.close();
		add(label, BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea();
		stream = getClass().getResourceAsStream(textResource + "about.txt");
		in = new Scanner(stream);
		while (in.hasNext()) {
			textArea.append(in.nextLine() + "\n");
		}
		in.close();
		add(textArea);
		
		/**
		 * Confirms exit of application if true exits, if false opens Chapter10 class
		 */
		Views.openWindowOpenerListener((JFrame) ResourceTestFrame.this, MAIN_CLASS, message);
	}
}

