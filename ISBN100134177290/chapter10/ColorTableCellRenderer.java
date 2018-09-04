package chapter10;  // tableCellRender

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * {@code ColorTableCellRenderer} class extends JPanel implements TableCellRenderer Listing 10.10 <br />
 * {@link TableCellRenderFrame} class extends JFrame Listing 10.8 <br />
 * {@link PlanetTableModel} class implements TableModel Listing 10.9 <br />
 * {@link ColorTableCellEditor} class extends AbstractCellEditor implements TableCellEditor  Listing 10.11 <br />
 * This cell renderer renders a color value as a panel with the given color. <br />
 */
public class ColorTableCellRenderer extends JPanel implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
		setBackground((Color) value); 
		if (hasFocus) 
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		else setBorder(null);
		return this;
	}
}
