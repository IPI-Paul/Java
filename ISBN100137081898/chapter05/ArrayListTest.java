package chapter05; // arrayList

import java.util.*;
import ipi.*;

/**
 * ArrayListTest class Listing 5.11 
 * This program demonstrates the ArrayList class.
 * @version 1.11 2012-01-26
 * @author Cay Horstmann
 */
public class ArrayListTest {
	private static final String MAIN_CLASS = "chapter05.Chapter05";
	private static String message = "";

	public static void main(String[] args) {
		// fill the staff array list with three Employee objects
		ArrayList<Employee2> staff = new ArrayList<>();
		staff.add(new Employee2("Carl Cracker", 75000, 1987, 12, 15));
		staff.add(new Employee2("Harry Hacker", 50000, 1989, 10, 1));
		staff.add(new Employee2("Tony Tester", 40000, 1990, 3, 15));
		
		// raise everyone's salary by 5%
		for (Employee2 e : staff) {
			e.raiseSalary(5);
		}
		
		// print out information about all Employee objects
		for (Employee2 e : staff) {
			System.out.println("name=" + e.getName() + ",salary=" + String.format("%,.2f", e.getSalary()) + 
					",hireDay=" + e.getHireDay());
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
