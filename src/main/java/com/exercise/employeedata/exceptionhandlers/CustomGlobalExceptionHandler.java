package com.exercise.employeedata.exceptionhandlers;

import com.exercise.employeedata.Responses.CustomErrorDetailsResponse;
import com.exercise.employeedata.exceptions.EmployeeNameNotFoundException;
import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.exceptions.TaskNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomErrorDetailsResponse customErrorDetailsResponse = new CustomErrorDetailsResponse(
                new Date(),
                "From MethodArgumentNotValid Exception in Global Exception Handler",
                ex.getMessage());
        return new ResponseEntity<>(customErrorDetailsResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomErrorDetailsResponse customErrorDetailsResponse = new CustomErrorDetailsResponse(
                new Date(),
                "From HttpRequestMethodNotSupported Exception in Global Exception Handler",
                ex.getMessage());
        return new ResponseEntity<>(customErrorDetailsResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(EmployeeNameNotFoundException.class)
    public final ResponseEntity<Object> handleEmployeeNameNotFound(
            EmployeeNameNotFoundException ex, WebRequest request) {
        CustomErrorDetailsResponse customErrorDetailsResponse = new CustomErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(customErrorDetailsResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<Object> handleEmployeeNotFound(
            EmployeeNotFoundException ex, WebRequest request) {
        CustomErrorDetailsResponse customErrorDetailsResponse = new CustomErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(customErrorDetailsResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public final ResponseEntity<Object> handleTaskNotFound(
            TaskNotFoundException ex, WebRequest request) {
        CustomErrorDetailsResponse customErrorDetailsResponse = new CustomErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(customErrorDetailsResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        CustomErrorDetailsResponse customErrorDetailsResponse = new CustomErrorDetailsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(customErrorDetailsResponse, HttpStatus.BAD_REQUEST);
    }
}
