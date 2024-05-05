package com.example.apigateway.presentationlayer.employees;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseModel extends RepresentationModel<EmployeeResponseModel> {

    private String employeeId;
    private String lastName;
    private String firstName;
    private String jobTitle;
    private String department;
    private BigDecimal salary;

}