package com;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.io.IOException;
import java.util.InputMismatchException;

public class UseEmployee {	
	private static final Logger LOGGER = Logger.getLogger(UseEmployee.class.getName());
	
	public static void main(String[] args) {	
		LOGGER.info("Logger Name: " + LOGGER.getName());
		
		EmployeeServiceImpl service = new EmployeeServiceImpl();
		
		service.addEmployee(10001, "Samuel Sells", 10000.00, "Dallas, TX");
		service.addEmployee(10002, "Rachel Sells", 5000.00, "Dallas, TX");
		service.addEmployee(10003, "Christopher Sells", 45000.00, "Dallas, TX");
		service.addEmployee(10004, "Katherine Sells", 15000.00, "Dallas, TX");
		service.addEmployee(10005, "Kelsey Galvin", 20000.00, "Los Angeles, CA");
		service.addEmployee(10006, "Christina Spear", 30000.00, "Indianapolis, IN");
		
		Scanner scan = new Scanner(System.in);
		int select = 0;
		
		//printProblems();
		
		while(select != 6) {
			try {
				LOGGER.info("Taking in Int Value for Switch Decision Making");
				
				System.out.println("1. List All Employees" + "\n" 
						+ "2. Display Yearly Salary" + "\n"
						+ "3. Display Specific Employee Details" + "\n"
						+ "4. Modify the Employee Details" + "\n"
						+ "5. Delete an Employee" + "\n"
						+ "6. Exit" + "\n");
				
				System.out.print("Input Desired Decision: ");
				select = scan.nextInt();
				scan.nextLine();
				
				switch(select) {
					case 1:
						LOGGER.info("Displaying All Employee Names");
						service.displayAllEmployees();
						break;
					
					case 2:
						LOGGER.info("Requesting Employee Name String Value to Obtain Yearly Salary");
						
						System.out.print("\n" + "Input Employee Name: ");
						String salaryName = scan.nextLine().trim();
						
						if(!service.exists(salaryName)) {
							throw new NonexistantEmployeeException();
						}
						
						Employee findSalEmp = service.findByName(salaryName);
						
						System.out.println("\n" + findSalEmp.getName() + "'s Yearly Salary: $" + service.calculateYearlySalary(findSalEmp) + "\n");
						break;
					
					case 3:
						LOGGER.info("Requesting Employee No. Int Value to Display Employee Info");
						
						System.out.print("\n" + "Input Employee No: ");
						int empNo = scan.nextInt();
						scan.nextLine();
						
						if(!service.exists(empNo)) {
							throw new NonexistantEmployeeException();
						}
						
						System.out.println("\n" + service.findByEmployeeNo(empNo) + "\n");
						break;
					
					case 4:
						LOGGER.info("Requesting Employee Name String Value to Update Employee Info");
						
						System.out.print("\n" + "Input Employee Name: ");
						String empName = scan.nextLine().trim();
						
						if(!service.exists(empName)) {
							throw new NonexistantEmployeeException();
						}
						
						System.out.print("\n" + "Input New Address: ");
						String newAddress = scan.nextLine();
						System.out.println();
						
						String[] addrSplit = newAddress.split(",");
						
						if(addrSplit.length != 2) {
							throw new InvalidInputAddressException();
						}
						
						Employee updatedEmp = service.findByName(empName);
						
						updatedEmp.setAddress(new Address(addrSplit[0].trim(), addrSplit[1].trim()));
						service.updateEmployee(updatedEmp);
						
						break;
					
					case 5:
						LOGGER.info("Requesting Employee Name to Delete From List");
						
						System.out.print("\n" + "Input Employee Name: ");
						String deleteName = scan.nextLine().trim();
						
						if(!service.exists(deleteName)) {
							throw new NonexistantEmployeeException();
						}
						
						service.deleteEmployee(service.findByName(deleteName));
						break;
					
					case 6:
						System.out.println("\n" + "Exiting program...");
						break;
					
					default:
						System.out.println("\n" + "Did not select a valid option from the list. No action taken." + "\n");
				}
			}
			catch(InputMismatchException i) {
				System.out.println("\n" + "User Attempted an Invalid Input. No Actions Taken." + "\n");
				LOGGER.warning("Invalid Input Attempted. Program caught InputMismatchException and started program over at root menu.");
				scan.nextLine();
			}
			catch(Exception e) {
				System.out.println("\n" + e.getMessage() + ". No Actions Taken." + "\n");
				LOGGER.warning(e.getMessage());
			}
		}
		
		scan.close();
	}
}