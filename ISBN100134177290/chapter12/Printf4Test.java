package chapter12;

import java.io.*;

import ipi.Views;

/**
 * {@code Printf4Test} class Listing 12.19 <br />
 * chapter12_Printf4.c Listing 12.17 <br />
 * {@link Printf4} class Listing 12.18 <br />
 * chapter12_Printf4.h Listing 12.18 <br>
 * This program demonstrates how the native method throws an exception when the formatting
 * string is not valid.
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */
class Printf4Test {
	private static final String MAIN_CLASS = "chapter12.Chapter12";
	private static String message = "";

	public static void main(String[] args) {
		double price = 44.95;
		double tax = 7.75;
		double amountDue = price * (1 + tax / 100);
		PrintWriter out = new PrintWriter(System.out);
		
		System.out.println("'Amount due = %8.2f' will not throw an exception:");
		Printf4.fprint(out, "Amount due = %8.2f\n", amountDue);
		out.flush();
		System.out.println("'Amount due = %%8.2f' will throw an exception:");
		try {
			/* This call will throw an exception--note the %% */
			Printf4.fprint(out, "Amount due = %%8.2f\n", amountDue);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		out.flush();
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
