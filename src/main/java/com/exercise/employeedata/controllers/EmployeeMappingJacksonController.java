package com.exercise.employeedata.controllers;

import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.models.Employee;
import com.exercise.employeedata.services.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/jacksonfilter/employee")
@Validated
public class EmployeeMappingJacksonController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeMappingJacksonController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    // getEmployeeById - fields with hashset
    @GetMapping("/{id}")
    public MappingJacksonValue getEmployeeById(@PathVariable @Min(1) Integer id) {
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(id);

            Set<String> fields = new HashSet<>();
            fields.add("id");
            fields.add("name");
            fields.add("department");

            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter("employeeFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(employee);

            mapper.setFilters(filterProvider);

            return mapper;
        } catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    // getEmployeeById - fields with @RequestParam
    @GetMapping("/params/{id}")
    public MappingJacksonValue getEmployeeById2(@PathVariable @Min(1) Integer id, @RequestParam Set<String> fields) {
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(id);

            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter("employeeFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(employee);

            mapper.setFilters(filterProvider);

            return mapper;
        } catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

}
