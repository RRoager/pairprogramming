package com.exercise.employeedata.services;

import com.exercise.employeedata.exceptions.EmployeeNotFoundException;
import com.exercise.employeedata.exceptions.TaskNotFoundException;
import com.exercise.employeedata.models.Employee;
import com.exercise.employeedata.models.Task;
import com.exercise.employeedata.repositories.EmployeeRepository;
import com.exercise.employeedata.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(EmployeeRepository employeeRepository,
                           TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(Integer employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found in Employee Repository");
        }

        return employee.get().getTasks();
    }

    public Task createTask(Integer employeeId, Task task) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found in Employee Repository");
        }

        task.setEmployee(employee.get());
        return taskRepository.save(task);
    }

    public Task getTaskById(Integer employeeId, Integer taskId) throws TaskNotFoundException, EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isPresent()) {
            Optional<Task> task = taskRepository.findById(taskId);

            if (task.isPresent() && task.get().getEmployee().equals(employee.get())) {
                return task.get();
            }
            throw new TaskNotFoundException("Task not found in Task Repository for this Employee");
        }
        throw new EmployeeNotFoundException("Employee not found in Employee Repository");
    }
}
