package chapter14;

import javax.swing.*;
import ipi.*;

/**
 * This class combines all examples and programs from chapter 14
 * @author Paul I Ighofose
 */
public class Chapter14 {
	public static final String CLASS_NAME = Chapter14.class.getName();
	public static String message = "";
	public static JFrame frame;
	
	private static final String BOUNCE = "Listing 14.1-3 Bounce";
	private static final String BOUNCE_THREAD = "Listing 14.4 Bounce Thread";
	private static final String UNSYNCH_BANK_TEST = "Listing 14.5-7 Unsynched Bank Test";
	private static final String LOCKED_BANK_TEST = "Listing 14.8 Locked Bank Test";
	private static final String SYNCHED_BANK_TEST = "Listing 14.9 Synched Bank Test";
	private static final String BLOCKING_QUEUE_TEST = "Listing 14.10 Blocking Queue Test";
	private static final String FUTURE_TEST = "Listing 14.11 Future Test";
	private static final String THREAD_POOL_TEST = "Listing 14.12 Thread Pool Test";
	private static final String FORK_JOIN_TEST = "Listing 14.13 Fork Join Test";
	private static final String SWING_THREAD_TEST = "Listing 14.14 Swing Thread Test";
	private static final String SWING_WORKER_TEST = "Listing 14.15 Swing Worker Test";
	private static final String[] example = {BOUNCE, BOUNCE_THREAD, UNSYNCH_BANK_TEST, LOCKED_BANK_TEST, 
			SYNCHED_BANK_TEST, BLOCKING_QUEUE_TEST, FUTURE_TEST, THREAD_POOL_TEST, FORK_JOIN_TEST, 
			SWING_THREAD_TEST, SWING_WORKER_TEST}; 
	private static int runAgain;
	private static String title = "Example Of";

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			Views.runMethod(CLASS_NAME, args[0]);
			return;
		}

		do {
			/**
			 * JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, initialSelectionValue)
			 */
			Object exampleType = JOptionPane.showInputDialog(null, "Please Select an Example!", title,
					JOptionPane.QUESTION_MESSAGE, null, example, BOUNCE);
			if (exampleType != null)
				title = "Last Used: " + exampleType.toString();
			if (exampleType == BOUNCE) {
				/**
				 * Bounce class Listing 14.1 <br />
				 * BounceFrame JFrame inner class <br />
				 * Ball class Listing 14.2 <br />
				 * BallComponent JPanel Listing 14.3 <br />
				 */
				Bounce.main(null);
				return;
			}
			else if (exampleType == BOUNCE_THREAD) {
				/**
				 * BounceThread class Listing 14.4 <br />
				 * BallRunnable runnable inner class <br />
				 * BounceFrame1 JFrame inner class <br />	
				 */
				BounceThread.main(null);
				return;
			}
			else if (exampleType == UNSYNCH_BANK_TEST) {
				/**
				 * UnsynchBankTest class Listing 14.5 <br />
				 * Bank class Listing 14.6 <br />
				 * TransferRunnable Runnable Listing 14.7 <br />
				 */
				UnsynchBankTest.main(null);
				return;
			}
			else if (exampleType == LOCKED_BANK_TEST) {
				/**
				 * LockedBankTest class <br />
				 * LockedBank class Listing 14.8 <br />
				 * LockedTransferRunnable Runnable <br />
				 */
				LockedBankTest.main(null);
				return;
			}
			else if (exampleType == SYNCHED_BANK_TEST) {
				/**
				 * SynchedBankTest class <br />
				 * SynchedBank class Listing 14.9 <br />
				 * SynchedTransferRunnable Runnable <br />
				 */
				SynchedBankTest.main(null);
				return;
			}
			else if (exampleType == BLOCKING_QUEUE_TEST) {
				/** 
				 * BlockingQueueTest class Listing 14.10 <br />
				 * FileEnumerationTask Runnable inner class <br />
				 * SearchTask Runnable inner class <br />
				 */
				BlockingQueueTest.main(null);
				return;
			}
			else if (exampleType == FUTURE_TEST) {
				/**
				 * FutureTest class Listing 14.11 <br />
				 * MatchCounter callable inner class <br />
				 */
				FutureTest.main(null);
				return;
			}
			else if (exampleType == THREAD_POOL_TEST) {
				/** 
				 * ThreadPoolTest classListing 14.12 <br />
				 * MatchCounterPool callable inner class <br />
				 */
				try {
					ThreadPoolTest.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
			else if (exampleType == FORK_JOIN_TEST) {
				/**
				 * ForkJoinTest class Listing 14.13 <br />
				 * Filter interface <br />
				 * Counter RecursiveTask inner class <br />
				 */
				ForkJoinTest.main(null);
				return;
			}
			else if (exampleType == SWING_THREAD_TEST) {
				/**
				 * SwingThreadTest class Listing 14.14 <br />
				 * SwingThreadFrame JFrame inner class <br />
				 * BadWorkerRunnable Runnable inner class <br />
				 * GoodWorkerRunnable Runnable inner class <br />
				 */
				SwingThreadTest.main(null);
				return;
			}
			else if (exampleType == SWING_WORKER_TEST) {
				/**
				 * SwingWorkerTest class Listing 14.15 <br />
				 * SwingWorkerFrame JFrame inner class <br />
				 * ProgressData private class <br />
				 * TextReader private class <br />
				 */
				SwingWorkerTest.main(null);
				return;
			}

			/**
			 * JOptionPane.showConfirmDialog(parentComponent, message, title, optionType)
			 */
			runAgain = JOptionPane.showConfirmDialog(null, title + System.lineSeparator() +
					"Do you want to run another method?", "Run Another Method", JOptionPane.YES_NO_OPTION);
		}
		while (runAgain == JOptionPane.YES_OPTION);
		Threads.closeApplication();
	}
}
