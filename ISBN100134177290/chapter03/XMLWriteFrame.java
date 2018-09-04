package chapter03;  // write

/**
import java.io.*;
import java.nio.file.*;
 */
import javax.swing.*;
import javax.xml.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import ipi.*;

/**
 * A frame with a component for showing a modern drawing. <br />
 */
public class XMLWriteFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private RectangleComponent comp;
	/**
	 * Amended to enable web start mode <br />
	private JFileChooser chooser;
	 */
	private Loaders file = new Loaders();
	
	public XMLWriteFrame() {
		/**
		chooser = new JFileChooser();
		 */
		
		// add component to frame
		comp = new RectangleComponent();
		add(comp);
		
		// set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem newItem = new JMenuItem("New");
		menu.add(newItem);
		newItem.addActionListener(event -> comp.newDrawing());
		
		JMenuItem saveItem = new JMenuItem("save with DOM/XSLT");
		menu.add(saveItem);
		saveItem.addActionListener(event -> saveDocument());
		
		JMenuItem saveStAXItem = new JMenuItem("Save with StAX");
		menu.add(saveStAXItem);
		saveStAXItem.addActionListener(event -> saveStAX());
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(event -> /** System.exit(0) */ Views.closeWindow(this));
		pack();
		setSize(400, 300);
	}
	
	/**
	 * Saves the drawing in SVG format, using DOW/XSLT. <br />
	 */
	public void saveDocument() {
		try {
			if (file.getOutputStream("img", "sourceFiles/images") != null) {
				/**
				if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
				File file = chooser.getSelectedFile();
				 */
				Document doc = comp.buildDocument();
				Transformer t = TransformerFactory.newInstance().newTransformer();
				t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, 
						"http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg20000802.dtd");
				t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, 
						"-//W3C//DTD SVG 20000802//EN");
				t.setOutputProperty(OutputKeys.INDENT, "yes");
				t.setOutputProperty(OutputKeys.METHOD, "xml");
				t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				/**
					t.transform(new DOMSource(doc), new StreamResult(Files.newOutputStream(file.toPath())));
				 */
				t.transform(new DOMSource(doc), new StreamResult(file.getOutputStream()));
			}
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Saves the drawing in SVG format, using StAX. <br />
	 */
	public void saveStAX() {
		if (file.getOutputStream("img", "sourceFiles/images") != null) {
		/**
		if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
		File file = chooser.getSelectedFile();
		 */
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			/**
				XMLStreamWriter writer = factory.createXMLStreamWriter(
						Files.newOutputStream(file.toPath()));
			 */
			XMLStreamWriter writer= factory.createXMLStreamWriter(file.getOutputStream());
			try {
				comp.writeDocument(writer);
			} finally {
				writer.close(); // Not autocloseable
			}
		} catch (XMLStreamException ex) {
			ex.printStackTrace();
		}
		}
	}
}
