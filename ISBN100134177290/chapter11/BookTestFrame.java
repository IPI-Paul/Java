package chapter11;  // book

import java.awt.*;
import java.awt.print.*;
import javax.print.attribute.*; 
import javax.swing.*;

/**
 * {@code BookTestFrame} class extends JFrame Listing 11.12 <br />
 * {@link Banner} class implements Printable Listing 11.13 <br />
 * {@link CoverPage} inner class implements Printable Listing 11.13 <br />
 * {@link PrintPreviewDialog} class extends JDialog Listing 11.14 <br />
 * {@link PrintPreviewCanvas} class extends JComponent Listing 11.15 <br />
 * This frame has a text field for the banner  text and buttons for printing, page setup, 
 * and print preview.
 */
public class BookTestFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField text;
	private PageFormat pageFormat;
	private PrintRequestAttributeSet attributes;
	
	public BookTestFrame() {
		text = new JTextField();
		add(text, BorderLayout.NORTH);
		
		attributes = new HashPrintRequestAttributeSet();
		
		JPanel buttonPanel = new JPanel();
		
		JButton printButton = new JButton("Print");
		buttonPanel.add(printButton);
		printButton.addActionListener(event -> {
			try {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPageable(makeBook());
				if (job.printDialog(attributes)) {
					job.print(attributes);
				}
			} catch (PrinterException e) {
				JOptionPane.showMessageDialog(BookTestFrame.this, e);
			}
		});
		
		JButton pageSetupButton = new JButton("Page setup");
		buttonPanel.add(pageSetupButton);
		pageSetupButton.addActionListener(event -> {
			PrinterJob job = PrinterJob.getPrinterJob();
			pageFormat = job.pageDialog(attributes);
		});
		
		JButton printPreviewButton = new JButton("Print preview");
		buttonPanel.add(printPreviewButton);
		printPreviewButton.addActionListener(event -> {
			PrintPreviewDialog dialog = new PrintPreviewDialog(makeBook());
			dialog.setVisible(true);
		});
		
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	
	/**
	 * Makes a book that contains a cover page and the pages for the banner.
	 */
	public Book makeBook() {
		if (pageFormat == null) {
			PrinterJob job = PrinterJob.getPrinterJob();
			pageFormat = job.defaultPage();
		}
		Book book = new Book();
		String message = text.getText();
		Banner banner = new Banner(message);
		int pageCount = banner.getPageCount((Graphics2D) getGraphics(), pageFormat);
		book.append(new CoverPage(message + " (" + pageCount + " pages)"), pageFormat);
		book.append(banner, pageFormat, pageCount);
		return book;
	}
}
