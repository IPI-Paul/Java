package chapter10;  // tableCellRender

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import ipi.ResourcePaths;

/**
 * {@code TableCellRenderFrame} class extends JFrame Listing 10.8 <br />
 * {@link PlanetTableModel} class implements TableModel Listing 10.9 <br />
 * {@link ColorTableCellRenderer} class extends JPanel implements TableCellRenderer Listing 10.10 <br />
 * {@link ColorTableCellEditor} class extends AbstractCellEditor implements TableCellEditor  Listing 10.11 <br />
 * This frame contains a table of planet data. <br />
 */
public class TableCellRenderFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
	
	public TableCellRenderFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		TableModel model = new PlanetTableModel();
		JTable table = new JTable(model);
		table.setRowSelectionAllowed(false);
		
		// set up renderers and editors
		table.setDefaultRenderer(Color.class, new ColorTableCellRenderer());
		table.setDefaultEditor(Color.class, new ColorTableCellEditor());
		
		JComboBox<Integer> moonCombo = new JComboBox<>();
		for (int i = 0; i <=20; i++) 
			moonCombo.addItem(i);
		
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn moonColumn = columnModel.getColumn(PlanetTableModel.MOONS_COLUMN);
		moonColumn.setCellEditor(new DefaultCellEditor(moonCombo));
		moonColumn.setHeaderRenderer(table.getDefaultRenderer(ImageIcon.class));
		moonColumn.setHeaderValue(new ImageIcon(ResourcePaths.getResource("img", "Moons.gif")));
		
		//show table
		table.setRowHeight(100);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
}
