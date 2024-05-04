package com.example.employees.presentationlayer;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class EmployeeResponseModel extends RepresentationModel<EmployeeResponseModel> {

    private String employeeId;
    private String lastName;
    private String firstName;
    private String jobTitle;
    private String department;
    private BigDecimal salary;

}