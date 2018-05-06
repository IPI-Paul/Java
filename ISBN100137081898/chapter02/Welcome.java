package chapter02;

import ipi.*;

/**
 * Welcome class Listing 2.1
 * This program displays a greeting from the authors.
 * @version 1.20 2004-02-28
 * @author Cay Horstmann
*/
public class Welcome {
	private static final String MAIN_CLASS = "chapter02.Chapter02";
	private static String message = "";

	public static void main (String[] args) {
		String[] greeting = new String[3];
		greeting[0] = "Welcome to Core Java";
		greeting[1] = "by Cay Horstmann";
		greeting[2] = "and Gary Cornell";
		
		for (String g : greeting) {
			System.out.println(g);
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}