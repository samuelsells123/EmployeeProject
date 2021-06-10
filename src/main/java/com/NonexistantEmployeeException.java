package com;

public class NonexistantEmployeeException extends Exception{
    @Override
    public String getMessage() {
        return "Attempted to Search for an Employee Whose Information Does Not Exist in the System";
    }
}