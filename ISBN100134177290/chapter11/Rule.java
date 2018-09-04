package chapter11;  // composite

import java.awt.*;

/**
 * {@code Rule} class Listing 11.5 <br />
 * {@link CompositeTestFrame} class extends JFrame Listing 11.3 <br />
 * {@link CompositeComponent extends Component Listing 11.4 <br />
 * This class describes a Porter-Duff rule.
 */
public class Rule {
	private String name;
	private String porterDuff1;
	private String porterDuff2;

	/**
	 * Construcs a Porter-Duff rule. <br />
	 * @param n the rule name <br />
	 * @param pd1 the first row of the Porter-Duff square <br />
	 * @param pd2 the second row of the Porter-Duff square
	 */
	public Rule(String n, String pd1, String pd2) {
		name = n;
		porterDuff1 = pd1;
		porterDuff2 = pd2;
	}
	
	/**
	 *  Gets an explanation of the behaviour of this rule. <br />
	 *  @return the explanation 
	 */
	public String getExplanation() {
		StringBuilder r = new StringBuilder("Source ");
		if (porterDuff2.equals("  ")) r.append("clears");
		if (porterDuff2.equals(" S")) r.append("overwrites");
		if (porterDuff2.equals("DS")) r.append("blends with");
		if (porterDuff2.equals(" D")) r.append("alpha modifies");
		if (porterDuff2.equals("D ")) r.append("alpha complement modifies");
		if (porterDuff2.equals("DD")) r.append("does not affect");
		r.append(" destination");
		if (porterDuff1.equals(" S")) r.append(" and overwrites empty pixels");
		r.append(".");
		return r.toString();
	}
	
	public String toString() {
		return name;
	}
	
	/**
	 * Gets the value of this rule in the AlphaComposite class. <br />
	 * @return the AlphaComposite constant value, or-1 if there is no matching constant
	 */
	public int getValue() {
		try {
			return (Integer) AlphaComposite.class.getField(name).get(null);
		} catch (Exception e) {
			return -1;
		}
	}
}
