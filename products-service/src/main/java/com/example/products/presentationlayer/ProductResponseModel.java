package com.example.products.presentationlayer;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseModel extends RepresentationModel<ProductResponseModel> {

    private String productId;
    private String name;
    private String description;
    private String size;
    private BigDecimal price;
    private int quantity;
    private String status;

}