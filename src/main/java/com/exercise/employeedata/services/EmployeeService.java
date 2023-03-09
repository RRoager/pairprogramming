package com.exercise.employeedata.services;

import com.exercise.employeedata.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Integer id);
    Employee updateEmployeeById(Employee updatedEmployee);
    List<Employee> getAllEmployees();
    Employee createEmployee(Employee employee);
    boolean deleteEmployeeById(Integer id);
}
