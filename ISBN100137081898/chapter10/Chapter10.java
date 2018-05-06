package chapter10;

import java.net.*;
import javax.swing.*;
import javax.jnlp.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 10
 * @author Paul I Ighofose
 */
public class Chapter10 {
	private static final String MAIN_CLASS = "chapter10.Chapter10";
	private static JFrame frame;
	private static String title = "Example Of";

	private static final String RESOURCE_TEST = "Listing 10.1 Resource Test";
	private static final String RESOURCE_WEB_FRAME = "Listing 10.1(a) Resource Web Frame";
	private static final String CALCULATOR_FRAME = "Listing 10.2 Calculator Frame";
	private static final String NOT_HELLO_WORLD = "Listing 10.3 Not Hello World";
	private static final String CHART = "Listing 10.4 Diameters of the Planets Chart";
	private static final String PROPERTIES_TEST = "Listing 10.5 Properties Test";
	private static final String PREFERENCES_TEST = "Listing 10.6 Preferences Test";
	private static final String[] example = {RESOURCE_TEST, RESOURCE_WEB_FRAME, CALCULATOR_FRAME, 
			NOT_HELLO_WORLD, CHART, PROPERTIES_TEST, PREFERENCES_TEST}; 
	private static int runAgain;
	public static void main(String[] args) {
		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null,"Please Select an Example!",title,
					JOptionPane.QUESTION_MESSAGE,null,example,RESOURCE_TEST);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == CALCULATOR_FRAME) {
				/**
				 * CalculatorFrame JFrame Listing 10.2
				 */
				frame = Views.newFrame(new CalculatorFrame(), null);
				frame.setSize(300, 500);
				frame.setVisible(true);
				return;
			}
			else if (exampleType == CHART) {
				try {
					Views.browseDocument("chapter10/Chart.html");
				}
				catch (MalformedURLException e) {
					e.printStackTrace();
				}
				catch (UnavailableServiceException e) {
					frame = Views.newFrame(new JFrame(), "Diameters of the Planets Chart");
					frame.setSize(400, 300);
					/** 
					 * Chart JApplet Listing 10.4
					 * Chart.html HTML
					 */
					JApplet applet = new Chart();
					frame.add(applet);
					applet.init();
					frame.setVisible(true);
					frame.repaint();
					Views.openWindowOpenerListener(frame, MAIN_CLASS, "");
					return;
				}
			}
			else if (exampleType == NOT_HELLO_WORLD) {
				try {
					Views.browseDocument("chapter10/NotHelloWorld.html");
				}
				catch (MalformedURLException e) {
					e.printStackTrace();
				}
				catch (UnavailableServiceException e) {
					frame = Views.newFrame(new JFrame(), "Not Hello World");
					frame.setSize(300, 200);
					/**
					 * NotHelloWorld JApplet Listing 10.3
					 * NotHelloWorld.html HTML
					 */
					JApplet applet = new NotHelloWorld();
					frame.add(applet);
					applet.init();
					frame.setVisible(true);
					frame.repaint();
					Views.openWindowOpenerListener(frame, MAIN_CLASS, "");
					return;
				}
			}
			else if (exampleType == PREFERENCES_TEST) {
				/**
				 * PreferencesTest class Listing 10.6
				 */
				PreferencesTest.main(null);
				return;
			}
			else if (exampleType == PROPERTIES_TEST) {
				/**
				 * PropertiesTest class Listing 10.5
				 */
				PropertiesTest.main(null);
				return;
			}
			else if (exampleType == RESOURCE_TEST) {
				/**
				 * ResourceTest class Listing 10.1
				 */
				ResourceTest.main(null);
				return;
			}
			else if (exampleType == RESOURCE_WEB_FRAME) {
				/**
				 * ResourceWebFrame JFrame Listing 10.1 extended
				 */
				frame = Views.newFrame(new ResourceWebFrame(), "Resource Web Frame");	
				frame.setVisible(true);
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
