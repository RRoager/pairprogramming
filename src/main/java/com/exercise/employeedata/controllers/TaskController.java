package com.exercise.employeedata.controllers;

import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.exceptions.TaskNotFoundException;
import com.exercise.employeedata.models.Task;
import com.exercise.employeedata.services.TaskServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employee")
public class TaskController {
    private final TaskServiceImpl taskServiceImpl;

    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @GetMapping("/{employeeId}/tasks")
    public List<Task> getAllTasks(@PathVariable Integer employeeId) throws EmployeeNotFoundException {
        return taskServiceImpl.getAllTasks(employeeId);
    }

    @PostMapping("/{employeeId}/tasks")
    public Task createTask(@PathVariable Integer employeeId, @RequestBody Task task) throws EmployeeNotFoundException {
        return taskServiceImpl.createTask(employeeId, task);
    }

    @GetMapping("/{employeeId}/tasks/{taskId}")
    public Task getTaskById(@PathVariable Integer employeeId, @PathVariable Integer taskId) throws TaskNotFoundException, EmployeeNotFoundException {
        return taskServiceImpl.getTaskById(employeeId, taskId);
    }
}
