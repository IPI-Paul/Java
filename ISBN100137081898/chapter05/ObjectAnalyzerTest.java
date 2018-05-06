package chapter05; // objectAnalyzer

import java.security.AccessControlException;
import java.util.ArrayList;
import ipi.Threads;
import ipi.Views;

/**
 * ObjectAnalyzerTest class Listing 5.14
 * ObjectAnalyzer class Listing 5.15
 * This program uses reflection to spy on objects.
 * @version 1.12 2012-01-26
 * @author Cay Horstmann
 */
public class ObjectAnalyzerTest {
	private static final String MAIN_CLASS = "chapter05.Chapter05";
	private static String message = "";
	private static final String CLASS_NAME = ObjectAnalyzerTest.class.getName();

	public static void main(String[] args) {
		ArrayList<Integer> squares = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			squares.add(i * i);
		}
		try {
			System.out.println(new ObjectAnalyzer().toString(squares));
		} catch (AccessControlException ex) {
			message = Threads.getInJnlpMessage(CLASS_NAME);
			System.out.println(ex.toString());
		}
		
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
