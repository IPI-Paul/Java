package chapter05; // abstractClasses

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Employee1 Person Listing 5.6
 * PersonTest class Listing 5.4
 * Person abstract class Listing 5.5
 * Student class Listing 5.7
 * @author Cay Horstmann
 */
public class Employee1 extends Person {
	private double salary;
	private Date hireDay;
	
	public Employee1(String n, double s, int year, int month, int day) {
		super(n);
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}
	public double getSalary() {
		return salary;
	}
	public Date getHireDay() {
		return (Date) hireDay.clone();
	}
	public String getDescription() {
		return String.format("an employee with a salary of $%,.2f", salary);
	}
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
}
