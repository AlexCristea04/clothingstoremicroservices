package com.example.customers.datalayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
public class CustomerIdentifier {

    private String customerId;
    public CustomerIdentifier() {
        this.customerId = UUID.randomUUID().toString();
    }
    public CustomerIdentifier(String customerId) {
        this.customerId = customerId;
    }
}