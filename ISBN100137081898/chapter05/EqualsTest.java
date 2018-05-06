package chapter05; // equals

import ipi.*;

/**
 * EqualsTest class Listing 5.8
 * Employee2 class Listing 5.9
 * Manager1 class Listing 5.10
 * This program demonstrates the equals method.
 * @version 1.12 2012-01-26
 * @author Cay Horstmann
 */
public class EqualsTest {
	private static final String MAIN_CLASS = "chapter05.Chapter05";
	private static String message = "";

	public static void main(String[] args) {
		Employee2 alice1 = new Employee2("Alice Adams", 75000, 1987, 12, 15);
		Employee2 alice2 = alice1;
		Employee2 alice3 = new Employee2("Alice Adams", 75000, 1987, 12, 15);
		Employee2 bob = new Employee2("Bob Brandson", 50000, 1989, 10, 1);
		
		System.out.println("alice1 == alice2: " + (alice1 == alice2));		
		System.out.println("alice1 == alice3: " + (alice1 == alice3));		
		System.out.println("alice1.equals(alice3): " + alice1.equals(alice3));
		System.out.println("alice1.equals(bob): " + alice1.equals(bob));
		System.out.println("bob.toString(): " + bob);
		
		Manager1 carl = new Manager1("Carl Cracker", 80000, 1987, 12, 15);
		Manager1 boss = new Manager1("Carl Craker", 80000, 1987, 12, 15);
		boss.setBonus(5000);
		
		System.out.println("boss.toString(): " + boss);
		System.out.println("carl.equals(boss): " + carl.equals(boss));
		System.out.println("alice1.hashCode(): " + alice1.hashCode());
		System.out.println("alice3.hashCode(): " + alice3.hashCode());
		System.out.println("bob.hashCode(): " + bob.hashCode());
		System.out.println("carl.hashCode(): " + carl.hashCode());
		
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
