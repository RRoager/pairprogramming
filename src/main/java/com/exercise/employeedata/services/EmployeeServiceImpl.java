package com.exercise.employeedata.services;

import com.exercise.employeedata.models.Employee;
import com.exercise.employeedata.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public boolean deleteEmployeeById(Integer id) {
        if (getEmployeeById(id) != null)
        {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Employee updateEmployeeById(Employee updatedEmployee) {
        if (employeeRepository.findById(updatedEmployee.getId()).isPresent()) {
            return employeeRepository.save(updatedEmployee);
        } else {
            return null;
        }
    }
}
