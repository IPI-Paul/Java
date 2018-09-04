package chapter10;  // longList

import java.awt.*;
import javax.swing.*;

/**
 * {@code LongListFrame] class Listing 10.2 <br />
 * {@link WordListModel} class Listing 10.3 <br />
 * This frame contains a long word list and a label that shows a sentence made up from the 
 * chosen word. <br />
 */
public class LongListFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JList<String> wordList;
	private JLabel label;
	private String prefix = "The quick brown ";
	private String suffix = " jumps over the lazy dog.";
	
	public LongListFrame() {
		wordList = new JList<String>(new WordListModel(3));
		wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		wordList.setPrototypeCellValue("www");
		JScrollPane scrollPane = new JScrollPane(wordList);
		
		JPanel p = new JPanel();
		p.add(scrollPane);
		wordList.addListSelectionListener(event -> setSubject(wordList.getSelectedValue()));
		
		Container contentPane = getContentPane();
		contentPane.add(p, BorderLayout.NORTH);
		label = new JLabel(prefix + suffix);
		contentPane.add(label, BorderLayout.CENTER);
		setSubject("fox");
		pack();
	}
	
	/** 
	 * Sets the subject in the label. <br />
	 * @param word the new subject that jumps over the lazy dog <br />
	 */
	public void setSubject(String word) {
		StringBuilder text = new StringBuilder(prefix);
		text.append(word);
		text.append(suffix);
		label.setText(text.toString());
		label.validate();
		pack();
	}
}
