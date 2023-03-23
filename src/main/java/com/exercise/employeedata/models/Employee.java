package com.exercise.employeedata.models;

import com.fasterxml.jackson.annotation.JsonView;
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
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
// @JsonFilter("employeeFilter") -- Used for MappingJacksonValue filtering
public class Employee extends RepresentationModel<Employee> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.External.class)
    private Integer id;
    @NotEmpty(message = "Name is mandatory. Please provide a name.")
    @Size(min = 2, message = "Name must be at least 2 characters")
    @JsonView(Views.External.class)
    private String name;
    @NotEmpty(message = "Address is mandatory. Please provide an address.")
    @JsonView(Views.External.class)
    private String address;
    @NotEmpty(message = "Department is mandatory. Please provide a department.")
    @JsonView(Views.Internal.class)
    private String department;
    @OneToMany(mappedBy = "employee")
    @JsonView(Views.Internal.class)
    private List<Task> tasks;
}
