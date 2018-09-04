package chapter05;  // test

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.AccessControlException;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import ipi.*;

/**
 * {@code TestDB} class Listing 5.1 <br />
 * <a href="../sourceFiles/properties/derby-database-ch05.properties">
 * derby-database-ch05.properties</a> Apache Derby connection settings <br /> 
 * <a href="../sourceFiles/properties/ms-access-database-ch05.properties">
 * ms-access-database-ch05.properties</a> Ms Access connection settings <br />
 * <a href="../sourceFiles/properties/mysql-database-ch05.properties">
 * mysql-database-ch05.properties</a> MySQL connection settings <br />
 * <a href="../sourceFiles/properties/post-gre-database-ch05.properties">
 * post-gre-database-ch05.properties</a> Post Gre SQL connection settings <br />
 * <a href="../sourceFiles/properties/sql-server-database-ch05.properties">
 * sql-server-database-ch05.properties</a> SQL Server connection settings <br />
 * <a href="../sourceFiles/properties/sqlite-database-ch05.properties">
 * sqlite-database-ch05.properties</a> SQLite connection settings <br />
 * This program tests that the database and the JDBC driver are correctly configured. <br />
 * @version 1.02 2012-06-05
 * @author Cay Horstmann
 */
public class TestDB {
	private static final String MAIN_CLASS = "chapter05.Chapter05";
	private static String message = "";
	private static Object dbType;
	private static Loaders file = new Loaders();
	private static int runAgain;
	
	public static void main(String[] args) throws IOException {
		try {
			runTest();
		} catch (SQLException ex) {
			for (Throwable t : ex) 
				t.printStackTrace();
		}
	}
	
	/**
	 * Runs a test by creating a table, adding a value, showing the table contents, and removing 
	 * the table. <br />
	 */
	public static void runTest() throws SQLException, IOException {
		/**
		 * Amended to enable multiple re-runs using various database types. <br /> 
		 */
		do {
			/**
			 * Amended to enable Web Start mode. Starts database server and waits 5 seconds for server to be 
			 * fully functional. <br />
			 */
			try {
				dbType = Databases.startDatabase(0, true);
			} catch (AccessControlException ex) {
				message = Threads.getFullTrusMessage();
				Views.openWindowOpener(MAIN_CLASS, message);
			}
			String fileName = dbType.toString().toLowerCase().replace(" ", "-");
			Charset cs = StandardCharsets.UTF_8;
			file.setChoice("DS", "prop", "", fileName + "-database-ch05.properties", cs, "", "",  
					"For Properties File");
			if (file.getChoice() == false) {
				Views.openWindowOpener(MAIN_CLASS, message);
				return;
			}
			try (Connection conn = getConnection(); Statement stat = conn.createStatement()) {
				stat.executeUpdate("create table Greetings (Message char(20))");
				stat.executeUpdate("insert into Greetings values ('Hello, World!')");
				
				try (ResultSet result = stat.executeQuery("select * from Greetings")) {
					if (result.next())
						System.out.println(result.getString(1));
				}
				stat.executeUpdate("drop table Greetings");
			} catch (NullPointerException ex) {
			}
			/**
			 * Amended to enable server shutdown process on completion of current thread. <br />
			 */
			try {
				Databases.stopDatabase(dbType);;
			} catch (AccessControlException ex) {
				message = Threads.getFullTrusMessage();
			}

			runAgain = JOptionPane.showConfirmDialog(null, "Do you want to run again?", 
					"Run Again", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);

		String text = message; 
		message = "";
		Views.openWindowOpener(MAIN_CLASS, text);
	}
	
	/**
	 * Gets a connection from the properties specified in the file database.properties. <br />
	 * @return the database connection <br />
	 */
	public static Connection getConnection() throws SQLException, IOException {
		Properties props = new Properties();
		String path = file.getFilePath();
		try (InputStream in = Files.newInputStream(
				Paths.get(path))) {
			props.load(in);
		/**
		 * Amended to enable Web Start mode and loading sourceFiles from the jar file. <br />
		 */
		} catch (Exception e) {
			props.load(file.getInputStream());
		}
		try {
			String drivers = props.getProperty("jdbc.drivers");
			if (drivers != null) System.setProperty("jdbc.drivers", drivers);
			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			/**
			 * Amended for Password Input Dialog. Default password is used if no new password is given. <br />
			 */
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Hello "+ username + ", please enter a password: ");
			JPasswordField pwd = new JPasswordField(10);
			panel.add(label);
			panel.add(pwd);
			String[] options = new String[] {"OK", "Cancel"};
			int option = JOptionPane.showOptionDialog(null, panel, "Sever Connection Password", 
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, pwd);
			if (option == 0 && (new String(pwd.getPassword())).length() != 0) 
				password = new String(pwd.getPassword());
	
			// DriverManager.setLogWriter(new PrintWriter(System.out));
			url = url.replace("DbFolder", Databases.getDatabaseFolder().replace("\"", ""));
			try {
				return DriverManager.getConnection(url , username, password);
			} catch (Exception e) {
				message = e.getMessage();
				return null;
			}
		} catch (AccessControlException ex) {
			message = Threads.getFullTrusMessage();
			return null;
		}
	}
}
