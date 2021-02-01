package models;

import java.util.Arrays;

public class Department {
//Instance Variables
	String name;
	float budget;
	int phoneNumber;
	int ID;
	Employee employees[];
	
//Constructors	
	public Department() {
		
	}

	public Department(String name, int iD, Employee[] employees) {
		super();
		this.name = name;
		ID = iD;
		this.employees = employees;
	}
	
	
//Getters
	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}

	public Employee[] getEmployees() {
		return employees;
	}
//Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}

//Override methods
	@Override
	public String toString() {
		return "Department [name=" + name + ", ID=" + ID + ", employees=" + Arrays.toString(employees) + "]";
	}
	
}
