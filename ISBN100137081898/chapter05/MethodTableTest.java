package chapter05; // mehtods

import java.lang.reflect.*;
import ipi.*;

/**
 * MethodTableTest class Listing 5.17
 * This program shows how to invoke methods through reflection.
 * @version 1.2 2012-05-04
 * @author Cay Horstmann
 */
public class MethodTableTest {
	private static final String MAIN_CLASS = "chapter05.Chapter05";
	private static String message = "";

	public static void main(String[] args) throws Exception {
		// get method pointers to the square and sqrt methods
		Method square = MethodTableTest.class.getMethod("square", double.class);
		Method sqrt = Math.class.getMethod("sqrt", double.class);
		
		// print tables of x- and y-values
		
		printTable(1, 10, 10, square);
		printTable(1, 10, 10, sqrt);

		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	/**
	 * Returns the square of a number
	 * @param x a number
	 * @return x squared
	 */
	public static double square(double x) {
		return x * x;
	}
	
	/**
	 * Prints a table with x- and y- values for a method
	 * @param from the lower bound for the x-values
	 * @param to the upper bound for the x-values
	 * @param n the number of rows in the table
	 * @param f a method with a double parameter and double return value
	 */
	public static void printTable(double from, double to, int n, Method f) {
		// print out the method as table header
		System.out.println(f);
		
		double dx = (to - from) / (n - 1);
		
		for (double x = from; x <= to; x += dx) {
			try {
				double y = (Double) f.invoke(null, x);
				System.out.printf("%10.4f | %10.4f%n", x, y);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
