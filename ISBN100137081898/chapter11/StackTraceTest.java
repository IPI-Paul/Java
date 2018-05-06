package chapter11;  // stackTrace

import java.util.*;
import ipi.*;

/**
 * StackTraceTest class Listing 11.1
 * A program that displays a trace feature of a recursive method call.
 * @version 1.01 2004-05-10
 * @author Cay Horstmann
 */
public class StackTraceTest {
	private static final String MAIN_CLASS = "chapter11.Chapter11";
	private static String message = "";
	private static final String CLASS_NAME = StackTraceTest.class.getName();

	/**
	 * Computes the factorial of a number
	 * @param n a non-negative integer
	 * @return n! = 1 * 2 * ... * n
	 */
	public static int factorial(int n) {
		System.out.println("factorial(" + n + "): ");
		Throwable t = new Throwable();
		StackTraceElement[] frames = t.getStackTrace();
		for (StackTraceElement f : frames)
			System.out.println(f);
		int r;
		if (n <= 1) r = 1;
		else r = n * factorial(n - 1);
		System.out.println("return " + r);
		return r;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		if (Threads.isInClosed() == false) {
			System.out.print("Enter n: ");
			try {
				int n = in.nextInt();
				factorial(n);
				Threads.closeStream(in);
			} catch (NoSuchElementException e) {
				message = Threads.runJarProcess("cp", CLASS_NAME, "Chapter11", message);			
			}
		} else {
			message = Threads.getInMessage();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
