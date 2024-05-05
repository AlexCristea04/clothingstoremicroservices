package com.example.orders.businesslayer;

import com.example.orders.datalayer.*;
import com.example.orders.domainclientlayer.customers.CustomerServiceClient;
import com.example.orders.domainclientlayer.employees.EmployeeServiceClient;
import com.example.orders.domainclientlayer.products.ProductServiceClient;
import com.example.orders.mapperlayer.OrderRequestMapper;
import com.example.orders.mapperlayer.OrderResponseMapper;
import com.example.orders.presentationlayer.OrderRequestModel;
import com.example.orders.presentationlayer.OrderResponseModel;
import com.example.orders.utils.exceptions.InvalidInputException;
import com.example.orders.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderResponseMapper orderResponseMapper;
    private final OrderRequestMapper orderRequestMapper;
    private final CustomerServiceClient customerServiceClient;
    private final EmployeeServiceClient employeeServiceClient;
    private final ProductServiceClient productServiceClient;

    public OrderServiceImpl(OrderRepository orderRepository, OrderResponseMapper orderResponseMapper, OrderRequestMapper orderRequestMapper,
                            CustomerServiceClient customerServiceClient, EmployeeServiceClient employeeServiceClient, ProductServiceClient productServiceClient) {
        this.orderRepository = orderRepository;
        this.orderResponseMapper = orderResponseMapper;
        this.orderRequestMapper = orderRequestMapper;
        this.customerServiceClient = customerServiceClient;
        this.employeeServiceClient = employeeServiceClient;
        this.productServiceClient = productServiceClient;
    }

    @Override
    public List<OrderResponseModel> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderResponseMapper.entityListToResponseModelList(orders);
    }

    @Override
    public OrderResponseModel getOrderByOrderId(String orderId) {
        if(!orderRepository.existsOrderByOrderIdentifier_OrderId(orderId))
            throw new NotFoundException("Unknown orderId " + orderId);
        Order order = orderRepository.findByOrderIdentifier_OrderId(orderId);

        if(customerServiceClient.getCustomerByCustomerId((order.getCustomerIdentifier().getCustomerId())) == null)
            throw new InvalidInputException("Invalid input for customerId " + order.getCustomerIdentifier().getCustomerId());
        if(employeeServiceClient.getEmployeeByEmployeeId(order.getEmployeeIdentifier().getEmployeeId()) == null)
            throw new InvalidInputException("Invalid input for employeeId " + order.getCustomerIdentifier().getCustomerId());
        if(productServiceClient.getProductByProductId(order.getProductIdentifier().getProductId()) == null)
            throw new InvalidInputException("Invalid input for productId " + order.getProductIdentifier().getProductId());

        return orderResponseMapper.entityToResponseModel(order);
    }

    @Override
    public OrderResponseModel addOrder(OrderRequestModel orderRequestModel) {
        return null;
        /*
        if(customerServiceClient.getCustomerByCustomerId(orderRequestModel.getCustomerId()) == null)
            throw new InvalidInputException("Invalid input for customerId " + orderRequestModel.getCustomerId());

        if(employeeServiceClient.getEmployeeByEmployeeId(orderRequestModel.getEmployeeId()) == null)
            throw new InvalidInputException("Invalid input for employeeId " + orderRequestModel.getEmployeeId());

        if(productServiceClient.getProductByProductId(orderRequestModel.getProductId()) == null)
            throw new InvalidInputException("Invalid input for productId " + orderRequestModel.getProductId());

        Order order = orderRequestMapper.requestModelToEntity(orderRequestModel, new OrderIdentifier(),
                customerServiceClient.getCustomerByCustomerId(orderRequestModel.getCustomerId()),
                employeeServiceClient.getEmployeeByEmployeeId(orderRequestModel.getEmployeeId()),
                productServiceClient.getProductByProductId(orderRequestModel.getProductId()));
    */
    }

    @Override
    public OrderResponseModel updateOrder(OrderRequestModel orderRequestModel, String orderId) {
        return null;
    }

    @Override
    public void removeOrder(String orderId) {

    }

}
