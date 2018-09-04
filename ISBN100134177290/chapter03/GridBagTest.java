package chapter03;  // read

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import ipi.*;

/**
 * {@code GridBagTest} class Listing 3.2 <br />
 * {@link FontFrame} inner class extends JFrame <br />
 * {@link GridBagPane} class extends JPanel Listing 3.3 <br />
 * <a href="fontdialog.xml">
 * fontdialog.xml
 * </a> Listing 3.4 <br />
 * <a href="gridbag.dtd">
 * gridbag.dtd
 * </a> Listing 3.5 <br />
 * <a href="gridbag.xsd">
 * gridbag.xsd
 * </a> listing 3.6 <br />
 * This program shows how to use an XML file to describes gridbag layout. <br />
 * @version 1.12 2016-04-27
 * @author Cay Horstmann
 */
public class GridBagTest {
	public static final String MAIN_CLASS = "chapter03.Chapter03";
	public static String message = "";
	private static Loaders file = new Loaders();
	private static Charset cs = StandardCharsets.UTF_8;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			/**
			 * Altered to allow web start mode
			JFileChooser chooser = new JFileChooser(".");
			chooser.showOpenDialog(null);
			File file = chooser.getSelectedFile();
			 */
			file.setChoice("DS", "xml", "chapter03", "fontdialog.xml", cs, "", "", "For Settings File");
			if (file.getChoice() == false) {
				Views.openWindowOpener(MAIN_CLASS, message);
				return;
			}
			JFrame frame;
			if (file.isTrusted() == true && file.isJNLP() == false)
				frame = new FontFrame(file.getFile(), null);
			else
				frame = new FontFrame(null, file);
			frame.setTitle("Grid Bag Test");
			/**
			 * Altered with ipi.Views.openWindowOpenerListener to enable switchboard actions
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 */
			frame.setVisible(true);
			Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
		});
	}
}

/**
 * this frame contains a font selection dialog that is described by an XML file. <br />
 * @param filename the file containing the user interface components for the dialog. <br />
 */
class FontFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GridBagPane gridbag;
	private JComboBox<String> face;
	private JComboBox<String> size;
	private JCheckBox bold;
	private JCheckBox italic;
	
	@SuppressWarnings("unchecked")
	public FontFrame(File file, Loaders lFile) {
		if (file != null) gridbag = new GridBagPane(file, null);
		else if (lFile != null) gridbag = new GridBagPane(null, lFile);
		else Views.openWindowOpener(this, GridBagTest.MAIN_CLASS, GridBagTest.message);
		add(gridbag);
		
		face = (JComboBox<String>) gridbag.get("face");
		size = (JComboBox<String>) gridbag.get("size");
		bold = (JCheckBox) gridbag.get("bold");
		italic = (JCheckBox) gridbag.get("italic");
		
		face.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput" 
		}));
		
		size.setModel(new DefaultComboBoxModel<String>(new String[] {
				"8", "10", "12", "15", "18", "24", "36", "48"
		}));
		
		ActionListener listener = event -> setSample();
		
		face.addActionListener(listener);
		size.addActionListener(listener);
		bold.addActionListener(listener);
		italic.addActionListener(listener);
		
		setSample();
		pack();
		setSize(600, 200);
	}
	
	/** 
	 * this method sets the text sample to the selected font. <br />
	 */
	public void setSample() {
		String fontFace = face.getItemAt(face.getSelectedIndex());
		int fontSize = Integer.parseInt(
				size.getItemAt(size.getSelectedIndex())
				); 
		JTextArea sample = (JTextArea) gridbag.get("sample");
		int fontStyle = (bold.isSelected() ? Font.BOLD : 0) + 
				(italic.isSelected() ? Font.ITALIC : 0);
		
		sample.setFont(new Font(fontFace, fontStyle, fontSize));
		sample.repaint();
	}
}
