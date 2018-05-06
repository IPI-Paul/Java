package chapter02;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import ipi.*;
import javax.jnlp.*;
import java.security.*;

/**
 * ImageViewer class Listing 2.2
 * A program for viewing images.
 * @version 1.22 2007-05-21
 * @author Cay Horstmann
 */
public class ImageViewer {
	public static void main(String[] args) {
		EventQueue.invokeLater (new Runnable() {
			public void run () {
				JFrame frame = new ImageViewerFrame();
				frame.setTitle("Image Viewer");
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * A frame with a label to show an image.
*/
class ImageViewerFrame extends JFrame {	
	/**
	 * This is a serializable class and therefore needs a Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JFileChooser chooser;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	private static final String MAIN_CLASS = "chapter02.Chapter02";
	private static final String MESSAGE = "";
	
	public ImageViewerFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		Views.openWindowOpenerListener(this, MAIN_CLASS, MESSAGE);
		
		// use a label to display the images
		label = new JLabel();
		add(label);
		
		// set up the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String name = null;
				ImageIcon icon = null;
				try {
					// set up the file chooser
					chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("."));
					// show file chooser dialog
					int result = chooser.showOpenDialog(null);
					
					// if file selected, set it as icon of the label
					if (result == JFileChooser.APPROVE_OPTION) {
						name = chooser.getSelectedFile().getPath();
						icon = new ImageIcon(name);
					}
				}
				catch (AccessControlException e) {
					try {
						FileOpenService service = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
						FileContents contents = service.openFileDialog(".", new String[] {"gif", 
								"jpg", "jpeg", "bmp", "png"});
						
						if (contents != null) {
							byte[] imageData = new byte[(int) contents.getLength()];
							name = contents.getName();
							contents.getInputStream().read(imageData);
							icon = new ImageIcon(imageData);
						}
					}
					catch (UnavailableServiceException ex) {
						ex.printStackTrace();
					}
					catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				if (name != null) {
					label.setIcon(new ImageIcon(icon.getImage().getScaledInstance(getWidth(), -1, 
							Image.SCALE_DEFAULT)));
				}
			}
		});
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Views.closeWindow(ImageViewerFrame.this);
			}
		});
	}
}