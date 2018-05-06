package chapter13;  // shuffle

import java.util.*;
import ipi.*;

/**
 * ShuffleTest class listing 13.7 <br />
 * This program demonstrates the random shuffle and sort algorithms.
 * @version 1.11 2012-01-26
 * @author Cay Horstmann
 */
public class ShuffleTest {
	private static final String MAIN_CLASS = "chapter13.Chapter13";
	private static String message = "";
	
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 1; i <=49; i++) 
			numbers.add(i);
		Collections.shuffle(numbers);
		List<Integer> winningCombination = numbers.subList(0, 6);
		Collections.sort(winningCombination);
		System.out.println(winningCombination);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
