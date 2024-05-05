package com.example.orders.datalayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class OrderIdentifier {

    private String orderId;
    public OrderIdentifier() {
        this.orderId = UUID.randomUUID().toString();
    }
    public OrderIdentifier(String orderId) {
        this.orderId = orderId;
    }
}