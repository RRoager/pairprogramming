package com.exercise.employeedata.services;

import com.exercise.employeedata.exceptions.EmployeeExistsException;
import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Integer id) throws EmployeeNotFoundException;
    Employee updateEmployeeById(Employee updatedEmployee) throws EmployeeNotFoundException;
    List<Employee> getAllEmployees();
    Employee createEmployee(Employee employee) throws EmployeeExistsException;
    void deleteEmployeeById(Integer id) throws EmployeeNotFoundException;
    Employee getEmployeeByName(String name);
}
