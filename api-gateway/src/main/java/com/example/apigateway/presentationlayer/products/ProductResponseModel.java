package com.example.apigateway.presentationlayer.products;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseModel extends RepresentationModel<ProductResponseModel> {

    private String productId;
    private String name;
    private String description;
    private String size;
    private BigDecimal price;
    private int quantity;
    private String status;

}
