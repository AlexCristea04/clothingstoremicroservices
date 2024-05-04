package com.example.employees.utils.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(){

    }
    public EmployeeNotFoundException(String message){super(message);}
    public EmployeeNotFoundException(Throwable cause){super(cause);}
    public EmployeeNotFoundException(String message, Throwable cause){super(message,cause);}
}
