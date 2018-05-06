package chapter07;  // sizedFrame

import java.awt.*;
import java.security.AccessControlException;
import javax.swing.*;
import ipi.*;

/**
 * SizedFrameTest class Listing 7.2
 * This program illustrates a sized JFrame
 * @version 1.32 2007-04-14
 * @author Cay Horstmann
 */
public class SizedFrameTest {
	private static final String MAIN_CLASS = "chapter07.Chapter07";
	private static String message = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new SizedFrame();
				frame.setTitle("Sized Frame");
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
			}
		});
	}
}

class SizedFrame extends JFrame {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SizedFrame() {
		// get images folder path
		String filePath;
		try {
			filePath = FolderPaths.getImagesFolder();
		} catch (AccessControlException ex) {
			filePath = ResourcePaths.getImagesResource();
		}

		// get screen resolution
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		// set frame width, height and let platform pick screen location
		setSize(screenWidth / 2, screenHeight / 2);
		setLocationByPlatform(true);
		
		// set frame icon
		filePath = filePath + "icon.gif";
		//Image img = new ImageIcon(filePath).getImage();
		Image img = Images.loadImage(filePath).getImage();
		setIconImage(img);
	}
}