package com.example.products.datalayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private ProductIdentifier productIdentifier;

    private String name;
    private String description;
    private String size;
    private BigDecimal price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public Product(@NotNull String name, @NotNull  String description, @NotNull  String size, @NotNull BigDecimal price,
                   @NotNull int quantity, @NotNull ProductStatus status) {
        this.productIdentifier = new ProductIdentifier();
        this.name = name;
        this.description = description;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }
}
