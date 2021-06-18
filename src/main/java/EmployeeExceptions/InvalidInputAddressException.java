package EmployeeExceptions;

public class InvalidInputAddressException extends Exception{
    @Override
    public String getMessage() {
        return "Attempted to Input an Invalid Address. Input Addresses Should be Formated as <CityName>,<StateAbbreviation> (e.g. \"Dallas, TX\").";
    }
}