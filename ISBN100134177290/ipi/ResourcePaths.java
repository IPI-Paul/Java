package ipi;


import java.net.URL;
import java.nio.file.Path;

/**
 * @version 1.0 2018-04-01
 * @author Paul I Ighofose 
 * Resource class provides default resource path attributes
 */
public class ResourcePaths {
	private static String csvResource;
	private static String datResource;
	private static String dbResource;
	private static String htmlResource;
	private static String imagesResource;
	private static String keyResource;
	private static String logsResource;
	private static String propertiesResource;
	private static String scriptResource;
	private static String settingsResource;
	private static String styleResource;
	private static String textResource;
	private static String webAppsResource;
	private static String xmlResource;
	private static String zipResource;
	
	public ResourcePaths() {		
	}

	public static String getResourcePath(Path path) {
		if (path.toString().contains("ISBN")) {
			StringBuilder root = new StringBuilder();
			boolean start = false;
			String regex;
			if (path.toString().contains("\\")) regex = "\\\\";
			else regex = "/";
			String[] aPath = path.toString().split(regex);
			for (String folder : aPath) {
				if (start == true) 
					root.append(folder + "/");
				if (folder.contains("ISBN")) start = true;
			}
			return root.toString().replace(aPath[aPath.length - 1] + "/", aPath[aPath.length - 1]);
		} else
			return path.toString().replace("\\", "/");
	}
	
	/**
	 * Gets the default Resource location for source files
	 * @param type the type of file
	 * @param source the resource file
	 * @return returns the given resource in the file type location
	 */
	public static URL getResource(String type, String source) {
		String location = "";
		if (type == "csv") location = getCSVResource();
		else if (type == "dat") location = getDATResource();
		else if (type == "db") location = getDatabaseResource();
		else if (type == "htm") location = getHTMLResource();
		else if (type == "img") location = getImagesResource();
		else if (type == "key") location = getKeysResource();
		else if (type == "log") location = getLogsResource();
		else if (type == "prop") location = getPropertiesResource();
		else if (type == "scr") location = getScriptResource();
		else if (type == "cnf") location = getSettingsResource();
		else if (type == "stl") location = getStyleResource();
		else if (type == "txt") location = getTextResource();
		else if (type == "app") location = getWebAppsResource();
		else if (type == "xml") location = getXMLResource();
		else if (type == "zip") location = getZIPResource();
		ClassLoader cl =  Thread.currentThread().getContextClassLoader();
		return cl.getResource(ResourcePaths.getResourcePath(location + source));
	}

	public static String getResourcePath(String path) {
		if (path.contains("ISBN")) {
			StringBuilder root = new StringBuilder();
			boolean start = false;
			String regex;
			if (path.contains("\\")) regex = "\\\\";
			else regex = "/";
			String[] aPath = path.split(regex);
			for (String folder : aPath) {
				if (start == true) 
					root.append(folder + "/");
				if (folder.contains("ISBN")) start = true;
			}
			return root.toString().replace(aPath[aPath.length - 1] + "/", aPath[aPath.length - 1]);
		} else
			return path.toString().replace("\\", "/");
	}
	
	/**
	 * Gets the default Resource location for CSV source files
	 * @return returns the Resource location for CSV source files
	 */
	public static String getCSVResource() {
		csvResource = "sourceFiles/csv/";
		return csvResource;
	}
	
	/**
	 * Gets the default Resource location for DAT source files
	 * @return returns the Resource location for DAT source files
	 */
	public static String getDATResource() {
		datResource = "sourceFiles/dat/";
		return datResource;
	}
	
	/**
	 * Gets the default Resource location for DAT source files
	 * @return returns the Resource location for DAT source files
	 */
	public static String getDatabaseResource() {
		dbResource = "sourceFiles/database/";
		return dbResource;
	}
	
	/**
	 * Gets the default Resource location for HTML source files
	 * @return returns the Resource location for HTML source files
	 */
	public static String getHTMLResource() {
		htmlResource = "sourceFiles/html/";
		return htmlResource;
	}
	
	/**
	 * Gets the default Resource location for image source files
	 * @return returns the Resource location for image source files
	 */
	public static String getImagesResource() {
		imagesResource = "sourceFiles/images/";
		return imagesResource;
	}
	
	/**
	 * Gets the default Resource location for encryption key source files
	 * @return returns the Resource location for encryption key source files
	 */
	public static String getKeysResource() {
		keyResource = "sourceFiles/keys/";
		return keyResource;
	}
	
	/**
	 * Gets the default Resource location for log files
	 * @return returns the Resource location for log files
	 */
	public static String getLogsResource() {
		logsResource = "logs/";
		return logsResource;
	}
	
	/**
	 * Gets the default Resource location for properties files
	 * @return returns the Resource location for properties files
	 */
	public static String getPropertiesResource() {
		propertiesResource = "sourceFiles/properties/";
		return propertiesResource;
	}
	
	/**
	 * Gets the default Resource location for script source files
	 * @return returns the Resource location for script source files
	 */
	public static String getScriptResource() {
		scriptResource = "sourceFiles/scripts/";
		return scriptResource;
	}
	
	/**
	 * Gets the default Resource location for script source files
	 * @return returns the Resource location for script source files
	 */
	public static String getSettingsResource() {
		settingsResource = "sourceFiles/settings/";
		return settingsResource;
	}
	
	/**
	 * Gets the default Resource location for style source files
	 * @return returns the Resource location for style source files
	 */
	public static String getStyleResource() {
		styleResource = "sourceFiles/styles/";
		return styleResource;
	}
	
	/**
	 * Gets the default Resource location for text source files
	 * @return returns the Resource location for text source files
	 */
	public static String getTextResource() {
		textResource = "sourceFiles/text/";
		return textResource;
	}
	
	/**
	 * Gets the default Resource location for webApps source files
	 * @return returns the Resource location for webApps source files
	 */
	public static String getWebAppsResource() {
		webAppsResource = "webapps/";
		return webAppsResource;
	}
	
	/**
	 * Gets the default Resource location for XML source files
	 * @return returns the Resource location for XML source files
	 */
	public static String getXMLResource() {
		xmlResource = "sourceFiles/xml/";
		return xmlResource;
	}
	
	/**
	 * Gets the default Resource location for XML source files
	 * @return returns the Resource location for XML source files
	 */
	public static String getZIPResource() {
		zipResource = "sourceFiles/zip/";
		return zipResource;
	}
}
