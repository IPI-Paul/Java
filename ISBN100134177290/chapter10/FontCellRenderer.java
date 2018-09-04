package chapter10; // listRendering

import java.awt.*;
import javax.swing.*;

/**
 * {@code FontCellRenderer] class Listing 10.4 <br />
 * {@link ListRenderingTest} class Core Java 8th edition <br />
 * {link ListRenderingFrame} inner class extends JFrame <br />
 * A cell renderer for Font objects that renders the font name in its own font. <br />
 */
public class FontCellRenderer extends JComponent implements ListCellRenderer<Font> {
	private static final long serialVersionUID = 1L;
	private Font font;
	private Color background;
	private Color foreground;
	
	public Component getListCellRendererComponent(JList<? extends Font> list, 
			Font value, int index, boolean isSelected, boolean cellHasFocus) {
		font = value;
		background = isSelected ? list.getSelectionBackground() : list.getBackground();
		foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
		return this;
	}
	
	public void paintComponent(Graphics g) {
		String text = font.getFamily();
		FontMetrics fm = g.getFontMetrics(font);
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(foreground);
		g.setFont(font);
		g.drawString(text, 0, fm.getAscent());
	}
	
	public Dimension getPreferredSize() {
		String text = font.getFamily();
		Graphics g = getGraphics();
		FontMetrics fm = g.getFontMetrics(font);
		return new Dimension(fm.stringWidth(text), fm.getHeight());
	}
}
