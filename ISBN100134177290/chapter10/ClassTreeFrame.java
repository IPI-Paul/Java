package chapter10;  // treeRender

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.tree.*;

import ipi.ResourcePaths;

/**
 * {@code ClassTreeFrame} class extends JFrame Listing 10.14 <br />
 * {@link ClassNameTreeCellRenderer} class extends DefaultTreeCellRenderer Listing 10.15 <br />
 * This frame displays the class tree, a text field and a "Add" button to add more classes
 * into the tree. <br />
 */
public class ClassTreeFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 500;
	
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private JTree tree;
	private JTextField textField;
	private JTextArea textArea;
	
	public ClassTreeFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// the root of the class tree is Object
		root = new DefaultMutableTreeNode(java.lang.Object.class);
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		
		// add this class to populate the tree with some data
		addClass(getClass());
		
		// set up node icons
		ClassNameTreeCellRenderer renderer = new ClassNameTreeCellRenderer();
		renderer.setClosedIcon(new ImageIcon(ResourcePaths.getResource("img", "red-ball.gif")));
		renderer.setOpenIcon(new ImageIcon(ResourcePaths.getResource("img", "yellow-ball.gif")));
		renderer.setLeafIcon(new ImageIcon(ResourcePaths.getResource("img", "blue-ball.gif")));
		tree.setCellRenderer(renderer);
		
		// set up selection mode
		tree.addTreeSelectionListener(event -> {
			// the user selected a different node--update description
			TreePath path = tree.getSelectionPath();
			if (path == null) return;
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
			Class<?> c = (Class<?>) selectedNode.getUserObject();
			String description = getFieldDescription(c);
			textArea.setText(description);
		});
		
		int mode = TreeSelectionModel.SINGLE_TREE_SELECTION;
		tree.getSelectionModel().setSelectionMode(mode);
		
		// this text are holds the class description
		textArea = new JTextArea();
		
		// add tree and text area
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(new JScrollPane(tree));
		panel.add(new JScrollPane(textArea));
		
		add(panel, BorderLayout.CENTER);
		
		addTextField();
	}
	
	/**
	 * Add the text field and "Add" button to add a new class.
	 */
	public void addTextField() {
		JPanel panel = new JPanel();
		
		ActionListener addListener = event -> {
			// add the class whose name is in the text field
			try {
				String text = textField.getText();
				addClass(Class.forName(text)); 
				// clear text field to indicate success
				textField.setText("");
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Class not found");
			}
		};
		
		// new class names are typed into this text field 
		textField = new JTextField(20);
		textField.addActionListener(addListener);
		panel.add(textField);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(addListener);
		panel.add(addButton);
		
		add(panel, BorderLayout.SOUTH);
	}
	
	/**
	 * Finds an object in the tree. <br />
	 * @param obj the object to find <br />
	 * @return the node containing the object or null if the object is not present in the tree 
	 */
	@SuppressWarnings("unchecked")
	public DefaultMutableTreeNode findUserObject(Object obj) {
		// find the node containing a user object
		Enumeration<TreeNode> e = (Enumeration<TreeNode>) root.breadthFirstEnumeration();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
			if (node.getUserObject().equals(obj)) return node;
		}
		return null;
	}
	
	/** 
	 * Adds a new class and any parent classes that aren't yet part of the tree <br />
	 * @param c the class to add <br />
	 * @return the newly added node
	 */
	public DefaultMutableTreeNode addClass(Class<?> c) {
		// add a new class to the tree
		
		//skip non-class types
		if (c.isInterface() || c.isPrimitive()) return null;
		
		// if the class is alredy in the tree, return its node
		DefaultMutableTreeNode node = findUserObject(c);
		if (node != null) return node;
		
		// class isn't present--first ass class parent recursively
		Class<?> s = c.getSuperclass();
		
		DefaultMutableTreeNode parent;
		if (s == null) parent = root;
		else parent = addClass(s);
		
		// add the class as a child to the parent
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
		model.insertNodeInto(newNode, parent, parent.getChildCount());
		
		// make node visible
		TreePath path = new TreePath(model.getPathToRoot(newNode));
		tree.makeVisible(path);
		
		return newNode;
	}
	
	/**
	 * Returns a description of the fields of a class. <br />
	 * @param the class to be described <br />
	 * @return a string containing all field types and names
	 */
	public static String getFieldDescription(Class<?> c) {
		// use reflection to find types and names of fields
		StringBuilder r = new StringBuilder();
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			if ((f.getModifiers() & Modifier.STATIC) != 0) r.append("static ");
			r.append(f.getType().getName());
			r.append(" ");
			r.append(f.getName());
			r.append("\n");			
		}
		return r.toString();
	}
}
