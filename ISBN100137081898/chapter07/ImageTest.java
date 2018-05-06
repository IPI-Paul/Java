package chapter07; // image

import java.awt.*;
import java.security.AccessControlException;

import javax.swing.*;
import ipi.*;

/**
 * ImageTest class Listing 7.6
 * This program illustrates loading an icon image and then copying oit to the rest of the frame.
 * @version 1.33 2007-04-14
 * @author Cay Horstmann
 */
public class ImageTest {
	private static final String MAIN_CLASS = "chapter07.Chapter07";
	private static String message = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new ImageFrame();
				frame.setTitle("Image Test");
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
			}
		});
	}
}

/**
 * A frame with an image component
 */ 
class ImageFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageFrame() {
		add(new ImageComponent());
		pack();
	}
}

/**
 * A component that displays a tiled image
 */
class ImageComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	private Image image;
	
	public ImageComponent() {
		// get images folder path
		String filePath;
		try {
			filePath = FolderPaths.getImagesFolder();
		} catch (AccessControlException ex) {
			filePath = ResourcePaths.getImagesResource();
		}

		filePath = filePath + "bang.gif";
		//image = new ImageIcon(filePath).getImage();
		image = Images.loadImage(filePath).getImage();
	}
	
	public void paintComponent(Graphics g) {
		if (image == null) return;
		
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		
		// draw the image in the upper-left corner
		g.drawImage(image, 0, 0, null);
		
		// tile the image across the component
		for (int i = 0; i * imageWidth <= getWidth(); i++) {
			for (int j = 0; j * imageHeight <= getHeight(); j++) {
				if (i + j > 0) {
					g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
				}
			}
		}
	}
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}