package ipi;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class IPI_Paul_ExcelCustomStylesRemover {
	private static Scanner in;

	public static void main(String[] args) {
		SystemTray tray = SystemTray.getSystemTray();
		PopupMenu popup = new PopupMenu();

		ClassLoader cl =  Thread.currentThread().getContextClassLoader();
		Image image = new ImageIcon(cl.getResource("sourceFiles/images/Earth.jpg")).getImage();
		TrayIcon trayIcon = new TrayIcon(image, "IPI Paul - Java Methods", popup);
		trayIcon.setImageAutoSize(true);
		MenuItem excelStyleRemoveMenu = new MenuItem("Remove Excel Custom Styles");
		excelStyleRemoveMenu.addActionListener(event -> removeExcelCustomStyles());
		popup.add(excelStyleRemoveMenu);			

		MenuItem exitMenu = new MenuItem("Exit");
		exitMenu.addActionListener(event -> System.exit(0));
		popup.add(exitMenu);			

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.err.println("TrayIcon could not be added.");
			return;
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
					if (entry.toString().equalsIgnoreCase("/xl/styles.xml")) files[0] = entry;
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
	}
}
