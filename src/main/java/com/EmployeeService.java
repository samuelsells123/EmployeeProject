package com;

import EmployeeExceptions.NonexistantEmployeeException;

public interface EmployeeService {
	public void displayAllEmployees();
	public double calculateYearlySalary(Employee e1);
	public Employee findByName(String empName) throws NonexistantEmployeeException;
	public Employee findByEmployeeNo(int empNo) throws NonexistantEmployeeException;
	public void updateEmployee(Employee e1) throws NonexistantEmployeeException;
	public void deleteEmployee(Employee e1)throws NonexistantEmployeeException;
}
