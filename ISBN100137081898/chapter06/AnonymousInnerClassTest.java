package chapter06;  // anonymousInnerClass

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import ipi.*;

/**
 * AnonymousInnerClassTest class Listing 6.7
 * This program demonstrates anonymous inner classes.
 * @version 1.10 2004-02-27
 * @author Cay Horstmann
 */
public class AnonymousInnerClassTest {
	private static final String MAIN_CLASS = "chapter06.Chapter06";
	private static String message = "";

	public static void main(String[] args) {
		TalkingClock1 clock = new TalkingClock1();
		clock.start(1000, true);
		
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
 class TalkingClock1 {
	 Timer t;
	 /** 
	  * Starts the clock.
	  * @param interval the interval between messages (in milliseconds)
	  * @param beep true if the clock should beep
	  */
	 public void start(int interval, final boolean beep) {
		 ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Date now = new Date();
				System.out.println("At the tone, the time is " + now);
				if (beep) Toolkit.getDefaultToolkit().beep();
			}
		};
		t = new Timer(interval, listener);
		t.start();
	 }
	 public void stop() {
		 t.stop();
	 }
 }