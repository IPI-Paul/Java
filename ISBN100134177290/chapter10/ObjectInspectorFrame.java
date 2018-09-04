package chapter10;  // treeModel

import java.awt.*;
import javax.swing.*;

/**
 * {@code ObjectInspectorFrame} class extends JFrame Listing 10.16 <br />
 * {@link ObjectTreeModel} class implements TreeModel Listing 10.17 <br />
 * {@link Variable} class Listing 10.18 <br />
 * This frame holds the object tree. 
 */
public class ObjectInspectorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTree tree;
	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 500;
	
	public ObjectInspectorFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// we inspect this frame object
		Variable v = new Variable(getClass(), "this", this);
		ObjectTreeModel model = new ObjectTreeModel();
		model.setRoot(v);
		
		// construct and show tree
		tree = new JTree(model);
		add(new JScrollPane(tree), BorderLayout.CENTER);
	}
}
