package chapter13;  // set

import java.util.*;
import ipi.*;

/**
 * SetTest class Listing 13.2 <br />
 * This program uses a set to print all unique words in System.in.
 * @version 1.11 2012-01-26
 * @author Cay Horstmann
 */
public class SetTest {
	private static final String MAIN_CLASS = "chapter13.Chapter13";
	private static final String CLASS_NAME = SetTest.class.getName();
	private static String message = "";
	
	public static void main(String[] args) {
		Set<String> words = new HashSet<>(); // HashSet implements Set
		long totalTime = 0;
		
		if (Threads.isInClosed() == false) {
			Scanner in = new Scanner(System.in);
			System.out.println("Type 'quit' to end typing!");
			String word = null;
			while (in.hasNext()) {
				word = in.next();
				if (word.equals("quit")) break;
				long callTime = System.currentTimeMillis();
				words.add(word);
				callTime = System.currentTimeMillis() - callTime;
				totalTime += callTime;
			}
			
			if (word == null) {
				message = Threads.runJarProcess("cp", CLASS_NAME, "Chapter13", CLASS_NAME);
			} else {
				Iterator<String> iter = words.iterator();
				for (int i = 1; i <= 20 && iter.hasNext(); i++) 
					System.out.println(iter.next());
				System.out.println("...");
				System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds.");
				Threads.closeStream(in);
			}
		} else {
			message = Threads.getInMessage();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
