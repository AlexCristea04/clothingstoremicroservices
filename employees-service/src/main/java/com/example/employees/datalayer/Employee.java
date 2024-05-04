package com.example.employees.datalayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="employees")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //private identifier

    @Embedded
    private EmployeeIdentifier employeeIdentifier; //public identifier

    private String lastName;
    private String firstName;
    private String jobTitle;
    private String department;
    private BigDecimal salary;

    public Employee(@NotNull String lastName, @NotNull String firstName, @NotNull String department, @NotNull String jobTitle, @NotNull BigDecimal salary) {
        this.employeeIdentifier = new EmployeeIdentifier();
        this.lastName = lastName;
        this.firstName = firstName;
        this.department = department;
        this.salary = salary;
    }

}