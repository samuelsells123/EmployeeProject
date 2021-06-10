package com;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.InputMismatchException;

public class UseEmployee {	
	private static final Logger LOGGER = Logger.getLogger(UseEmployee.class.getName());
	//FileHandler fh;
	
	public static void main(String[] args) {
		LOGGER.info("Logger Name: " + LOGGER.getName());
		
		EmployeeServiceImpl service = new EmployeeServiceImpl();
		
		Employee e1 = new Employee(10001, "Samuel Sells", 2000.00, new Address("Dallas", "TX"));
		Employee e2 = new Employee(10002, "Kelsey Galvin", 4000.00, new Address("Los Angeles", "CA"));
		Employee e3 = new Employee(10003, "Christina Spear", 6000.00, new Address("Indianapolis", "IN"));
		
		service.addEmployee(e1);
		service.addEmployee(e2);
		service.addEmployee(e3);
		
		Scanner scan = new Scanner(System.in);
		int select = 0;
		
		//printProblems();
		
		while(select != 6) {
			System.out.println("1. List All Employees" + "\n" 
								+ "2. Display Yearly Salary" + "\n"
								+ "3. Display Specific Employee Details" + "\n"
								+ "4. Modify the Employee Details" + "\n"
								+ "5. Delete an Employee" + "\n"
								+ "6. Exit" + "\n");
			
			System.out.print("Input Desired Decision: ");
			
			try {
				select = scan.nextInt();
				
				switch(select) {
					case 1:
						service.displayAllEmployees();
						break;
						
					case 2:
						System.out.println("\n" + "Printing Salaries...");
						System.out.println(e1.getName() + ": " + service.calculateYearlySalary(e1));
						System.out.println(e2.getName() + ": " + service.calculateYearlySalary(e2));
						System.out.println(e3.getName() + ": " + service.calculateYearlySalary(e3) + "\n");
						break;
					
					case 3:
						Employee findEmp = service.findByEmployeeNo(10002);
						System.out.println("\n" + findEmp + "\n");
						break;
					
					case 4:
						System.out.println("\n" + "Updating Samuel Sells's address to Austin, TX" + "\n");
						e1.setAddress(new Address("Austin", "TX"));
						service.updateEmployee(e1);
						break;
					
					case 5:
						System.out.println("\n" + "Deleting Kelsey Galvin from employees list" + "\n");
						service.deleteEmployee(e2);
						break;
					
					case 6:
						System.out.println("\n" + "Exiting program...");
						break;
					
					default:
						throw new InputMismatchException();
						//System.out.println("\n" + "Did not select a valid option from the list. No action taken." + "\n");
				}
			}
			catch(InputMismatchException e) {
				System.out.println("\n" + "Did not select a valid option from the list. No action taken." + "\n");
				LOGGER.warning("Invalid Input Attempted. Program caught InputMismatchException and asked user for input again.");
				scan.nextLine();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		scan.close();
	}
}