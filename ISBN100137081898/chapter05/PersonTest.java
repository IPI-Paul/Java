package chapter05; //abstractClasses

import ipi.*;

/**
 * PersonTest class Listing 5.4
 * Person abstract class Listing 5.5
 * Employee1 Person Listing 5.6
 * Student class Listing 5.7
 * This program demonstrates abstract classes.
 * @version 1.01 2004-02-21
 * @author Cay Horstmann
 */

public class PersonTest {
	private static final String MAIN_CLASS = "chapter05.Chapter05";
	private static String message = "";

	public static void main(String[] args) {
		Person[] people = new Person[2];
		
		// fill the people array with Student and Employee objects
		people[0] = new Employee1("Harry Hacker", 50000, 1989, 10, 1);
		people[1] = new Student("Maria Morris", "computer science");
		
		// print out names and descriptions of all Person objects
		for (Person p : people) {
			System.out.println(p.getName() + ", " + p.getDescription());
		}
		
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
