package com.exercise.employeedata.controllers;

import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.exceptions.TaskNotFoundException;
import com.exercise.employeedata.models.Task;
import com.exercise.employeedata.services.TaskServiceImpl;
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
public class TaskHateoasController {
    private final TaskServiceImpl taskServiceImpl;

    public TaskHateoasController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @GetMapping("/{employeeId}/tasks")
    public CollectionModel<Task> getAllTasks(@PathVariable Integer employeeId) throws EmployeeNotFoundException {
        List<Task> tasks = taskServiceImpl.getAllTasks(employeeId);
        tasks.forEach(t -> t.add(WebMvcLinkBuilder.linkTo(
                        this.getClass())
                .slash(t.getEmployee().getId())
                .slash("tasks")
                .slash(t.getId())
                .withSelfRel()));

        Link selfLinkGetAllTasks = WebMvcLinkBuilder.linkTo(this.getClass())
                .slash(employeeId)
                .slash("tasks")
                .withSelfRel();

        return CollectionModel.of(tasks, selfLinkGetAllTasks);
    }

    @GetMapping("/{employeeId}/tasks/{taskId}")
    public EntityModel<Task> getTaskById(@PathVariable Integer employeeId, @PathVariable Integer taskId) throws TaskNotFoundException, EmployeeNotFoundException {
        Task task =  taskServiceImpl.getTaskById(employeeId, taskId);
        Link selfLink = WebMvcLinkBuilder.linkTo(
                        this.getClass())
                .slash(task.getId())
                .withSelfRel();

        return EntityModel.of(task).add(selfLink);
    }
}
