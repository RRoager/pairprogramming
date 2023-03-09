package com.exercise.employeedata.controllers;

import com.exercise.employeedata.models.Employee;
import com.exercise.employeedata.repositories.EmployeeRepository;
import com.exercise.employeedata.services.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("api/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeServiceImpl.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employeeServiceImpl.getEmployeeById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>("No employee with ID: " + id, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("api/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeServiceImpl.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping("api/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServiceImpl.createEmployee(employee), HttpStatus.CREATED);
    }

    @DeleteMapping("api/employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        if (employeeServiceImpl.deleteEmployeeById(id)) {
            return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No employee with ID: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("api/employee")
    public ResponseEntity<?> updateEmployeeById(@RequestBody Employee updatedEmployee) {
        Employee employee = employeeServiceImpl.updateEmployeeById(updatedEmployee);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>("No employee with ID: " + updatedEmployee.getId(), HttpStatus.BAD_REQUEST);
    }
}
