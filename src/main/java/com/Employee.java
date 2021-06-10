package com;

import java.io.Serializable;

public class Employee {
	int empNo;
	String empName;
	double salary;
	Address address;
	
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
	
	public String greetEmployee()
	{
		return "Hello,  " + this.empName;
	}

	@Override
	public String toString() {
		return "[emp. No: " + empNo + ", Name: " + empName + ", salary: " + salary + ", address: " + address + "]";
	}
}
