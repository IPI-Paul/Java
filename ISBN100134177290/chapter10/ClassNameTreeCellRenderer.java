package chapter10;  // treeRender

import java.awt.*;
import java.lang.reflect.*;
import javax.swing.*;
import javax.swing.tree.*;

/**
 * {@code ClassNameTreeCellRenderer} class extends DefaultTreeCellRenderer Listing 10.15 <br />
 * {@link ClassTreeFrame} class extends JFrame Listing 10.14 <br />
 * This class renders a class name either in plain or italic. Abstract classes are italic.
 */
public class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = 1L;
	private Font plainFont = null;
	private Font italicFont = null;
	
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
			boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		// get user object
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Class<?> c = (Class<?>) node.getUserObject();
		
		// the first time, derive italic font from plain font
		if (plainFont == null) {
			plainFont = getFont();
			// the tree cell renderer is sometimes called with a label that has a null font
			if (plainFont != null) italicFont = plainFont.deriveFont(Font.ITALIC);
		}
		
		// set font to italic if the class is abstract, plain otherwise
		if ((c.getModifiers() & Modifier.ABSTRACT) == 0) setFont(plainFont);
		else setFont(italicFont);
		return this;
	}
}
