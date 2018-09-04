package chapter10;  // tableCellRender

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * {@code ColorTableCellEditor} class extends AbstractCellEditor implements TableCellEditor  Listing 10.11 <br />
 * {@link TableCellRenderFrame} class extends JFrame Listing 10.8 <br />
 * {@link PlanetTableModel} class implements TableModel Listing 10.9 <br />
 * {@link ColorTableCellRenderer} class extends JPanel implements TableCellRenderer Listing 10.10 <br />
 * This editor pops up a color dialog to edit a cell value. <br />
 */
public class ColorTableCellEditor extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 1L;
	private JColorChooser colorChooser;
	private JDialog colorDialog;
	private JPanel panel;
	
	public ColorTableCellEditor() {
		panel = new JPanel();
		
		//prepare color dialog
		colorChooser = new JColorChooser();
		colorDialog = JColorChooser.createDialog(null, "Planet Color", false, colorChooser, 
				EventHandler.create(ActionListener.class, this, "stopCellEditing"), 
				EventHandler.create(ActionListener.class, this, "cancelCellEditing"));
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
			int row, int column) {
		// this is where we get the current Color value. We store it in the dialog in case the user 
		// starts editing
		colorChooser.setColor((Color) value);
		return panel;
	}
	
	public boolean shouldSelectCell(EventObject anEvent) {
		// start editing
		colorDialog.setVisible(true);
		
		// tell caller it is ok to select this cell
		return true;
	}
	
	public void cancelCellEditing() {
		// editing is canceled--hide dialog
		colorDialog.setVisible(false);
		super.cancelCellEditing();
	}
	
	public boolean stopCellEditing() {
		// editing is complete--hide dialog
		colorDialog.setVisible(false);
		super.stopCellEditing();
		
		// tell caller it is ok to use color value
		return true;
	}
	
	public Object getCellEditorValue() {
		return colorChooser.getColor();
	}
}
