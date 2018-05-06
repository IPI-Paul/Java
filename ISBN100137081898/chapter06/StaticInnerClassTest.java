package chapter06; //staticInnerClass

import ipi.Views;

/**
 * StaticInnerClassTest class Listing 6.8
 * This program demonstrates the use of static inner classes.
 * @version 1.01 2004-02-27 
 * @author Cay Horstmann
 */
public class StaticInnerClassTest {
	private static final String MAIN_CLASS = "chapter06.Chapter06";
	private static String message = "";

	public static void main(String[] args) {
		double[] d = new double[20];
		for (int i = 0; i < d.length; i++) {
			d[i] = 100 * Math.random();
		}
		ArrayAlg.Pair p = ArrayAlg.minmax(d);
		System.out.println("min = " + p.getFirst());
		System.out.println("max = " + p.getSecond());
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
class ArrayAlg {
	/**
	 * A pair of floating-point numbers
	 */
	public static class Pair {
		private double first;
		private double second;
		
		/**
		 * Constructs a pair form two floating-point numbers
		 * @param f the first number
		 * @param s the second number
		 */
		public Pair(double f, double s) {
			first = f;
			second = s;
		}
		
		/** 
		 * Returns the first number of the pair
		 * @return the first number
		 */
		public double getFirst() {
			return first;
		}
		
		/**
		 * Returns the second number of the pair
		 * @return the second number
		 */
		public double getSecond() {
			return second;
		}
	}
		
	/**
	 * Computes both the minimum and the maximum of an array
	 * @param values an array of floating-point numbers
	 * @return a pair whose first element is the minimum and whose second element
	 * is the maximum
	 */
	public static Pair minmax(double[] values) {
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (double v : values) {
			if (min > v) min = v;
			if (max < v) max = v;
		}
		return new Pair(min, max);
	}
}
