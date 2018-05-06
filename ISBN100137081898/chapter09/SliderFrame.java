package chapter09;  // slider

import java.awt.*;
import java.security.AccessControlException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import ipi.*;

/**
 * SliderFrame JFrame Listing 9.7
 * A frame with many sliders and a text field to show slider values.
 * @author Cay Horstmann
 */
public class SliderFrame extends JFrame {
	private static final String MAIN_CLASS = "chapter09.Chapter09";
	private static String message = "";
	private static String filePath;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel sliderPanel;
	private JTextField textField;
	private ChangeListener listener;
	private static int COLOUR_RED = 0;
	private static int COLOUR_GREEN = 0;
	private static int COLOUR_BLUE = 0;
	
	public SliderFrame() {
		// get images folder path
		try {
			filePath = FolderPaths.getImagesFolder();
		} catch (AccessControlException ex) {
			filePath = ResourcePaths.getImagesResource();
		}
		
		sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridBagLayout());
		
		// common listener for all sliders
		listener = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// update text field when the slider value changes
				JSlider source = (JSlider) event.getSource();
				textField.setText("" + source.getValue());
				
				/**
				 * Sets the text field background colour by listening to the RGB sliders
				 * @version 1.0 2018-03-18
				 * @author Paul I Ighofose 
				 */
				if (source.getName() != null) {
					if (source.getName().equals("Red")) {
						COLOUR_RED = source.getValue();
					}
					if (source.getName().equals("Green")) {
						COLOUR_GREEN = source.getValue();
					}
					if (source.getName().equals("Blue")) {
						COLOUR_BLUE = source.getValue();
					}
					Color color = new Color(COLOUR_RED, COLOUR_GREEN, COLOUR_BLUE);
					textField.setBackground(color);
					textField.setText("Red: " + COLOUR_RED + " Green: " + COLOUR_GREEN + 
							" Blue: " + COLOUR_BLUE);
					if (COLOUR_RED <= 127 && COLOUR_GREEN <= 127) {
						textField.setForeground(Color.WHITE);
					}
					else {
						textField.setForeground(Color.BLACK);
					}
				}
			}
		};
		
		// add a plain slider
		JSlider slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Ticks");
		
		// add a slider that snaps to ticks
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Snap to ticks");
		
		// add a slider with no track
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintTrack(false);
		addSlider(slider, "No track");
		
		// add an inverted slider
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setInverted(true);
		addSlider(slider, "Inverted");
		
		// add a slider with numeric labels
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Labels");
		
		// add a slider with alphabetic labels
		slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		
		Dictionary<Integer, Component> labelTable = new Hashtable<>();
		labelTable.put(0, new JLabel("A"));
		labelTable.put(20, new JLabel("B"));
		labelTable.put(40, new JLabel("C"));
		labelTable.put(60, new JLabel("D"));
		labelTable.put(80, new JLabel("E"));
		labelTable.put(100, new JLabel("F"));
		
		slider.setLabelTable(labelTable);
		addSlider(slider, "Custom labels");
		
		// add a slider with icon labels 
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(20);
		
		labelTable = new Hashtable<Integer, Component>();
		
		// add card images
		double width = 200 / 6;
		labelTable.put(0, new JLabel(Images.resizeImage(filePath + "nine-of-hearts.gif", width)));
		labelTable.put(20, new JLabel(Images.resizeImage(filePath + "ten-of-hearts.gif", width)));
		labelTable.put(40, new JLabel(Images.resizeImage(filePath + "jack-of-hearts.gif", width)));
		labelTable.put(60, new JLabel(Images.resizeImage(filePath + "queen-of-hearts.gif", width)));
		labelTable.put(80, new JLabel(Images.resizeImage(filePath + "king-of-hearts.gif", width)));
		labelTable.put(100, new JLabel(Images.resizeImage(filePath + "ace-of-hearts.gif", width)));
		
		slider.setLabelTable(labelTable);
		addSlider(slider,"Icon labels");
		
		/**
		 * RGB colour sliders to set background colour of text field
		 * @author Paul I Ighofose
		 */
		// add red colour slider
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMaximum(255);
		slider.setMajorTickSpacing(51);
		slider.setMinorTickSpacing(1);
		slider.setName("Red");
		addSlider(slider, "Red");
		
		// add green colour slider
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMaximum(255);
		slider.setMajorTickSpacing(51);
		slider.setMinorTickSpacing(1);
		slider.setName("Green");
		addSlider(slider, "Green");
		
		// add blue colour slider
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMaximum(255);
		slider.setMajorTickSpacing(51);
		slider.setMinorTickSpacing(1);
		slider.setName("Blue");
		addSlider(slider, "Blue");
		
		// add the text field that displays the slider value
		textField = new JTextField();
		add(sliderPanel, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		pack();
		Views.openWindowOpenerListener(this, MAIN_CLASS, message);
	}
	
	/**
	 * Adds a slider to the slider panel and hooks up the listener
	 * @version 1.0 2018-03-18
	 * @param s the slider
	 * @param description the slider description
	 */
	public void addSlider(JSlider s, String description) {
		s.addChangeListener(listener);
		JPanel panel = new JPanel();
		panel.add(s);
		panel.add(new JLabel(description));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = sliderPanel.getComponentCount();
		gbc.anchor = GridBagConstraints.WEST;
		sliderPanel.add(panel, gbc);
	}
}
