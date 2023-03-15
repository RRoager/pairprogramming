package com.exercise.employeedata.controllers;

import com.exercise.employeedata.exceptions.EmployeeExistsException;
import com.exercise.employeedata.exceptions.EmployeeNameNotFoundException;
import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.models.Employee;
import com.exercise.employeedata.services.EmployeeServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable @Min(1) Integer id) {
        try {
            return employeeServiceImpl.getEmployeeById(id);
        } catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeServiceImpl.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody Employee employee, UriComponentsBuilder builder) {
        try {
            employeeServiceImpl.createEmployee(employee);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/api/employee/{id}").buildAndExpand(employee.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (EmployeeExistsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable Integer id) {
        employeeServiceImpl.deleteEmployeeById(id);
        return "Employee with ID " + id + " deleted.";
    }

    @PatchMapping("")
    public Employee updateEmployeeById(@RequestBody Employee updatedEmployee) {
        try {
            return employeeServiceImpl.updateEmployeeById(updatedEmployee);
        } catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public Employee getEmployeeByName(@PathVariable String name) throws EmployeeNameNotFoundException {
        Employee employee = employeeServiceImpl.getEmployeeByName(name);

        if (employee == null) {
            throw new EmployeeNameNotFoundException("Employee with name '" + name + "' not found in Employee Repository");
        }

        return employee;
    }
}


