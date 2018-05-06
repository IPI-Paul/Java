package ipi;

import java.io.*;

/**
 * @version 1.0 2018-04-01
 * @author Paul I Ighofose 
 * FolderPaths class provides default file path attributes and retrieves current working directory
 */
public class FolderPaths {
	private static String htmlFolderPath;
	private static String imagesFolderPath;
	private static String logsFolderPath;
	private static String textFolderPath;
	private static String webAppsFolderPath;
	private static String f;
	
	public FolderPaths() {		
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for HTML source files
	 * @return returns the folder location for HTML source files
	 */
	public static String getHTMLFolder() {
		f = getPath();
		htmlFolderPath = f + "sourceFiles" + File.separator + "html" + File.separator;
		return htmlFolderPath;
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for images source files
	 * @return returns the folder location for images source files
	 */
	public static String getImagesFolder() {
		f = getPath();
		imagesFolderPath = f + "sourceFiles" + File.separator + "images" + File.separator;
		return imagesFolderPath;
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for log files
	 * @return returns the folder location for log files
	 */
	public static String getLogsFolder() {
		f = getPath();
		logsFolderPath = f + "logs" + File.separator;
		return logsFolderPath;
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for text source files
	 * @return returns the folder location for text source files
	 */
	public static String getTextFolder() {
		f = getPath();
		textFolderPath =  f + "sourceFiles" + File.separator + "text" + File.separator;
		return textFolderPath;
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for jar application files
	 * @return returns the folder location for jar application files
	 */
	public static String getWebAppsFolder() {
		f = getPath();
		webAppsFolderPath = f + "webapps" + File.separator;
		return webAppsFolderPath;
	}

	/**
	 * When in normal mode uses .getProtectionDomain().getCodeSource().getLocation().toString() to return 
	 * the current working directory for the application's root path
	 * When in Web Start mode uses Runtime.getRuntime().exec("cmd /c cd") to return 
	 * the current working directory for the application's root path
	 * @return returns the current working directory for the application's root path
	 */
	public static String getPath() {
		f = FolderPaths.class
				.getProtectionDomain().getCodeSource().getLocation().toString();
		if (f.substring(0,4).equals("http")) {
			f = "/";
			try {
				Process p = Runtime.getRuntime().exec("cmd /c cd");
				p.waitFor();
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					f = line;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		f = f.replace("\\webstart\\signed", "");
		f = f.replace("\\webstart\\unsigned", "");
		f = f.replace("\\webapps\\webstart", "");
		f = f.replace("\\webstart", "");	
		f = f.replace("\\webapps", "");	
		f = f.replace("file:/C:/", File.separator);
		f = f.replace("C:/", File.separator);
		f = f.replace("C:\\", File.separator);
		f = f.replace("\\", File.separator);
		f = f.replace("/", File.separator);
		f = f + File.separator;
		return f;
	}
	
	public static String getUserTempFolder() {
		f = System.getProperty("user.home") + "/appdata/local/temp";
		f = f.replace("\\\\", File.separator);
		f = f.replace("\\", File.separator);
		f = f.replace("/", File.separator);
		return f;
	}
}
