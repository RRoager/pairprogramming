package com.exercise.employeedata.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name is mandatory. Please provide a name.")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name;
    @NotEmpty(message = "Address is mandatory. Please provide an address.")
    private String address;
    @NotEmpty(message = "Department is mandatory. Please provide a department.")
    private String department;
    @OneToMany(mappedBy = "employee")
    private List<Task> tasks;
}
