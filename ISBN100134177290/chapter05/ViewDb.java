package chapter05;  // view

import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
import java.nio.file.Paths;
import java.nio.file.*;
 */
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.sql.rowset.*;
import javax.swing.*;
import ipi.*;

/**
 * {@code ViewDb} class Listing 5.4 <br />
 * {@link ViewDBFrame} inner class extends JFrame <br />
 * {@link DataPanel} inner class extends JPanel <br />
 * This program uses meta data to display arbitrary tables in a database. <br /> 
 * @version 1.33 2016-04-27
 * @author Cay Horstmann
 */
public class ViewDb {
	private static final String MAIN_CLASS = "chapter05.Chapter05";
	private static String message = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new ViewDBFrame();
			frame.setTitle("View DB");
			/**
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 */
			frame.setVisible(true);
			Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
		});
	}
}

/**
 * The frame that holds the data panel and the navigation buttons. <br />
 */
class ViewDBFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton previousButton;
	private JButton nextButton;
	private JButton deleteButton;
	private JButton saveButton;
	private DataPanel dataPanel;
	private Component scrollPane;
	private JComboBox<String> tableNames;
	private JComboBox<String> databaseApps;
	private Properties props;
	private CachedRowSet crs;
	private Connection conn;
	private int lastApp;
	
	public ViewDBFrame() {
		tableNames = new JComboBox<String>();
		
		/**
		 * Moved to getTables to allow selection of database application. <br /> 
		try {
			readDatabaseProperties();
			conn = getConnection();
			DatabaseMetaData meta = conn.getMetaData();
			try (ResultSet mrs = meta.getTables(null, null, null, new String[] {"TABLE"})) {
				while (mrs.next()) 
					tableNames.addItem(mrs.getString(3));
			}
		} catch (SQLException ex) {
			for (Throwable t : ex) {
				t.printStackTrace();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		 */
		JPanel header = new JPanel();
		header.setLayout(new GridLayout(2,1));
		databaseApps = new JComboBox<String>();
		databaseApps.addItem("Derby");  
		databaseApps.addItem("Post GRE");
		databaseApps.addItem("Ms Access");
		databaseApps.addItem("MySQL");
		databaseApps.addItem("SQLite");
		databaseApps.addItem("SQL Server");
		databaseApps.setSelectedIndex(3);
		databaseApps.addActionListener(event -> getTables());
		header.add(databaseApps, BorderLayout.NORTH);
		getTables();
		
		tableNames.addActionListener(
				event -> showTable((String) tableNames.getSelectedItem(), conn));
		header.add(tableNames, BorderLayout.SOUTH);
		add(header, BorderLayout.NORTH);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				try {
					if (conn != null) conn.close();
				} catch (SQLException ex) {
					for (Throwable t : ex)
						t.printStackTrace();
				}
			}
		});
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
		previousButton = new JButton("Previous");
		previousButton.addActionListener(event -> showPreviousRow());
		buttonPanel.add(previousButton);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(event -> showNextRow());
		buttonPanel.add(nextButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(event -> deleteRow());
		buttonPanel.add(deleteButton);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(event -> saveChanges());
		buttonPanel.add(saveButton);
		
		if (tableNames.getItemCount() > 0) 
			showTable(tableNames.getItemAt(0), conn);
	}

	/**
	 * 
	 */
	private void getTables() {
		if (lastApp >= 0) Databases.stopDatabase(lastApp);
		lastApp = databaseApps.getSelectedIndex();
		tableNames.removeAllItems();
		try {
			readDatabaseProperties();
			conn = getConnection();
			DatabaseMetaData meta = conn.getMetaData();
			try (ResultSet mrs = meta.getTables(null, null, null, new String[] {"TABLE"})) {
				while (mrs.next()) 
					tableNames.addItem(mrs.getString(3));
			}
		} catch (SQLException ex) {
			for (Throwable t : ex) {
				t.printStackTrace();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
		
	/**
	 * Prepares the text fields for showing a new table, and shows the first row. <br />
	 * @param tableName the name of the table to display <br />
	 * @param conn the database connection <br />
	 */
	public void showTable(String tableName, Connection conn) {
		try (Statement stat = conn.createStatement();
				ResultSet result = stat.executeQuery("select * from " + tableName)
				) {
			// get result set
			
			// copy into cached row set
			RowSetFactory factory = RowSetProvider.newFactory();
			crs = factory.createCachedRowSet();
			crs.setTableName(tableName);
			crs.populate(result);
			
			if (scrollPane != null) remove(scrollPane);
			dataPanel = new DataPanel(crs);
			scrollPane = new JScrollPane(dataPanel);
			add(scrollPane, BorderLayout.CENTER);
			pack();
			showNextRow();
		} catch (SQLException ex) {
			for (Throwable t : ex) 
				t.printStackTrace();
		}
	}
	
	/**
	 * Moves to the previous table row. <br />
	 */
	public void showPreviousRow() {
		try {
			if (crs == null || crs.isFirst()) return;
			crs.previous();
			dataPanel.showRow(crs);
		} catch (SQLException ex) { 
			for (Throwable t : ex) 
				t.printStackTrace();
		}
	}
	
	/**
	 * Moves to the next table row. <br />
	 */
	public void showNextRow() {
		try {
			if (crs == null || crs.isLast()) return;
			crs.next();
			dataPanel.showRow(crs);
		} catch (SQLException ex) {
			for (Throwable t : ex)
				t.printStackTrace();
		}
	}
	
	/**
	 * Deletes current table row. <br />
	 */
	public void deleteRow() {
		if (crs == null) return;
		new SwingWorker<Void, Void>() {
			public Void doInBackground() throws SQLException {
				crs.deleteRow();
				crs.acceptChanges(conn);
				if (crs.isAfterLast()) 
					if (!crs.last()) crs = null;
				return null;
			}
			
			public void done() {
				dataPanel.showRow(crs);
			}
		}.execute();
	}
	
	/** 
	 * Saves all changes. <br />
	 */
	public void saveChanges() {
		if (crs == null) return;
		new SwingWorker<Void, Void>() {
			public Void doInBackground() throws SQLException {
				dataPanel.setRow(crs);
				crs.acceptChanges(conn);
				return null;
			}
		}.execute();
	}
	
	private void readDatabaseProperties() throws IOException {
		Databases.startDatabase(databaseApps.getSelectedIndex(), false);
		String fileName = databaseApps.getSelectedItem().toString().toLowerCase().replace(" ", "-");
		props = new Properties();
		/**
		try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
			props.load(in);
		}
		 */
		props.load(new Loaders().getInputStream(
				"sourceFiles/properties/" + fileName + "-database-ch05.properties"
				));
		String drivers = props.getProperty("jdbc.drivers");
		if (drivers != null) System.setProperty("jdbc.drivers", drivers);
	}
	
	/** 
	 * Gets a connection from the porperties specified in the file database.properties. <br />
	 * @return the datatabase connection <br />
	 */
	private Connection getConnection() throws SQLException {
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

/**
 * This panel displays the contents of a result set. <br />
 */
class DataPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private java.util.List<JTextField> fields;
	
	/**
	 * Constructs the data panel. <br />
	 * @param rs the result set whose contents this panel displays <br />
	 */
	public DataPanel(RowSet rs) throws SQLException {
		fields = new ArrayList<>();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			gbc.gridy = i - 1;
			
			String columnName = rsmd.getColumnLabel(i);
			gbc.gridx = 0;
			gbc.anchor = GridBagConstraints.EAST;
			add(new JLabel(columnName), gbc);
			
			int columnWidth = rsmd.getColumnDisplaySize(i) / 2;
			if (columnWidth == 1073741823) columnWidth = 50;
			JTextField tb = new JTextField(columnWidth);
			if (!rsmd.getColumnClassName(i).equals("java.lang.String")) 
				tb.setEditable(false);
			
			fields.add(tb);
			
			gbc.gridx = 1;
			gbc.anchor = GridBagConstraints.WEST;
			add(tb, gbc);
		}
	}
	
	/**
	 * Shows a database row by populating all text fields with the column values. <br />
	 */
	public void showRow(ResultSet rs) {
		try {
			if (rs == null) return;
			for (int i = 1; i <= fields.size(); i++) {
				String field = rs == null ? "" : rs.getString(i);
				JTextField tb = fields.get(i - 1);
				tb.setText(field);
			}
		} catch (SQLException ex) {
			for (Throwable t : ex) 
				t.printStackTrace();
		}
	}
	
	/** 
	 * Updates changed data into the current row of the row set. <br />
	 */
	public void setRow(RowSet rs) throws SQLException {
		for (int i = 1; i <= fields.size(); i++) {
			String field = rs.getString(i);
			JTextField tb= fields.get(i - 1);
			if (!field.equals(tb.getText())) 
				rs.updateString(i, tb.getText());
		}
		rs.updateRow();
	}
}




