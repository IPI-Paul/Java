package chapter03;

import java.util.*;
import ipi.*;

/**
 * LotteryOdds class Listing 3.5
 * This program demonstrates a <code>for</code> loop.
 * @version 1.20 2--4-02-10
 * @author Cay Horstmann
 */
public class LotteryOdds {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";
	private static final String CLASS_NAME = LotteryOdds.class.getName();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		if (Threads.isInClosed() == false) {
			try {
				System.out.println("How many numbers do you need to draw? ");
				int k = in.nextInt();
				
				System.out.print("What is the highest number you can draw? ");
				int n = in.nextInt();
				/** 
				 * compute binomial coefficient n*(n-1)*(n-2)*...*(n-k+1)/(1*2*3*....*k)
				 */
				int lotteryOdds = 1;
				for (int i = 1; i <= k; i++) {
					lotteryOdds = lotteryOdds * (n - i + 1) / i;			
				}
				System.out.println("Your odds are 1 in " + String.format("%,d", lotteryOdds) + ". Good luck!");
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
