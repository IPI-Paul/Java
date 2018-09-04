package chapter06;  // timeline

import java.time.*;
import java.util.*;
import java.util.stream.*;
import ipi.*;

/**
 * {@code TimeLine} class Listing 6.1 <br />
 * This program shows how to use the {@link Instant} and {@link Duration} classes for timing 
 * two algorithms. <br />
 * @author Cay Horstmann
 */
public class TimeLine {
	private static final String MAIN_CLASS = "chapter06.Chapter06";
	private static String message = "";

	public static void main(String[] args) {
		Instant start = Instant.now();
		runAlgoritm();
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start,  end);
		long millis = timeElapsed.toMillis();
		System.out.printf("%d milliseconds\n", millis);
		
		Instant start2 = Instant.now();
		runAlgoritm2();
		Instant end2 = Instant.now();
		Duration timeElapsed2 = Duration.between(start2, end2);
		System.out.printf("%d milliseconds\n", timeElapsed2.toMillis());
		boolean overTenTimesFaster = timeElapsed.multipliedBy(10)
				.minus(timeElapsed2).isNegative();
		System.out.printf("The first algoritm is %smore than ten times faster", 
				overTenTimesFaster ? "" : "not ");
		System.out.println();
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	public static void runAlgoritm() {
		int size =10;
		List<Integer> list = new Random().ints().map(i -> i % 100).limit(size)
				.boxed().collect(Collectors.toList());
		Collections.sort(list);
		System.out.println(list);
	}
	
	public static void runAlgoritm2() {
		int size = 10;
		List<Integer> list = new Random().ints().map(i -> i % 100).limit(size)
				.boxed().collect(Collectors.toList());
		while (!IntStream.range(1,  list.size()).allMatch(
				i -> list.get(i - 1).compareTo(list.get(i)) <= 0))
			Collections.shuffle(list);
		System.out.println(list);
	}
}
