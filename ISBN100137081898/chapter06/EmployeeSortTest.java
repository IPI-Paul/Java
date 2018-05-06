package chapter06; // interfaces

import java.util.*;

import ipi.Views;

/**
 * EmployeeSortTest class Listing 6.1
 * Employee Comparable Listing 6.2
 * This program demonstrates the use of the Comparable interface.
 * @version 1.30 2004-02-27
 * @author Cay Horstmann
 */
public class EmployeeSortTest {
	private static final String MAIN_CLASS = "chapter06.Chapter06";
	private static String message = "";

	public static void main(String[] args) {
		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("Harry Hacker", 35000);
		staff[1] = new Employee("Carl Cracker", 75000);
		staff[2] = new Employee("Tony Tester", 38000);
		
		Arrays.sort(staff);
		
		// print out information about all Employee objects
		for (Employee e : staff) {
			System.out.println("name=" + e.getName() + ",salary=" + String.format("%,.2f", e.getSalary()));
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
