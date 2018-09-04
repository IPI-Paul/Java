package chapter12;

import ipi.Views;

/**
 * {@code EmployeeTest} class Listing 12.11 <br />
 * {@link Employee} class Listing 12.12 <br />
 * chapter12_Emplyoee.h Listing 12.12 <br />
 * chapter12_Employee.c Listing 12.13 <br />
 * This program calls a C function that implements the <code>raiseSalary</code> method as 
 * a native method.
 * @version 1.10 1999-11-13
 * @author Cay Horstmann
 */
public class EmployeeTest {
	private static final String MAIN_CLASS = "chapter12.Chapter12";
	private static String message = "";

	public static void main(String[] args) {
		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("Harry Hacker", 35000);
		staff[1] = new Employee("Carl Cracker", 75000);
		staff[2] = new Employee("Tony Tester", 38000);
		
		for (Employee e : staff) 
			e.raiseSalary(5);
		for (Employee e : staff) 
			e.print();
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
