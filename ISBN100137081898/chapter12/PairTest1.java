package chapter12;  // pair1

import java.util.Arrays;

import ipi.*;

/**
 * PairTest1 class Listing 12.1 <br />
 * ArrayAlg class inner class <br />
 * Pair<T> class generic inner class <br />
 * @version 1.01 2012-01-26
 * @author Cay Horstmann
 */
public class PairTest1 {
	private static final String MAIN_CLASS	= "chapter12.Chapter12";
	private static String message = "";
	
	public static void main(String[] args) {
		String[] words = {"Mary", "had", "a", "little", "lamb"};
		Pair<String> mm = ArrayAlg.minmax(words);
		System.out.println(Arrays.toString(words));
		System.out.println("Character encoding min/max order: " + System.lineSeparator() + "min = " + mm.getFirst());
		System.out.println("max = " + mm.getSecond());
		String[] lowercaseWords = {words[0].toLowerCase(), words[1].toLowerCase(), words[2].toLowerCase(),
				words[3].toLowerCase(), words[4].toLowerCase()};
		mm = ArrayAlg.minmax(lowercaseWords);
		System.out.println("Dictionary min/max order: " + System.lineSeparator() + "min = " + mm.getFirst());
		System.out.println("max = " + mm.getSecond());
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}

class ArrayAlg {
	/**
	 * Gets the minimum and maximum of an array of strings.
	 * @param a an array of strings
	 * @return a pair with the min and max value, or null if a is null or empty
	 */
	public static Pair<String> minmax(String[] a){
		if (a == null || a.length == 0) return null;
		String min = a[0];
		String max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (min.compareTo(a[i]) > 0) min = a[i];
			if (max.compareTo(a[i]) < 0) max = a[i];
		}
		return new Pair<>(min, max);
	}
}

class Pair<T> {
	private T first;
	private T second;
	
	public Pair() { first = null; second = null; }
	public Pair(T first, T second) { this.first = first; this.second = second; }
	
	public T getFirst() { return first; }
	public T getSecond() { return second; }
	
	public void SetFirst(T newValue) { first = newValue; }
	public void setSecond(T newValue) { second = newValue; }
}