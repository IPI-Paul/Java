package chapter12;

import ipi.Views;

/**
 * {@code Printf2Test} class Listing 12.8 <br />
 * {@link Printf2} class Listing 12.9 <br />
 * chapter12_Printf2.h Listing 12.9 <br />
 * chapter12_Printf2.c Listing 12.10 <br />
 * This program calls the C function <code>sprintf</code>.
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */
class Printf2Test {
	private static final String MAIN_CLASS = "chapter12.Chapter12";
	private static String message = "";

	public static void main(String[] args) {
		double price = 44.95;
		double tax = 7.75;
		double amountDue = price * (1 + tax / 100);
		
		String s = Printf2.sprint("Amount due = %8.2f", amountDue);
		System.out.println(s);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
