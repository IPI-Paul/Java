package chapter06; // timer

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import ipi.Views;
// to resolve conflict with java.util.Timer

/**
 * TimerTest class Listing 6.5
 * This program demonstrates the use of a timer and ActionListener
 * @version 1.00 2000-04-13
 * @author Cay Horstmann
 */
public class TimerTest {
	private static final String MAIN_CLASS = "chapter06.Chapter06";
	private static String message = "";

	public static void main(String[] args) {
		ActionListener listener = new TimePrinter();
		
		/**
		 *  construct a timer that calls the listener
		 *  once every 10 seconds
		 */
		Timer t = new Timer(10000, listener);
		t.start();
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		//System.exit(0);
		t.stop();
		Views.openWindowOpener(MAIN_CLASS, message);
		return;
	}
}

class TimePrinter implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		Date now = new Date();
		System.out.println("At the tone, the time is " + now);
		Toolkit.getDefaultToolkit().beep();
	}
}