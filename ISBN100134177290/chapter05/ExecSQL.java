package chapter05;  // exec

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.AccessControlException;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.sql.*;
import ipi.*;

/**
 * {@code ExecSQL} class Listing 5.2 <br />
 * <a href="../sourceFiles/text/execsql-command-ch05.txt">
 * execsql-command-ch05.txt</a> SQL statements to execute <br /> 
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
 * <a href="../sourceFiles/scripts/Authors-ch05.sql">
 * Authors-ch05.sql</a> List of Authors <br />
 * <a href="../sourceFiles/scripts/Authors-msaccess-ch05.sql">
 * Authors-msaccess-ch05.sql</a> List of Authors <br />
 * <a href="../sourceFiles/scripts/Authors-sqlite-ch05.sql">
 * Authors-sqlite-ch05.sql</a> List of Authors <br />
 * <a href="../sourceFiles/scripts/Authors-sqlserver-ch05.sql">
 * Authors-sqlserver-ch05.sql</a> List of Authors <br />
 * <a href="../sourceFiles/scripts/Books-ch05.sql">
 * Books-ch05.sql</a> List of Books <br />
 * <a href="../sourceFiles/scripts/BooksAuthors-ch05.sql">
 * BooksAuthors-ch05.sql</a> List of Books and their Authors <br />
 * <a href="../sourceFiles/scripts/Publishers-ch05.sql">
 * Publishers-ch05.sql</a> List of Publishers <br />
 * <a href="../sourceFiles/scripts/Publishers-msaccess-ch05.sql">
 * Publishers-msaccess-ch05.sql</a> List of Publishers <br />
 * <a href="../sourceFiles/scripts/Publishers-sqlite-ch05.sql">
 * Publishers-sqlite-ch05.sql</a> List of Publishers <br />
 * <a href="../sourceFiles/scripts/Publishers-sqlserver-ch05.sql">
 * Publishers-sqlserver-ch05.sql</a> List of Publishers <br />
 * Executes all SQL statements in a file. You can call this program as 
 * java-classpath driverPath:. ExecSQL commandFile <br />
 */
public class ExecSQL {
	private static final String MAIN_CLASS = "chapter05.Chapter05";
	private static String message = "";
	private static Object dbType;
	private static Loaders file = new Loaders();
	private static Loaders cmdFile = new Loaders();
	private static Charset cs = StandardCharsets.UTF_8;
	private static int runAgain;
	
	public static void main(String[] args) throws IOException {
		/**
		 * Amended to enable Web Start mode, also gives the user the option of either using a default file 
		 * or the file open dialog to select and open a file <br />
		 * Moved to after connection method to allow multiple scripts being run against the same database. <br />
		try (Scanner in = args.length == 0 ? new Scanner(System.in)
				: new Scanner(Paths.get(args[0]), "UTF-8")
				) {
		 */
		Scanner in = new Scanner("");
		try (Connection conn = getConnection();
				Statement stat = conn.createStatement()
				) {
			do {
				int cmdType = 0;
				if (args == null || args.length == 0)
				cmdType = cmdFile.setChoice("CDS", "scr", "", "execsql-command-ch05.sql", cs, "", "",  
						"For SQL Command File");
				if (cmdFile.getChoice() == false) {
					message = "";
					Views.openWindowOpener(MAIN_CLASS, message);
					return;
				}
				if (cmdType == 0)
					in = args == null || args.length == 0 ? new Scanner(System.in)
								: new Scanner(Paths.get(args[0]), "UTF-8");
				else 
					in = args == null || args.length == 0 ? new Scanner(cmdFile.getInputStream())
							: new Scanner(Paths.get(args[0]), "UTF-8");
					
				String help = "Enter command (e.g. select * from world.country) or type 'Exit' to exit:";
				while (true) {
					if (args == null || args.length == 0) {
						if (Threads.hasConsoleView() == true && cmdType == 0) {
							System.out.println(help);
						} else if (cmdType == 0) {
							Object command = JOptionPane.showInputDialog(help);
							in = new Scanner(command.toString());
						}
					}
					if (!in.hasNextLine()) return;
					
					String line = in.nextLine().trim();
					if (line.equalsIgnoreCase("EXIT")) {
						runAgain = JOptionPane.showConfirmDialog(null, "Do you want to run again?", 
								"Run Again", JOptionPane.YES_NO_OPTION);
						break;
						// return;
					}
					if (line.endsWith(";"))  {// remove trailing semicolon 
						line = line.substring(0, line.length() - 1);
					}
					try {
						boolean isResult = stat.execute(line);
						if (isResult) {
							try (ResultSet rs = stat.getResultSet()) {
								showResultSet(rs);
							}
						} else {
							int updateCount = stat.getUpdateCount();
							System.out.println(updateCount + " rows updated");
						}
					} catch (SQLException ex) {
						for (Throwable e : ex) 
							e.printStackTrace();
					}
				}
			}
			while (runAgain == JOptionPane.YES_OPTION);
		} catch (SQLException e) {
			message = e.getMessage();
			for (Throwable t : e) 
				t.printStackTrace();
		}
		Threads.closeStream(in);
		Databases.stopDatabase(dbType);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	/**
	 * Gets a connection from the properties specified in the file database.properties. <br />
	 * @return the database connection <br />
	 */
	public static Connection getConnection() throws SQLException, IOException {
		/**
		 * Amended to enable Web Start mode. Starts database server and waits 5 seconds for server to be 
		 * fully functional. <br />
		 */
		try {
			dbType = Databases.startDatabase(3, true);
		} catch (AccessControlException ex) {
			message = Threads.getFullTrusMessage();
			Views.openWindowOpener(MAIN_CLASS, message);
		}
		String fileName = dbType.toString().toLowerCase().replace(" ", "-");
		file.setChoice("DS", "prop", "", fileName + "-database-ch05.properties", cs, "", "",  
				"For Properties File");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
		}
		Properties props = new Properties();
		/**
		 * Amended to enable Web Start mode. <br />
		try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
			props.load(in);
		}
		 */
		props.load(file.getInputStream());
		String drivers = props.getProperty("jdbc.drivers");
		if (drivers != null) System.setProperty("jdbc.drivers", drivers);
		
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.pawwsord");
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
		
		url = url.replace("DbFolder", Databases.getDatabaseFolder().replace("\"", ""));
		return DriverManager.getConnection(url, username, password);
	}
	
	/**
	 * Prints a result set. <br />
	 * @param result the result set to be printed <br />
	 */
	public static void showResultSet(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		
		for (int i = 1; i <= columnCount; i++) {
			if (i > 1) System.out.print(", ");
			System.out.print(metaData.getColumnLabel(i));
		}
		System.out.println();
		
		while (result.next()) {
			for (int i = 1; i <= columnCount; i++) {
				if (i > 1) System.out.print(", ");
				System.out.print(result.getString(i));
			}
			System.out.println();
		}
	}
}
