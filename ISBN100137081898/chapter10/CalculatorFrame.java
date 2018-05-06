package chapter10;  // webstart

import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.net.*;
import javax.jnlp.*;
import javax.swing.*;
import ipi.*;

/**
 * CalculatorFrame JFrame Listing 10.2
 * CalculatorPanel JPanel 
 * A frame with a calculator panel and a menu to load and save the calculator history.
 * @author Paul
 *
 */
public class CalculatorFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CalculatorPanel panel;
	private static final String MAIN_CLASS = "chapter10.Chapter10";
	private static String message = "";
	
	public CalculatorFrame() {
		Views.openWindowOpenerListener(this, MAIN_CLASS, message);
		setTitle();
		panel = new CalculatorPanel();
		add(panel);
		
		JMenu fileMenu = new JMenu("File");
		JMenu windowMenu = new JMenu("Window");
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		setJMenuBar(menuBar);
		
		JMenuItem clearItem = fileMenu.add("Clear");
		clearItem.addActionListener(EventHandler.create(ActionListener.class, this, "clearPanel"));
		JMenuItem openItem = fileMenu.add("Open");
		openItem.addActionListener(EventHandler.create(ActionListener.class, this, "open"));
		JMenuItem saveItem = fileMenu.add("Save");
		saveItem.addActionListener(EventHandler.create(ActionListener.class, this, "save"));
		JMenuItem exitItem = fileMenu.add("Exit");
		exitItem.addActionListener(EventHandler.create(ActionListener.class, this, "exit"));
		
		JMenuItem changeItem = windowMenu.add("Change Title");
		changeItem.addActionListener(EventHandler.create(ActionListener.class, this, "changeTitle"));
		JMenuItem deleteItem = windowMenu.add("Delete Title");
		deleteItem.addActionListener(EventHandler.create(ActionListener.class, this, "deleteTitle"));
		
		pack();
	}
	
	/**
	 * Clears the text from the Calculator Panel.
	 */
	public void clearPanel() {
		panel.clearText();
	}
	
	/**
	 * Gets the title from the persistent store or asks the user for the title if there is no prior entry.
	 */
	public void changeTitle() {
		try {			
			String title = null;
			
			BasicService basic = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
			URL codeBase = basic.getCodeBase();
			
			PersistenceService service = (PersistenceService) 
					ServiceManager.lookup("javax.jnlp.PersistenceService");
			URL key = new URL(codeBase, "title");
			
			title = JOptionPane.showInputDialog("Please supply a frame title:");
			if (title == null) return;
			
			try {
				service.delete(key);
			}
			catch (Exception e) {
				//
			}
			service.create(key, 100);
			FileContents contents = service.get(key);
			OutputStream out = contents.getOutputStream(true);
			PrintStream printOut = new PrintStream(out);
			printOut.print(title);
			out.close();
			setTitle(title);
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(this, e);
		}
		catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	/**
	 * Deletes the title from the persistent store.
	 */
	public void deleteTitle() {
		try {			
			BasicService basic = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
			URL codeBase = basic.getCodeBase();
			
			PersistenceService service = (PersistenceService) 
					ServiceManager.lookup("javax.jnlp.PersistenceService");
			URL key = new URL(codeBase, "title");
			
			try {
				service.delete(key);
			}
			catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, e);
			}
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(this, e);
		}
		catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	/**
	 * Confirms exit of application if true exits, if false opens Chapter10 class
	 */
	public void exit() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
		
	/**
	 * Opens a history file and updates the display.
	 */
	public void open() {
		try {
			FileOpenService service = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
			FileContents contents = service.openFileDialog(".", new String[] {"txt"});
			
			JOptionPane.showMessageDialog(this, contents.getName());
			if (contents != null) {
				InputStream in = contents.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line;
				while ((line = reader.readLine()) != null) {
					if (panel.isEmptyText() == false)
						panel.appendLine("\n");
					panel.appendLine(line);
					if (line.contains("="))
							panel.setResult(line.replace("= ", ""));
				}
			}
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(this, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	/**
	 * Saves the calculator history to a file.
	 */
	public void save() {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream printOut = new PrintStream(out);
			printOut.print(panel.getText());
			InputStream data = new ByteArrayInputStream(out.toByteArray());
			FileSaveService service = (FileSaveService) 
					ServiceManager.lookup("javax.jnlp.FileSaveService");
			service.saveFileDialog(".", new String[] {"txt"}, data, "calc.txt");
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(this, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	/**
	 * Gets the title from the persistent store or asks the user for the title if there is no prior entry.
	 */
	public void setTitle() {
		try {			
			String title = null;
			
			BasicService basic = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
			URL codeBase = basic.getCodeBase();
			
			PersistenceService service = (PersistenceService) 
					ServiceManager.lookup("javax.jnlp.PersistenceService");
			URL key = new URL(codeBase, "title");
			
			try {
				FileContents contents = service.get(key);
				InputStream in = contents.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				title = reader.readLine();
				in.close();
			}
			catch (FileNotFoundException e) {
				title = JOptionPane.showInputDialog("Please supply a frame title:");
				if (title == null) return;
				
				service.create(key, 100);
				FileContents contents = service.get(key);
				OutputStream out = contents.getOutputStream(true);
				PrintStream printOut = new PrintStream(out);
				printOut.print(title);
				out.close();
			}
			setTitle(title);
		}
		catch (UnavailableServiceException e) {
			JOptionPane.showMessageDialog(this, e);
			setTitle("Calculator Frame");
		}
		catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
}
