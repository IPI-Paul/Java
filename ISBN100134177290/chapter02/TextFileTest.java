package chapter02;  // textFile

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.*;
/**
import java.util.stream.Stream;
 */
import ipi.*;

/**
 * {@code TextFileTest} class Listing 2.1 <br />
 * Employee inner class <br />
 * This program demonstrates storing an array of <code>Employee</code> records in a text 
 * file <br />
 * @version 1.14 2016-07-11
 * @author Cay Horstmann
 */
public class TextFileTest {
	private static final String MAIN_CLASS = "chapter02.Chapter02";
	private static String message = "";
	private static Loaders file = new Loaders();
	private static String delimiter = "\\|";
	
	public static void main(String[] args) throws IOException {
		Charset cs = StandardCharsets.UTF_8;
		file.setChoice("D", "dat", "", "employee.dat", cs, "" , "", "For DAT File");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
			return;
		}
		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
		staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);
		
		// save all employee records to the employee.dat
		/**
		 * Altered to enable web start support. <br />
		try (PrintWriter out = new PrintWriter(file.getFile(), "UTF-8")) {
		 */
		PrintWriter out = new PrintWriter(file.getOutputStream("dat", file.getFileName()));
		/**
			 * Also works
			writeDataStream(staff);
		 */
		writeData(staff, out);
		out.close();
		
		// retrieve all records into a new array
		/**
		 * Altered to enable web start support. <br />
		try (Scanner in = new Scanner(new FileInputStream(file.getFile()), "UTF-8")) {
		 */
		Scanner in = new Scanner(file.getInputStream(file.getPath()));
		/**
		 * Also works!
		file.setFile(file.getPath().toString(), cs);
		Stream<Employee> employees = file.toLines(cs).skip(1).map(l -> l.split(delimiter))
		.map(a -> new Employee(a[0], Double.parseDouble(a[1]), LocalDate.parse(a[2]).getYear(), 
				LocalDate.parse(a[2]).getMonthValue(), LocalDate.parse(a[2]).getDayOfMonth()));

		//print the newly read employee records
		employees.forEach(s -> {
			System.out.println(s + ": " + s.getName() + ", " + s.getSalary() + ", " +  
					String.format("%tF", s.getHireDay()));
		});
		*/
		Employee[] newStaff = readData(in);
		
		//print the newly read employee records
		for (Employee e : newStaff)
			System.out.println(e);
		in.close();;
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	/** writes all employees in array to a print writer
	 * @param employee an array of employees
	 * @param out a print writer
	 */
	private static void writeData(Employee[] employees, PrintWriter out) throws IOException {
		// write number of employees
		out.println(employees.length);
		
		for (Employee e : employees)
			writeEmployee(out, e);
	}
	
	/** 
	 * Also works!
	 * writes all employees in array to a print writer
	 * @param employee an array of employees
	 * @param out a print writer
	private static void writeDataStream(Employee[] employees) throws IOException {
		// write number of employees
		StringBuilder data = new StringBuilder();
		data.append(employees.length + System.lineSeparator());
		for (Employee e : employees) 
			data.append(writeEmployeeStream(e) + System.lineSeparator());
		file.printOut(data.toString());
	}
	 */
	
	/**
	 * Read an array of employees from a scanner
	 * @param in the scanner
	 * @return the array of employees
	 */
	private static Employee[] readData(Scanner in) {
		// retrieve the array size
		int n = in.nextInt();
		in.nextLine(); // consume newLine
		
		Employee[] employees = new Employee[n];
		for (int i = 0; i < n; i++) {
			employees[i] = readEmployee(in);
		}
		return employees;
	}
	
	/** 
	 * Writes employee data to a print writer
	 * @param out the print writer 
	 */
	public static void writeEmployee(PrintWriter out, Employee e) {
		out.println(e.getName() + "|" + e.getSalary() + "|" + String.format("%tF", e.getHireDay()));
	}
	
	/** 
	 * Writes employee data to a print writer
	 * @param out the print writer 
	 */
	public static String writeEmployeeStream(Employee e) {
		return e.getName() + "|" + e.getSalary() + "|" + String.format("%tF", e.getHireDay());
	}
	
	/**
	 * Reads employee data from a buffered reader
	 * @param in the scanner
	 */
	public static Employee readEmployee(Scanner in) {
		String line = in.nextLine();
		String[] tokens = line.split(delimiter);
		String name = tokens[0];
		double salary = Double.parseDouble(tokens[1]);
		LocalDate hireDate = LocalDate.parse(tokens[2]);
		int year = hireDate.getYear();
		int month = hireDate.getMonthValue();
		int day = hireDate.getDayOfMonth();
		return new Employee(name, salary, year, month, day);
	}
}

class Employee{
	private String name;
	private double salary;
	private Date hireDay;
	
	public Employee(String name, double salary, int year, int month, int day) {
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

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * @return the hireDate clone
	 */
	public Date getHireDay() {
		return (Date) hireDay.clone();
	}

	/**
	 * @param hireDate the hireDate to set
	 */
	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}

	/**
	 * Overrides the toString method and returns the following text
	 */
	public String toString() {
		return "name: " + getName() + ", salary: " + String.format("%,.2f", getSalary()) + ", hireDay: " + 
				String.format("%tF", getHireDay()); 
	}
}