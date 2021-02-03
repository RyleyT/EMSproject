package models;

import java.io.Serializable;

public class Employee implements Serializable {

private static final long serialVersionUID = 1; //Initialize a serial ID to prevent deserialization errors.
	
//Instance Variables
	int ID;
	String firstName;
	String lastName;
	String departmentName;
	
//Constructors
	public Employee(int ID, String firstName, String lastName, String departmentName) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentName = departmentName;
	}	
//Getters
	public int getID() {
		return ID;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getDepartment() {
		return departmentName;
	}
//Setters
	public void setID(int iD) {
		ID = iD;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setDepartment(String department) {
		this.departmentName = department;
	}
	
//Overridden methods
	@Override
	public String toString() {
		return "Employee [ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", departmentName="
				+ departmentName + "]";
	}
}
