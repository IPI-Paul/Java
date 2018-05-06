package chapter03;

import java.util.*;
import ipi.*;

/**
 * Retirement2 class Listing 3.4
 * This program demonstrates a <code>do/while</code> loop.
 * @version 1.20 2004-02-10
 * @author Cay Horstmann
 */
public class Retirement2 {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";
	private static final String CLASS_NAME = Retirement2.class.getName();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		if (Threads.isInClosed() == false) {
			try {
				// get first input
				System.out.println("What is your name? ");
				String name = in.nextLine();
				
				System.out.println("How much money will you contribute every year? ");
				double payment = in.nextDouble();
				
				System.out.print("Interest rate in %: ");
				double interestRate = in.nextDouble();
				
				double balance = 0;
				int year = 0;
				
				String input;
				
				// update account balance while user isn't ready to retire
				do {
					// add this year's payment and interest
					balance += payment;
					double interest = balance * interestRate / 100;
					balance += interest;
					
					year++;
					
					// print current balance
					System.out.printf(name + ", after year %d, your balance is %,.2f%n", year, balance);
					
					// ask if ready to retire and get input
					System.out.print("Ready to retire? (Y/N) ");
					input = in.next();
				}
				while (input.equalsIgnoreCase("N"));
				Threads.closeStream(in);
			} catch (NoSuchElementException e) {
				message = Threads.runJarProcess("cp", CLASS_NAME, "Chapter03", message);			
			}
		} else {
			message = Threads.getInMessage();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
