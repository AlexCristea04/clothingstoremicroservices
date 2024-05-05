package com.example.apigateway.presentationlayer.orders;

import com.example.apigateway.presentationlayer.products.ProductResponseModel;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseModel extends RepresentationModel<ProductResponseModel> {
    String orderId;
    String productId;
    String customerId;
    String employeeId;
    String deliveryStatus;
    BigDecimal shippingPrice;
    BigDecimal totalPrice;
}
