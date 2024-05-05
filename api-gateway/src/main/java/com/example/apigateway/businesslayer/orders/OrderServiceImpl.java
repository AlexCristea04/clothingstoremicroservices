package com.example.apigateway.businesslayer.orders;

import com.example.apigateway.domainclientlayer.orders.OrderServiceClient;
import com.example.apigateway.mapperlayer.orders.OrderResponseMapper;
import com.example.apigateway.presentationlayer.orders.OrderRequestModel;
import com.example.apigateway.presentationlayer.orders.OrderResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderServiceClient orderServiceClient;
    private final OrderResponseMapper orderResponseMapper;

    public OrderServiceImpl(OrderServiceClient orderServiceClient, OrderResponseMapper orderResponseMapper) {
        this.orderServiceClient = orderServiceClient;
        this.orderResponseMapper = orderResponseMapper;
    }

    @Override
    public List<OrderResponseModel> getOrders() {
        return orderResponseMapper.responseModelListToResponseModelList(orderServiceClient.getAllOrders());
    }

    @Override
    public OrderResponseModel getOrderByOrderId(String orderId) {
        return orderResponseMapper.responseModelToResponseModel(orderServiceClient.getOrderByOrderId(orderId));
    }

    @Override
    public OrderResponseModel addOrder(OrderRequestModel orderRequestModel) {
        return orderResponseMapper.responseModelToResponseModel(orderServiceClient.createOrder(orderRequestModel));
    }

    @Override
    public OrderResponseModel updateOrder(OrderRequestModel orderRequestModel, String orderId) {
        return orderResponseMapper.responseModelToResponseModel(orderServiceClient.updateOrderByOrder_Id(orderRequestModel, orderId));
    }

    @Override
    public void removeOrder(String orderId) {
        orderServiceClient.deleteOrderByOrder_Id(orderId);
    }
}
