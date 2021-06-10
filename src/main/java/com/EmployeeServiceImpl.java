package com;

import java.util.ArrayList;
import java.util.ListIterator;

public class EmployeeServiceImpl implements EmployeeService {
	
	static ArrayList<Employee> empList;
	
	//Initializes empList and fills it with data of several employees 
	public EmployeeServiceImpl() {
		empList = new ArrayList<>();
	}
	
	public void addEmployee(Employee e1) {
		empList.add(e1);
	}
	
	//Second addEmployee function for ease of adding new employee data
	public void addEmployee(int empNo, String empName, double salary, String addrString) {
		String[] addrSplit = addrString.replaceAll(" ", "").split(",");
		
		//Removes whitespace from addressSplit entries and creates a new address with them
		Address address = new Address(addrSplit[0].replaceAll(" ", ""), addrSplit[1].replaceAll(" ", ""));
		
		//Creates a new employee object and adds it to empList
		this.addEmployee(new Employee(empNo, empName, salary, address));
	}
	
	public void displayAllEmployees() {
		System.out.println("\n" + "Employee List:");
		empList.forEach(Employee -> System.out.println(Employee.getName()));
		System.out.println();
		
		/*ListIterator<Employee> itr = empList.listIterator();
		
		while(itr.hasNext())
			System.out.println(itr.next());*/
	}
	
	public double calculateYearlySalary(Employee e1) {
		return e1.getSalary();
	}
	
	public Boolean exists(String empName) {
		ListIterator<Employee> itr = empList.listIterator();
		Employee curEmp;
		Boolean empExists = false;
		
		while(itr.hasNext()) {
			curEmp = itr.next();
			
			if(empName.equals(curEmp.getName()))
				return true;
		}
		
		return empExists;
	}
	
	public Boolean exists(int empNo) {
		ListIterator<Employee> itr = empList.listIterator();
		Employee curEmp;
		
		while(itr.hasNext()) {
			curEmp = itr.next();
			
			if(empNo == curEmp.getEmpNo())
				return true;
		}
		
		return false;
	}
	
	public Employee findByName(String empName) {
		ListIterator<Employee> itr = empList.listIterator();
		
		Employee curEmp = itr.next();
		
		while(!empName.equals(curEmp.getName()))
			curEmp = itr.next();
		
		return curEmp;
	}
	
	public Employee findByEmployeeNo(int empNo) {
		ListIterator<Employee> itr = empList.listIterator();
		
		Employee curEmp = itr.next();
		
		while(curEmp.getEmpNo() != empNo)
			curEmp = itr.next();
		
		return curEmp;
	}
	
	public void updateEmployee(Employee e1) {
		Employee curEmp = this.findByEmployeeNo(e1.getEmpNo());
		
		curEmp.setName(e1.getName());
		curEmp.setSalary(e1.getSalary());
		curEmp.setAddress(e1.getAddress());
	}
	
	public void deleteEmployee(Employee e1) {
		empList.remove(e1);
	}
}
