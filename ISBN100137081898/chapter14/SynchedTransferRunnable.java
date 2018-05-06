package chapter14;  // synched

/**
 * SynchedBankTest class <br />
 * SynchedBank class Listing 14.9 <br />
 * SynchedTransferRunnable Runnable <br />
 * A runnable that transfers money from an account to other accounts in a bank.
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class SynchedTransferRunnable implements Runnable {
	private SynchedBank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	
	/**
	 * Constructs a transfer runnable.
	 * @param b the bank between whose account money is transferred
	 * @param from the account to transfer money from
	 * @param max the maximum amount of money in each transfer
	 */
	public SynchedTransferRunnable(SynchedBank b, int from, double max) {
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}
	
	public void run() {
		try {
			while (SynchedBankTest.getRunState() == true) {
				int toAccount = (int) (bank.size() * Math.random());
				double amount = maxAmount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int) (DELAY * Math.random()));
			}
		} catch (InterruptedException e) {
		}
	}
}
