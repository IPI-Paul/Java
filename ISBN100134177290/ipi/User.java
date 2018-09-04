package ipi;

import java.util.*;

/**
 * @version 2018-04-01
 * @author Paul I Ighofose 
 * User class extends Person class and gives a construct for user data
 */
public class User extends Person {
	private String userName;
	private char[] userPassword;

	/**
	 * Sets the userName attribute
	 * @param name userName to set
	 */
	public User(String name) {
		setName(name);
	}
	
	/**
	 * Sets both the userName and userPassword 
	 * @param name userName to set
	 * @param password userPassword to set
	 */
	public User(String name, char[] password) {
		setName(name);
		setPassword(password);
	}

	/**
	 * Sets the attributes for both the super class Person and this subclass
	 * @param first sets the Person class firstName attribute
	 * @param middle sets the Person class middleName attribute
	 * @param last sets the Person class lastName attribute
	 * @param name sets the userName attribute of this class
	 * @param password sets the userPassword attribute of this class
	 */
	public User(String first, String middle, String last, String name, char[] password) {
		super.setFirstName(first);
		super.setMiddleName(middle);
		super.setLastName(last);		
		setName(name);
		setPassword(password);
	}
	
	/**
	 * Sets the attributes for both the super class Person and this subclass
	 * @param first sets the Person class firstName attribute
	 * @param middle sets the Person class middleName attribute
	 * @param last sets the Person class lastName attribute
	 * @param maiden sets the Person class maidenName attribute
	 * @param name sets the userName attribute of this class
	 * @param password sets the userPassword attribute of this class
	 */
	public User(String first, String middle, String last, String maiden, String name, char[] password) {
		super.setFirstName(first);
		super.setMiddleName(middle);
		super.setLastName(last);	
		super.setMaidenName(maiden);
		setName(name);
		setPassword(password);
	}
	
	/**
	 * Sets the attributes for both the super class Person and this subclass
	 * @param first sets the Person class firstName attribute
	 * @param middle sets the Person class middleName attribute
	 * @param last sets the Person class lastName attribute
	 * @param maiden sets the Person class maidenName attribute
	 * @param status sets the Person class status attribute
	 * @param name sets the userName attribute of this class
	 * @param password sets the userPassword attribute of this class
	 */
	public User(String first, String middle, String last, String maiden, String status, String name, char[] password) {
		super.setFirstName(first);
		super.setMiddleName(middle);
		super.setLastName(last);	
		super.setMaidenName(maiden);
		super.setStaus(status);
		setName(name);
		setPassword(password);
	}
	
	/**
	 * Sets the attributes for both the super class Person and this subclass
	 * @param first sets the Person class firstName attribute
	 * @param middle sets the Person class middleName attribute
	 * @param last sets the Person class lastName attribute
	 * @param maiden sets the Person class maidenName attribute
	 * @param status sets the Person class status attribute
	 * @param born sets the Person class born attribute
	 * @param name sets the userName attribute of this class
	 * @param password sets the userPassword attribute of this class
	 */
	public User(String first, String middle, String last, String maiden, String status, Date born, String name, char[] password) {
		super.setFirstName(first);
		super.setMiddleName(middle);
		super.setLastName(last);	
		super.setMaidenName(maiden);
		super.setStaus(status);
		super.setBirthDate(born);
		setName(name);
		setPassword(password);
	}
	
	/**
	 * Sets the attributes for both the super class Person and this subclass
	 * @param first sets the Person class firstName attribute
	 * @param middle sets the Person class middleName attribute
	 * @param last sets the Person class lastName attribute
	 * @param maiden sets the Person class maidenName attribute
	 * @param status sets the Person class status attribute
	 * @param born sets the Person class born attribute
	 * @param sex sets the Person class sex attribute
	 * @param name sets the userName attribute of this class
	 * @param password sets the userPassword attribute of this class
	 * @param nhs sets the Person class nhs attribute
	 * @param ni sets the Person class ni attribute
	 */
	public User(String first, String middle, String last, String maiden, String status, Date born, String sex, String name, char[] password, String nhs, String ni) {
		super.setFirstName(first);
		super.setMiddleName(middle);
		super.setLastName(last);		
		super.setMaidenName(maiden);
		super.setStaus(status);
		super.setBirthDate(born);
		super.setSex(sex);
		setName(name);
		setPassword(password);
		super.setNhsNumber(nhs);
		super.setNINumber(ni);
	}

	/**
	 * Gets the userName of the User class
	 * @return returns the userName
	 */
	public String getName() {
		return userName;
	}

	/**
	 * Sets the userName of the User class
	 * @param userName User class attribute for a system or web site userName
	 */
	public void setName(String userName) {
		this.userName = userName;
	}

	/** 
	 * Gets the userPassword of the User class
	 * @return returns the userPassword
	 */
	public char[] getPassword() {
		return userPassword;
	}

	/**
	 * Sets the userName of the User class
	 * @param userPassword User class attribute for a system or web site userPassword
	 */
	public void setPassword(char[] userPassword) {
		this.userPassword = userPassword;
	}
	
	public String toString() {
		return userName;
	}
}
