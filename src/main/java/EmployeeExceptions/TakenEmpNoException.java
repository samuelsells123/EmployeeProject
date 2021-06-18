package EmployeeExceptions;

public class TakenEmpNoException extends Exception{
	@Override
    public String getMessage() {
        return "Attempted to Add an Employee Whose Employee Number is Already In Use." + "\n"
        		+ "Attempting to Add an Employee With an Already-taken Employee Number Will" + "\n"
        		+ "Cause the Operation to Fail, and No Change to the System Will Result.";
    }
}
