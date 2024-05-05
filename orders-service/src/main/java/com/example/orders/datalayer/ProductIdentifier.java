package com.example.orders.datalayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
public class ProductIdentifier {

    private String productId;
    public ProductIdentifier() {
        this.productId = UUID.randomUUID().toString();
    }
    public ProductIdentifier(String productId) {
        this.productId = productId;
    }

}
