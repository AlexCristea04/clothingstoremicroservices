package com.example.orders.datalayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
public class EmployeeIdentifier {

    private String employeeId;
    public EmployeeIdentifier() {
        this.employeeId = UUID.randomUUID().toString();
    }
    public EmployeeIdentifier(String employeeId) {
        this.employeeId = employeeId;
    }
}