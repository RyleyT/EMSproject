package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Department implements Serializable {

private static final long serialVersionUID = 2;

//Instance Variables
	String name;
	float budget;
	int phoneNumber;
	int ID;
	List<Employee> employees;
	
//Constructors	
	public Department(String name) {
		this.name = name;
		this.ID = 1;
		employees = new ArrayList<Employee>();
	}

	public Department(String name, float budget, int phoneNumber, int id) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.ID = id;
	}
	
//Methods
	public void addEmployee(Employee e) {
		employees.add(e);
	}
	
	public void removeEmployee(int id) {
		for(Employee e : employees) {
			if(e.getID() == id) {
				employees.remove(e);
				System.out.println("Employee removed");
			} else {
				System.out.println("Employee not found");
			}
		}
	}
	
//Getters
	public String getName() {
		return name;
	}

	public float getBudget() {
		return budget;
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	
	public int getID() {
		return ID;
	}
//Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
//Overridden Methods
	@Override
	public String toString() {
		return "Department [name=" + name + ", budget=" + budget + ", phoneNumber=" + phoneNumber + ", ID=" + ID
				+ ", employees=" + employees + "]";
	}
}
