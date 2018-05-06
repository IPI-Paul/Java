package chapter03;

import ipi.*;

/**
 * FirstSample class Listing 3.1
 * This is the first sample program in Core Java Chapter 3
 * @version 1.01 1997-03-22
 * @author Gary Cornell
 */
public class FirstSample {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";

	public static void main(String[] args) {
		System.out.println("We will not use 'Hello, World!");
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
