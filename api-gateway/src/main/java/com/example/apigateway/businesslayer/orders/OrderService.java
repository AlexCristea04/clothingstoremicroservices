package com.example.apigateway.businesslayer.orders;

import com.example.apigateway.presentationlayer.orders.OrderRequestModel;
import com.example.apigateway.presentationlayer.orders.OrderResponseModel;

import java.util.List;

public interface OrderService {
    List<OrderResponseModel> getOrders();
    OrderResponseModel getOrderByOrderId(String orderId);
    OrderResponseModel addOrder(OrderRequestModel orderRequestModel);
    OrderResponseModel updateOrder(OrderRequestModel updatedOrder, String orderId);
    void removeOrder(String orderId);
}
