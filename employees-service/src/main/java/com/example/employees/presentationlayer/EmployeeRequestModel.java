package com.example.employees.presentationlayer;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class EmployeeRequestModel {

    private String lastName;
    private String firstName;
    private String jobTitle;
    private String department;
    private BigDecimal salary;

}