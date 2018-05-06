package chapter14; // unsynch

import ipi.*;

/**
 * UnsynchBankTest class Listing 14.5 <br />
 * Bank class Listing 14.6 <br />
 * TransferRunnable Runnable Listing 14.7 <br />
 * This program shows data corruption when multiple threads access a data structure.
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class UnsynchBankTest {
	private static final String MAIN_CLASS = "chapter14.Chapter14";
	private static String message = "";
	private static boolean continueRunning = true;
	
	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;
	
	public static void main(String[] args) {
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for (i = 0; i < NACCOUNTS; i++) {
			TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
		continueRunning = false;
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	public static boolean getRunState() {
		return continueRunning;
	}
}
