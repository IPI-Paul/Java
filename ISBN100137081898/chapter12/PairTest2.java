package chapter12;  // pair2

import java.util.*;
import ipi.*;

/**
 * PairTest2 class Listing 12.2 <br />
 * ArrayAlg class inner class <br />
 * Pair1<T> generic inner class <br />
 * @version 1.01 2012-01-26
 * @author Cay Horstmann
 */
public class PairTest2 {
	private static final String MAIN_CLASS = "chapter12.Chapter12";
	private static String message = "";
	
	public static void main(String[] args) {
		GregorianCalendar[] birthdays = {
				new GregorianCalendar(1906, Calendar.DECEMBER, 9),  // G. Hopper
				new GregorianCalendar(1815, Calendar.DECEMBER, 10), // A. Lovelace
				new GregorianCalendar(1903, Calendar.DECEMBER, 3), // J. von Neumann
				new GregorianCalendar(1910, Calendar.JUNE, 22) // K.Zuse
		};
		Pair1<GregorianCalendar> mm = ArrayAlg1.minmax(birthdays);
		System.out.println(birthdays[0].getTime() + "\n" + birthdays[1].getTime() + "\n" + 
				birthdays[2].getTime() + "\n" + birthdays[3].getTime());
		System.out.println("min = " + mm.getFirst().getTime());
		System.out.println("max = " + mm.getSecond().getTime());
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}

class ArrayAlg1 {
	/** 
	 * Gets the minimum and maximum of an array of objects of type T.
	 * @param a an array of objects of type T
	 * @return a pair with the min and max value, or null if a is null or empty
	 */
	public static <T extends Comparable> Pair1<T> minmax(T[] a) {
		if (a == null || a.length == 0) return null;
		T min = a[0];
		T max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (min.compareTo(a[i]) > 0) min = a[i];
			if (max.compareTo(a[i]) < 0) max = a[i];
		}
		return new Pair1<>(min, max);
	}
}


class Pair1<T> {
	private T first;
	private T second;
	
	public Pair1() { first = null; second = null; }
	public Pair1(T first, T second) { this.first = first; this.second = second; }
	
	public T getFirst() { return first; }
	public T getSecond() { return second; }
	
	public void SetFirst(T newValue) { first = newValue; }
	public void setSecond(T newValue) { second = newValue; }
}