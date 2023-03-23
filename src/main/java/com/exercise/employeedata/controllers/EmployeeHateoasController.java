package com.exercise.employeedata.controllers;

import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.models.Employee;
import com.exercise.employeedata.models.Task;
import com.exercise.employeedata.services.EmployeeServiceImpl;
import jakarta.validation.constraints.Min;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hateoas/employee")
@Validated
public class EmployeeHateoasController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeHateoasController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/{id}")
    public EntityModel<Employee> getEmployeeById(@PathVariable @Min(1) Integer id) throws EmployeeNotFoundException {
        Employee employee =  employeeServiceImpl.getEmployeeById(id);
        Link selfLink = WebMvcLinkBuilder.linkTo(
                this.getClass())
                .slash(employee.getId())
                .withSelfRel();

        return EntityModel.of(employee).add(selfLink);
    }

    @GetMapping("")
    public CollectionModel<Employee> getAllEmployees() {
        List<Employee> employees = employeeServiceImpl.getAllEmployees();
        employees.forEach(e -> e.add(WebMvcLinkBuilder
                .linkTo(this.getClass())
                .slash(e.getId())
                .withSelfRel()));

        employees.forEach(e -> {
            CollectionModel<Task> tasks;
            try {
                tasks = WebMvcLinkBuilder
                        .methodOn(TaskHateoasController.class)
                        .getAllTasks(e.getId());
            } catch (EmployeeNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            e.add(WebMvcLinkBuilder.linkTo(tasks)
                    .withRel("all-tasks"));
        });

        Link selfLinkGetAllEmployees = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();

        return CollectionModel.of(employees, selfLinkGetAllEmployees);
    }
}
