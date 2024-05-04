package com.example.products.presentationlayer;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ProductRequestModel {

    private String name;
    private String description;
    private String size;
    private BigDecimal price;
    private int quantity;
    private String status;

}
