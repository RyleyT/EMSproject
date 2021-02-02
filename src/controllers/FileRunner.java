package controllers;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import models.Department;
import models.Employee;

/*
 * Purpose of this class is to handle all operations involving reading or writing to a file.
 */

public class FileRunner {

	static class MyObjectOutputStream extends ObjectOutputStream { //Custom object output stream class. Needed to prevent corrupted file stream
		public MyObjectOutputStream() throws SecurityException, IOException {
			super();
		}
		
		public MyObjectOutputStream(OutputStream out) throws SecurityException, IOException {
			super(out);
		}
		
		@Override
		protected void writeStreamHeader() {
			return;
		}
	}
	
//Methods
	public static void addToFile(String fileName) throws FileNotFoundException, IOException {
		File file = openFile(fileName);
		Scanner scan = new Scanner(System.in);
		ObjectOutputStream writer;
		
		if (fileName.equals("Employee")) { 
			if(file.length() < 1) {
				writer = new ObjectOutputStream(new FileOutputStream(file, true)); //If the file has not been written to, use a new stream
			} else {
				writer = new MyObjectOutputStream(new FileOutputStream(file, true)); //If it has use our previous file stream
			}	
			System.out.println("Enter the new employee's ID."); //Prompting our user for employee information
				int id = scan.nextInt();
				scan.nextLine();
			System.out.println("Enter the new employee's First Name.");
				String firstName = scan.nextLine();
			System.out.println("Enter the new employee's Last Name.");
				String lastName = scan.nextLine();
			System.out.println("Enter the new Employee's department.");
				String departmentName = scan.nextLine();
			Employee employee = new Employee(id, firstName, lastName, departmentName);
				writer.writeObject(employee); //Write employee to file
			System.out.println("Employee added to file");
			Menu.mainMenu(); //Return to main menu		
		}
		
		if (fileName.equals("Department")) {
			if(file.length() < 1) {
				writer = new ObjectOutputStream(new FileOutputStream(file, true)); //If the file has not been written to, use a new stream
			} else {
				writer = new MyObjectOutputStream(new FileOutputStream(file, true)); //If it has use our previous file stream
			}	
			System.out.println("Enter the new Departments ID."); //Prompting our user for employee information
				int id = scan.nextInt();
				scan.nextLine();
			System.out.println("Enter the new Department's Name.");
				String name = scan.nextLine();
			System.out.println("Enter the new Department's Phone Number.");
				int phoneNumber = scan.nextInt();
				scan.nextLine();
			System.out.println("Enter the new Department's budget.");
				Float budget = scan.nextFloat();
				scan.nextLine();
			Department department = new Department(name, budget, phoneNumber, id);
				writer.writeObject(department);
			System.out.println("Department added to file");
			Menu.mainMenu(); //Return to main menu
		}
		scan.close();
	}
	
	public static void removeFromFile(String fileName, int id) {
		File file = openFile(fileName);
		
		try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) { //Open up a writer with append set to false to overwrite old file
			if(fileName.equals("Employee")) {
				List<Employee> employeeList = (List<Employee>) readEmployeeFile(fileName); //Find our employee in the file and add them to a list
				for(Employee employee : employeeList) { //Search for the employee in the list
					if(employee.getID() == id) {
						employeeList.remove(employee); //Remove employee from list
					} 
				}
				for(Employee employee : employeeList) { //Now for each employee in the list, write to the file
					if(employee.getID() == id) {
						writer.writeObject(employee);
					} 
				}
			}
			
			if(fileName.equals("Department")) {
				List<Department> departmentList = (List<Department>) readDepartmentFile(fileName); //Find our employee in the file and add them to a list
				for(Department department : departmentList) { //Search for the employee in the list
					if(department.getID() == id) {
						departmentList.remove(department); //Remove employee from list
					} 
				}
				for(Department department : departmentList) { //Now for each employee in the list, write to the file
					if(department.getID() == id) {
						writer.writeObject(department);
					} 
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} catch(InputMismatchException e) {
			System.out.println("Input mismatch");
			e.printStackTrace();
		}
		
	}
	
	public static void updateFile(String fileName, int ID) throws FileNotFoundException, IOException {
		File file = openFile(fileName);
		Scanner scan = new Scanner(System.in);
		ObjectOutputStream writer;
		
		if(fileName.equals("Employee")) { 
			if(file.length() < 1) { //Check if file has contents
				writer = new ObjectOutputStream(new FileOutputStream(file, true)); //If the file has not been written to, use a new stream
			} else {
				writer = new MyObjectOutputStream(new FileOutputStream(file, true)); //If it has use our previous file stream
			}
			List<Employee> employeeList = (List<Employee>) readEmployeeFile(fileName); //Grab employees from file and convert them into a list
			for(Employee e : employeeList) { //Iterate through our employee list until we find the right ID
				if(e.getID() == ID) { //If we find the right ID, initiate the prompts for the user
					System.out.println("What would you like to update?");
					System.out.println("1.)ID\t2.)First Name\t3.)Last Name\t4.)Department name");
					int choice = scan.nextInt();
					scan.nextLine();
					if(choice == 1) {
						System.out.println("Enter a new ID");
						int newId = scan.nextInt();
						scan.nextLine();
						e.setID(newId);
						System.out.println();
					} else if(choice == 2) {
						System.out.println("Enter a new First Name");
						String firstName = scan.nextLine();
						e.setFirstName(firstName);
					} else if(choice == 3) {
						System.out.println("Enter a new Last Name");
						String lastName = scan.nextLine();
						e.setLastName(lastName);
					} else if(choice == 4) {
						System.out.println("Enter a new Department");
						String department = scan.nextLine();
						e.setDepartment(department);				
					}
				}
			}
			for(Employee e : employeeList) { //Write new employee objects to file
				writer.writeObject(e);
			}
		}
		
		if(fileName.equals("Department")) {
			List<Department> departmentList = (List<Department>) readDepartmentFile(fileName); //Convert them into a list
			for(Department d: departmentList) {
				if(d.getID() == ID) {
					
				} 
			}
		}
		
		Menu.mainMenu();
	}
	
	public static void listFile(String fileName) {
		int id;
		if(fileName.equals("Employee")) { //Should list only one employee's information. Search can be done using ID
			try(Scanner scan = new Scanner(System.in)) {
				System.out.println("Enter the ID of the employee you would like to find: ");
				id = scan.nextInt(); //Get our employee ID
				scan.nextLine(); //Eat the new line character		
				List<Employee> employeeList = (List<Employee>) readEmployeeFile(fileName); //Grab the list of employees from the file
				for(Employee employee : employeeList) {
					if(employee.getID() == id) {
						System.out.println(employee.toString());
					} 
				}
				Menu.mainMenu();
			} catch(Exception e) {	
				e.printStackTrace();
			}
		} else if(fileName.equals("Department")) { //Should list only one departments information
			try(Scanner scan = new Scanner(System.in)) {
				System.out.println("Enter the ID of the department you would like to find: ");
				id = scan.nextInt(); //Get our employee ID
				List<Department> departmentList = (List<Department>) readDepartmentFile(fileName);
				for(Department department : departmentList) {
					if(department.getID() == id) {
						System.out.println(department.toString());
					} 
				}
				Menu.mainMenu();
			} catch(Exception e) {	
				e.printStackTrace();
			}
		}
	}
	
//Private Helper methods
	private static File openFile(String fileName) { //Helper method to open a file
		File file = new File("resources/" + fileName + ".txt");
		if(!file.exists()) { //If file does not exist create a new one
			try {
				file.createNewFile();
				System.out.println("File created");
				return file;
			} catch(IOException e) {
			System.out.println("File not created");	
			}
		} else if (file.exists()) { //Return the file if it exists
			return file;
		} 
		return null;
	}
	
	private static List<Employee> readEmployeeFile(String fileName) throws FileNotFoundException, IOException { //Helper method to read a file and format its data. 
		File file = openFile(fileName);
		List<Employee> employees = new ArrayList<Employee>(); 
			try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
				boolean isTrue = true;	
				while(isTrue) {
					Object object = reader.readObject();
					if(object != null) {
						employees.add((Employee) object);
					} else {
						isTrue = false;
					}
				}
				return employees;
			} catch(EOFException e) {
				return employees;
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Returning null from read file");
			return null;
	}
	
	private static List<Department> readDepartmentFile(String fileName) { //Helper method to read a file and format its data. 
		File file = openFile(fileName);
		List<Department> departments = new ArrayList<Department>(); 
			try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
				boolean isTrue = true;	
				while(isTrue) {
					Object object = reader.readObject();
					if(object != null) {
						departments.add((Department) object);
					} else {
						isTrue = false;
					}
				}
				return departments;
			} catch(EOFException e) {
				return departments;
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Returning null from read file");
			return null;
	}
	
	
}
