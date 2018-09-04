package chapter11; // book

import java.awt.*;
import java.awt.geom.*;
import java.awt.print.*;
import javax.swing.*;

/**
 * {@code PrintPreviewCanvas} class extends JComponent Listing 11.15 <br />
 * {@link BookTestFrame} class extends JFrame Listing 11.12 <br />
 * {@link Banner} class implements Printable Listing 11.13 <br />
 * {@link CoverPage} inner class implements Printable Listing 11.13 <br />
 * {@link PrintPreviewDialog} class extends JDialog Listing 11.14 <br />
 * The canvas for displaying the print preview.
 */
public class PrintPreviewCanvas extends JComponent {
	private static final long serialVersionUID = 1L;
	private Book book;
	private int currentPage;
	
	/**
	 * Constructs a print preview canvas. <br />
	 * @param b the book to be previewed
	 */
	public PrintPreviewCanvas(Book b) {
		book = b;
		currentPage = 0;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		PageFormat pageFormat = book.getPageFormat(currentPage);
		
		double xoff; // x offset of page start in window
		double yoff; // y offset of page start in window
		double scale; // scale factor to fit page in window
		double px = pageFormat.getWidth();
		double py = pageFormat.getHeight();
		double sx = getWidth() - 1;
		double sy = getHeight() - 1;
		if (px / py < sx / sy) { // center horizontally 
			scale = sy / py;
			xoff = 0.5 * (sx - scale * px);
			yoff = 0;
		} else { // center vertically
			scale = sx / px;
			xoff = 0;
			yoff = 0.5 * (sy - scale * py);
		}
		g2.translate((float) xoff, (float) yoff);
		g2.scale((float) scale, (float) scale);
		
		// draw page outline (ignoring margins)
		Rectangle2D page = new Rectangle2D.Double(0, 0, px, py);
		g2.setPaint(Color.white);
		g2.fill(page);
		g2.setPaint(Color.black);
		g2.draw(page);
		
		Printable printable = book.getPrintable(currentPage);
		try {
			printable.print(g2, pageFormat, currentPage);
		} catch (PrinterException e) {
			g2.draw(new Line2D.Double(0, 0, px, py));
			g2.draw(new Line2D.Double(px, 0, 0, py));
		}
	}
	
	/**
	 * Flip the book by the given number of pages. <br />
	 * @param by the number of pages to flip by. Negative values flip backward. 
	 */
	public void flipPage(int by) {
		int newPage = currentPage + by;
		if (0 <= newPage && newPage < book.getNumberOfPages()) {
			currentPage = newPage;
			repaint();
		}
	}
}
