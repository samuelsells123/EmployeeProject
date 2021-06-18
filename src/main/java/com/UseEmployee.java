package com;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.InputMismatchException;
import EmployeeExceptions.TakenEmpNoException;

public class UseEmployee {	
	public static void main(String[] args){
		//set up logger to output log to UseEmployee.log
		Logger LOGGER = Logger.getLogger(UseEmployee.class.getName());  
		
		try {
			FileHandler fh = new FileHandler("UseEmployee.log", true);
			LOGGER.addHandler(fh);
			LOGGER.setUseParentHandlers(false);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		LOGGER.info("Logger Name: " + LOGGER.getName());
		
		//starts employee service and adds several employees
		EmployeeServiceImpl service = new EmployeeServiceImpl();
		
		try {
			service.addEmployee("Samuel Sells", 600.00, "Dallas, TX");
			service.addEmployee("Rachel Sells", 300.00, "Dallas, TX");
			service.addEmployee("Christopher Sells", 2000.00, "Dallas, TX");
			service.addEmployee("Katherine Sells", 2000.00, "Dallas, TX");
			service.addEmployee("Kelsey Galvin", 1500.00, "Los Angeles, CA");
			service.addEmployee("Christina Spear", 2500.00, "Indianapolis, IN");
			service.addEmployee(9001, "Jimmy Notarealperson", 9999.99, "Nowhere Island, Nowhere");
			service.addEmployee(9001, "Johnny Fakename", 0.0, "Nowhere Island, Nowhere");
		}
		catch(TakenEmpNoException t) {
			LOGGER.warning(t.getMessage());
			System.out.println(t.getMessage() + "\n");
		}
		
		//takes in user input and calls desired function
		Scanner scan = new Scanner(System.in);
		int select = 0;
		
		while(select != 7) {
			try {
				LOGGER.info("Taking in Int Value for Switch Decision Making");
				
				System.out.println("1. Display All Employee Info" + "\n"  
						+ "2. List All Employees" + "\n" 
						+ "3. Display Yearly Salary" + "\n"
						+ "4. Display Specific Employee Details" + "\n"
						+ "5. Update Employee Address" + "\n"
						+ "6. Delete an Employee" + "\n"
						+ "7. Exit" + "\n");
				
				System.out.print("Input Desired Decision: ");
				select = scan.nextInt();
				scan.nextLine();
				
				switch(select) {
				 	case 1:
				 		LOGGER.info("Displaying All Employee Info");
				 		System.out.println();
				 		service.displayAllInfo();
				 		System.out.println();
				 		break;
					case 2:
						LOGGER.info("Displaying Sorted Employee Names");
						System.out.println();
						service.displayAllEmployees();
						System.out.println();
						break;
					
					case 3:
						LOGGER.info("Requesting Employee Name String Value to Obtain Yearly Salary");
						
						System.out.print("\n" + "Input Employee Name: ");
						String salaryName = scan.nextLine().trim();
						
						Employee findSalEmp = service.findByName(salaryName);
						
						System.out.println("\n" + findSalEmp.getName() + "'s Yearly Salary: $" + service.calculateYearlySalary(findSalEmp) + "\n");
						break;
					
					case 4:
						LOGGER.info("Requesting Employee No. Int Value to Display Employee Info");
						
						System.out.print("\n" + "Input Employee No: ");
						int empNo = scan.nextInt();
						scan.nextLine();
						
						System.out.println("\n" + service.findByEmployeeNo(empNo) + "\n");
						break;
					
					case 5:
						LOGGER.info("Requesting Employee Name String Value to Update Employee Info");
						
						System.out.print("\n" + "Input Employee Name: ");
						String empName = scan.nextLine().trim();
						Employee updateEmp = service.findByName(empName);
						
						System.out.print("\n" + "Input New Address: ");
						String newAddress = scan.nextLine();
						System.out.println();
						updateEmp.setAddress(newAddress);
						
						service.updateEmployee(updateEmp);
						
						break;
					
					case 6:
						LOGGER.info("Requesting Employee Name to Delete From List");
						
						System.out.print("\n" + "Input Employee Name: ");
						String deleteName = scan.nextLine().trim();
						
						service.deleteEmployee(service.findByName(deleteName));
						break;
					
					case 7:
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