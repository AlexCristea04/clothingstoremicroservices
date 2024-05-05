package com.example.orders.businesslayer;

import com.example.orders.presentationlayer.OrderRequestModel;
import com.example.orders.presentationlayer.OrderResponseModel;

import java.util.List;

public interface OrderService {
    List<OrderResponseModel> getOrders();
    OrderResponseModel getOrderByOrderId(String orderId);
    OrderResponseModel addOrder(OrderRequestModel orderRequestModel);
    OrderResponseModel updateOrder(OrderRequestModel orderRequestModel, String orderId);
    void removeOrder(String orderId);
}
