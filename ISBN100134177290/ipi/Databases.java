package ipi;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 * Maintains database program locations and methods <br />
 * @version 1.0 <br />
 * @author Paul I Ighofose <br />
 */
public class Databases {
	private static final String driversFolder = 
			"\"" + getPath() + "drivers/";
	private static final String databaseFolder = 
			"\"" + getPath() + "sourceFiles/database\"";
	private static final String[] databaseTypes = {
			"Derby", "Post GRE", "Ms Access", "MySQL", "SQLite", "SQL Server"
			};
	private static final String derbyDbProgram = 
			"\"/Program Files (x86)/Java/jdk1.8.0_161/db/lib/derbyrun.jar\"";
	private static final String mySQLDbProgram = 
			getDriversFolder() + "mysql-connector-java-5.1.46.jar\"";
	private static final String postgreSQLDbFolder = 
			"/Program Files/PostgreSQL/10/data";
	private static final String postgreSQLDbProgram = 
			"/Program Files/PostgreSQL/10/bin/pg_ctl.exe";
	private static final String sqliteDbProgram = 
			getDriversFolder() + "sqlite-jdbc-3.8.11.2.jar\"";
	private static final String sqlServerDbProgram = 
			getDriversFolder() + "sqljdbc42.jar\"";

	public static String getPath() {
		if (Threads.isWebStart() == true) {
			return "";
		} else 
		try {
			String path = FolderPaths.getPath();
			path = path.replace("\\\\", "/");
			path = path.replace("\\", "/");
			return path;
		} catch (Exception ex) {
			return "";
		}
	}
	
	/**
	 * @return the database folder
	 */
	public static String getDatabaseFolder() {
		return databaseFolder;
	}

	/**
	 * @return the database types
	 */
	public static String[] getDatabaseTypes() {
		return databaseTypes;
	}

	/**
	 * @return the derby DB Server Program
	 */
	public static String getDerbyDbProgram() {
		return derbyDbProgram;
	}
	
	/**
	 * @return the drivers folder
	 */
	public static String getDriversFolder() {
		return driversFolder;
	}

	/**
	 * @return the mysql database program
	 */
	public static String getMySQLDbProgram() {
		return mySQLDbProgram;
	}

	/**
	 * @return the post gre sql database folder
	 */
	public static String getPostgreSQLDbFolder() {
		return postgreSQLDbFolder;
	}

	/**
	 * @return the post gre sql database program
	 */
	public static String getPostgreSQLDbProgram() {
		return postgreSQLDbProgram;
	}

	/**
	 * @return the sqlite database program
	 */
	public static String getSqliteDbProgram() {
		return sqliteDbProgram;
	}

	/**
	 * @return the sql server database program
	 */
	public static String getSQLServerSbProgram() {
		return sqlServerDbProgram;
	}

	/**
	 * Starts the database server
	 * @return the database type
	 */
	public static Object startDatabase(int db, boolean show) throws AccessControlException {
		Object type;
		if (show == true) 
			do {
				type = JOptionPane.showInputDialog(null, "Please Select a database type!", "Database Type ",
						JOptionPane.QUESTION_MESSAGE, null, databaseTypes, databaseTypes[db]);
			} while (type == null);
		else 
			type = databaseTypes[db];
		try {
			if (type == databaseTypes[0]) {
				try {
					Process p = Runtime.getRuntime()
						.exec(
							"cmd /k cd " 
							+ getDatabaseFolder() 
							+ " && java -jar "
							+ getDerbyDbProgram() 
							+ " server start"
							);
					try {
						p.waitFor(9000, TimeUnit.MILLISECONDS);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			else if (type == databaseTypes[1]) {
				try {
					List<String> params = new ArrayList<>();
					params.add(getPostgreSQLDbProgram());
					params.add("start");
					params.add("-D");
					params.add(getPostgreSQLDbFolder());
					ProcessBuilder pb = new ProcessBuilder(params);
					pb.start();
					Threads.waitFor(5);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (AccessControlException ex) {
		}
		return type;
	}

	/** 
	 * Stops the database server
	 */
	public static void stopDatabase(Object type) {
		if (type == databaseTypes[0]) {
			try {
				List<String> params = new ArrayList<>();
				params.add("java");
				params.add("-jar");
				params.add(getDerbyDbProgram());
				params.add("server");
				params.add("shutdown");
				ProcessBuilder pb = new ProcessBuilder(params);
				pb.start();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}		
		else if (type == databaseTypes[1]) {
			try {
				List<String> params = new ArrayList<>();
				params.add(getPostgreSQLDbProgram());
				params.add("stop");
				params.add("-D");
				params.add(getPostgreSQLDbFolder());
				ProcessBuilder pb = new ProcessBuilder(params);
				pb.start();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
