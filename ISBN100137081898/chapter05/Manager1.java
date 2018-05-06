package chapter05; // equals

/**
 * Manager1 Employee Listing 5.10
 * EqualsTest class Listing 5.8
 * Employee2 class Listing 5.9
 */
public class Manager1 extends Employee2 {
	private double bonus;
	
	public Manager1(String n, double s, int year, int month, int day) {
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
	
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) return false;
		Manager1 other = (Manager1) otherObject;
		// super.equals checked that this and other belong to the same class
		return bonus == other.bonus;
	}
	
	public int hashCode() {
		return super.hashCode() + 17 * new Double(bonus).hashCode();
	}
	
	public String toString() { 
		return super.toString() + "[bonus=" + String.format("%,.2f", bonus) + "]";
	}
}
