package com.exercise.employeedata.controllers;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.exercise.employeedata.models.Employee;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private ResourceBundleMessageSource messageSource;

    public HelloWorldController(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @GetMapping("/hello-int")
    public String getMessagesInI18nFormat() {
        return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
    }
}
