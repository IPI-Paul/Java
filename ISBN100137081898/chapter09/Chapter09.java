package chapter09;

import java.awt.EventQueue;
import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 9
 * @author Paul I Ighofose
 */
public class Chapter09 {
	private static String title = "Example Of";
	private static final String MAIN_CLASS = "chapter09.Chapter09";
	private static String message = "";	
	private static JFrame frame;

	private static final String CALCULATOR_PANEL = "Listing 9.1 Calculator Panel";
	private static final String TEXT_COMPONENT_FRAME = "Listing 9.2 Text Component Frame";
	private static final String CHECK_BOX_TEST = "Listing 9.3 Check Box Test";
	private static final String RADIO_BUTTON_FRAME = "Listing 9.4 Radio Button Frame";
	private static final String BORDER_FRAME = "Listing 9.5 Border Frame";
	private static final String COMBO_BOX_FRAME = "Listing 9.6 Combo Box Frame";
	private static final String SLIDER_FRAME = "Listing 9.7 Slider Frame";
	private static final String MENU_FRAME = "Listing 9.8 Menu Frame";
	private static final String TOOLBAR_TEST = "Listing 9.9 Toolbar Test";
	private static final String FONT_FRAME = "Listing 9.10-11 Font Frame Grid Bag Constraints Layout";
	private static final String FONT_FRAME1 = "Listing 9.12 Font Frame Group Layout";
	private static final String CIRCLE_LAYOUT_FRAME = "Listing 9.13-14 Circle Layout Frame";
	private static final String OPTION_DIALOG_FRAME = "Listing 9.15-16 Option Dialog Frame";
	private static final String DIALOG_FRAME = "Listing 9.17-18 Dialog Frame";
	private static final String DATA_EXCHANGE_FRAME = "Listing 9.19-20 Data Exchange Frame";
	private static final String IMAGE_VIEWER_FRAME = "Listing 9.21-23 Image Viewer Frame";
	private static final String COLOR_CHOOSER_PANEL = "Listing 9.24 Colour Chooser Panel";
	private static final String[] example = {CALCULATOR_PANEL, TEXT_COMPONENT_FRAME, CHECK_BOX_TEST,  
			RADIO_BUTTON_FRAME, BORDER_FRAME, COMBO_BOX_FRAME, SLIDER_FRAME, MENU_FRAME, TOOLBAR_TEST,       
			FONT_FRAME, FONT_FRAME1, CIRCLE_LAYOUT_FRAME, OPTION_DIALOG_FRAME, DIALOG_FRAME, DATA_EXCHANGE_FRAME, 
			IMAGE_VIEWER_FRAME, COLOR_CHOOSER_PANEL}; 
	private static int runAgain;
	
	public static void main(String[] args) {
		Threads.setDefaultLocale();
		
		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null,"Please Select an Example!",title,
					JOptionPane.QUESTION_MESSAGE,null,example,CALCULATOR_PANEL);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == BORDER_FRAME) {
				/**
				 * BorderFrame JFrame Listing 9.5
				 */
				frame = Views.newFrame(new BorderFrame(), "Border Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == CALCULATOR_PANEL) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						frame = Views.newFrame(new JFrame(), "Calculator Panel"); 
						frame.setSize(300, 300);
						/**
						 * CalculatorPanel JPanel Listing 9.1
						 */
						JPanel panel = new CalculatorPanel();
						frame.add(panel);
						frame.setVisible(true);
						Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
					}
				});
				return;
			}
			else if (exampleType == CHECK_BOX_TEST) {
				/**
				 * CheckBoxTest class Listing 9.3
				 * CheckBoxFrame JFrame
				 */
				CheckBoxTest.main(null);
				return;
			}
			else if (exampleType == CIRCLE_LAYOUT_FRAME) {
				/**
				 * CircleLayoutFrame JFrame Listing 9.14
				 * CircleLayout LayoutManager Listing 9.13
				 */
				frame = Views.newFrame(new CircleLayoutFrame(), "Circle Layout Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == COLOR_CHOOSER_PANEL) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						frame = Views.newFrame(new JFrame(), "Colour Chooser Panel"); 
						frame.setSize(300, 300);
						/** 
						 * ColorChooserPanel JPanel Listing 9.24
						 */
						JPanel panel = new ColorChooserPanel();
						frame.add(panel);
						frame.setVisible(true);
						Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
					}
				});
				return;
			}
			else if (exampleType == COMBO_BOX_FRAME) {
				/**
				 * ComboBoxFrame JFrame Listing 9.6
				 */
				frame = Views.newFrame(new ComboBoxFrame(), "Combo Box Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == DATA_EXCHANGE_FRAME) {
				/**
				 * DataExchangeFrame JFrame Listing 9.19
				 * PasswordChooser JPanel Listing 9.20
				 */
				frame = Views.newFrame(new DataExchangeFrame(), "Data Exchange Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == DIALOG_FRAME) {
				/**
				 * DialogFrame JFrame Listing 9.17
				 * AboutDialog JDialog Listing 9.18
				 */
				frame = Views.newFrame(new DialogFrame(), "Dialog Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == FONT_FRAME) {
				/**
				 * FontFrame JFrame Listing 9.10
				 * GBC GridBagConstraints Listing 9.11
				 */
				frame = Views.newFrame(new FontFrame(), "Font Frame Grid Bag Constraints Layout");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == FONT_FRAME1) {
				/**
				 * FontFrame1 JFrame Listing 9.12
				 */
				frame = Views.newFrame(new FontFrame1(), "Font Frame Group Layout");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == IMAGE_VIEWER_FRAME) {
				/** 
				 * ImageViewerFrame JFrame Listing 9.21
				 * ImagePreviewer JLabel Listing 9.22
				 * FileIconView FileView Listing 9.23
				 */
				frame = Views.newFrame(new ImageViewerFrame(), "Image Viewer Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == MENU_FRAME) {
				/**
				 * MenuFrame JFrame Listing 9.8
				 * TestAction inner class
				 */
				frame = Views.newFrame(new MenuFrame(), "Menu Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == OPTION_DIALOG_FRAME) {
				/**
				 * OptionDialogFrame JFrame Listing 9.15
				 * ButtonPanel JPanel Listing 9.16
				 * ShowAction private class
				 * SampleComponent inner class
				 */
				frame = Views.newFrame(new OptionDialogFrame(), "Option Dialog Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == RADIO_BUTTON_FRAME) {
				/**
				 * RadioButtonFrame JFrame Listing 9.4
				 */
				frame = Views.newFrame(new RadioButtonFrame(), "Radio Button Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == SLIDER_FRAME) {
				/**
				 * SliderFrame JFrame Listing 9.7
				 */
				frame = Views.newFrame(new SliderFrame(), "Slider Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == TEXT_COMPONENT_FRAME) {
				/**
				 * TextComponentFrame JFrame Listing 9.2
				 */
				frame = Views.newFrame(new TextComponentFrame(), "Text Component Frame");
				frame.setVisible(true);
				return;
			}
			else if (exampleType == TOOLBAR_TEST) {
				/**
				 * ToolBarTest class Listing 9.9
				 */
				ToolBarTest.main(null);
				return;
			}
			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?", "Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeApplication();
	}
}
