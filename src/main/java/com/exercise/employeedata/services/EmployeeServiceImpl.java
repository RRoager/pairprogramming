package com.exercise.employeedata.services;

import com.exercise.employeedata.exceptions.EmployeeExistsException;
import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.models.Employee;
import com.exercise.employeedata.repositories.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found in Employee Repository");
        }

        return employee.get();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) throws EmployeeExistsException {
        Optional<Employee> existingEmployee = employeeRepository.findByName(employee.getName());

        if (existingEmployee.isPresent()) {
            throw new EmployeeExistsException("Employee already exist in Repository");
        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found in Employee Repository");
        }

        employeeRepository.deleteById(id);
    }

    public Employee updateEmployeeById(Employee updatedEmployee) throws EmployeeNotFoundException {
        if (employeeRepository.findById(updatedEmployee.getId()).isPresent()) {
            return employeeRepository.save(updatedEmployee);
        } else {
            throw new EmployeeNotFoundException("Employee not found in Employee Repository");
        }
    }

    public Employee getEmployeeByName(String name) {
        Optional<Employee> employee = employeeRepository.findByName(name);

        return employee.orElse(null);
    }
}
