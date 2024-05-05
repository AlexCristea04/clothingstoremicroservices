package com.example.orders.presentationlayer;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestModel {
    String orderId;
    String productId;
    String customerId;
    String employeeId;
    String deliveryStatus;
    BigDecimal shippingPrice;
    BigDecimal totalPrice;
}
