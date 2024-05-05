package com.example.apigateway.presentationlayer.employees;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class EmployeeRequestModel {

    private String employeeId;
    private String lastName;
    private String firstName;
    private String jobTitle;
    private String department;
    private BigDecimal salary;

}