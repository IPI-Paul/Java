package chapter07; // draw

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import ipi.Views;

/**
 * DrawTest class Listing 7.4 
 * This program demonstrates geometric drawings
 * @version 1.32 2007-04-14 
 * @author Cay Horstmann
 */
public class DrawTest {
	private static final String MAIN_CLASS = "chapter07.Chapter07";
	private static String message = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new DrawFrame();
				frame.setTitle("Draw Test");
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				Views.openWindowOpenerListener(frame, MAIN_CLASS, message);
			}
		});
	}
}

/**
 * A frame that contains a panel with drawings
 */
class DrawFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DrawFrame() {
		add(new DrawComponent());
		pack();
	}
}

/**
 * A component that displays rectangles and ellipses.
 */
class DrawComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// draw a rectangle
		double leftX = 100;
		double topY = 100;
		double width = 200;
		double height = 150;
		
		Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
		g2.draw(rect);
		
		// draw the enclosed ellipse
		Ellipse2D ellipse = new Ellipse2D.Double();
		ellipse.setFrame(rect);
		g2.draw(ellipse);
		
		// draw a diagonal line
		g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));
		
		// draw a circle with the same centre
		double centerX = rect.getCenterX();
		double centerY = rect.getCenterY();
		double radius = 150;
		
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
		g2.draw(circle);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}