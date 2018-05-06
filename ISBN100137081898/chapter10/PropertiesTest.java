package chapter10;  // properties

import java.awt.EventQueue;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.security.AccessControlException;
import java.util.Properties;
import javax.jnlp.*;
import javax.swing.*;
import ipi.*;

/**
 * PropertiesTest class Listing 10.5
 * A program to test properties. The program remembers the frame position, size, and title.
 * @version 1.00 2007-04-29
 * @author Cay Horstmann
 */
public class PropertiesTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PropertiesFrame frame = new PropertiesFrame();
				frame.setVisible(true);
			}
		});
	}
}

/**
 * A frame that restores position and size from a properties file and updates the properties upon
 * exit.
 */
class PropertiesFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private static final String MAIN_CLASS = "chapter10.Chapter10";
	private static String message = "";
	
	private File propertiesFile;
	private Properties settings;
	
	public PropertiesFrame() {
		Properties defaultSettings = new Properties();
		defaultSettings.put("left", "0");
		defaultSettings.put("top", "0");
		defaultSettings.put("width", "" + DEFAULT_WIDTH);
		defaultSettings.put("height", "" + DEFAULT_HEIGHT);
		defaultSettings.put("title", "");
		
		settings = new Properties(defaultSettings);
		
		// get position, size, title from properties
		try {
			String userDir = System.getProperty("user.home");
			File propertiesDir = new File(userDir, ".corejava");
			if (!propertiesDir.exists()) propertiesDir.mkdir();
			propertiesFile = new File(propertiesDir, "program.properties");
			if (propertiesFile.exists()) 
				try {
					FileInputStream in = new FileInputStream(propertiesFile);
					settings.load(in);
					in.close();
				}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		catch (AccessControlException ex) {
			PersistenceService ps;
			BasicService bs;
			try {
				ps = (PersistenceService) ServiceManager.lookup("javax.jnlp.PersistenceService");
				bs = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
				
				if (ps != null && bs != null) {
					URL codebase = bs.getCodeBase();
					URL key = new URL(codebase, "PropertiesTest");
					FileContents fc = ps.get(key);
					if (fc != null) {
						InputStream in = fc.getInputStream();
						settings.load(in);
						in.close();
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (UnavailableServiceException e) {
				e.printStackTrace();
			}
		}
				
		int left = Integer.parseInt(settings.getProperty("left"));
		int top = Integer.parseInt(settings.getProperty("top"));
		int width = Integer.parseInt(settings.getProperty("width"));
		int height = Integer.parseInt(settings.getProperty("height"));
		setBounds(left, top, width, height);
		
		// if no title given, ask user
		String title = settings.getProperty("title");
		if (title.equals("")) title = JOptionPane.showInputDialog("Please supply a frame title:");
		if (title == null) title = "";
		setTitle(title);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				settings.put("left", "" + getX());
				settings.put("top", "" + getY());
				settings.put("width", "" + getWidth());
				settings.put("height", "" + getHeight());
				settings.put("title", getTitle());
				try {
					FileOutputStream out = new FileOutputStream(propertiesFile);
					settings.store(out, "Program Properties");
					out.close();
				}
				catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				catch (IOException ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				catch (NullPointerException ex) {
					PersistenceService ps;
					BasicService bs;
					@SuppressWarnings("unused")
					long keyLength = 0;
					try {
						ps = (PersistenceService) ServiceManager.lookup("javax.jnlp.PersistenceService");
						bs = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
						
						URL codebase = bs.getCodeBase();
						URL key = new URL(codebase, "PropertiesTest");
						try {
							keyLength = ps.get(key).getLength();
						}
						catch (FileNotFoundException f) {
							ps.create(key, 300);
						}
						FileContents fc = ps.get(key);
						OutputStream out = fc.getOutputStream(true);
						settings.store(out, "Program Properties");
						out.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
					catch (UnavailableServiceException e) {
						e.printStackTrace();
					}
				}		
				Views.openWindowOpener(PropertiesFrame.this, MAIN_CLASS, message);
			}
		});
	}
}