package com.example.orders.domainclientlayer.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private String productId;
    private String name;
    private String description;
    private String size;
    private BigDecimal price;
    private int quantity;
    private String status;

}
