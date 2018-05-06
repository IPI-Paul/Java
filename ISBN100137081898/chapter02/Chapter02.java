package chapter02;
import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 2
 * @author Paul I Ighofose
 */
public class Chapter02 {
	private static final String MAIN_CLASS = "chapter02.Chapter02";
	private static final String FOR = "For Loop";
	private static final String WELCOME = "Listing 2.1 Welcome";
	private static final String IMAGE_VIEWER = "Listing 2.2 Image Viewer";
	private static final String WELCOME_Applet = "Listing 2.3-4 Welcome Applet";
	private static final String[] example = {FOR, WELCOME, IMAGE_VIEWER, WELCOME_Applet}; 
	private static int runAgain;
	private static String title = "Example Of";
	public static void main(String[] args) {
		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null,"Please Select an Example!",title,JOptionPane.QUESTION_MESSAGE,null,example,FOR);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == FOR) {
				ForLoop();
			}
			else if (exampleType == IMAGE_VIEWER) {
				/**
				 * ImageViewer class Listing 2.2
				 */
				ImageViewer.main(null);
				return;
			}
			else if (exampleType == WELCOME) {
				/**
				 * Welcome class Listing 2.1
				 */
				Welcome.main(null);
				return;
			}
			else if (exampleType == WELCOME_Applet) {
				/** 
				 * WelcomeApplet JAplet Listing 2.4
				 * WelcomeApplet.html HTML Listing 2.3
				 */
				JFrame frame = new JFrame("Welcome Applet");
				frame.setSize(400, 300);
				JApplet applet = new WelcomeApplet();
				frame.add(applet);
				applet.init();
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, "");
				return;
			}
			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?", "Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeApplication();
	}
	/*
	 * with older versions of java you may need to use the following for loop
	 */
	public static void ForLoop() {
		String[] greeting = new String[3];
		greeting[0] = "Welcome to Core Java";
		greeting[1] = "by Cay Horstmann";
		greeting[2] = "and Gary Cornell";
		
		for (int i = 0; i < greeting.length; i++) {
			System.out.println(greeting[i]);
		}
	}
}
