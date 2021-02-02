package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	public static void mainMenu() {
		System.out.println("Welcome to the Employee Management System! \nWhich menu would you like to access?");
		System.out.println("1.) Employee menu\t 2.) Department Menu\t 3.) Exit");
		int choice;
		try(Scanner scan = new Scanner(System.in)) {
			choice = scan.nextInt();
			if(choice == 1) {
				menuHelper("Employee");
			} else if (choice == 2) {
				menuHelper("Department");
			}else if(choice == 3) {
				System.exit(0);
			} else {
				System.out.println("please enter 1 or 2");
				mainMenu();
			}
		} catch (Exception e) {
			
		}
	}
	
	private static void menuHelper(String menuName) { //Helper method to display different prompts to the user
		System.out.println("Welcome to the "+ menuName +" menu!\nPlease select an option from below.");
		System.out.println("1.) Add new "+menuName+"\t 2.) Update "+menuName+"\t 3.) Remove "+menuName+"\t 4.) List "+menuName+" Information\t5.) Back");		
		try(Scanner scan = new Scanner(System.in)) {
			int choice = scan.nextInt();
			scan.nextLine(); //Eat the new line character
			if(choice == 1) {
				FileRunner.addToFile(menuName);
			}else if (choice == 2) {
				updateMenu(menuName);
			} else if (choice == 3) {
				//FileRunner.removeFromFile(menuName);
			} else if (choice == 4) {
				FileRunner.listFile(menuName);
				mainMenu();
			} else if(choice == 5) {
				mainMenu();
			} else {
				System.out.println("Error: Please select a valid choice");
				menuHelper(menuName);
			}
		} catch(InputMismatchException e) {
			System.out.println("Input mismatch");
			mainMenu();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void updateMenu(String menuName) throws FileNotFoundException, IOException {
		System.out.println("Enter the ID of the " + menuName + " you would like to update.");
		try(Scanner scan = new Scanner(System.in)){		
			int ID = scan.nextInt();
			scan.nextLine(); // Eat the new line character		
			
			if(menuName.equals("Employee")) { //Prompts for our Employee menu
					FileRunner.updateFile(menuName, ID);
			}
			
			if(menuName.equals("Department")) { //Prompts for our Department menu
				System.out.println("What would you like to update?");
				System.out.println("1.)Department name\t2.)Department budget\t3.)Department phone number\t4.)Department ID\t5.)Employees");
				int choice = scan.nextInt();
				scan.nextLine();
				if(choice == 1) {
					
				} else if(choice == 2) {
					
				} else if(choice == 3) {
					
				} else if(choice == 4) {
					
				} else if(choice == 5) {
					
				}
			} 
	
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
	}
	
	
}
