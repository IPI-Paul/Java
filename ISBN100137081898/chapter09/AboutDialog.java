package chapter09;  // dialog

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * AboutDialog JDialog Listing 9.18
 * DialogFrame JFrame Listing 9.17 
 * A sample modal dialog that displays a message and waits for the user to click the OK button.
 * @author Cay Horstmann
 */
public class AboutDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AboutDialog(JFrame owner) {
		super(owner, "About Dialog Test", true);
		
		// add HTML label to center
		add(new JLabel("<html><h1><i>Core Java</i></h1><hr>By Cay Horstmann and Gary Cornell</html>"), 
				BorderLayout.CENTER);
		
		// Ok button closes the dialog
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
			}
		});
		
		// add Ok button to southern border
		JPanel panel = new JPanel();
		panel.add(ok);
		add(panel, BorderLayout.SOUTH);
		
		pack();
	}
}
