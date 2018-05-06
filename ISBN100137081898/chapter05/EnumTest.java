package chapter05; // enums

import java.util.*;
import ipi.*;

/**
 * EnumTest class Listing 5.12 
 * This program demonstrates enumerated types.
 * @version 1.0 2004-05-24
 * @author Cay Horstmann
 */
public class EnumTest {
	private static final String MAIN_CLASS = "chapter05.Chapter05";
	private static String message = "";
	private static final String CLASS_NAME = EnumTest.class.getName();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		if(Threads.isInClosed() == false) {
			System.out.print("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE) ");
			try {
				String input = in.next().toUpperCase();
				Size size = Enum.valueOf(Size.class, input);
				System.out.println("size= " + size);
				System.out.println("abreviation=" + size.getAbbreviation());
				if (size == Size.EXTRA_LARGE) {
					System.out.println("Good job--you paid attention to the _.");
				}
				Threads.closeStream(in);
			} catch (NoSuchElementException ex) {
				message = Threads.runJarProcess("cp", CLASS_NAME, "Chapter05", message);			
			}
		} else {
			Threads.getInMessage();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}


enum Size {
	SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");
	private Size(String abbreviation) { this.abbreviation = abbreviation;}
	public String getAbbreviation() { return abbreviation;}
	private String abbreviation;
}
