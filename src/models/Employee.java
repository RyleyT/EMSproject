package models;

public class Employee {
//Instance Variables
	int ID;
	String firstName;
	String lastName;
	Department department;
	
//Constructors
	public Employee() {
		
	}
	
	public Employee(int ID, String firstName, String lastName, Department department) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
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
	public Department getDepartment() {
		return department;
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
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
}
