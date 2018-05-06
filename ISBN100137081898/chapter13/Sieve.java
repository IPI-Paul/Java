package chapter13;  // sieve

import java.util.*;
import ipi.*;

/** 
 * Sieve class Listing 13.8 <br />
 * This program runs the Sieve of Erathostenes benchmark. It computes all primes up to 2,000,000.
 * @version 1.21 2004-08-03
 * @author Cay Horstmann
 */
public class Sieve {
	private static final String MAIN_CLASS = "chapter13.Chapter13";
	private static String message = "";
	
	public static void main(String[] s) {
		int n = 2000000;
		long start = System.currentTimeMillis();
		BitSet b = new BitSet(n + 1);
		int count = 0;
		int i;
		for (i = 2; i <= n; i++) 
			b.set(i);
		i = 2;
		while (i * i <= n) {
			if (b.get(i)) {
				count++;
				int k = 2 * i;
				while (k <= n) {
					b.clear(k);
					k += i;
				}
			}
			i++;
		}
		while (i <= n) {
			if (b.get(i)) count++;
			i++;
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " primes");
		System.out.println((end - start) + " milliseconds");
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
