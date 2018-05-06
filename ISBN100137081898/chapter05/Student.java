package chapter05; // abstractClasses

/**
 * Student person Listing 5.7
 * PersonTest class Listing 5.4
 * Person abstract class Listing 5.5
 * Employee1 Person Listing 5.6
 */
public class Student extends Person {
	private String major;
	
	/**
	 * @param n the student's name
	 * @param m the student's major
	 */
	public Student(String n, String m) {
		// pass n to superclass constructor
		super(n);
		major = m;
	}
	public String getDescription() {
		return "a student majoring in " + major;
	}
}
