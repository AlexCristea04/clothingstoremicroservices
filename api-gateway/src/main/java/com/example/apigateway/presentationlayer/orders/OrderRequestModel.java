package com.example.apigateway.presentationlayer.orders;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderRequestModel {
    String orderId;
    String productId;
    String customerId;
    String employeeId;
    String deliveryStatus;
    BigDecimal shippingPrice;
    BigDecimal totalPrice;
}
