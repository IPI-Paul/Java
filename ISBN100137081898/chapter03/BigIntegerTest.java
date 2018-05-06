package chapter03;

import java.math.*;
import java.util.*;
import ipi.*;

/**
 * BigIntegerTest class Listing 3.6
 * This program uses big numbers to compute the odds of minning the
 * grand prize in a lottery.
 * @version 1.20 2004-02-10
 * @author Cay Horstmann
 */
public class BigIntegerTest {
	private static final String MAIN_CLASS = "chapter03.Chapter03";
	private static String message = "";
	private static final String CLASS_NAME = BigIntegerTest.class.getName();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		if (Threads.isInClosed() == false) {
			System.out.println("How many numbers do you need to draw? ");
			try {
				int k = in.nextInt();
				
				System.out.print("What is the highest number you can draw? ");
				int n = in.nextInt();
				
				/**
				 * compute binomial coefficient n*(n-1)*(n-2)*...*(n-k+1)/(1*2*3*...*k)
				 */
				
				BigInteger lotteryOdds = BigInteger.valueOf(1);
				
				for (int i = 1; i <= k; i++) {
					lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(n - i + 
							1)).divide(BigInteger.valueOf(i));
				}
				System.out.println("Your odds are 1 in " + String.format("%,d", lotteryOdds) + 
						". Good Luck!");
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
