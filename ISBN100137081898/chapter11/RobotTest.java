package chapter11; // robot

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.security.AccessControlException;

import javax.swing.*;
import ipi.*;

/**
 * RobotTest class Listing 11.4 <br />
 * ButtonFrame JFrame class <br />
 * ImageFrame inner class <br />
 * @version 1.04 2012-05-17
 * @author Cay Horstmann
 */
public class RobotTest {
	private static final String MAIN_CLASS = "chapter11.Chapter11";
	private static String message = "";
	private static ButtonFrame frame;
	public static JFrame imageFrame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// make frame with a button panel
				frame = new ButtonFrame();
				frame.setTitle("ButtonTest");
				// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				//setFrame(frame);
			}
		});
		
		// attach a robot to the screen device
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice screen = environment.getDefaultScreenDevice();
		
		try {
			final Robot robot = new Robot(screen);
			robot.waitForIdle();
			new Thread() {
				public void run() {
					runTest(robot);
				};
			}.start();
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (AccessControlException e) {
			message = Threads.getRobotJnlpMessage(RobotTest.class.getName());
			JLabel label = new JLabel("<html>" + Threads.getRobotJnlpMessage("") + "</html>");
			frame.add(label, BorderLayout.NORTH);
			frame.setSize(frame.getWidth(), frame.getHeight() + 40);
			Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
		}
	}
	
	/**
	 * Runs a sample test procedure
	 * @param robot the robot attached to the screen device
	 */
	public static void runTest(Robot robot) {
		// simulate a space bar press
		robot.keyPress(' ');
		robot.keyRelease(' ');
		
		// simulate a tab key followed by a space
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(' ');
		robot.keyRelease(' ');
		
		// simulate a mouse click over the rightmost button
		robot.delay(2000);
		robot.mouseMove(220, 40);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		// capture the screen and show the resulting image
		robot.delay(2000);
		BufferedImage image = robot.createScreenCapture(new Rectangle(0,0,400,300));
		
		ImageFrame frame = new ImageFrame(image);
		frame.setVisible(true);
		Views.openWindowOpenerListener(RobotTest.frame, frame, MAIN_CLASS, message);
	}
}

/**
 * A frame to display a captured image
 */
class ImageFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 350;
	
	/**
	 * @param image the image to dsiplay
	 */
	public ImageFrame(Image image) {
		setTitle("Capture");
		//setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setBounds(300, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JLabel label = new JLabel(new ImageIcon(image));
		add(label);
	}
	
}



