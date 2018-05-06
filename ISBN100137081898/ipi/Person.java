package ipi;

import java.util.*;

/**
 * @version 1.0 2018-04-01
 * @author Paul I Ighofose 
 * Person class gives a default construct for person data
 */
public class Person {
	private String firstName;
	private String middleName;
	private String lastName;
	private String maidenName;
	private String nhsNumber;
	private String nationalInsuranceNumber;
	private String status;
	private Date born;
	private Date died;
	private String sex;
	
	/**
	 * Person class default construct
	 */
	public Person() {}
	
	/**
	 * Sets the firstName and lastName attributes
	 * @param first First name of the person
	 * @param last Last name of the person
	 */
	public Person(String first, String last) {
		setFirstName(first);
		setLastName(last);
	}
	
	/**
	 * Sets the firstName, middleName and lastName attributes
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 */
	public Person(String first, String middle, String last) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);		
	}
	
	/**
	 * Sets the firstName, middleName, lastName, born and sex attributes
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param born The person's date of birth
	 * @param sex The sex of the person 
	 */
	public Person(String first, String middle, String last, Date born, String sex) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);		
		setBirthDate(born);
		setSex(sex);;
	}
	
	/**
	 * Sets the firstName, middleName, lastName, maidenName and status attributes
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param maiden Maiden name of the person
	 * @param status Marital status of the person
	 */
	public Person(String first, String middle, String last, String maiden, String status) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);	
		setMaidenName(maiden);
		setStaus(status);
	}
	
	/**
	 * Sets the firstName, middleName, lastName, maidenName, status and born attributes
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param maiden Maiden name of the person
	 * @param status Marital status of the person
	 * @param born The person's date of birth
	 */
	public Person(String first, String middle, String last, String maiden, String status, Date born) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);	
		setMaidenName(maiden);
		setStaus(status);
		setBirthDate(born);
	}
	
	/**
	 * Sets the firstName, middleName, lastName, maidenName, status, born and nhs attributes
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param maiden Maiden name of the person
	 * @param status Marital status of the person
	 * @param born The person's date of birth
	 * @param nhs NHS number of the person
	 */
	public Person(String first, String middle, String last, String maiden, String status, Date born, String nhs) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);		
		setMaidenName(maiden);
		setStaus(status);
		setBirthDate(born);
		setNhsNumber(nhs);
	}
	
	/**
	 * Sets the firstName, middleName, lastName, maidenName, status, born, nhs and ni attributes
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param maiden Maiden name of the person
	 * @param status Marital status of the person
	 * @param born The person's date of birth
	 * @param nhs NHS number of the person
	 * @param ni National Insurance number of the person
	 */
	public Person(String first, String middle, String last, String maiden, String status, Date born, String nhs, String ni) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);		
		setMaidenName(maiden);
		setStaus(status);
		setBirthDate(born);
		setNhsNumber(nhs);
		setNINumber(ni);
	}
	
	/**
	 * Sets the firstName, middleName, lastName, maidenName, status, born, nhs and ni attributes
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param maiden Maiden name of the person
	 * @param status Marital status of the person
	 * @param born The person's date of birth
	 * @param sex The sex of the person 
	 * @param nhs NHS number of the person
	 * @param ni National Insurance number of the person
	 */
	public Person(String first, String middle, String last, String maiden, String status, Date born, String sex, String nhs, String ni) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);		
		setMaidenName(maiden);
		setStaus(status);
		setBirthDate(born);
		setSex(sex);
		setNhsNumber(nhs);
		setNINumber(ni);
	}
	
	/**
	 * Default construct for setting a person up as a user
	 */
	public void User() {}
	
	/**
	 * Construct for setting a person up as a user using the First Name and Last Name
	 * @param first Sets the firstName attribute
	 * @param last Sets the lastName attribute
	 */
	public void User(String first, String last) {
		setFirstName(first);
		setLastName(last);
	}
	
	/**
	 * Construct for setting a person up as a user
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 */
	public void User(String first, String middle, String last) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);		
	}
	
	/**
	 * Construct for setting a person up as a user
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param born The person's date of birth
	 * @param sex The sex of the person 
	 */
	public void User(String first, String middle, String last, Date born, String sex) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);		
		setBirthDate(born);
		setSex(sex);;
	}
	
	/**
	 * Construct for setting a person up as a user
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param maiden Maiden name of the person
	 * @param status Marital status of the person
	 */
	public void User(String first, String middle, String last, String maiden, String status) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);	
		setMaidenName(maiden);
		setStaus(status);
	}
	
	/**
	 * Construct for setting a person up as a user
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param maiden Maiden name of the person
	 * @param status Marital status of the person
	 * @param born The person's date of birth
	 */
	public void User(String first, String middle, String last, String maiden, String status, Date born) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);	
		setMaidenName(maiden);
		setStaus(status);
		setBirthDate(born);
	}
	
	/**
	 * Construct for setting a person up as a user
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param maiden Maiden name of the person
	 * @param status Marital status of the person
	 * @param born The person's date of birth
	 * @param nhs NHS number of the person
	 */
	public void User(String first, String middle, String last, String maiden, String status, Date born, String nhs) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);		
		setMaidenName(maiden);
		setStaus(status);
		setBirthDate(born);
		setNhsNumber(nhs);
	}
	
	/**
	 * Construct for setting a person up as a user
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param maiden Maiden name of the person
	 * @param status Marital status of the person
	 * @param born The person's date of birth
	 * @param nhs NHS number of the person
	 * @param ni National Insurance number of the person
	 */
	public void User(String first, String middle, String last, String maiden, String status, Date born, String nhs, String ni) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);		
		setMaidenName(maiden);
		setStaus(status);
		setBirthDate(born);
		setNhsNumber(nhs);
		setNINumber(ni);
	}
	
	/**
	 * Construct for setting a person up as a user
	 * Construct for setting a person up as a user
	 * @param first First name of the person
	 * @param middle Middle name of the person
	 * @param last Last name of the person
	 * @param maiden Maiden name of the person
	 * @param status Marital status of the person
	 * @param born The person's date of birth
	 * @param sex The sex of the person 
	 * @param nhs NHS number of the person
	 * @param ni National Insurance number of the person
	 */
	public void User(String first, String middle, String last, String maiden, String status, Date born, String sex, String nhs, String ni) {
		setFirstName(first);
		setMiddleName(middle);
		setLastName(last);		
		setMaidenName(maiden);
		setStaus(status);
		setBirthDate(born);
		setSex(sex);
		setNhsNumber(nhs);
		setNINumber(ni);
	}

	/**
	 * Gets the firstName
	 * @return returns the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the firstName
	 * @param firstName First name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** 
	 * Gets the middleName
	 * @return returns the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middleName
	 * @param middleName Middle name to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets the lastName
	 * @return returns the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the lastName
	 * @param lastName Last name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the maidenName
	 * @return returns the maidenName
	 */
	public String getMaidenName() {
		return maidenName;
	}

	/**
	 * Sets the maidenName
	 * @param maidenName Maiden name to set
	 */
	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	/**
	 * Gets the nhsNumber
	 * @return returns the nhsNumber
	 */
	public String getNhsNumber() {
		return nhsNumber;
	}

	/**
	 * Sets the nhsNumber 
	 * @param nhsNumber NHS number to set
	 */
	public void setNhsNumber(String nhsNumber) {
		this.nhsNumber = nhsNumber;
	}

	/**
	 * Gets the nationalInsuranceNumber
	 * @return returns the nationalInsuranceNumber
	 */
	public String getNINumber() {
		return nationalInsuranceNumber;
	}

	/**
	 * Sets the nationalInsuranceNumber
	 * @param nationalInsuranceNumber National Insurance Number to set
	 */
	public void setNINumber(String nationalInsuranceNumber) {
		this.nationalInsuranceNumber = nationalInsuranceNumber;
	}

	/** 
	 * Gets the status (Marital Status)
	 * @return returns the status (Marital Status)
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status (Marital Status)
	 * @param status Marital staus to set
	 */
	public void setStaus(String status) {
		this.status = status;
	}

	/**
	 * Gets the date of birth
	 * @return returns born date
	 */
	public Date getBirthDate() {
		return (Date) born.clone();
	}

	/** 
	 * Sets the born date
	 * @param born Date of birth to set
	 */
	public void setBirthDate(Date born) {
		this.born = born;
	}

	/**
	 * Gets the date the person died
	 * @return returns the date the person died
	 */
	public Date getDied() {
		return (Date) died.clone();
	}

	/**
	 * Sets the date the person died
	 * @param died the date the person died
	 */
	public void setDied(Date died) {
		this.died = died;
	}

	/**
	 * Gets the sex of the person
	 * @return returns the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the sex of the person
	 * @param sex the gender of the person 
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String toString() {
		return firstName + " " + lastName;
	}
}
