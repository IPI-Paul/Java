package chapter02;  // serialClone

import java.io.*;
/**
import java.util.*;
 */
import java.time.*;

import ipi.Views;

/**
 * {@code SerailCloneTest} class Listing 2.4 <br />
 * {@link SerialCloneable} inner class implements {@link Cloneable}, {@link Serializable} <br />
 * {@link Employee3} inner class extends {@link SerialCloneable} <br />
 * @version 1.21 2016-06-13
 * @author Cay Horstmann
 */
public class SerailCloneTest {
	private static final String MAIN_CLASS = "chapter02.Chapter02";
	private static String message = "";
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Employee3 harry = new Employee3("Harry Hacker", 35000, 1989, 10, 1);

		// clone harry
		Employee3 harry2 = (Employee3) harry.clone();
		
		// mutate harry
		harry.raiseSalary(10);
		
		// now harry and the clone are different
		System.out.println("harry: " + harry);
		System.out.println("harry2: " + harry2);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}

/**
 * A class whose clone method uses serialization.
 */
class SerialCloneable implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	public Object clone() throws CloneNotSupportedException {
		try {
			// save the object to a byte array
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			
			try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
				out.writeObject(this);
			}
			
			// read a clone of the object from the byte array
			try (InputStream bin = new ByteArrayInputStream(bout.toByteArray())) {
				ObjectInputStream in = new ObjectInputStream(bin);
				return in.readObject();
			}
		} catch (IOException | ClassNotFoundException e) {
			CloneNotSupportedException e2 = new CloneNotSupportedException();
			e2.initCause(e);
			throw e2;
		}
	}
}

/**
 * The familiar Employee3 class, redefined to extend the SerialCloneable class.
 */
class Employee3 extends SerialCloneable {
	private static final long serialVersionUID = 1L;
	private String name;
	private double salary;
	private LocalDate hireDay;
	
	public Employee3(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		hireDay = LocalDate.of(year, month, day);
	}
	
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public LocalDate getHireDay() {
		return hireDay;
	}
	
	/**
	 * Raises the salary of this enployee. <br />
	 * @param byPercent the percentage of the raise <br />
	 */
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public String toString() {
		return getClass().getName() 
				+ "[name=" + name 
				+ ", salary=" + String.format("%,.2f", salary) 
				+ ", hireDay=" + hireDay
				+ "]";
	}
}


