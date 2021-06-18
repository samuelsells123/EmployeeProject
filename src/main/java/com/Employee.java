package com;

import EmployeeExceptions.InvalidInputAddressException;

public class Employee implements Comparable<Employee> {
	private int empNo;
	private String empName;
	private double salary;
	private Address address;
	
	public Employee(int empNo, String empName, double salary, Address address) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.salary = salary;
		this.address = address;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return empName;
	}

	public void setName(String empName) {
		this.empName = empName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setAddress(String address) throws InvalidInputAddressException {
		String[] addrSplit = address.split(",");
		
		if(addrSplit.length != 2) {
			throw new InvalidInputAddressException();
		}
		
		this.address.setCity(addrSplit[0].trim());
		this.address.setState(addrSplit[1].trim());
	}
	
	public String greetEmployee()
	{
		return "Hello,  " + this.empName;
	}

	@Override
	public String toString() {
		return "[emp. No: " + empNo + ", Name: " + empName + ", salary: " + salary + ", address: " + address + "]";
	}

	@Override
	public int compareTo(Employee e) {
		if(this.getEmpNo() > e.getEmpNo())
			return 1;
		else if(this.getEmpNo() < e.getEmpNo())
			return -1;
		else
			return 0;
	}
}
