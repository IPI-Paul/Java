package chapter02;  // randomAccess

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.AccessControlException;
import java.util.*;

import javax.jnlp.JNLPRandomAccessFile;

import java.time.*;
import ipi.*;

/**
 * {@code RandomAccessTest} class Listing 2.2 <br />
 * @version 1.13 2016-07-11
 * @author Cay Horstmann
 */
public class RandomAccessTest {
	private static final String MAIN_CLASS = "chapter02.Chapter02";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static void main(String[] args) throws IOException {
		Charset cs = StandardCharsets.UTF_8;
		file.setChoice("D", "dat", "", "employee.dat", cs, "", "", "For DAT File");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
			return;
		}

		Employee1[] staff = new Employee1[3];
		
		staff[0] = new Employee1("Carl Cracker", 75000, 1987, 12, 15);
		staff[1] = new Employee1("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee1("Tony Tester", 40000, 1990, 3, 15);
		
		/**
		 * Altered to enable web start support. <br />
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file.getFile()))) {
		 */
		DataOutputStream out = new DataOutputStream(file.getOutputStream("dat", file.getFileName()));
		
		// save all employee records to the file employee.dat
		for (Employee1 e : staff)
		writeData(out, e);
		out.close();
		
		Employee1[] newStaff;
		/**
		 * Altered to enable web start support. <br />
		 */
		try (RandomAccessFile in = new RandomAccessFile(file.getFile(), "r")) {
			// retrieve all records into a new array
			
			//compute the array size
			int n = (int) (in.length() / Employee1.RECORD_SIZE);
			newStaff = new Employee1[n];
			
			// read employees in reverse order
			for (int i = n -1; i >= 0; i--) {
				newStaff[i] = new Employee1();
				in.seek(i * Employee1.RECORD_SIZE);
				newStaff[i] = readData(in);
			}
			in.close();
		} catch(AccessControlException ex) {
			JNLPRandomAccessFile in = file.getContents().getRandomAccessFile("r");
			// retrieve all records into a new array
			
			//compute the array size
			int n = (int) (in.length() / Employee1.RECORD_SIZE);
			newStaff = new Employee1[n];
			
			// read employees in reverse order
			for (int i = n -1; i >= 0; i--) {
				newStaff[i] = new Employee1();
				in.seek(i * Employee1.RECORD_SIZE);
				newStaff[i] = readData(in);
			}
			in.close();
		}
		
		// print the newly read employee records
		for (Employee1 e : newStaff) 
			System.out.println(e);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	/**
	 * Writes employee data to a data output
	 * @param out the data output
	 * @param e the employee
	 */
	public static void writeData(DataOutput out, Employee1 e) throws IOException {
		DataIO.writeFixedString(e.getName(), Employee1.NAME_SIZE, out);
		out.writeDouble(e.getSalary());
		
		LocalDate hireDay = e.getHireDay();
		out.writeInt(hireDay.getYear());
		out.writeInt(hireDay.getMonthValue());
		out.writeInt(hireDay.getDayOfMonth());
	}
	
	/**
	 * Reads employee data from a data input
	 * @param in the data input
	 * @return the employee
	 */
	public static Employee1 readData(DataInput in) throws IOException {
		String name = DataIO.readFixedString(Employee1.NAME_SIZE, in);
		double salary = in.readDouble();
		int y = in.readInt();
		int m = in.readInt();
		int d = in.readInt();
		return new Employee1(name, salary, y, m, d);
	}
}

class Employee1{
	private String name;
	private double salary;
	private Date hireDay;
	static final int NAME_SIZE = 40;
	static final int RECORD_SIZE = 100;
	
	public Employee1() {
	}
	
	public Employee1(String name, double salary, int year, int month, int day) {
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
	public LocalDate getHireDay() {
		return LocalDate.parse(String.format("%tF", hireDay));
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
				getHireDay(); 
	}
}

class DataIO {
	public DataIO() {		
	}
	
	public static void writeFixedString(String s, int size, DataOutput out) throws IOException {
		for (int i = 0; i < size; i++) {
			char ch = 0;
			if (i < s.length()) ch = s.charAt(i);
			out.writeChar(ch);
		}
	}
	
	public static String readFixedString(int size, DataInput in) throws IOException {
		StringBuilder b = new StringBuilder(size);
		int i = 0;
		boolean more = true;
		while (more && i < size) {
			char ch = in.readChar();
			i++;
			if (ch == 0) more = false;
			else b.append(ch);
		}
		in.skipBytes(2 * (size - i));
		return b.toString();
	}
}