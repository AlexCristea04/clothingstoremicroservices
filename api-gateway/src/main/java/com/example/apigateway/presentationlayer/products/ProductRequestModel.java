package com.example.apigateway.presentationlayer.products;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductRequestModel {
    private String productId;
    private String name;
    private String description;
    private String size;
    private BigDecimal price;
    private int quantity;
    private String status;

}
