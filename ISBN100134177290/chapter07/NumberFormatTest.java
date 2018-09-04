package chapter07;  // numberFormat

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import ipi.*;

/**
 * {@code NumberFormatTest} class Listing 7.1 <br />
 * {@link NumberFormatFrame} inner class extends JFrame <br />
 * {@link GBC} GridBagConstraints copied from Listing 9.11 project ISBN100137081898 <br />
 * {@link FontFrame} JFrame copied from Listing 9.10 project ISBN100137081898 <br />
 * This program demonstrates formatting numbers under various locales. <br />
 * @version 1.14 2016-05-06
 * @author Cay Horstmann
 */
public class NumberFormatTest {
	private static final String MAIN_CLASS = "chapter07.Chapter07";
	private static String message = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new NumberFormatFrame();
			frame.setTitle("Number Format Test");
			/**
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 */
			frame .setVisible(true);
			Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
		});
	}
}

/**
 * This frame contains radio buttons to select a number format, a combo box to pick a locale, a
 * text field to display a formatted number, and a button to parse the text field contents. <br />
 */
class NumberFormatFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Locale[] locales;
	private double currentNumber;
	private JComboBox<String> localeCombo = new JComboBox<>();
	private JButton parseButton = new JButton("Parse");
	private JTextField numberText = new JTextField(30);
	private JRadioButton numberRadioButton = new JRadioButton("Number");
	private JRadioButton currencyRadioButton = new JRadioButton("Currency");
	private JRadioButton percentRadioButton = new JRadioButton("Percent");
	private ButtonGroup rbGroup = new ButtonGroup();
	private NumberFormat currentNumberFormat;
	
	public NumberFormatFrame() {
		setLayout(new GridBagLayout());
		
		ActionListener listener = event -> updateDisplay();
		
		JPanel p = new JPanel();
		addRadioButton(p, numberRadioButton, rbGroup, listener);
		addRadioButton(p, currencyRadioButton, rbGroup, listener);
		addRadioButton(p, percentRadioButton, rbGroup, listener);
		
		add(new JLabel("Locale:"), new GBC(0,0).setAnchor(GBC.EAST));
		add(p, new GBC(1, 1));
		add(parseButton, new GBC(0, 2).setInsets(2));
		add(localeCombo, new GBC(1,0).setAnchor(GBC.WEST));
		add(numberText, new GBC(1, 2).setFill(GBC.HORIZONTAL));
		locales = (Locale[]) NumberFormat.getAvailableLocales().clone();
		Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
		for (Locale loc : locales) 
			localeCombo.addItem(loc.getDisplayName());
		localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
		currentNumber = 123456.78;
		updateDisplay();
		
		localeCombo.addActionListener(listener);
		
		parseButton.addActionListener(event -> {
			String s = numberText.getText().trim();
			try {
				Number n = currentNumberFormat.parse(s);
				if (n != null) {
					currentNumber = n.doubleValue();
					updateDisplay();
				} else {
					numberText.setText("Parse error: " + s);
				}
			} catch (ParseException e) {
				numberText.setText("Parse error: " + s);
			}
		});
		pack();
	}
	
	/** 
	 * Adds a radio button to a container. <br />
	 * @param p the container into which to place the button <br />
	 * @param b the button <pr />
	 * @param g the button group <br />
	 * @param listener the button listener <br />
	 */
	public void addRadioButton(Container p, JRadioButton b, ButtonGroup g, ActionListener listener) {
		b.setSelected(g.getButtonCount() == 0);
		b.addActionListener(listener);
		g.add(b);
		p.add(b);
	}
	
	/**
	 * Updates the display and formats the number according to the user settings. <br />
	 */
	public void updateDisplay() {
		Locale currentLocale = locales[localeCombo.getSelectedIndex()];
		currentNumberFormat = null;
		if (numberRadioButton.isSelected()) 
			currentNumberFormat = NumberFormat.getNumberInstance(currentLocale);
		else if (currencyRadioButton.isSelected()) 
			currentNumberFormat = NumberFormat.getCurrencyInstance(currentLocale);
		else if (percentRadioButton.isSelected()) 
			currentNumberFormat = NumberFormat.getPercentInstance(currentLocale);
		String formatted = currentNumberFormat.format(currentNumber);
		numberText.setText(formatted);
	}
}


