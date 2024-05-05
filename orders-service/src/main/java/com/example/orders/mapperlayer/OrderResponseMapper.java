package com.example.orders.mapperlayer;

import com.example.orders.datalayer.Order;
import com.example.orders.presentationlayer.OrderResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {
    @Mapping(expression = "java(order.getOrderIdentifier().getOrderId())", target = "orderId")
    @Mapping(expression = "java(order.getProductIdentifier().getProductId())", target = "productId")
    @Mapping(expression = "java(order.getCustomerIdentifier().getCustomerId())", target = "customerId")
    @Mapping(expression = "java(order.getEmployeeIdentifier().getEmployeeId())", target = "employeeId")
    @Mapping(expression = "java(order.getDeliveryStatus().name())", target = "deliveryStatus")
    @Mapping(expression = "java(order.getShippingPrice())", target = "shippingPrice")
    @Mapping(expression = "java(order.getTotalPrice())", target = "totalPrice")

    OrderResponseModel entityToResponseModel(Order order);

    List<OrderResponseModel> entityListToResponseModelList(List<Order> orders);
}
