package chapter03;  // xpath

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.security.AccessControlException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.xml.namespace.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import ipi.*;

/**
 * {@code XPathTester} class Listing 3.7 <br />
 * {@link XPathFrame} inner class extends JFrame <br />
 * This program evaluates {@link XPath} expressions. <br />
 * @version 1.02 2016-05-10
 * @author Cay Horstmann
 */
public class XPathTester {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new XPathFrame();
			frame.setTitle("XPath Test");
			/**
			 * Amended with openWindowOpenerListener to enable Switchboard
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 */
			frame.setVisible(true);
			Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
		});
	}
}

/**
 * This frame shows an XML document, a panel to type an XPath expression, and a text field to 
 * display the result. <br /> 
 */
class XPathFrame extends JFrame {
	private static Loaders file = new Loaders();
	
	private static final long serialVersionUID = 1L;
	private DocumentBuilder builder;
	private Document doc;
	private XPath path;
	private JTextField expression;
	private JTextField result;
	private JTextArea docText;
	private JComboBox<String> typeCombo;
	
	public XPathFrame() {
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(event -> openFile());
		fileMenu.add(openItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(event -> /** System.exit(0) */ Views.closeWindow(this));
		fileMenu.add(exitItem);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		ActionListener listener = event -> evaluate();
		expression = new JTextField(20);
		expression.addActionListener(listener);
		JButton evaluateButton = new JButton("Evaluate");
		evaluateButton.addActionListener(listener);
		
		typeCombo = new JComboBox<>(new String[] {
				"STRING", "NODE", "NODESET", "NUMBER", "BOOLEAN"
		});
		typeCombo.setSelectedItem("STRING");
		
		JPanel panel = new JPanel();
		panel.add(expression);
		panel.add(typeCombo);
		panel.add(evaluateButton);
		docText = new JTextArea(10, 40);
		result = new JTextField();
		result.setBorder(new TitledBorder("Result"));
		
		add(panel, BorderLayout.NORTH);
		add(new JScrollPane(docText), BorderLayout.CENTER);
		add(result, BorderLayout.SOUTH);
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(this, e);
		}
		
		XPathFactory xpfactory = XPathFactory.newInstance();
		path = xpfactory.newXPath();
		pack();
		setSize(800, 500);
	}
	
	/**
	 * Open a file and load the document. <br />
	 */
	public void openFile() {
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("chapter03/"));
			
			chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
			int r = chooser.showOpenDialog(this);
			if(r != JFileChooser.APPROVE_OPTION) return;
			/**
			File file = chooser.getSelectedFile();
			 */
			file.setFile(chooser.getSelectedFile());
			try {
				docText.setText(new String(Files.readAllBytes(file.getFile().toPath())));
				builder.setEntityResolver(file.getEntityResolver(builder));
				doc = builder.parse(file.getFile());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, e);
			} catch (SAXException e) {
				JOptionPane.showMessageDialog(this, e);				
			}
		} catch (AccessControlException ex) {
			file.getTextFileService("chapter03/");
			docText.setText(file.toString());
			builder.setEntityResolver(file.getEntityResolver(builder));
			try {
				doc = builder.parse(file.getInputStream());
			} catch (SAXException | IOException e) {
				JOptionPane.showMessageDialog(this, e);
			}
		}
	}
	
	public void evaluate() {
		result.setText("");
		try {
			String typeName = (String) typeCombo.getSelectedItem();
			QName returnType = (QName) XPathConstants.class.getField(typeName).get(null);
			Object evalResult = path.evaluate(expression.getText(), doc, returnType);
			if(typeName.equals("NODESET")) {
				NodeList list = (NodeList) evalResult;
				// Can't use String.join since NodeList isn't Iterable
				StringJoiner joiner = new StringJoiner(",", "{", "}");
				String[] text;
				String value = "";
				Node node;
				for (int i = 0; i < list.getLength(); i++) {
					 text = new String[list.item(i).getChildNodes().getLength()];
					for (int j = 0; j < list.item(i).getChildNodes().getLength(); j++) {
						if (list.item(i).getChildNodes().item(j).getTextContent() != null) {
							if (!expression.getText().contains("@")) {
								if (list.item(i).getChildNodes().item(j).hasChildNodes()) {
									node = list.item(i).getChildNodes().item(j).getFirstChild();
									if (list.item(i).getChildNodes().item(j).getChildNodes().getLength() > 1) {
										value = "(" + node.getTextContent() + "= " + 
										node.getNextSibling().getTextContent() + ")";
									} else
										value = node.getTextContent();
								} else {
									node = list.item(i).getChildNodes().item(j);
									value = node.getNodeValue();
								}
								value = node.getParentNode().getNodeName() + ":" + value;
							} 
							text[Filters.getCount(text)] = value;
						}
					}
					if (value.length() > 0)
					joiner.add("" + list.item(i) + ": " + Filters.toString(text)); 
					else
						joiner.add("" + list.item(i));
				}
				result.setText("" + joiner);
			} else 
				result.setText("" + evalResult);
		} catch (XPathExpressionException e) {
			result.setText("" + e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


