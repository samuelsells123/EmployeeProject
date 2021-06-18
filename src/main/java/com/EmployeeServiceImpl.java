package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import EmployeeExceptions.NonexistantEmployeeException;
import EmployeeExceptions.TakenEmpNoException;

public class EmployeeServiceImpl implements EmployeeService {
	
	private static ArrayList<Employee> empList;
	private static ArrayList<Integer> empNoList;
	private int nextEmpNo;
	
	//Initializes empList and fills it with data of several employees 
	public EmployeeServiceImpl() {
		empList = new ArrayList<Employee>();
		empNoList = new ArrayList<Integer>();
		nextEmpNo = 10001;
	}
	
	//Adds Employee
	public void addEmployee(Employee e1) throws TakenEmpNoException {
		if(empNoList.contains(e1.getEmpNo()))
			throw new TakenEmpNoException();
		
		empList.add(e1);
		empNoList.add(e1.getEmpNo());
	}
	
	//Further addEmployee functions for ease of adding new employee data
	public void addEmployee(int empNo, String empName, double salary, String addrString) throws TakenEmpNoException {
		String[] addrSplit = addrString.split(",");
		
		//Removes whitespace from addressSplit entries and creates a new address with them
		Address address = new Address(addrSplit[0].trim(), addrSplit[1].trim());
		
		//Creates a new employee object and adds it to empList
		this.addEmployee(new Employee(empNo, empName, salary, address));
	}
	
	//This function should be used and will handle empNo logic itself
	public void addEmployee(String empName, double salary, String addrString) throws TakenEmpNoException {
		this.addEmployee(nextEmpNo++, empName, salary, addrString);
	}
	
	public void displayAllInfo() {
		Collections.sort(empList);
		
		ListIterator<Employee> itr = empList.listIterator();
		Employee curEmp;
		
		while(itr.hasNext()) {
			curEmp = itr.next();
			
			System.out.println("----------------------------------------" + "\n"
								+ "Employee # \t: " + curEmp.getEmpNo() + "\n"
								+ "Name \t\t: " + curEmp.getName() + "\n"
								+ "Monthly Salary \t: " + curEmp.getSalary() + "\n"
								+ "Address \t: " + curEmp.getAddress());
		}
		
		System.out.println("----------------------------------------");
	}
	
	public void displayAllEmployees() {
		System.out.println("Employee List:");
		empList.stream().sorted().forEach(Employee -> System.out.println(Employee.getName()));
		
		/*ListIterator<Employee> itr = empList.listIterator();
		
		while(itr.hasNext())
			System.out.println(itr.next());*/
	}
	
	public double calculateYearlySalary(Employee e1) {
		return 12 * e1.getSalary();
	}
	
	/*public Boolean exists(String empName) {
		ListIterator<Employee> itr = empList.listIterator();
		Employee curEmp;
		Boolean empExists = false;
		
		while(itr.hasNext()) {
			curEmp = itr.next();
			
			if(empName.equals(curEmp.getName()))
				return true;
		}
		
		return empExists;
	}*/
	
	/*public Boolean exists(int empNo) {
		ListIterator<Employee> itr = empList.listIterator();
		Employee curEmp;
		
		while(itr.hasNext()) {
			curEmp = itr.next();
			
			if(empNo == curEmp.getEmpNo())
				return true;
		}
		
		return false;
	}*/
	
	public Employee findByName(String empName) throws NonexistantEmployeeException {
		ListIterator<Employee> itr = empList.listIterator();
		
		Employee curEmp;
		
		while(itr.hasNext()) {
			curEmp = itr.next();
			
			if(empName.equals(curEmp.getName())) {
				return curEmp;
			}
		}
		
		throw new NonexistantEmployeeException();
	}
	
	public Employee findByEmployeeNo(int empNo) throws NonexistantEmployeeException {
		ListIterator<Employee> itr = empList.listIterator();
		
		Employee curEmp;
		
		while(itr.hasNext()) {
			curEmp = itr.next();
			
			if(empNo == curEmp.getEmpNo()) {
				return curEmp;
			}
		}
		
		throw new NonexistantEmployeeException();
	}
	
	public void updateEmployee(Employee e1) throws NonexistantEmployeeException {
		Employee curEmp = this.findByEmployeeNo(e1.getEmpNo());
		
		curEmp.setName(e1.getName());
		curEmp.setSalary(e1.getSalary());
		curEmp.setAddress(e1.getAddress());
	}
	
	public void deleteEmployee(Employee e1) {
		empList.remove(e1);
	}
}
