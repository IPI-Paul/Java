package chapter06; // clone

import java.util.Date;
import java.util.GregorianCalendar;

/** 
 * Employee1 Cloneable Listing 6.4
 * CloneTest class Listing 6.3
 */
public class Employee1 implements Cloneable {
	private String name;
	private double salary;
	private Date hireDay;
	
	public Employee1(String n, double s) {
		name = n;
		salary = s;
		hireDay = new Date();
	}
	
	public Employee1 clone() throws CloneNotSupportedException {
		// call Object.clone()
		Employee1 cloned = (Employee1) super.clone();
		
		// clone mutable fields
		cloned.hireDay = (Date) hireDay.clone();
		
		return cloned;
	}
	
	/**
	 * Set the hire day to a given date.
	 * @param year the year of the hire day
	 * @param month the month of the hire day
	 * @param day the day of the hire day
	 */
	public void setHireDay(int year, int month, int day) {
		Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
		
		// Example of instance field mutation
		hireDay.setTime(newHireDay.getTime());
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public String toString() {
		return "Employee[name=" + name + ",salary=" + String.format("%,.2f", salary) + ",hireDay=" + 
				hireDay + "]";
	}
}
