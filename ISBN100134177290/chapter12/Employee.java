package chapter12;

/**
 * {@code Employee} class Listing 12.12 <br />
 * {@link EmployeeTest} class Listing 12.11 <br />
 * chapter12_Emplyoee.h Listing 12.12 <br />
 * chapter12_Employee.c Listing 12.13 <br />
 * @version 1.10 1999-11-13
 * @author Cay Horstmann
 */
public class Employee {
	private String name;
	private double salary;
	
	public native void raiseSalary(double byPercent);
	
	public Employee(String n, double s) {
		name = n;
		salary = s;
	}
	
	public void print() {
		System.out.println(name + " " + salary);
	}
	
	static {
		System.loadLibrary("sourceFiles/libraries/chapter12_Employee");
	}
}
