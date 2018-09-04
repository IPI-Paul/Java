package chapter05;  // query

import java.io.*;
/**
import java.nio.file.*;
 */
import java.security.AccessControlException;
import java.sql.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import ipi.*;

/**
 * {@code QueryTest} class Listing 5.2 <br />
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
 * @version 1.30 2012-06-05
 * @author Cay Horstmann
 */
public class QueryTest {
	private static final String MAIN_CLASS = "chapter05.Chapter05";
	private static String message = "";
	private static Object dbType;
	private static int runAgain;
	
	public static final String allQuery = "select Books.Price, Books.Title from Books";
	private static final String authorPublisherQuery = "select Books.Price, Books.Title" 
			+ " from Books, BooksAuthors, Authors, Publishers"
			+ " where Authors.Author_Id = BooksAuthors.Author_Id and BooksAuthors.ISBN = Books.ISBN" 
			+ " and Books.Publisher_Id = Publishers.Publisher_Id and Authors.Name = ?"
			+ " and Publishers.Name = ?";
	private static final String authorQuery = "select Books.Price, Books.Title from Books, BooksAuthors, Authors"
			+ " where Authors.Author_Id = BooksAuthors.Author_Id and BooksAuthors.ISBN = Books.ISBN"
			+ " and Authors.Name = ?";
	private static final String publisherQuery = "select Books.Price, Books.Title from Books, Publishers"
			+ " where Books.Publisher_Id = Publishers.Publisher_Id and Publishers.Name = ?";
	private static final String priceUpdate = "update Books " + "set Price = Price + ?"
			+ " where Books.Publisher_Id = (select Publisher_Id from Publishers where Name = ?)";
	private static Scanner in;
	private static ArrayList<String> authors = new ArrayList<>();
	private static ArrayList<String> publishers = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		/**
		 * Amended to enable multiple re-runs using various database types. <br /> 
		 */
		do {
			try (Connection conn = getConnection()) {
				in = new Scanner(System.in);
				authors.add("Any");
				publishers.add("Any");
				try (Statement stat = conn.createStatement()) {
					// Fill the authors array list
					String query = "select Name from Authors";
					try (ResultSet rs = stat.executeQuery(query)) {
						while (rs.next())
							authors.add(rs.getString(1));
					}
					
					// Fill the publishers array list
					query = "select Name from Publishers";
					try (ResultSet rs = stat.executeQuery(query)) {
						while (rs.next())
							publishers.add(rs.getString(1));
					}
				}
				boolean done = false;
				while (!done) {
					String help = "Q)uery C)hange E)xit: ";
					String input;
					if (Threads.hasConsoleView() == true) {
						System.out.print(help);
						input = in.next().toUpperCase();
					} else {
						Object command = JOptionPane.showInputDialog(help);
						input = command.toString().toUpperCase();
					}
					if (input.equals("Q"))
						executeQuery(conn);
					else if (input.equals("C"))
						changePrices(conn);
					else 
						done = true;
				}
				message = "";
			} catch (SQLException e) {
				message = e.getMessage();
				for (Throwable t : e) 
					System.out.println(t.getMessage());
			}
			Databases.stopDatabase(dbType);
			authors.clear();
			publishers.clear();

			runAgain = JOptionPane.showConfirmDialog(null, "Do you want to run again?", 
					"Run Again", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeStream(in);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	/**
	 * Executes the selected query. <br />
	 * @param conn the database connection <br />
	 */
	private static void executeQuery(Connection conn) throws SQLException {
		String author = select("Authors:", authors);
		String publisher = select("Publishers:", publishers);
		PreparedStatement stat;
		if (!author.equals("Any") && !publisher.equals("Any")) {
			stat = conn.prepareStatement(authorPublisherQuery);
			stat.setString(1, author);
			stat.setString(2, publisher);
		} else if (!author.equals("Any") && publisher.equals("Any")) {
			stat = conn.prepareStatement(authorQuery);
			stat.setString(1, author);
		} else if (author.equals("Any") && !publisher.equals("Any")) {
			stat = conn.prepareStatement(publisherQuery);
			stat.setString(1, publisher);
		} else 
			stat = conn.prepareStatement(allQuery);
		
		try (ResultSet rs = stat.executeQuery()) {
			while (rs.next())
				System.out.println(rs.getString(1) + ", " + rs.getString(2));
		}
	}
	
	/** 
	 * Executes an update statement to change price. <br />
	 * @param conn the database connection <br />
	 */
	private static void changePrices(Connection conn) throws SQLException {
		String publisher = select("Publihers:", publishers.subList(1, publishers.size()));
		System.out.print("Change prices by: ");
		double priceChange = in.nextDouble();
		PreparedStatement stat = conn.prepareStatement(priceUpdate);
		stat.setDouble(1, priceChange);
		stat.setString(2, publisher);
		int r = stat.executeUpdate();
		System.out.println(r + " records updated.");
	}
	
	/**
	 * Asks the user to select a string. <br />
	 * @param prompt the prompt to display <br />
	 * @param options the options from which the user can choose <br />
	 * @return the option that the user chose <br />
	 */
	public static String select(String prompt, List<String> options) {
		while (true) {
			System.out.println(prompt);
			for (int i = 0; i < options.size(); i++) 
				System.out.printf("%2d) %s%n", i + 1, options.get(i));
			int sel;
			if (Threads.hasConsoleView() == true) {
				sel = in.nextInt();
			} else {
				Object command = JOptionPane.showInputDialog(prompt);
				sel = Integer.parseInt(command.toString());
			}
			if (sel > 0 && sel <= options.size()) 
				return options.get(sel - 1);
		}
	}
	
	/**
	 * Gets a connection from the properties specified ion the file database.properties. <br />
	 * @return the database connection <br />
	 */
	public static Connection getConnection() throws SQLException, IOException {
		Properties props = new Properties();
		/**
		 * Amended to enable Web Start mode. Starts database server and waits 5 seconds for server to be 
		 * fully functional. <br />
		try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
		 */
		try {
			dbType = Databases.startDatabase(0, true);
		} catch (AccessControlException ex) {
			message = Threads.getFullTrusMessage();
			Views.openWindowOpener(MAIN_CLASS, message);
		}
		String fileName = dbType.toString().toLowerCase().replace(" ", "-");
		try (InputStream in = new Loaders().getInputStream(
				"sourceFiles/properties/" + fileName + "-database-ch05.properties"
				)) {
			props.load(in);
		}
		
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
	
		url = url.replace("DbFolder", Databases.getDatabaseFolder().replace("\"", ""));
		return DriverManager.getConnection(url, username, password);
	}
}
