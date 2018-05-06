package chapter04;

import ipi.Views;

/**
 * ParamTest class Listing 4.4
 * This program demonstrates parameter passing in Java.
 * @version 1.00 2000-01-27
 * @author Cay Horstmann
 */

public class ParamTest {
	private static final String MAIN_CLASS = "chapter04.Chapter04";
	private static String message = "";

	public static void main(String[] args) {
		/*
		 * Test 1: Methods can't modify numeric parameters
		 */
		System.out.println("Testing tripleValue:");
		double percent = 10;
		System.out.println("Before: percent=" + String.format("%d%%", (int) percent));
		tripleValue(percent);
		System.out.println("After: percent=" + String.format("%d%%", (int) percent));
		
		/*
		 * Test2: Methods can change the state of object parameters
		 */
		System.out.println("\nTesting tripleSalary:");
		Employee2 harry = new Employee2("Harry", 50000);
		System.out.println("Before: salary=" + String.format("%,.2f", harry.getSalary()));
		tripleSalary(harry);
		System.out.println("After: salary=" + String.format("%,.2f", harry.getSalary()));
		
		/*
		 * Test 3: Method can't attach new objects to object parameters
		 */
		System.out.println("\nTesting swap:");
		Employee2 a = new Employee2("Alice", 70000);
		Employee2 b = new Employee2("Bob", 60000);
		System.out.println("Before: a=" + a.getName());
		System.out.println("Before: b=" + b.getName());
		swap(a, b);
		System.out.println("After: a=" + a.getName());
		System.out.println("After: b=" + b.getName());
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	public static void tripleValue(double x) {// doesn't work
		x = 3 * x;
		System.out.println("End of method: x=" + String.format("%d%%", (int) x));
	}
	
	public static void tripleSalary(Employee2 x) {// works
		x.raiseSalary(200);
		System.out.println("End of method: salary=" + String.format("%,.2f", x.getSalary()));
	}
	
	public static void swap(Employee2 x, Employee2 y) {
		Employee2 temp = x;
		x = y;
		y = temp;
		System.out.println("End of method: x=" + x.getName());
		System.out.println("End of method: y=" + y.getName());
	}
}

class Employee2 {// simplified Employee class
	private String name;
	private double salary;
	
	public Employee2(String n, double s) {
		name = n;
		salary = s;
	}
	
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent /100;
		salary += raise;
	}
}