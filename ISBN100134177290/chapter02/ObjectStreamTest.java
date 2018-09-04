package chapter02;  // objectStream

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import ipi.*;

/**
 * {@code ObjectStreamTest} class Listing 2.3 <br />
 * Employee2 Serializable inner class <br />
 * Manager Serializable inner class extends Employee2 <br />
 * @version 1.10 1998-08-17
 * @author Cay Horstmann
 */
public class ObjectStreamTest {
	private static final String MAIN_CLASS = "chapter02.Chapter02";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Charset cs = StandardCharsets.UTF_8;
		file.setChoice("D", "dat", "", "employee.dat", cs, "", "", "For DAT File");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
			return;
		}
		
		Employee2 harry = new Employee2("Harry Hacker", 50000, 1989, 10, 1);
		Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		carl.setSecretary(harry);
		Manager tony = new Manager("Tony tester", 40000, 1990, 3, 15);
		tony.setSecretary(harry);
		
		Employee2[] staff = new Employee2[3];
		
		staff[0] = carl;
		staff[1] = harry;
		staff[2] = tony;
		
		// save all employee records to the file employee.dat
		/**
		 * Altered to enable web start support. <br />
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(file.getFile())
				)) {
		 */
		ObjectOutputStream out = new ObjectOutputStream(file.getOutputStream("dat", file.getFileName()));
		out.writeObject(staff);
		out.close();
		
		/**
		 * Altered to enable web start support. <br />
		try (ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(file.getFile())
				)) {
		 */
		ObjectInputStream in = new ObjectInputStream(file.getInputStream(file.getPath()));
		// retrieve all records into a new array
		
		Employee2[] newStaff = (Employee2[]) in.readObject();
		
		// raise secretary's salary
		newStaff[1].raiseSalary(10);
		
		// print the newly read employee records
		for (Employee2 e : newStaff)
			System.out.println(e);
		in.close();
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}

class Employee2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private double salary;
	private Date hireDay;
	static final int NAME_SIZE = 40;
	static final int RECORD_SIZE = 100;
	
	public Employee2() {
	}
	
	public Employee2(String name, double salary, int year, int month, int day) {
		this.setName(name);
		this.setSalary(salary + 0.0);
		// GregorianCalendar uses 0 for January
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * @return the hireDate clone
	 */
	public LocalDate getHireDay() {
		return LocalDate.parse(String.format("%tF", hireDay));
	}

	/**
	 * @param hireDate the hireDate to set
	 */
	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	
	public String toString() {
		return "name: " + getName() + ", salary: " + String.format("%,.2f", getSalary()) + ", hireDay: " + 
				getHireDay(); 
	}
}

class Manager extends Employee2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private double bonus;
	private Employee2 secretary;
	
	public Manager(String n, double s, int year, int month, int day) {
		super(n, s, year, month, day);
		bonus = 0;
	}
	
	public double getSalary() {
		double baseSalary = super.getSalary();
		return baseSalary += bonus;
	}
	
	public void setBonus(double b) {
		bonus = b;
	}

	/**
	 * @return the secretary
	 */
	public Employee2 getSecretary() {
		return secretary;
	}

	/**
	 * @param secretary the secretary to set
	 */
	public void setSecretary(Employee2 secretary) {
		this.secretary = secretary;
	}
	
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) return false;
		Manager other = (Manager) otherObject;
		// super.equals checked that this and other belong to the same class
		return bonus == other.bonus;
	}
	
	public int hashCode() {
		return super.hashCode() + 17 * new Double(bonus).hashCode();
	}
	
	public String toString() { 
		return super.toString() + ", secretary: " + getSecretary().getName();
	}
}
