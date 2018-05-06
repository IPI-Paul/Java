package chapter06; // clone

import ipi.Views;

/**
 * CloneTest class Listing 6.3
 * Employee1 Cloneable Listing 6.4
 * This program demonstrates cloning
 * @version 1.10 2002-07-01
 * @author Cay Horstmann
 */
public class CloneTest {
	private static final String MAIN_CLASS = "chapter06.Chapter06";
	private static String message = "";

	public static void main(String[] args) {
		try {
			Employee1 original = new Employee1("John Q. Public", 50000);
			original.setHireDay(2000, 1, 1);
			Employee1 copy = original.clone();
			copy.raiseSalary(10);
			copy.setHireDay(2002, 12, 31);
			System.out.println("original=" + original);
			System.out.println("copy=" + copy);
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
