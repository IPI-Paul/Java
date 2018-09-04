package chapter11; // book

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;;

/**
 * {@code PrintPreviewDialog} class extends JDialog Listing 11.14 <br />
 * {@link BookTestFrame} class extends JFrame Listing 11.12 <br />
 * {@link Banner} class implements Printable Listing 11.13 <br />
 * {@link CoverPage} inner class implements Printable Listing 11.13 <br />
 * {@link PrintPreviewCanvas} class extends JComponent Listing 11.15 <br />
 * This class implements a generic print preview dialog.
 */
public class PrintPreviewDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	private PrintPreviewCanvas canvas;
	
	/**
	 * Constructs a print preview dialog. <br />
	 * @param p a Printable <br />
	 * @param pf the page format <br />
	 * @param pages the number of pages in p
	 */
	public PrintPreviewDialog(Printable p, PageFormat pf, int pages) {
		Book book = new Book();
		book.append(p, pf, pages);
		layoutUI(book);
	}
	
	/**
	 * Constructs a print preview dialog. <br />
	 * @param book the book to be previewed
	 */
	public PrintPreviewDialog(Book b) {
		layoutUI(b);
	}
	
	/**
	 * Lays out the UI of the dialog. <br />
	 * @param book the book to be previewed
	 * @param book
	 */
	public void layoutUI(Book book) {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		canvas = new PrintPreviewCanvas(book);
		add(canvas, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		
		JButton nextButton = new JButton("Next");
		buttonPanel.add(nextButton);
		nextButton.addActionListener(event -> canvas.flipPage(1));
		
		JButton previousButton = new JButton("Previous");
		buttonPanel.add(previousButton);
		previousButton.addActionListener(event -> canvas.flipPage(-1));
		
		JButton closeButton = new JButton("Close");
		buttonPanel.add(closeButton);
		closeButton.addActionListener(event -> setVisible(false));
		
		add(buttonPanel, BorderLayout.SOUTH);
		
	}
}
