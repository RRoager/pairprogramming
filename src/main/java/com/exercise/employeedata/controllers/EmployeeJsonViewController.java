package com.exercise.employeedata.controllers;

import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.models.Employee;
import com.exercise.employeedata.models.Views;
import com.exercise.employeedata.services.EmployeeServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/jsonview/employee")
@Validated
public class EmployeeJsonViewController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeJsonViewController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/external/{id}")
    @JsonView(Views.External.class)
    public Employee getEmployeeByIdExternal(@PathVariable @Min(1) Integer id) {
        try {
            return employeeServiceImpl.getEmployeeById(id);
        } catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/internal/{id}")
    @JsonView(Views.Internal.class)
    public Employee getEmployeeByIdInternal(@PathVariable @Min(1) Integer id) {
        try {
            return employeeServiceImpl.getEmployeeById(id);
        } catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
