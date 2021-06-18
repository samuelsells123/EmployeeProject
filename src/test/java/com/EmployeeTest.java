package com;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EmployeeExceptions.NonexistantEmployeeException;

class EmployeeTest {
	private EmployeeServiceImpl service;
	private ArrayList<Employee> empList;
	
	@BeforeEach
	void initEach() {
		service = new EmployeeServiceImpl();
		empList = new ArrayList<Employee>();
		
		try {
			empList.add(new Employee(10003, "Samuel Sells", 600.00, new Address("Dallas","TX")));
			empList.add(new Employee(10002, "Katherine Sells", 2000.00, new Address("Dallas","TX")));
			empList.add(new Employee(10004, "Rachel Sells", 300.00, new Address("Dallas","TX")));
			empList.add(new Employee(10001, "Christopher Sells", 2000.00, new Address("Dallas","TX")));
			
			for(int i = 0; i < empList.size(); i++) {
				service.addEmployee(empList.get(i));
			}
		}
		catch(Exception e) {
			System.out.println("\n" + e.getMessage() + ". No Actions Taken." + "\n");
		}
	}
	
	//displayAllEmployees test
	@Test
	void testDisplayAllEmployees() {
		//sorts collection before printing out
		Collections.sort(empList);
		
		//saves starting system output
		PrintStream outStream = System.out;
		
		//creates ByteArrayOuputStream and sets it to receive console output
		ByteArrayOutputStream printContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(printContent));
		
		/*String expectedOutput = "Employee List:" + "\n";
		
		Iterator<Employee> itr = empList.iterator();
		while(itr.hasNext()) {
			expectedOutput += itr.next().getName() + "\n";
		}*/
		
		//prints expected output and saves it to String expectedOutput
		System.out.println("Employee List:");
		
		Iterator<Employee> itr = empList.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next().getName());
		}
		
		String expectedOutput = printContent.toString();
		
		
		//creates another ByteArrayOutputStream and switches it to receive console output.
		//this is necessary because this causes outContent to start as a blank slate
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		service.displayAllEmployees();
		
		/*String functionOutput = outContent.toString();
		System.setOut(outStream);
		
		System.out.println("-------------------------");
		System.out.println(expectedOutput);
		//for(int i = 0; i < expectedOutput.length(); i++)
			//System.out.println(expectedOutput.charAt(i));
		System.out.println("-------------------------");
		System.out.println(functionOutput);
		//for(int i = 0; i < functionOutput.length(); i++)
			//System.out.println(functionOutput.charAt(i));
		System.out.println("-------------------------");
		System.out.println("Equals? -> " + expectedOutput.equals(functionOutput));
		System.out.println("-------------------------");*/
		
		//assertEquals(expectedOutput, outContent.toString());
		
		assertTrue(outContent.toString().equals(expectedOutput));
		System.setOut(outStream);
	}
	
	//calculateYearlySalary test
	@Test
	void testCalculateYearlySalary() {
		double returnSalary = service.calculateYearlySalary(empList.get(0));
		
		assertEquals(12*empList.get(0).getSalary(), returnSalary);
	}
	
	//findByEmployeeNo test
	@Test
	void testFindByEmployeeNo() {
		try {
			assertEquals(empList.get(0), service.findByEmployeeNo(empList.get(0).getEmpNo()));
		}
		catch(Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
	}
	
	//updateEmployee test
	@Test
	void testUpdateEmployee() {
		try {
			Employee updateEmp = new Employee(10002, "John Rambo", 55000.00, new Address("Los Angeles", "CA"));
			
			service.updateEmployee(updateEmp);
			
			Employee testEmp = service.findByEmployeeNo(10002);
			
			assertEquals(updateEmp.getEmpNo(), testEmp.getEmpNo());
			assertEquals(updateEmp.getName(), testEmp.getName());
			assertEquals(updateEmp.getSalary(), testEmp.getSalary());
			assertEquals(updateEmp.getAddress(), testEmp.getAddress());
		}
		catch(Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
	}
	
	//deleteEmployee test
	@Test
	void testDeleteEmployee() {
		boolean nonexistantExceptionThrown = false;
		
		try {
			Employee testEmp = new  Employee(999, "Johnny Fakename", 10.0, new Address("Nowhere Island","Nowhere"));
			
			service.addEmployee(testEmp);
			
			assertEquals(testEmp, service.findByEmployeeNo(testEmp.getEmpNo()));
			
			service.deleteEmployee(testEmp);
			
			service.findByEmployeeNo(testEmp.getEmpNo());
		}
		catch(NonexistantEmployeeException n) {
			nonexistantExceptionThrown = true;
		}
		catch(Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
		
		assertTrue(nonexistantExceptionThrown);
	}
}
