package ipi;

import java.awt.event.*;

import javax.jnlp.BasicService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import javax.swing.*;
import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URL;

/** 
 * Methods to initiate new windows
 * @version 1.0 2018-03-31
 * @author Paul I Ighofose
 * Views class uses Reflect to Invoke methods passed by variable
 */
public class Views {	
	/**
	 * Initiate a new Browser document
	 * @param doc the file to load
	 */
	public static void browseDocument(String doc) throws MalformedURLException, UnavailableServiceException {
		BasicService basic = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
		URL codeBase = basic.getCodeBase();
		URL key = new URL(codeBase, doc);
		basic.showDocument(key);
	}

	/**
	 * Initiates JFrame window closing event
	 */
	public static void closeWindow(JFrame frame) {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Initiate a new Frame Window
	 * @param frame variable frame to load
	 * @param title of frame 
	 */
	public static JFrame newFrame(JFrame aFrame, String title) {
		if (title != null) 
			aFrame.setTitle(title);
		return aFrame;
	}
	
	/**
	 * Offers to either exit the application or open the provided class
	 * @param frame the frame to add the window listener to
	 * @param cl the main class to open
	 * @param message additional message to notify about environment
	 */
	public static void openWindowOpener(JFrame frame, String cl, String message) {
		int exit = JOptionPane.showConfirmDialog(null, message + "\nClick YES if you want to Exit" +
			"\nOr \nclick NO if you would like to run another application in " + cl, "Exit Program", 
			JOptionPane.YES_NO_OPTION);
		if (exit == JOptionPane.YES_OPTION)
			System.exit(0);
		else {
			frame.setVisible(false);
			frame.dispose();
			try {
				Method m = (Class.forName(cl)).getMethod("main", String[].class);
				String[] params = null;
				m.invoke(null, (Object) params);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Offers to either exit the application or open the provided class
	 * @param cl the main class to open
	 * @param message additional message to notify about environment
	 */
	public static void openWindowOpener(String cl, String message) {
		int exit = JOptionPane.showConfirmDialog(null, message + "\nClick YES if you want to Exit" +
			"\nOr \nclick NO if you would like to run another application in " + cl, "Exit Program", 
			JOptionPane.YES_NO_OPTION);
		if (exit == JOptionPane.YES_OPTION)
			System.exit(0);
		else {
			try {
				Method m = (Class.forName(cl)).getMethod("main", String[].class);
				String[] params = null;
				m.invoke(null, (Object) params);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Initiates a window listener event to either exit the application or open the provided class
	 * @param frame the frame to add the window listener to
	 * @param cl the main class to open
	 * @param message additional message to notify about environment
	 */
	public static void openWindowOpenerListener(final JFrame frame, final String cl, final String message) {
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				openWindowOpener(frame, cl, message);
			}
		});
	}
	
	/**
	 * Initiates a window listener event to either exit the application or open the provided class
	 * @param frame the frame to add the window listener to
	 * @param logger tracing or logging frame window to also close
	 * @param cl the main class to open
	 * @param message additional message to notify about environment
	 */
	public static void openWindowOpenerListener(final JFrame frame, final JFrame logger, 
                final String cl, final String message) {
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				logger.setVisible(false);
				logger.dispose();
				openWindowOpener(frame, cl, message);
			}
		});
	}
	
	/**
	 * Invokes a method passed as a variable string
	 */
	public static void runMethod(String cl, String method) {
		try {
			Method m = (Class.forName(cl)).getDeclaredMethod(method);
			m.invoke(null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
