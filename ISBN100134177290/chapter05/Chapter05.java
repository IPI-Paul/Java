package chapter05;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ipi.*;

public class Chapter05 {
	public static final String CLASS_NAME = Chapter05.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String[] example = {
			"Listing 5.1 Test Database",
			"Listing 5.2 Execute SQL",
			"Listing 5.3 Query Test",
			"Listing 5.4 View Database"
			}; 
	private static int runAgain;
	private static String title = "Example Of";

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			Views.runMethod(CLASS_NAME, args[0]);
			return;
		}

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
				 * {@link TestDB} class Listing 5.1 <br />
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
				 */
				try {
					TestDB.main(null);
				} catch (IOException e) {
				}
				return;
			}
			else if (exampleType == example[1]) {
				/**
				 * {@link ExecSQL} class Listing 5.2 <br />
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
				try {
					ExecSQL.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[2]) {
				/**
				 * {@link QueryTest} class Listing 5.2 <br />
				 * <a href="../sourceFiles/text/queryTest-command-ch05.txt">
				 * queryTest-command-ch05.txt</a> SQL statements to execute <br /> 
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
				 * This program demonstrates several complex database queries. <br />
				 */
				try {
					QueryTest.main(null);
				} catch (IOException ex) {
				}
				return;
			}
			else if (exampleType == example[3]) {
				/**
				 * {@link ViewDb} class Listing 5.4 <br />
				 * {@link ViewDBFrame} inner class extends JFrame <br />
				 * {@link DataPanel} inner class extends JPanel <br />
				 * This program uses meta data to display arbitrary tables in a database. <br /> 
				 */
				ViewDb.main(null);
				return;
			}

			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?", "Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeApplication(CLASS_NAME);
	}
}

