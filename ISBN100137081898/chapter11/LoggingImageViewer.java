package chapter11;  //logging

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.AccessControlException;
import java.util.logging.*;
import javax.swing.*;
import ipi.*;

/**
 * LoggingImageViewer class Listing 11.2 <br /> 
 * WindowHandler inner class <br />
 * ImageViewerFrame inner class <br />
 * FileOpenListener inner class <br />
 * A modification of the image viewer program tha logs various events.
 * @version 1.02 2007-05-31
 *@author Cay Horstmann
 */
public class LoggingImageViewer {
	private static JFrame frame = new JFrame();

	public static void main(String[] args) {
		try {
			if (System.getProperty("java.util.logging.config.class") == null 
					&& System.getProperty("java.util.logging.config.file") ==
					null) {
				try {
					Logger.getLogger("com.horstmann.corejava").setLevel(Level.ALL);
					final int LOG_ROTATION_COUNT = 3;
					// Handler handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
					Handler handler = new FileHandler(FolderPaths.getLogsFolder() + "LoggingImageViewer.log", 
							0, LOG_ROTATION_COUNT);
					Logger.getLogger("com.horstmann.corejava").addHandler(handler);
				} catch(IOException e) {
					Logger.getLogger("com.horstmann.corejava").log(Level.SEVERE, 
							"Can't create log file handler", e);
				}
			}
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					Handler windowHandler = new WindowHandler();
					windowHandler.setLevel(Level.ALL);
					Logger.getLogger("com.horstmann.corejava").addHandler(windowHandler);
					
					frame = new ImageViewerFrame();
					frame.setTitle("LoggingImageViewer");
					//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					Logger.getLogger("com.horstmann.corejave").fine("Showing frame!");
					frame.setVisible(true);
				}
			});
		} catch (AccessControlException e) {
			System.out.println(e.toString());
			Views.openWindowOpener(ImageViewerFrame.MAIN_CLASS, 
					Threads.getLogJnlpMessage(LoggingImageViewer.class.getName()));
		}
	} 
}

/**
 * The frame that shows the image.
 */
class ImageViewerFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MAIN_CLASS = "chapter11.Chapter11";
	private static String message = "";
	private static JFrame logWindow = new JFrame();
	private static JFrame frame = new JFrame();

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	
	private JLabel label;
	private static Logger logger = Logger.getLogger("com.horstmann.corejava");
	
	public ImageViewerFrame() {
		logger.entering("ImageViewerFrame", "<init>");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new FileOpenListener());
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				logger.fine("Exiting.");
				//System.exit(0);
				Views.closeWindow(ImageViewerFrame.this);
			}
		});
		
		// use a label to display the images
		label = new JLabel();
		add(label);
		logger.exiting("ImageViewerFrame", "<init>");
		setFrame(this);
		closeApp();
	}
	
	private class FileOpenListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			logger.entering("ImageViewerFrame.FileOpenListener", 
					"actionPerformed", event);
			
			// set up file chooser
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			
			// accept all files ending with .gif
			chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File f) {
					// return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
					return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory() || 
							f.getName().toLowerCase().endsWith(".jpg");
				}
				
				public String getDescription() {
					// return "GIF Images";
					return "Images";
				}
			});
			
			// show file chooser dialog
			int r = chooser.showOpenDialog(ImageViewerFrame.this);
			
			// if image file accepted, set it as icon of the label
			if (r == JFileChooser.APPROVE_OPTION) {
				String name = chooser.getSelectedFile().getPath();
				logger.log(Level.FINE, "Reading file {0}", name);
				label.setIcon(new ImageIcon(name));
			} else {
				logger.fine("File open dialog canceled.");
				logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
			}
		}
	}
	
	public static void closeApp() {
		Views.openWindowOpenerListener(frame, logWindow, MAIN_CLASS, message);
	}
	
	public static void setMessage(String error) {
		message = error;
	}
	
	public void setFrame(JFrame window) {
		frame = window;
	}
	
	public static void setLogWindow(JFrame log) {
		logWindow = log;
	}
	
	public static String[] getParams() {
		String[] params = {MAIN_CLASS, message};
		return params;
	}
}

/**
 * A handler for displaying log records in a window.
 */
class WindowHandler extends StreamHandler {
	private JFrame frame = new JFrame();
	
	public WindowHandler() {
		frame = new JFrame();
		final JTextArea output = new JTextArea();
		output.setEditable(false);
		frame.setLocation(300, 0);
		frame.setSize(400,200);
		frame.add(new JScrollPane(output));
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
		try {
			setOutputStream(new OutputStream() {
				public void write(int b) {
					
				} // not called
				
				public void write(byte[] b, int off, int len) {
					output.append(new String(b, off, len));
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public JFrame getLog() {
		return frame;
	}
	
	public void setLog(JFrame log) {
		frame = log;
	}
	
	public void publish(LogRecord record) {
		if (!frame.isVisible()) return;
		super.publish(record);
		flush();
		ImageViewerFrame.setLogWindow(frame);
	}
}


