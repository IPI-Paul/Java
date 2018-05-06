package chapter14;  // bouncThread

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ipi.*;

/**
 * BounceThread class Listing 14.4 <br />
 * BallRunnable runnable inner class <br />
 * BounceFrame1 JFrame inner class <br />
 * Shows animated bouncing balls.
 * @version 1.33 2007-05-17 
 * @author Cay Horstmann
 */
public class BounceThread {
	private static final String MAIN_CLASS = "chapter14.Chapter14";
	private static String message = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new BounceFrame1();
				frame.setTitle("Bounce Thread");
				// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
			}
		});
	}	
}

/** 
 * A runnable that animates a bouncing ball.
 */
class BallRunnable implements Runnable {
	private Ball ball;
	private Component component;
	public static final int STEPS = 1000;
	public static final int DELAY = 5;
	
	/**
	 * Constructs the runnable.
	 * @param aBall the ball to bounce
	 * @param aComponent the component in which the ball bounces
	 */
	public BallRunnable(Ball aBall, Component aComponent) {
		ball = aBall;
		component = aComponent;
	}
	
	public void run() {
		try {
			for (int i = 1; i <= STEPS; i++) {
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
		}
	}
}

/**
 * The frame with panel and buttons.
 */
class BounceFrame1 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BallComponent comp;
	
	/**
	 * Constructs the frame with the component for showing the bouncing ball and Start and Close buttons
	 */
	public BounceFrame1() {
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addBall();
			}
		});
		
		addButton(buttonPanel, "Close", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.exit(0);
				Views.closeWindow(BounceFrame1.this);
			}
		});
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	
	/**
	 * Adds a button to a container.
	 * @param c the container
	 * @param title the button title
	 * @param listener the action listener for the button
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	/**
	 * Adds a bouncing ball to the canvas and starts a thread to make it bounce
	 */
	public void addBall() {
		Ball b = new Ball();
		comp.add(b);
		Runnable r = new BallRunnable(b, comp);
		Thread t = new Thread(r);
		t.start();
	}
}
