package chapter14; // unsynch

import ipi.*;

/**
 * LockedBankTest class <br />
 * LockedBank class Listing 14.8 <br />
 * LockedTransferRunnable Runnable <br />
 * This program shows data corruption when multiple threads access a data structure.
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class LockedBankTest {
	private static final String MAIN_CLASS = "chapter14.Chapter14";
	private static String message = "";
	private static boolean continueRunning = true;
	
	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;
	
	public static void main(String[] args) {
		LockedBank b = new LockedBank(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for (i = 0; i < NACCOUNTS; i++) {
			LockedTransferRunnable r = new LockedTransferRunnable(b, i, INITIAL_BALANCE);
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
