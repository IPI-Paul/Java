package chapter02;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import javax.swing.*;

/**
 * WelcomeApplet JApplet Listing 2.4
 * WelcomeApplet.html HTML Listing 2.3
 * This applet displays a greeting from the authors.
 * @version 1.22 2007-04-08
 * @author Cay Horstmann
 */
public class WelcomeApplet extends JApplet {
	JLabel label;
	JEditorPane editor;
	JTextArea url;

	/**
	 * This is a serializable class and therefore needs a Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				setLayout(new BorderLayout());
				
				try {
					label = new JLabel(getParameter("greeting"), SwingConstants.CENTER);
				}
				catch (Exception e) {
					label = new JLabel("Welcome to Core Java, non Browser!", SwingConstants.CENTER);
				}
				label.setFont(new Font("Serif", Font.BOLD, 18));
				
				url = new JTextArea("http://127.0.0.1", 1, 35);

				JPanel header = new JPanel();
				header.add(label, BorderLayout.SOUTH);
				header.add(url, BorderLayout.NORTH);
				header.setLayout(new GridLayout(2,1));
				add(header, BorderLayout.NORTH);
				
				JPanel panel = new JPanel();
				
				JButton cayButton = new JButton("Cay Horstmann");
				cayButton.addActionListener(makeAction("http://www.horstmann.com"));
				panel.add(cayButton);
				
				JButton garyButton = new JButton("Gary Cornell");
				garyButton.addActionListener(makeAction("mailto:gary_cornell@missing.com"));
				panel.add(garyButton);
				
				JButton localButton = new JButton("Go to URL");
				localButton.addActionListener(makeAction("Get URL"));
				panel.add(localButton);
				
				add(panel, BorderLayout.SOUTH);
				setSize(400, 250);
				setVisible(true);
			}
		});
	}
	private ActionListener makeAction(final String urlString) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					if (urlString.equals("Get URL")) {
						getAppletContext().showDocument(new URL(url.getText()));
					} else {
						getAppletContext().showDocument(new URL(urlString));
					}
				}
				catch (Exception e) {
					// e.printStackTrace();
					if (urlString.equals("Get URL")) {
						label.setText("<html>" + url.getText() + "</html>");
					} else {
						label.setText("<html>" + urlString + "</html>");
					}
					try {
						if (urlString.equals("Get URL")) {
							editor = new JEditorPane(url.getText());
						} else {
							editor = new JEditorPane(urlString);
						}
						editor.setEditable(false);
						JScrollPane scrollPane = new JScrollPane(editor);
						add(scrollPane, BorderLayout.CENTER);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
	}
}
