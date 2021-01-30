package controllers;

import java.util.Scanner;

public class Menu {
	public static void mainMenu() {
		System.out.println("Welcome to the Employee Management System! \nWhich menu would you like to access?");
		System.out.println("1.) Employee menu\t 2.) Department Menu\t 3.) Exit");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		if(choice == 1) {
			menuHelper("Employee");
		} else if (choice == 2) {
			menuHelper("Department");
		}else if(choice == 3) {
			System.exit(0);
		} else {
			System.out.println("please enter 1 or 2");
		}
	}
	
	private static void menuHelper(String menuName) {
		System.out.println("Welcome to the "+ menuName +" menu!\nPlease select an option from below.");
		System.out.println("1.) Add new "+menuName+"\t 2.) Update "+menuName+"\t 3.) Remove "+menuName+"\t 4.) List "+menuName+" Information\t 5.) Back");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		if(choice == 5) {
			mainMenu();
		} 
	}
}
