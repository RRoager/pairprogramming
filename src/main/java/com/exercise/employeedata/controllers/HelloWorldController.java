package com.exercise.employeedata.controllers;

import com.exercise.employeedata.models.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping(value = "/hello-world", method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World by request mapping";
    }

    @GetMapping("/hello-world-get")
    public Employee helloWorldGet() {
        return Employee.builder()
                .name("Rasmus Roager")
                .address("Copenhagen, Denmark")
                .department("IT")
                .build();
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello " + name;
    }
}
