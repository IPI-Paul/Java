package ipi;

/**
 * @version 1.0 2018-04-01
 * @author Paul I Ighofose 
 * Resource class provides default resource path attributes
 */
public class ResourcePaths {
	private static String htmlResource;
	private static String imagesResource;
	private static String logsResource;
	private static String textresource;
	
	public ResourcePaths() {		
	}
	
	/**
	 * Gets the default Resource location for HTML source files
	 * @return returns the Resource location for HTML source files
	 */
	public static String getHTMLResource() {
		htmlResource = "/sourceFiles/html/";
		return htmlResource;
	}
	
	/**
	 * Gets the default Resource location for image source files
	 * @return returns the Resource location for image source files
	 */
	public static String getImagesResource() {
		imagesResource = "/sourceFiles/images/";
		return imagesResource;
	}
	
	/**
	 * Gets the default Resource location for log files
	 * @return returns the Resource location for log files
	 */
	public static String getLogsResource() {
		logsResource = "/logs/";
		return logsResource;
	}
	
	/**
	 * Gets the default Resource location for text source files
	 * @return returns the Resource location for text source files
	 */
	public static String getTextResource() {
		textresource = "/sourceFiles/text/";
		return textresource;
	}
}
