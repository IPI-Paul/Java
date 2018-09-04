package chapter12;

import java.io.*;
import ipi.Views;

/**
 * {@code Printf3Test} class Listing 12.14 <br />
 * {@link Printf3} class Listing 12.15 <br />
 * chapter12_Printf3.h Listing 12.15 <br />
 * chapter12_Printf3.c Listing 12.16 <br />
 * This program calls Java functions from a native method.
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */
class Printf3Test {
	private static final String MAIN_CLASS = "chapter12.Chapter12";
	private static String message = "";

	public static void main(String[] args) {
		double price = 44.95;
		double tax = 7.75;
		double amountDue = price * (1 + tax / 100);
		PrintWriter out = new PrintWriter(System.out);
		Printf3.fprint(out, "Amount due = %8.2f\n", amountDue);
		out.flush();
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
