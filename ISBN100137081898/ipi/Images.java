package ipi;

import java.awt.*;
import java.net.*;
import java.security.AccessControlException;

import javax.swing.*;

/**
 * Manipulates images
 * @author Paul I Ighofose
 *
 */
public class Images {
	public static String test;
	
	public Images() {}
	
	/**
	 * Loads Icon Images avoiding AccessControlException
	 * @version 1.0 2018-03-18
	 * @author Paul I Ighofose
	 * @param imagePath location of image
	 * @return returns image icon
	 */
	public static ImageIcon loadImage(String imagePath) {
		Image img;
		try {
			img = new ImageIcon(imagePath).getImage();
		} catch (AccessControlException ex) {
			img = new ImageIcon(Images.class.getResource(imagePath)).getImage();
		}
		return new ImageIcon(img);
	}
	
	/**
	 * Resizes Icon Images to fit display
	 * @version 1.0 2018-03-18
	 * @author Paul I Ighofose
	 * @param imagePath location of image
	 * @param width width to scale image to
	 * @return returns image icon of a reduced size
	 */
	public static ImageIcon resizeImage(String imagePath, double width) {
		Image img;
		try {
			img = new ImageIcon(imagePath).getImage().getScaledInstance((int) width, -1, Image.SCALE_SMOOTH);
		} catch (AccessControlException ex) {
			img = new ImageIcon(Images.class.getResource(imagePath)).getImage().getScaledInstance((int) width, -1, Image.SCALE_SMOOTH);
		}
		return new ImageIcon(img);
	}
	
	/**
	 * Resizes Icon Images to fit display
	 * @version 1.0 2018-03-18
	 * @author Paul I Ighofose
	 * @param imagePath location of image
	 * @param width width to scale image to
	 * @return returns image icon of a reduced size
	 */
	public static ImageIcon resizeImage(URL imagePath, double width) {
		Image img = new ImageIcon(imagePath).getImage().getScaledInstance((int) width, -1, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

}
