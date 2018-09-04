package ipi;

import java.io.*;

/**
 * @version 1.0 2018-04-01
 * @author Paul I Ighofose 
 * FolderPaths class provides default file path attributes and retrieves current working directory
 */
public class FolderPaths {
	private static String csvFolderPath;
	private static String dbFolderPath;
	private static String datFolderPath;
	private static String htmlFolderPath;
	private static String imagesFolderPath;
	private static String keyFolderPath;
	private static String logsFolderPath;
	private static String propertiesFolderPath;
	private static String scriptFolderPath;
	private static String settingsFolderPath;
	private static String styleFolderPath;
	private static String textFolderPath;
	private static String webAppsFolderPath;
	private static String xmlFolderPath;
	private static String zipFolderPath;
	private static String f;
	
	/**
	 * Splits file path to add variable to file name <br />
	 */
	public static String getFilePath(String path, String prefix, String suffix, String remove) {
		String regex;
		if (path.contains("\\")) regex = "\\\\";
		else regex = "/";
		String[] oldPath = path.split(regex);
		String oldName = oldPath[oldPath.length - 1];
		if (remove != null)	
			oldName = oldName.replace(remove, "");
		if (prefix != null) 
			path = path.replace(oldPath[oldPath.length - 1], prefix + " " + oldName);
		if (suffix != null) 
			path = path.replace(oldPath[oldPath.length - 1], oldName + " " + suffix);
		return path;
	}
	
	/**
	 * Splits file path to return the folder path <br />
	 */
	public static String getFolderPath(String path) {
		String regex;
		if (path.contains("\\")) regex = "\\\\";
		else regex = "/";
		String[] oldName = path.split(regex);
		path = path.replace(oldName[oldName.length - 1], "");
		return path;
	}
	
	public FolderPaths() {		
	}

	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for CSV source files
	 * @return returns the folder location for CSV source files
	 */
	public static String getCSVFolder() {
		f = getPath();
		csvFolderPath =  f + "sourceFiles" + File.separator + "csv" + File.separator;
		return csvFolderPath;
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for DAT source files
	 * @return returns the folder location for DAT source files
	 */
	public static String getDATFolder() {
		f = getPath();
		datFolderPath =  f + "sourceFiles" + File.separator + "dat" + File.separator;
		return datFolderPath;
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for database source files
	 * @return returns the folder location for database source files
	 */
	public static String getDatabaseFolder() {
		f = getPath();
		dbFolderPath =  f + "sourceFiles" + File.separator + "database" + File.separator;
		return dbFolderPath;
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
	 * default folder location for encryption key source files
	 * @return returns the folder location for encryption key source files
	 */
	public static String getKeysFolder() {
		f = getPath();
		keyFolderPath = f + "sourceFiles" + File.separator + "keys" + File.separator;
		return keyFolderPath;
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
	 * default folder location for properties source files
	 * @return returns the folder location for properties source files
	 */
	public static String getPropertiesFolder() {
		f = getPath();
		propertiesFolderPath = f + "sourceFiles" + File.separator + "properties" + File.separator;
		return propertiesFolderPath;
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for script source files
	 * @return returns the folder location for script source files
	 */
	public static String getScriptFolder() {
		f = getPath();
		scriptFolderPath =  f + "sourceFiles" + File.separator + "scripts" + File.separator;
		return scriptFolderPath;
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for settings source files
	 * @return returns the folder location for settings source files
	 */
	public static String getSettingsFolder() {
		f = getPath();
		settingsFolderPath =  f + "sourceFiles" + File.separator + "settings" + File.separator;
		return settingsFolderPath;
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for style source files
	 * @return returns the folder location for style source files
	 */
	public static String getStyleFolder() {
		f = getPath();
		styleFolderPath =  f + "sourceFiles" + File.separator + "styles" + File.separator;
		return styleFolderPath;
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
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for XML source files
	 * @return returns the folder location for XML source files
	 */
	public static String getXMLFolder() {
		f = getPath();
		xmlFolderPath =  f + "sourceFiles" + File.separator + "xml" + File.separator;
		return xmlFolderPath;
	}
	
	/**
	 * Uses getPath to get the current working directory for the application's root path then gets the  
	 * default folder location for ZIP source files
	 * @return returns the folder location for ZIP source files
	 */
	public static String getZIPFolder() {
		f = getPath();
		zipFolderPath =  f + "sourceFiles" + File.separator + "zip" + File.separator;
		return zipFolderPath;
	}

	public static String getFileName() {
		f = getPath();
		String[] fileName = f.split(File.separator + File.separator);
		return fileName[fileName.length - 1];
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
