package EmployeeExceptions;

public class TakenEmpNoException extends Exception{
	@Override
    public String getMessage() {
        return "Attempted to Add an Employee Whose Employee Number is Already In Use.";
    }
}
