package com.exercise.employeedata.services;

import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.exceptions.TaskNotFoundException;
import com.exercise.employeedata.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks(Integer userId) throws EmployeeNotFoundException;
    Task createTask(Integer userId, Task task) throws EmployeeNotFoundException;
    Task getTaskById(Integer employeeId, Integer taskId) throws TaskNotFoundException, EmployeeNotFoundException;
}
