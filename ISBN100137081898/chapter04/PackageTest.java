package chapter04;

import static java.lang.System.*;
import ipi.*;
import chapter04.com.horstmann.corejava.Employee;

/**
 * PackageTest class Listing 4.6
 * Employee class Listing 4.7
 * This program demonstrates the use of packages.
 * @version 1.11 2004-02-19
 * @author Cay Horstmann
 */
public class PackageTest {
	private static final String MAIN_CLASS = "chapter04.Chapter04";
	private static String message = "";

	public static void main(String[] args) {
		/* because of the import statement, we don't have to use
		 * com.horstmann.corejava.Employee here
		 */
		Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		
		harry.raiseSalary(5);
		
		/* because of the static import statement, we don't have to use
		 * system.out here 
		 */
		out.println("name=" + harry.getName() + ",salary=" + 
		 String.format("%,.2f", harry.getSalary()));
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
