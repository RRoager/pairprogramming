package com.exercise.employeedata.exceptionhandlers;

import com.exercise.employeedata.Responses.CustomErrorDetailsResponse;
import com.exercise.employeedata.exceptions.EmployeeNameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

//@RestControllerAdvice
public class CustomGlobalRestControllerAdviceExceptionHandler {

    @ExceptionHandler(EmployeeNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetailsResponse employeeNameNotFound(EmployeeNameNotFoundException ex) {
        return new CustomErrorDetailsResponse(
                new Date(),
                "From @RestControllerAdvice NOT FOUND",
                ex.getMessage());
    }
}
