package com.exercise.employeedata.exceptions;

public class EmployeeNameNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public EmployeeNameNotFoundException(String message) {
        super(message);
    }
}
