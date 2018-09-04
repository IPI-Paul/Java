package chapter11; // composite

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * {@code CompositeComponent extends Component Listing 11.4 <br />
 * {@link CompositeTestFrame} class extends JFrame Listing 11.3 <br />
 * {@link Rule} class Listing 11.5 <br />
 * This component draws two shapes, composed with a composition rule.
 */
public class CompositeComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	private int rule;
	private Shape shape1;
	private Shape shape2;
	private float alpha;
	
	public CompositeComponent() {
		shape1 = new Ellipse2D.Double(100, 100, 150, 100);
		shape2 = new Rectangle2D.Double(150, 150, 150, 100);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D gImage = image.createGraphics();
		gImage.setPaint(Color.red);
		gImage.fill(shape1);
		AlphaComposite composite = AlphaComposite.getInstance(rule, alpha);
		gImage.setComposite(composite);
		gImage.setPaint(Color.blue);
		gImage.fill(shape2);
		g2.drawImage(image, null, 0, 0);
	}
	
	/**
	 * Sets the composition rule. <br />
	 * @param r the rule (as an AlphaComposite constant)
	 */
	public void setRule(int r) {
		rule = r;
		repaint();
	}
	
	/**
	 * Sets the alpha of the source. <br />
	 * @param a the alpha value between 0 and 100
	 */
	public void setAlpha(int a) {
		alpha = (float) a / 100.0F;
		repaint();
	}
}
