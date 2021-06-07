package com;

import java.util.ArrayList;
import java.util.ListIterator;

public class EmployeeServiceImpl implements EmployeeService {
	
	static ArrayList<Employee> empList;
	
	public EmployeeServiceImpl() {
		empList = new ArrayList<Employee>();
	}
	
	public void addEmployee(Employee e1) {
		empList.add(e1);
	}
	
	public void displayAllEmployees() {
		ListIterator<Employee> itr = empList.listIterator();
		
		System.out.println("\n" + "Employee List:");
		while(itr.hasNext())
			System.out.println(itr.next());
		System.out.println();
	}
	
	public double calculateYearlySalary(Employee e1) {
		return e1.getSalary();
	}
	
	public Employee findByEmployeeNo(int empNo) {
		ListIterator<Employee> itr = empList.listIterator();
		
		Employee curEmp = itr.next();
		
		while(curEmp.getEmpNo() != empNo)
			curEmp = itr.next();
		
		return curEmp;
	}
	
	public void updateEmployee(Employee e1) {
		ListIterator<Employee> itr = empList.listIterator();
		
		Employee curEmp = itr.next();
		
		while(curEmp.getEmpNo() != e1.getEmpNo())
			curEmp = itr.next();
		
		curEmp.setName(e1.getName());
		curEmp.setSalary(e1.getSalary());
		curEmp.setAddress(e1.getAddress());
	}
	
	public void deleteEmployee(Employee e1) {
		empList.remove(e1);
	}
}
