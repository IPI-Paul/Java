package chapter09;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * CheckBoxFrame JFrame
 * CheckBoxTest class Listing 9.3
 */
public class CheckBoxFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel label;
	private static final int FONTSIZE = 12;
	int mode = 0;
	
	public CheckBoxFrame() {
		// add the sample text label
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif", mode, FONTSIZE));
		add(label, BorderLayout.CENTER);
		
		// add check boxes
		panel = new JPanel();
		addCheckBox();
		add(panel, BorderLayout.SOUTH);
		pack();
		setSize(300, 200);
	}
	
	/**
	 * Adds a check box that sets the font style of the sample text
	 * @param name the string to identify the check box
	 */
	private void addCheckBox() {
		final JCheckBox bold = new JCheckBox("Bold");
		final JCheckBox italic = new JCheckBox("Italic");
		panel.add(bold);
		panel.add(italic);
		
		// this listener sets the label font style
		ActionListener listener = new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				int mode = 0;
				if (bold.isSelected()) {
					mode += Font.BOLD;
				}
				if (italic.isSelected()) {
					mode += Font.ITALIC;
				}
				label.setFont(new Font("Serif", mode, FONTSIZE));
			}
		};
		bold.addActionListener(listener);
		italic.addActionListener(listener);
	}
}
