package chapter06; // innerClass

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import ipi.Views;

/**
 * InnerClassTest class Listing 6.6
 * This program demonstrates the use of inner classes.
 * @version 1.10 2004-02-27
 * @author Cay Horstmann
 */
public class InnerClassTest {
	private static final String MAIN_CLASS = "chapter06.Chapter06";
	private static String message = "";

	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();
		
		// keep program running until user selects "OK"
		JOptionPane.showMessageDialog(null, "Quit program?");
		// System.exit(0);
		clock.stop();
		Views.openWindowOpener(MAIN_CLASS, message);
		return;
	}
}
/**
 * A clock that prints the time in regular intervals.
 */
class TalkingClock {
	private int interval;
	private boolean beep;
	private Timer t;
	
	/**
	 * Constructs a talking clock
	 * @param interval the interval between messages (in milliseconds)
	 * @param beep true if the clock should beep
	 */
	public TalkingClock(int interval, boolean beep) {
		this.interval = interval;
		this.beep = beep;
	}
	
	/** 
	 * Starts the clock.
	 */
	public void start() {
		ActionListener listener = new TimePrinter();
		t = new Timer(interval, listener);
		t.start();
	}
	
	public void stop() {
		t.stop();
	}

	public class TimePrinter implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Date now = new Date();
			System.out.println("At the tone, the time is " + now);
			if (beep) Toolkit.getDefaultToolkit().beep();
		}
	}
}