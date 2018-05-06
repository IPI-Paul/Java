package chapter04;

import java.util.*;
import ipi.*;

/**
 * EmployeeTest class Listing 4.2
 * This program tests the Employee class.
 * @version 1.11 2004-02-19
 * @author Cay Horstmann
 */
public class EmployeeTest {
	private static final String MAIN_CLASS = "chapter04.Chapter04";
	private static String message = "";

	public static void main(String[] args) {
		// fill the staff array with three Employee objects
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("Carl Craker", 75000, 1987, 12, 15);
		staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee("Tony Tester", 40000, 1990, 3,15);
		/**
		 * if the Employee getHireDay was not cloned the entries below would reference the same 
		 * Date object and alter the year in which the employee was hired
		Date d = staff[0].getHireDay();
		double tenYearsInMilliseconds = 10 * 365.25 * 24 * 60 * 60 * 1000;
		d.setTime(d.getTime() - (long) tenYearsInMilliseconds);
		*/
		
		// raise everyone's salary by 5%
		for (Employee e : staff) {
			e.raiseSalary(5);
		}
		
		// print out information about all Employee objects
		for (Employee e : staff) {
			System.out.println("name=" + e.getName() + ",salary=" + e.getSalary() + ",hireDay=" + e.getHireDay());
		}
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}

class Employee {
	// instance fields
	private String name;
	private double salary;
	private Date hireDay;
	
	// constructor
	public Employee(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		// GregorianCalendar uses 0 for January
		hireDay = calendar.getTime();
	}
	
	// methods
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public Date getHireDay() {
		/** this would break encapsulation as the method returns an object
		 * of class Date which is a mutable object
		 * return hireDay;
		 * instead use a cloning method to return a reference to a mutable object
		 */
		return (Date) hireDay.clone();
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
}