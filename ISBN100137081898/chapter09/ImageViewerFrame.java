package chapter09;  // fileChooser

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.AccessControlException;
import javax.jnlp.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import ipi.*;

/**
 * ImageViewerFrame JFrame Listing 9.21
 * ImagePreviewer JLabel Listing 9.22
 * FileIconView FileView Listing 9.23
 * A frame that has a menu for loading an image and a display area for the loaded image.
 * @author Cay Horstmann
 */
public class ImageViewerFrame extends JFrame {
	private static final String MAIN_CLASS = "chapter09.Chapter09";
	private static String message = "";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// get image folder path
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	private JLabel label;
	private JFileChooser chooser;
	
	public ImageViewerFrame() {
		String imageFolder;
		try {
			imageFolder = FolderPaths.getImagesFolder();
		} catch (AccessControlException ex) {
			imageFolder = ResourcePaths.getImagesResource();
		}

		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					chooser.setCurrentDirectory(new File("."));
				
					// show file chooser dialog
					int result = chooser.showOpenDialog(ImageViewerFrame.this);
					
					// if image file accepted, set it as icon of the label
					if (result == JFileChooser.APPROVE_OPTION) {
						String name = chooser.getSelectedFile().getPath();
						label.setIcon(new ImageIcon(name));
						pack();
					}
				} catch (AccessControlException ex) {
					try {
						FileOpenService service = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
						FileContents contents = service.openFileDialog(".", new String[] {"gif", "jpg", "png", "bmp", "jpeg"});

						if (contents != null) {
							byte[] imageData = new byte[(int) contents.getLength()];
							contents.getInputStream().read(imageData);
							label.setIcon(new ImageIcon(imageData));
						}
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					
				}
			}
		});
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.exit(0);
				Views.closeWindow(ImageViewerFrame.this);
			}
		});
		
		// use a label to display the images
		label = new JLabel();
		add(label);
		
		// set up file chooser
		chooser = new JFileChooser();
		
		// accept image files ending with .jpg, .jpeg, .gif
		/*
		final ExtensionFileFilter filter = new ExtensionFileFilter();
		filter.addExtension("jpg");
		filter.addExtension("jpeg");
		filter.addExtension("gif");
		filter.addExtension("Image files");
		*/
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "gif");
		chooser.setFileFilter(filter);
		chooser.setAccessory(new ImagePreviewer(chooser));
		
		//chooser.setFileView(new FileIconView(filter, new ImageIcon(imageFolder + "palette.gif")));
		chooser.setFileView(new FileIconView(filter, Images.loadImage(imageFolder + "palette.gif")));
		Views.openWindowOpenerListener(ImageViewerFrame.this, MAIN_CLASS, message);
	}
}
