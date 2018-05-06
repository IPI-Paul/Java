package chapter03;

import java.util.*;
import ipi.*;

/**
 * Listing 3.2
 * This program demonstrates console input.
 * @version 1.10 2004-02-10
 * @author Cay Horstmann
 */
public class InputTest {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";
	private static final String CLASS_NAME = InputTest.class.getName();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		if (Threads.isInClosed() == false) {
			// get first input
			System.out.println("What is your name? ");
			try {
				String name = in.nextLine();
				
				// get second input
				System.out.println("How old are you? ");
				int age = in.nextInt();
				
				// display output on console
				System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));
				
				/**
				 * Offer to close input stream to close off memory leak
				 */
				Threads.closeStream(in);
			} catch (NoSuchElementException e) {
				message = Threads.runJarProcess("cp", CLASS_NAME, "Chapter03", message);			
			}
		}
		else {
			message = Threads.getInMessage();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
