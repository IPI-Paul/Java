package chapter10;  // tableCellRender

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import ipi.ResourcePaths;

/**
 * {@code PlanetTableModel} class implements TableModel Listing 10.9 <br />
 * {@link TableCellRenderFrame} class extends JFrame Listing 10.8 <br />
 * {@link ColorTableCellRenderer} class extends JPanel implements TableCellRenderer Listing 10.10 <br />
 * {@link ColorTableCellEditor} class extends AbstractCellEditor implements TableCellEditor  Listing 10.11 <br />
 * The planet table model specifies the values, rendering and editing properties for the planet data. <br />
 */
public class PlanetTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	public static final int PLANET_COLUMN = 0;
	public static final int MOONS_COLUMN = 2;
	public static final int GASEOUS_COLUMN = 3;
	public static final int COLOR_COLUMN = 4;
	
	private Object[][] cells = {
			{"Mercury", 2440.0, 0, false, Color.YELLOW, 
				new ImageIcon(ResourcePaths.getResource("img","Mercury.gif"))},
			{"Venus", 6052.0, 0, false, Color.YELLOW, 
				new ImageIcon(ResourcePaths.getResource("img","Venus.gif"))},
			{"Earth", 6378.0, 1, false, Color.BLUE, 
				new ImageIcon(ResourcePaths.getResource("img","Earth.gif"))},
			{"Mars", 3397.0, 2, false, Color.RED, 
				new ImageIcon(ResourcePaths.getResource("img","Mars.gif"))},
			{"Jupiter", 71492.0, 16, true, Color.ORANGE, 
				new ImageIcon(ResourcePaths.getResource("img","Jupiter.gif"))},
			{"Saturn", 60268.0, 18, true, Color.ORANGE, 
				new ImageIcon(ResourcePaths.getResource("img","Saturn.gif"))},
			{"Uranus", 25559.0, 17, true, Color.BLUE, 
				new ImageIcon(ResourcePaths.getResource("img","Uranus.gif"))},
			{"Neptune", 24766.0, 8, true, Color.BLUE, 
				new ImageIcon(ResourcePaths.getResource("img","Neptune.gif"))},
			{"Pluto", 1137.0, 1, false, Color.BLACK, 
				new ImageIcon(ResourcePaths.getResource("img","Pluto.gif"))}
	};

	private String[] columnNames = {"Planet","Radius", "Moons", "Gaseous", "Color", "Image"};
	
	public String getColumnName(int c) {
		return columnNames[c];
	}
	
	public Class<?> getColumnClass(int c) {
		return cells[0][c].getClass();
	}
	
	public int getColumnCount() {
		return cells[0].length;
	}
	
	public int getRowCount() {
		return cells.length;
	}
	
	public Object getValueAt(int r, int c) {
		return cells[r][c];
	}
	
	public void setValueAt(Object obj, int r, int c) {
		cells[r][c] = obj;
	}
	
	public boolean isCellEditable(int r, int c) {
		return c == PLANET_COLUMN || c == MOONS_COLUMN || c == GASEOUS_COLUMN || c == COLOR_COLUMN;
	}
}
