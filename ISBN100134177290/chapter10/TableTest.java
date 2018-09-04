package chapter10; // table
import java.awt.*;
import java.awt.print.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ipi.*;

/**
 * {@link TableTest} class Listing 10.5 <br />
 * {@link PlanetTableFrame} inner class extends JFrame <br />
 * This program demonstrates how to show a simple table. <br /> 
 * @version 1.13 2016-05-10 
 * @author Paul
 *
 */
public class TableTest {
	private static final String MAIN_CLASS = "chapter10.Chapter10";
	private static String message = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new PlanetTableFrame();
			frame.setTitle("Table Test");
			/**
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 */
			frame.setVisible(true);
			Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
		});
	}
}


/**
 * This frame contains a table of planet data. <br />
 */
class PlanetTableFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Planet","Radius", "Moons", "Gaseous", "Color"};
	private Object[][] cells = {{"Mercury", 2440.0, 0, false, Color.YELLOW},
			{"Venus", 6052.0, 0, false, Color.YELLOW},
			{"Earth", 6378.0, 1, false, Color.BLUE},
			{"Mars", 3397.0, 2, false, Color.RED},
			{"Jupiter", 71492.0, 16, true, Color.ORANGE},
			{"Saturn", 60268.0, 18, true, Color.ORANGE},
			{"Uranus", 25559.0, 17, true, Color.BLUE},
			{"Neptune", 24766.0, 8, true, Color.BLUE},
			{"Pluto", 1137.0, 1, false, Color.BLACK}
	};
	
	public PlanetTableFrame() {
		final JTable table = new JTable(cells, columnNames);
		table.setAutoCreateRowSorter(true);
		add(new JScrollPane(table), BorderLayout.CENTER);
		JButton printButton = new JButton("Print");
		printButton.addActionListener(event -> {
			try {table.print(); }
			catch (SecurityException | PrinterException ex) { ex.printStackTrace(); }
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(printButton);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
}