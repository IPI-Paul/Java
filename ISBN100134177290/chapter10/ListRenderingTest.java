package chapter10; //listRendering

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ipi.*;

/**
 * {@code ListRenderingTest} class Core Java 8th edition <br />
 * {link ListRenderingFrame} inner class extends JFrame <br />
 * {@link FontCellRenderer] class Listing 10.4 <br />
 * This program demonstrates the use of cell renderers in a list box. <br />
 * @version 1.23 2007-008-01
 * @author Cay Horstmann
 */
public class ListRenderingTest {
	private static final String MAIN_CLASS = "chapter10.Chapter10";
	private static String message = ""; 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new ListRenderingFrame();
				/**
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 */
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
			}
		});
	}
}


/**
 * This frame contains a list with a set of fonts and a text area that is set to the selected font. 
 */
class ListRenderingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextArea text;
	private JList fontList;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
	
	public ListRenderingFrame() {
		setTitle("List Rendering Test");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		ArrayList<Font> fonts = new ArrayList<Font>();
		final int SIZE = 24;
		fonts.add(new Font("Serif", Font.PLAIN, SIZE));
		fonts.add(new Font("SansSerif", Font.PLAIN, SIZE));
		fonts.add(new Font("Monospaced", Font.PLAIN, SIZE));
		fonts.add(new Font("Dialog", Font.PLAIN, SIZE));
		fonts.add(new Font("DialogInput", Font.PLAIN, SIZE));
		fontList = new JList(fonts.toArray());
		fontList.setVisibleRowCount(4);
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fontList.setCellRenderer(new FontCellRenderer());
		JScrollPane scrollPane = new JScrollPane(fontList);
		
		JPanel p = new JPanel();
		p.add(scrollPane);
		fontList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Font font = (Font) fontList.getSelectedValue();
				text.setFont(font);
			}
		});
		
		Container contentPane = getContentPane();
		contentPane.add(p, BorderLayout.SOUTH);
		text = new JTextArea("The quick brown fox jumps over the lazy dog.");
		text.setFont((Font) fonts.get(0));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		contentPane.add(text, BorderLayout.CENTER);
	}
}


