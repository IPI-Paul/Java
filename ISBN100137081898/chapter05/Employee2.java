package chapter05; // equals

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Employee2 class Listing 5.9
 * EqualsTest class Listing 5.8
 * Manager1 class Listing 5.10
 */
public class Employee2 {
	private String name;
	private double salary;
	private Date hireDay;
	
	public Employee2(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}
	
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public Date getHireDay() {
		return (Date) hireDay.clone();
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public boolean equals(Object otherObject) {
		// quick test to see if the objects are identical
		if (this == otherObject) return true;
		
		// must return false if the explicit parameter is null
		if (otherObject == null) return false;
		
		// if classes don't match, they can't be equal
		if (getClass() != otherObject.getClass()) return false;
		
		// now we know other object is a non-null Employee2
		Employee2 other = (Employee2) otherObject;
		
		// test whether the fields have identical values
		return Objects.equals(name, other.name) && salary == other.salary && 
				Objects.equals(hireDay, other.hireDay);
	}
	
	public int hashCode() {
		return Objects.hash(name, salary, hireDay);
	}
	
	public String toString() {
		return getClass().getName() + "[name=" + name + ",salary=" + String.format("%,.2f", salary) + ",hireDay=" + hireDay + "]";
	}
}
