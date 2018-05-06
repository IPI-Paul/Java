package chapter03;

import java.util.*;
import ipi.*;

/** 
 * Retirement class Listing 3.3
 * This program demonstrates a <code>while</code> loop.
 * @version 1.20 2004-02-10
 * @author Cay Horstmann
 */
public class Retirement {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";
	private static final String CLASS_NAME = Retirement.class.getName();

	public static void main(String[] args) {
		//read inputs
		Scanner in = new Scanner(System.in);
		
		if (Threads.isInClosed() == false) {
			try {
				// get first input
				System.out.println("What is your name? ");
				String name = in.nextLine();
				
				System.out.println("How much money do you need to retire? ");
				double goal = 0;
				try {
					goal = in.nextDouble();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.print("How much money will you contribute every year? ");
				double payment = in.nextDouble();
				
				System.out.print("interest rate in %: ");
				double interestRate = in.nextDouble();
				
				double balance = 0;
				int years = 0;
				
				// update account balance while goal isn't reached
				while(balance < goal) {
					// add this year's payment and interest
					balance += payment;
					double interest = balance * interestRate / 100;
					balance += interest;
					years++;
				}
				System.out.println(name + ", you can retire in " + years + " years.");
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
