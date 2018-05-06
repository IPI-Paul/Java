package chapter05; // abstractClasses

/**
 * Person abstract class Listing 5.5
 * PersonTest class Listing 5.4
 * Employee1 Person Listing 5.6
 * Student class Listing 5.7
 * @author Cay Horstmann
 */
public abstract class Person {
	public abstract String getDescription();
	private String name;
	
	public Person(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
}
