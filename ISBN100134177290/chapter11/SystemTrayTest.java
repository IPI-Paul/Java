package chapter11;  // systemTray

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;
import ipi.ResourcePaths;
import ipi.Views;

/**
 * {@code SystemTrayTest} class Listing 11.25 <br />
 * This program demonstrates the system tray API.
 * @version 1.02 2016-05-10
 * @author Cay Horstmann
 */
public class SystemTrayTest {
	public static void main(String[] args) {
		SystemTrayApp app = new SystemTrayApp();
		app.init();
	}
}

class SystemTrayApp {
	private static final String MAIN_CLASS = "chapter11.Chapter11";
	private static String message = "";
	int index;
	List<String> fortunes;

	public void init() {
		final TrayIcon trayIcon;
		
		if (!SystemTray.isSupported()) {
			System.err.println("System tray is not supported.");
			return;
		}
		
		SystemTray tray = SystemTray.getSystemTray();
		/**
		Image image = new ImageIcon(getClass().getResource("cookie.png")).getImage();
		*/
		Image image = new ImageIcon(ResourcePaths.getResource("img", "Earth.jpg")).getImage();
		
		PopupMenu popup = new PopupMenu();
		MenuItem exitMenu = new MenuItem("Exit");
		exitMenu.addActionListener(event -> /** System.exit(0) */ Views.openWindowOpener(MAIN_CLASS, message));
		/**
		 * Moved to try out launching Desktop Apps and browse web pages
		popup.add(exitMenu);
		*/
		
		trayIcon = new TrayIcon(image, "Your Todo List", popup);
		trayIcon.setImageAutoSize(true);

		final List<String> fortunes = readFortunes();
		for (String fortune : fortunes) {
			if (fortune.startsWith("http") || fortune.startsWith("mailto") || fortune.contains(":\\") 
					|| fortune.contains("/Users/") || fortune.contains("\\Users\\")) {
				MenuItem webPage = new MenuItem(fortune);
				webPage.addActionListener(event -> 
				{
					try {
						if (fortune.startsWith("http")) Desktop.getDesktop().browse(new URI(fortune.trim()));
						else if (fortune.startsWith("mailto")) 
							Desktop.getDesktop().mail(new URI(fortune.trim()
									.replaceAll(" ", "%20")
									.replaceAll("<br>", "%0A")
									.replaceAll(">", "%3e")
									.replaceAll("<", "%3c")
									.replaceAll("\"", "%22")
									));
						else Desktop.getDesktop().open(new File(fortune.trim()));
					} catch (IOException | URISyntaxException e) {
						e.printStackTrace();
					}
				});
				popup.add(webPage);
			}
		}
		popup.add(exitMenu);
		Timer timer = new Timer(10000, event -> {
			index = (int) (fortunes.size() * Math.random());
			trayIcon.displayMessage("Your Todo List", fortunes.get(index), TrayIcon.MessageType.INFO);
		});
		trayIcon.addActionListener(event -> {
			if (fortunes.get(index).startsWith("http") || fortunes.get(index).startsWith("mailto")) {
				try {
					Desktop.getDesktop().browse(new URI(fortunes.get(index).trim()
							.replaceAll(" ", "%20")
							.replaceAll("<br>", "%0A")
							.replaceAll(">", "%3e")
							.replaceAll("<", "%3c")
							.replaceAll("\"", "%22")
							));
				} catch (URISyntaxException | IOException e) {
					e.printStackTrace();
				}
			}else if (fortunes.get(index).contains(":\\") || fortunes.get(index).contains("/Users/") 
					|| fortunes.get(index).contains("\\Users\\")) {
				try {
					Desktop.getDesktop().open(new File(fortunes.get(index).trim()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				trayIcon.displayMessage("How do I turn this off?", "Right-click on the fortune cookie and "
					+ "select Exit.", TrayIcon.MessageType.INFO);
			}
		});
		
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.err.println("TrayIcon could not be added.");
			return;
		}
		
		/**
		final List<String> fortunes = readFortunes();
		Timer timer = new Timer(10000, event -> {
			int index = (int) (fortunes.size() * Math.random());
			trayIcon.displayMessage("Your fortune", fortunes.get(index), TrayIcon.MessageType.INFO);
		});
		*/
		timer.start();
	}
	
	private List<String> readFortunes() {
		List<String> fortunes = new ArrayList<>();
		/**
		try (InputStream inStream = getClass().getResourceAsStream("fortunes")) {
		*/
		try (InputStream inStream = ResourcePaths.getResource("txt", "To Do List-ch11.txt").openStream()) {
			Scanner in = new Scanner(inStream, "UTF-8");
			
			StringBuilder fortune = new StringBuilder();
			while (in.hasNextLine()) {
				String line = in.nextLine();
				if (line.equals("%")) {
					fortunes.add(fortune.toString());
					fortune = new StringBuilder();
				} else {
					fortune.append(line);
					fortune.append(' ');
				}
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return fortunes;
	}
}
