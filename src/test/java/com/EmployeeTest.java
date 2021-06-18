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
			empList.add(new Employee(10003, "Samuel Sells", 15000.00, new Address("Dallas","TX")));
			empList.add(new Employee(10002, "Katherine Sells", 45000.00, new Address("Dallas","TX")));
			empList.add(new Employee(10004, "Rachel Sells", 5000.00, new Address("Dallas","TX")));
			empList.add(new Employee(10001, "Christopher Sells", 45000.00, new Address("Dallas","TX")));
			
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
		PrintStream outStream = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		String expectedOutput = "Employee List:" + "\n";
		
		Collections.sort(empList);
		
		Iterator<Employee> itr = empList.iterator();
		while(itr.hasNext()) {
			expectedOutput += itr.next().getName() + "\n";
		}
		
		service.displayAllEmployees();
		
		/*String functionOutput = outContent.toString();
		System.setOut(outStream);
		
		System.out.println("-------------------------");
		System.out.println(expectedOutput);
		System.out.println("-------------------------");
		System.out.println(functionOutput);
		System.out.println("-------------------------");
		System.out.println("Equals? -> " + expectedOutput.equals(functionOutput));
		System.out.println("-------------------------");*/
		
		assertEquals(expectedOutput.replaceAll("[^a-zA-Z0-9]", ""), outContent.toString().replaceAll("[^a-zA-Z0-9]", ""));
		//assertTrue(outContent.toString().replaceAll("[^a-zA-Z0-9]", " ").equals(expectedOutput.replaceAll("[^a-zA-Z0-9]", " ")));
		System.setOut(outStream);
	}
	
	//calculateYearlySalary test
	@SuppressWarnings("deprecation")
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
	@SuppressWarnings("deprecation")
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
