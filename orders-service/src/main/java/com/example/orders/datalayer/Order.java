package com.example.orders.datalayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private OrderIdentifier orderIdentifier;

    @Embedded
    private ProductIdentifier productIdentifier;

    @Embedded
    private CustomerIdentifier customerIdentifier;

    @Embedded
    private EmployeeIdentifier employeeIdentifier;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private BigDecimal shippingPrice;
    private BigDecimal totalPrice;

    public Order(@NotNull OrderIdentifier orderIdentifier, @NotNull ProductIdentifier productIdentifier,
                 @NotNull CustomerIdentifier customerIdentifier, @NotNull EmployeeIdentifier employeeIdentifier,
                 @NotNull DeliveryStatus deliveryStatus, @NotNull BigDecimal shippingPrice, @NotNull BigDecimal totalPrice){
        this.orderIdentifier = orderIdentifier;
        this.productIdentifier = productIdentifier;
        this.customerIdentifier = customerIdentifier;
        this.employeeIdentifier = employeeIdentifier;
        this.deliveryStatus = deliveryStatus;
        this.shippingPrice = shippingPrice;
        this.totalPrice = totalPrice;
    }

}
