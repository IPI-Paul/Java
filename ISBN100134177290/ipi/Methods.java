package ipi;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

/**
 * This class provides various methods to assist in project creation and running
 * @version 1.0 2018-05-06
 * @author Paul I Ighofose
 */
public class Methods {
	private static final String[] actions = {
			"",
			"Copy File to Another Location", 
			"Read File Contents in an Editor Pane",
			"View File Contents in a Table",
			"View File Contents in a Web View"
			};	
	private static final String[] example = {
			"List All Sub-Directories",
			"List All Sub Directories in a Folder using WalkFileTree",
			"Read Zip File using FileSystem and ZipFile",
			"Read Zip File using ZipInputStream and ZipFile",
			"Remove Excel Custom Styles",
			"Test Me"
			}; 
	private static final String[] delimiters = {
		"Comma Separated Values",
		"Pipe Separated Values",
		"Space Separated Values",
		"Tab Separated Values",
		"1st Row Headers",
		"No Headers"
	};
	private static Object delimiterType;
	private static Object headerType;
	private static int runAgain;
	private static String title = "Example Of";
	protected static String[] appDisplay = {"Run in System Tray", "Run as Switchboard"};
	protected static List<String> list;
	protected static List<String> columns;
	private static Scanner in;
	private static int count;
	private static Object[] objArray;
	protected static Object appType;
	private static JPanel panel;
	private static JPanel buttonPanel;
	private static ButtonGroup group;
	private static JPanel headerPanel;
	private static ButtonGroup headers;
	protected static String selectedItem;
	protected static StringBuilder line;
	private static List<Window> windows = new ArrayList<Window>();

	public static void main(String[] args) {
		appType = JOptionPane.showInputDialog(null, "Please Select an Example!", title,
				JOptionPane.QUESTION_MESSAGE, null, appDisplay, appDisplay[0]);

		if (appType == null) {
			System.exit(0);;
		}
		else if (appType == appDisplay[1]) {
			showSwitchBoard();
		} else {
			SystemTray tray = SystemTray.getSystemTray();
			PopupMenu popup = new PopupMenu();

			ClassLoader cl =  Thread.currentThread().getContextClassLoader();
			Image image = new ImageIcon(cl.getResource("sourceFiles/images/Earth.jpg")).getImage();
			TrayIcon trayIcon = new TrayIcon(image, "IPI Java Methods", popup);
			trayIcon.setImageAutoSize(true);

			MenuItem subDirMenu = new MenuItem("List All Sub-Directories");
			subDirMenu.addActionListener(event -> listDirectories());
			popup.add(subDirMenu);			

			MenuItem walkTreeMenu = new MenuItem("List All Sub Directories in a Folder using WalkFileTree");
			walkTreeMenu.addActionListener(event -> listSubDirectories());
			popup.add(walkTreeMenu);			

			MenuItem zipFlSysMenu = new MenuItem("Read Zip File using FileSystem and ZipFile");
			zipFlSysMenu.addActionListener(event -> readZipFileSystem());
			popup.add(zipFlSysMenu);			

			MenuItem zipInputStreamMenu = new MenuItem("Read Zip File using ZipInputStream and ZipFile");
			zipInputStreamMenu.addActionListener(event -> readZipInputStream());
			popup.add(zipInputStreamMenu);			
			
			MenuItem excelStyleRemoveMenu = new MenuItem("Remove Excel Custom Styles");
			excelStyleRemoveMenu.addActionListener(event -> removeExcelCustomStyles());
			popup.add(excelStyleRemoveMenu);			

			MenuItem exitMenu = new MenuItem("Exit");
			exitMenu.addActionListener(event -> {
				try {
					in.close(); 
				} finally {
				System.exit(0);
				}
			});
			popup.add(exitMenu);			

			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.err.println("TrayIcon could not be added.");
				return;
			}
		}
	}
	
	public static boolean acceptType(String ext, File f) {
		return ext.endsWith(".txt") || f.isDirectory() ||  ext.endsWith(".htm") ||  ext.endsWith(".xhtml") || 
				ext.endsWith(".html") || ext.endsWith(".java") || ext.endsWith(".vbs") ||
				ext.endsWith(".js") || ext.endsWith(".cpp") || ext.endsWith(".xml") || 
				ext.endsWith(".css") || ext.endsWith(".bat") || ext.endsWith(".csv") || 
				ext.endsWith(".xslt") || ext.endsWith(".xsl") || ext.endsWith(".xsd")  || 
				ext.endsWith(".dat") || ext.endsWith(".zip") || ext.endsWith(".jar") || 
				ext.endsWith(".config") || ext.endsWith(".inf") || ext.endsWith(".ini") || 
				ext.endsWith(".sql") || ext.endsWith(".svg") || ext.endsWith(".properties") || 
				ext.endsWith(".log") || ext.endsWith(".class") || ext.endsWith(".caesar") || 
				ext.endsWith(".key");
	}
	
	/**
	 * Adds a radio button that sets the font size of the sample text.
	 * @param name the string to appear on the button
	 * @param size the font size that this button sets
	 */
	private static void addRadioButton(String name, final int option) {
		boolean selected;
		JRadioButton button;
		if (option < 4) {
			selected = option == 3;
			button = new JRadioButton(name, selected);
			group.add(button);
			buttonPanel.add(button);
		}
		else {
			selected = option == 5;
			button = new JRadioButton(name, selected);
			headers.add(button);
			headerPanel.add(button);
		}
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (option < 4) delimiterType = delimiters[option];
				else headerType = delimiters[option];
			}
		};
		
		button.addActionListener(listener);
	}
	
	/**
	 * This method formats the file contents into html
	 */
	private static void ContentsToHTML(String title, String body) {
		String patternString = "http*(\"[^\"]*\"|[^\\s]*|\"[^\\<]\"|\"[^,]\")";
		Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(body);
		List<String> href = new ArrayList<>();
		List<String> domain = new ArrayList<>();
		
		if (!body.replace(" ", "").contains("href=") && !body.replace(" ", "").contains("<?xmlversion")) {
			while (matcher.find()) {
				String match = matcher.group();
				href.add(match.split("<")[0].split(",")[0]);
			}
			Collections.sort(href);
			for (int i = 0; i < href.size() - 1; i++) 
				if (href.get(i + 1).contains(href.get(i))) {
					domain.add(href.get(i));
					href.remove(i);
				}
			for (String ref : href)
				body = body.replace(ref, "<a href=\"" + ref + "\">" + ref + "</a>");
			for (String ref : domain) {
				body = body.replace(ref + ".", "<a href=\"" + ref + "\">" + ref + "</a>.");
				body = body.replace(ref + ",", "<a href=\"" + ref + "\">" + ref + "</a>,");
				body = body.replace(ref + " ", "<a href=\"" + ref + "\">" + ref + "</a> ");
				body = body.replace(ref + "\n", "<a href=\"" + ref + "\">" + ref + "</a>\n");
				body = body.replace(ref + "<br", "<a href=\"" + ref + "\">" + ref + "</a><br");
			}

			patternString = "ftp*(\"[^\"]*\"|[^\\s]*|\"[^\\<]\"|\"[^,]\")";
			pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(body);
			href.clear();

			while (matcher.find()) {
				String match = matcher.group();
				href.add(match.split("<")[0].split(",")[0]);
			}
			Collections.sort(href);
			for (int i = 0; i < href.size() - 1; i++) 
				if (href.get(i + 1).contains(href.get(i))) {
					domain.add(href.get(i));
					href.remove(i);
				}
			for (String ref : href)
				body = body.replace(ref, "<a href=\"" + ref + "\">" + ref + "</a>");
			for (String ref : domain) {
				body = body.replace(ref + ".", "<a href=\"" + ref + "\">" + ref + "</a>.");
				body = body.replace(ref + ",", "<a href=\"" + ref + "\">" + ref + "</a>,");
				body = body.replace(ref + " ", "<a href=\"" + ref + "\">" + ref + "</a> ");
				body = body.replace(ref + "\n", "<a href=\"" + ref + "\">" + ref + "</a>\n");
				body = body.replace(ref + "<br", "<a href=\"" + ref + "\">" + ref + "</a><br");
			}
			
			patternString = "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}\\b";
			pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(body);
			href.clear();
			while (matcher.find()) {
				String match = matcher.group();
				href.add(match);
			}
			for (String ref : href)
				body = body.replace(ref, "<a href=\"mailto:" + ref + "\">" + ref + "</a>");
		}
		if (!body.replace(" ", "").contains("<?xmlversion")) 
			body = "<html><head><style>" + 
					"body {font-family:SansSerif;font-size:11pt; text-align:centre; padding:10 10 10 10;" +
					"}</style><title>" + title + "</title></head><body>"+ body + "</body></html>";		
		line.delete(0, line.length());
		line.append(body);
	}
	
	/**
	 * This method filters an object using the filter passed in. <br />
	 */
	public static Stream<Path> getArray(Path[] paths) {
		Stream<Path> path = Arrays.stream(paths).filter(f -> getfilter(null, f));
		setObjArray(path.toArray());
		setCount(Math.toIntExact(objArray.length));
		return path;
	}

	/**
	 * @return the count
	 */
	public static int getCount(Path[] files) {
		getArray(files);
		return count;
	}
	
	private static boolean getfilter(String[] filters, Object obj) {
		if (obj != null && filters == null) {
			return true;
		}
		else if(filters != null && filters.length == 3) {
			if (filters[0].equals("eq")) return filters[1].equalsIgnoreCase(filters[2]);
			else if (filters[0].equals("ne")) return !filters[1].equals(filters[2]);
			else if (filters[0].equals("lt")) return filters[1].startsWith(filters[2]);
			else if (filters[0].equals("gt")) return filters[1].endsWith(filters[2]);
			else if (filters[0].equals("contains")) return filters[1].contains(filters[2]);
			else return false;
		}
		else
			return false;
	}
	
	/**
	 * Gets all sub-directories recursively
	 * @param files the folder and sub-folders to search
	 */
	public static void getSubDirectories(File[] files) {
		for (File file : files) 
			if (file.isDirectory()) {
				list.add(file.getPath().toString());
				getSubDirectories(file.listFiles());
			}
	}

	/**
	 * @param count the count to set
	 */
	public static void setCount(int count) {
		Methods.count = count;
	}

	/**
	 * @param objArray the objArray to set
	 */
	public static void setObjArray(Object[] objArray) {
		Methods.objArray = objArray;
	}

	/**
	 * Provides a folder open dialog using the JFileChooser class <br />
	 * @throws throws an AccessControlException when using web start <br />
	 */
	public static Path getFolderChooser(String path) throws AccessControlException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(path));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Open Folder");
		chooser.showOpenDialog(null);
		return chooser.getSelectedFile().toPath();
	}
	
	/**
	 * Initiates a folder search on directory given in dialog box input <br />
	 * @param cl the class to run once completed <br />
	 * @param message returns an error message if unable to run <br />
	 */
	public static void listDirectories() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Select Folder");
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		columns = new ArrayList<>();
		columns.add("Folder List");
		list = new ArrayList<>();
		try {
			if (file.getPath() != null) {
				File directory = new File(file.getPath().toString());
				getSubDirectories(directory.listFiles());
				JFrame frame = new MethodsListTableFrame(new JPanel());
				frame.setTitle("List Directories Recursively");
				if (appType == appDisplay[1]) SwitchBoardListener(frame);
				frame.setVisible(true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void listSubDirectories() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Select Folder");
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		columns = new ArrayList<>();
		columns.add("Folder List");
		list = new ArrayList<>();
		if (file.getPath() != null) {
			try {
				Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
					public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) 
						throws IOException {
						list.add(path.toString());
						return FileVisitResult.CONTINUE;
					}
					
					public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
						return FileVisitResult.CONTINUE;
					}
					
					@SuppressWarnings("unused")
					public FileVisitResult visitFileFailedPath(Path path, IOException exc) 
						throws IOException {
						return FileVisitResult.SKIP_SUBTREE;
					}
				});
				JFrame frame = new MethodsListTableFrame(new JPanel());
				frame.setTitle("Walk File Tree");
				if (appType == appDisplay[1]) SwitchBoardListener(frame);
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}		
	}
	
	/**
	 * Initiates an entries search on a ZIP file <br />
	 * @param cl the class to run on completion <br />
	 * @param message returns an error message if unable to run <br />
	 */
	public static void readZipFileSystem() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choose a file with a ZIP Format");
		chooser.setFileFilter(new FileNameExtensionFilter("Files with ZIP format", "xlsm", "zip"));
		int r = chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		if (r != JFileChooser.APPROVE_OPTION) return;
		try {
			ZipFile zif = new ZipFile(file);
			Path[] files = new Path[zif.size()];
			zif.close();
			columns = new ArrayList<>();
			columns.addAll(Arrays.asList(new String[] {"File Name","File Size","Last Modified Date"}));
			list = new ArrayList<>();
			FileSystem fs = FileSystems.newFileSystem(file.toPath(), null);
			Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>() {
				public FileVisitResult visitFile(Path entry, BasicFileAttributes attrs) 
					throws IOException {
					list.addAll(Arrays.asList(new String[] {String.format("%s",entry), 
							String.format("%,d", attrs.size()), 
							String.format("%s", attrs.lastModifiedTime())}));
					if (acceptType(entry.toString(), file) == true) {
						files[getCount(files)] = entry;
					}
					return FileVisitResult.CONTINUE;
				}
			});
			JPanel buttonPanel = new JPanel();
			JComboBox<String> actionList = new JComboBox<>(actions);
			actionList.addActionListener(event -> {
				if (!actionList.getSelectedItem().equals("")) 
					try {
						ZIPFileActions(actionList.getSelectedItem(), Paths.get(selectedItem), fs, new JFrame());
						actionList.setSelectedIndex(0);
						}
					catch (SecurityException | HeadlessException | IOException ex) { ex.printStackTrace(); }
			});
			buttonPanel.add(actionList);
			
			JFrame frame = new MethodsListTableFrame(buttonPanel);
			frame.setTitle("ZIP File using File System");
			if (appType == appDisplay[1]) SwitchBoardListener(frame);
			frame.setVisible(true);
		} catch (IOException | AccessControlException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Initiates an entries search on a ZIP file <br />
	 * @param cl the class to run on completion <br />
	 * @param message returns an error message if unable to run <br />
	 */
	public static void readZipInputStream() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choose a file with a ZIP Format");
		chooser.setFileFilter(new FileNameExtensionFilter("Files with ZIP format", "xlsm", "zip"));
		int r = chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		if (r != JFileChooser.APPROVE_OPTION) return;
		try {
			ZipFile zif = new ZipFile(file);
			ZipEntry[] files = new ZipEntry[zif.size()];
			columns = new ArrayList<>();
			columns.addAll(Arrays.asList(new String[] {"File Name","File Size","Last Modified Date"}));
			list = new ArrayList<>();
			ZipInputStream zin = new ZipInputStream(new FileInputStream(file));
			ZipEntry entry;
			int i = 0;
			while ((entry = zin.getNextEntry()) != null) {
				list.addAll(Arrays.asList(new String[] {
						entry.getName(), 
						String.format("%,d", entry.getSize()), 
						String.format("%s", new Date(entry.getTime()))}));
				if (acceptType(entry.getName(), file) == true) {
					files[i] = entry;
					i++;
				}
			}
			zin.close();
			zif.close();
			JPanel buttonPanel = new JPanel();
			JComboBox<String> actionList = new JComboBox<>(actions);
			actionList.addActionListener(event -> {
				if (!actionList.getSelectedItem().equals("")) 
					try {
						ZIPFileActions(actionList.getSelectedItem(), Paths.get(selectedItem), FileSystems.newFileSystem(file.toPath(), null), new JFrame());
						actionList.setSelectedIndex(0);
						}
					catch (SecurityException | HeadlessException | IOException ex) { ex.printStackTrace(); }
			});
			buttonPanel.add(actionList);
			
			JFrame frame = new MethodsListTableFrame(buttonPanel);
			//JFrame frame = new ListTableFrame(new JPanel());
			frame.setTitle("Walk File Tree");
			if (appType == appDisplay[1]) SwitchBoardListener(frame);
			frame.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AccessControlException ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	/**
	 * Initiates an entries search on an XLSM or ZIP file copying the styles.xml file to the same folder
	 * location. Then edits the file to remove all custom styles. Finally replaces the original XSLM or 
	 * ZIP content with the modified file and renames it's extension to XLSM before opening it.
	 */
	public static void removeExcelCustomStyles() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choose an Excel XLSM File");
		chooser.setFileFilter(new FileNameExtensionFilter("Excel XLSM files", "xlsm", "zip"));
		int r = chooser.showOpenDialog(null);
		Path[] files = new Path[1];
		StringBuilder styleFile = new StringBuilder();
		if (r != JFileChooser.APPROVE_OPTION) return;
		try {
			Path zipPath = chooser.getSelectedFile().toPath();
			FileSystem fs = FileSystems.newFileSystem(zipPath, null);
			Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>() {
				public FileVisitResult visitFile(Path entry, BasicFileAttributes attrs) 
					throws IOException {
					if (entry.toString().equalsIgnoreCase("/xl/styles.xml")) {
						System.out.println(entry.toString());
						files[0] = entry;
					}
					return FileVisitResult.CONTINUE;
				}
			});
			try {
				in = new Scanner(fs.getPath(files[0].toString()));
				int i = 0;
				while (in.hasNext()) {
					styleFile.append(in.nextLine());
					if (i == 0) styleFile.append("\n");
					i++;
				}
				int styleBegin = styleFile.indexOf("<cellStyles "); 
				int styleEnd = styleFile.indexOf("</cellStyles>") + "</cellStyles>".length(); 
				String normalStyle = "<cellStyles count=\"1\"><cellStyle name=\"Normal\" xfId=\"0\" "
						+ "builtinId=\"0\"/></cellStyles>";
				System.out.println(styleFile.toString());
				System.out.println(styleFile.toString().substring(0, styleBegin) + normalStyle + 
						styleFile.substring(styleEnd));
				Path filePath = Paths.get(chooser.getCurrentDirectory().getPath(), "styles.xml");
				PrintWriter writer = new PrintWriter(filePath.toString(), "UTF-8");
				writer.print(styleFile.toString().substring(0, styleBegin) + normalStyle + 
						styleFile.substring(styleEnd));
				writer.close();
				List<String> params = new ArrayList<>();
				params.add("cmd");
				params.add("/c");
				params.add("ren");
				params.add("\"" + zipPath.toString() + "\"");
				params.add("\"" + chooser.getSelectedFile().getName().replace(".xlsm", ".zip") + "\"");
				ProcessBuilder pb = new ProcessBuilder(params);
				Process p = pb.start();
				p.waitFor();
				List<File> listOfFiles = new ArrayList<>();
				listOfFiles.add(filePath.toFile());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();;
				MethodsFileTransferable selection = new MethodsFileTransferable(listOfFiles);
				clipboard.setContents(selection, null);				
				Desktop.getDesktop().open(new File(zipPath.toString().replace(".xlsm", ".zip")));
				JOptionPane.showMessageDialog(null, "!!!Before You Close This MessageBox!!!! \n\nOpen the xl "
						+ "folder in the current window and delete the styles.xml file. \nThen paste the file "
						+ "held in the clipboard, replacing the old styles.xml file. \nNext, close "
						+ "the Explorer window displaying the file contents of the Excel file. \nFinally,"
						+ "close this message box!");
				params.clear();
				params.add("cmd");
				params.add("/c");
				params.add("ren");
				params.add("\"" + zipPath.toString().replace(".xlsm", ".zip") + "\"");
				params.add("\"" + chooser.getSelectedFile().getName() + "\"");
				pb = new ProcessBuilder(params);
				p = pb.start();
				p.waitFor();
				Files.delete(filePath);
				Desktop.getDesktop().open(new File(zipPath.toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		if (appType == appDisplay[1]) showSwitchBoard();
	}

	private static void showSwitchBoard() {
		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null, "Please Select an Example!", title,
					JOptionPane.QUESTION_MESSAGE, null, example, example[0]);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == example[0]) {
				/**
				 * Lists all directories and sub-directories
				 */
				listDirectories();
				return;
			}
			else if (exampleType == example[1]) {
				listSubDirectories();
				return;
			}
			else if (exampleType == example[2]) {
				readZipFileSystem();
				return;
			}
			else if (exampleType == example[3]) {
				readZipInputStream();
				return;
			}
			else if (exampleType == example[4]) {
				removeExcelCustomStyles();
				return;
			}
			else if (exampleType == example[5]) {
				MethodsSimpleSwingBrowser.main(null);
				return;
			}

			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?", "Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		try {
			in.close();
		} finally {
			System.exit(0);
		}
	}
	
	/**
	 * Displays an options dialogue.
	 */
	private static void ShowOptionsDialog() {
		buttonPanel = new JPanel();
		group = new ButtonGroup();
		headerPanel = new JPanel();
		headers = new ButtonGroup();
		panel = new JPanel(new GridLayout(2, 1));
		panel.add(buttonPanel);
		panel.add(headerPanel);
		delimiterType = delimiters[3];
		headerType = delimiters[5];
		
		addRadioButton("Comma Separated Values", 0);
		addRadioButton("Pipe Separated Values", 1);
		addRadioButton("Space Separated Values", 2);
		addRadioButton("Tab Separated Values", 3);
		addRadioButton("1st Row Headers", 4);
		addRadioButton("No Headers", 5);		
	}
	
	/**
	 * Initiates a window listener event to either exit the application or open the provided class
	 * @param frame the frame to add the window listener to
	 * @param cl the main class to open
	 * @param message additional message to notify about environment
	 */
	public static void SwitchBoardListener(final JFrame frame) {
		frame.addWindowListener(new WindowAdapter() {
			
			@Override 
			public void windowOpened(WindowEvent e) {
				windows.add(e.getWindow());
			}
			
			@Override
			public void windowClosing(WindowEvent event) {
				int exit = JOptionPane.showConfirmDialog(null, title + "\nClick YES if you want to open" +
						"\n the Switch Board", "Open Switch Board", 
						JOptionPane.YES_NO_OPTION);
				windows.remove(event.getWindow());
				if (exit == JOptionPane.YES_OPTION) {
					event.getWindow().dispose();
					showSwitchBoard();
				}
				if (exit == JOptionPane.NO_OPTION && windows.size() == 0) 
					System.exit(0);
			}
		});
	}
	
	private static void ZIPFileActions(Object actionType, Path entryFile, FileSystem fs, JFrame frame) throws IOException {
		if (entryFile != null) {
			if (actionType == actions[1]) {
				Path path = getFolderChooser(".");
				if (path != null) {
					System.out.println("File copied to: " + path);
					Files.copy(fs.getPath(entryFile.toString()), 
							Paths.get(path.toString(), entryFile.toString()));
				}
			}
			else if (actionType == actions[2]) {
				in = new Scanner(fs.getPath(entryFile.toString()));
				line = new StringBuilder();
				String regex = null;
				while (in.hasNext()) {
					line.append(in.nextLine());
					if (regex == null)
						if (line.toString().replace(" ", "") .contains("<?xmlversion"))
							regex = "\n";
						else
							regex = "<br />";
					line.append(regex);
				}
				if (line.length() > 0) {
					ContentsToHTML(selectedItem + " File Contents", line.toString());
					frame = new MethodsContentsEditorPaneFrame("File Contents", line.toString());
					if (appType == appDisplay[1]) SwitchBoardListener(frame);
					frame.setVisible(true);
				}
			}
			else if (actionType == actions[3]) {
				in = new Scanner(fs.getPath(entryFile.toString()));
				String line;
				list.clear();
				int rw = 0;
				String regex;
				do {
					String[] options = new String[] {"OK", "Cancel"};
					ShowOptionsDialog();
					int option = JOptionPane.showOptionDialog(null, panel, "Table Options", 
						JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					if (option == 0 && delimiterType == delimiters[0])
						regex = ",";
					else if (option == 0 && delimiterType == delimiters[1])
						regex = "\\|";
					else if (option == 0 && delimiterType == delimiters[2])
						regex = "\\s";
					else if (option == 0 && delimiterType == delimiters[3])
						regex = "\\t";
					else if (option == 1)
						return;
					else 
						regex = null;
				} while(regex == null);
				while (in.hasNext()) {
					line = in.nextLine();
					if (rw == 0) {
						columns.clear();
						if (headerType == delimiters[5])
							for (int i = 0; i < line.split(regex).length; i++) 
								columns.add("Column" + (i + 1));
						else {
							for (int i = 0; i < line.split(regex).length; i++) 
								columns.add(line.split(regex)[i]);
							line = in.nextLine();
						}
					}
					rw++;
					if (line.split(regex).length == 1) 
						list.add(line);
					else 
						list.addAll(Arrays.asList(line.split(regex)));
				}
				if (list.size() > 0) {
					frame = new MethodsListTableFrame(new JPanel());
					frame.setTitle("File Contents");
					if (appType == appDisplay[1]) SwitchBoardListener(frame);
					frame.setVisible(true);
				}
			}
			else if (actionType == actions[4]) {
				in = new Scanner(fs.getPath(entryFile.toString()));
				line = new StringBuilder();
				String regex = null;
				while (in.hasNext()) {
					line.append(in.nextLine());
					if (regex == null)
						if (line.toString().replace(" ", "") .contains("<?xmlversion"))
							regex = "\n";
						else
							regex = "<br />";
					line.append(regex);
				}
				if (line.length() > 0) {
					ContentsToHTML(selectedItem + " Web View File Contents", line.toString());
					MethodsSimpleSwingBrowser.main(null);
				}
			}
		}		
	}
}

/**
 * This frame contains a and editor pane for display file contents in HTML and Text formats. <br />
 */
class MethodsContentsEditorPaneFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public MethodsContentsEditorPaneFrame(String title, String body) {
		final String base = body;
		final Stack<String> urlStack = new Stack<>();
		final JEditorPane pane = new JEditorPane();
		pane.setContentType("text/html");
		if (base.replace(" ", "").contains("<?xmlversion")) pane.setContentType("text/xml");
		pane.setText(base);
		pane.setOpaque(true);
		pane.setBackground(Color.white);
		pane.setAutoscrolls(true);
		pane.setEditable(false);
		pane.setEnabled(true);
		pane.setBorder(null);
		pane.setSize(new Dimension(950, 540));
		pane.addHyperlinkListener(event -> {
			if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				try {
					if (event.getURL().toString().contains("@"))
						Desktop.getDesktop().mail(new URI(
								event.getURL().toString().trim()
								.replaceAll(" ", "%20")
								.replaceAll("<br>", "%0A")
								.replaceAll(">", "%3e")
								.replaceAll("<", "%3c")
								.replaceAll("\"", "%22")
								));
					else {
						// remember URL for back button
						urlStack.push(event.getURL().toString());
						// show URL in text field
						pane.setPage(event.getURL());
					}
				} catch(IOException e) {
					pane.setText("Exception: " + e);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// set up checkbox for toggling edit mode
		final JCheckBox editable = new JCheckBox();
		editable.addActionListener(event -> {
			String text = pane.getText();
			pane.setEditable(editable.isSelected());
			if (editable.isSelected() == true) {
				pane.setContentType("text/text");
				Document doc = pane.getDocument();
				doc.putProperty(PlainDocument.tabSizeAttribute, 2);
			}
			else pane.setContentType("text/html");
			if (base.replace(" ", "").contains("<?xmlversion")) pane.setContentType("text/xml");
			pane.setText(text);
		});
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(event -> {
			pane.setContentType("text/text");
			pane.setContentType("text/html");
			if (base.replace(" ", "").contains("<?xmlversion")) pane.setContentType("text/xml");
			pane.setText(base);
			pane.setEditable(false);
		});
		//url.addActionListener(listener);
		
		// set up back button and button action
		JButton backButton = new JButton("Back");
		backButton.addActionListener(event -> {
			if (urlStack.size() <= 1) return;
			try {
				// get URL from back button
				urlStack.pop();
				// show URL in the text field
				String urlString = urlStack.peek();
				pane.setPage(urlString);
			} catch (IOException e) {
				pane.setText("Exception: " + e);
			}
		});
		add(new JScrollPane(pane), BorderLayout.CENTER);
		JButton printButton = new JButton("Print");
		printButton.addActionListener(event -> {
			try {pane.print(); }
			catch (SecurityException | PrinterException ex) { ex.printStackTrace(); }
		});
		
		// put all control components in a panel
		JPanel panel = new JPanel();
		panel.add(loadButton);
		panel.add(backButton);
		panel.add(printButton);
		panel.add(new JLabel("Editable"));
		panel.add(editable);
		add(panel, BorderLayout.SOUTH);
		setBounds(10, 5, 950, 590);
	}
}	

/**
 * This class reports that the only available data format is <em>DataFlavor.javaFileListFlavor</em>, and
 * it holds an <em>image</em> object.
 */
class MethodsFileTransferable implements Transferable {
	private List<File> listOfFiles;
	
	public MethodsFileTransferable(List<File> listOfFiles) {
		this.listOfFiles = listOfFiles;
	}
	
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] {DataFlavor.javaFileListFlavor};
	}
	
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(DataFlavor.javaFileListFlavor);
	}
	
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
		if (flavor.equals(DataFlavor.javaFileListFlavor)) {
			return listOfFiles;
		} else {
			throw new UnsupportedFlavorException(flavor);
		}
	}
}

/**
 * This frame contains a table of zip file entries or file data. <br />
 */
class MethodsListTableFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private String[] columnNames = new String[Methods.columns.size()];	
	private Object[][] cells = new String[Methods.list.size() / Methods.columns.size()][Methods.columns.size()];
	protected static List<Integer> columnSizes;

	public MethodsListTableFrame(JPanel buttonPanel) {
		columnSizes = new ArrayList<>();
		for (int i = 0; i < Methods.columns.size(); i++) {
			columnNames[i] = Methods.columns.get(i);
			TableColumnWidths(-1, columnNames[i].toString().length() * 8);
		}
		int col = 0;
		for (int i = 0; i < Methods.list.size() / Methods.columns.size(); i++) {
			for (int j = 0; j < Methods.columns.size(); j++)  {
				cells[i][j] = Methods.list.get(col);
				TableColumnWidths(j, cells[i][j].toString().length() * 8);
				col++;
			}
		}
		final JTable table = new JTable(cells, columnNames);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		if (columnSizes.size() > 0)
			for (int i = 0; i < columnSizes.size(); i++) {
				table.getColumn(table.getColumnName(i)).setPreferredWidth(columnSizes.get(i));
				if (Methods.columns.size() == 1) table.getColumn(table.getColumnName(i)).setMaxWidth(900);
				else table.getColumn(table.getColumnName(i)).setMaxWidth(400);
			}
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				Methods.selectedItem = table.getValueAt(table.getSelectedRow(), 0).toString();
			}
		});
		add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		JButton printButton = new JButton("Print");
		printButton.addActionListener(event -> {
			try {table.print(); }
			catch (SecurityException | PrinterException ex) { ex.printStackTrace(); }
		});
		buttonPanel.add(printButton);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}

	private static void TableColumnWidths(int index, int size) {
		if (index == -1) columnSizes.add(size);
		else if (columnSizes.get(index) < size)  
				columnSizes.set(index, size);
	}
}	

/**
 * Displays a frame with a web browser capable of running scripts.
 */
class MethodsSimpleSwingBrowser implements Runnable {
	private JFXPanel jfxPanel;
	private WebEngine engine;
	private JScrollPane scrollPane;
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel(new BorderLayout());
	private JLabel lblStatus = new JLabel();
	
	private JButton btnGo = new JButton("Go");
	private JButton btnLoad = new JButton("Load");
	private JTextField txtURL = new JTextField("http://localhost");
	private JProgressBar progressBar = new JProgressBar();
	
	private JEditorPane editorPane = new JEditorPane();
	private JCheckBox editEnable = new JCheckBox();
	
	private void initComponents() {
		jfxPanel = new JFXPanel();
		
		CreateScene();
		
		ActionListener al = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadURL(txtURL.getText());
			}
		};
		
		btnGo.addActionListener(al);
		btnLoad.addActionListener(event -> loadURL(Methods.line.toString()));
		txtURL.addActionListener(al);
		
		progressBar.setPreferredSize(new Dimension(150, 18));
		progressBar.setStringPainted(true);
		
		JPanel topBar = new JPanel(new BorderLayout(5, 0));
		JPanel buttonPanel = new JPanel();
		topBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
		topBar.add(txtURL, BorderLayout.CENTER);
		buttonPanel.add(btnGo);
		buttonPanel.add(btnLoad);
		buttonPanel.add(new JLabel("Edit Source"));
		buttonPanel.add(editEnable);
		topBar.add(buttonPanel, BorderLayout.EAST);
		
		JPanel statusBar = new JPanel(new BorderLayout(5, 0));
		statusBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
		statusBar.add(lblStatus, BorderLayout.CENTER);
		statusBar.add(progressBar, BorderLayout.EAST);
		
		panel.add(topBar,BorderLayout.NORTH);

		editorPane.setEditable(true);
		editorPane.setContentType("text/text");
		editorPane.setOpaque(true);
		editorPane.setBackground(Color.white);
		editorPane.setAutoscrolls(true);
		editorPane.setEnabled(true);
		editorPane.setBorder(null);
		editorPane.setSize(new Dimension(900, 400));
		Document doc = editorPane.getDocument();
		doc.putProperty(PlainDocument.tabSizeAttribute, 2);
		scrollPane = new JScrollPane(editorPane);
		scrollPane.setVisible(false);
		editEnable.addActionListener(event -> {
			if (editEnable.isSelected() == true) {
				jfxPanel.setVisible(false);
				panel.add(scrollPane, BorderLayout.CENTER);
				scrollPane.setVisible(true);
			} else {
				jfxPanel.setVisible(true);
				scrollPane.setVisible(false);
				loadURL(editorPane.getText());
			}
			panel.validate();
			frame.getContentPane().validate();
		});
		panel.add(jfxPanel, BorderLayout.CENTER);
		panel.add(statusBar, BorderLayout.SOUTH);
		
		frame.getContentPane().add(panel);
	}
	
	private void CreateScene() {
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				WebView view = new WebView();
				engine = view.getEngine();
				
				engine.titleProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, 
							final String newValue) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								frame.setTitle(newValue);
							}
						});
					}
				});
				
				engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
					@Override
					public void handle(WebEvent<String> event) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								lblStatus.setText(event.getData());
							}
						});
					}
				});
				
				engine.locationProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> ov, String oldValue, 
							final String newValue) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								txtURL.setText(newValue);
								if (newValue.startsWith("mailto:")) loadURL(newValue);
							}
						});
					}
				});
				
				engine .getLoadWorker().workDoneProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observableValue, Number oldValue,
							final Number newValue) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								progressBar.setValue(newValue.intValue());
								if (newValue.intValue() == 100) 
									try {
										Transformer transformer = TransformerFactory.newInstance().newTransformer();
										transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
										transformer.setOutputProperty(OutputKeys.METHOD, "xml");
										transformer.setOutputProperty(OutputKeys.INDENT, "yes");
										transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
										transformer.setOutputProperty("{http://xml.apache.org/xlst}indent-amount", "4");
										//transformer.transform(new DOMSource(engine.getDocument()), 
												//new StreamResult(new OutputStreamWriter(System.out, "UTF-8")));
										StringWriter writer = new StringWriter();
										transformer.transform(new DOMSource(engine.getDocument()), new StreamResult(writer));
										editorPane.setText(writer.toString()
												.replace("<!--?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?-->", "")
												.replace(System.lineSeparator() + System.lineSeparator() + "</BODY>", "</BODY>"));
									} catch (Exception ex) {
										ex.printStackTrace();
									}
							}
						});
					}
				});
				
				engine.getLoadWorker()
					.exceptionProperty()
					.addListener(new ChangeListener<Throwable>() {
						public void changed(ObservableValue<? extends Throwable> o, Throwable old, 
								final Throwable value) {
							if (engine.getLoadWorker().getState() == State.FAILED) {
								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run() {
										JOptionPane.showMessageDialog(
												panel,
												(value != null) ? 
												engine.getLocation() + "\n" + value.getMessage() :
												engine.getLocation() + "\nUnexpected error.",
												"Loading error...",
												JOptionPane.ERROR_MESSAGE
												);
									}
								});
							}
						}
					});
				jfxPanel.setScene(new Scene(view));
			}
		});
	}
	
	public void loadURL(final String url) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (url.startsWith("mailto:"))
					try {
						Desktop.getDesktop().mail(new URI(
								url.trim()
								.replaceAll(" ", "%20")
								.replaceAll("<br>", "%0A")
								.replaceAll(">", "%3e")
								.replaceAll("<", "%3c")
								.replaceAll("\"", "%22")
								));
					} catch (IOException | URISyntaxException e) {
						e.printStackTrace();
					}
				else if (!url.startsWith("<html>") && !url.startsWith("<?xml")) {
					String tmp = toURL(url);
					
					if (tmp == null) {
						tmp = toURL("http://" + url);
					}
					
					engine.load(tmp);
				} else
					engine.loadContent(url);
			}
		});
	}
	
	private static String toURL(String str) {
		try {
			return new URL(str).toExternalForm();
		} catch (MalformedURLException ex) {
			return null;
		}
	}
	
	@Override 
	public void run() {
		frame.setPreferredSize(new Dimension(1024, 600));
		/**
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		if (Methods.appType == Methods.appDisplay[1]) Methods.SwitchBoardListener(frame);
		
		initComponents();
		loadURL(Methods.line.toString());

		frame.pack();
		frame.setVisible(true);
	}
		
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MethodsSimpleSwingBrowser());
	}
}